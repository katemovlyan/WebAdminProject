package ua.com.codefire.cms.web.servlet.admin;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.codefire.cms.db.entity.PageEntity;
import ua.com.codefire.cms.db.service.Implementation.PageService;
import ua.com.codefire.cms.db.service.abstraction.IPageService;
import ua.com.codefire.cms.model.Fields;
import ua.com.codefire.cms.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kris on 08.12.16
 * <p>
 * PageServlet provides displaying of pages' info.
 * Also it affords functionality to create, edit and delete pages.
 */

@WebServlet("/admin/pages")
public class PageServlet extends HttpServlet {

    private static final String DEFAULT_TITLE = "Title";
    private static final String DEFAULT_CONTENT = "Content";

    @Autowired
    private PageService service;
//    private PageService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (service.getAmountOfEntities() <= 0) {
            prepareDefaultData(service);
        }


        String action = req.getParameter(Fields.ACTION);
        String id = req.getParameter(Fields.ID);

        if (Utils.isValid(action)) {
            if (Fields.ACTION_NEW.equals(action)) {
                openCreatePage(req, resp);
            } else if (Fields.ACTION_DELETE.equals(action)) {

                if (Utils.isValid(id)) {
                    service.delete(Long.parseLong(id));

                    resp.sendRedirect("/admin/pages");
                }
            }
        } else if (Utils.isValid(id)) {
            openEditPage(req, resp, Long.parseLong(id));
        } else {
            openAllPagesList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(Fields.ID);
        String title = req.getParameter(Fields.TITLE);
        String content = req.getParameter(Fields.CONTENT);

        if (req.getParameter(Fields.BTN_OK) != null) {

            if (Utils.isValid(title) && Utils.isValid(content)) {

                if (Utils.isValid(id)) {
                    service.update(getPage(Long.parseLong(id), title, content));
                } else {
                    service.create(getPage(null, title, content));
                }
            } else {
                req.setAttribute(Fields.ERROR_MESSAGE, "Fields title and content must be filled");
                req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/edit.jsp").forward(req, resp);
                return;
            }
        } else if (req.getParameter(Fields.BTN_DELETE) != null) {
            if (Utils.isValid(id)) {
                service.delete(Long.parseLong(id));
            }
        }

        resp.sendRedirect("/admin/pages");

    }

    private void openCreatePage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/edit.jsp").forward(req, resp);
    }

    private void openEditPage(HttpServletRequest req, HttpServletResponse resp, Long id)
            throws ServletException, IOException {

        PageEntity page = service.read(id);

        req.setAttribute(Fields.ID, page.getId());
        req.setAttribute(Fields.TITLE, page.getTitle());
        req.setAttribute(Fields.CONTENT, page.getContent());

        req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/edit.jsp").forward(req, resp);
    }

    private void openAllPagesList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<PageEntity> pages = service.getAllEntities();

        req.setAttribute(Fields.PAGE_COUNT, pages.size());
        req.setAttribute(Fields.PAGES, pages);

        req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/list.jsp").forward(req, resp);
    }

    private PageEntity getPage(Long id, String title, String content) {

        PageEntity page = new PageEntity();

        page.setId(id);
        page.setTitle(title);
        page.setContent(content);

        return page;

    }

    private void prepareDefaultData(IPageService service) {
        PageEntity page;

        for (int i = 1; i <= 15; i++) {
            page = new PageEntity();
            page.setTitle(DEFAULT_TITLE + " " + i);
            page.setContent(DEFAULT_CONTENT + " " + i);

            Long id = service.create(page);

            page.setId(id);
            page.setTitle(DEFAULT_TITLE + " " + id);
            page.setContent(DEFAULT_CONTENT + " " + id);
            service.update(page);
        }
    }
}
