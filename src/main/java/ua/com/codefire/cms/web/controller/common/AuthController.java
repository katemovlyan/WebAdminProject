package ua.com.codefire.cms.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.Implementation.UserService;
import ua.com.codefire.cms.model.AttributeNames;

import javax.servlet.ServletResponse;
import javax.servlet.http.*;

/**
 * Created by User on 24.12.2016.
 */
@Controller
public class AuthController {
    @Autowired
    private UserService service;

    @RequestMapping(path = "/auth")
    public String getAuth() {
        return "auth";
    }

    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public String postAuth(@RequestParam String username, @RequestParam String password,
                           HttpServletResponse resp) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        HttpServletRequest req
                = attr.getRequest();

        service.create(new UserEntity("test", "12345"));

        if (service.ifUserRegistered(username, password)) {
            UserEntity currUser = service.getUserByName(username);
            session.setAttribute(AttributeNames.SESSION_AUTHENTICATED, true);
            session.setAttribute(AttributeNames.SESSION_USER, currUser);
            //Left userName, because it is used too many times in too many places, need time to change. Need to change to user
            session.setAttribute(AttributeNames.SESSION_USERNAME, username);

            String[] checkboxes = req.getParameterValues("rememberMe");
            if (checkboxes.length != 0) {
                Cookie cookie = new Cookie(AttributeNames.SESSION_USERNAME, username);
                cookie.setMaxAge(60 * 60 * 24 * 365);
                resp.addCookie(cookie);
            }

            if (currUser.getAccessLvl() == UserEntity.AccessLevel.User) {
                return "redirect:/index";
            } else {
                return "redirect:/admin/index";
            }
        } else {
            session.setAttribute("flash_message", "Username or password is incorrect.");
            return "/auth";
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String getLogout(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        session.setAttribute(AttributeNames.SESSION_AUTHENTICATED, false);
        session.removeAttribute(AttributeNames.SESSION_USER);
        //Left userName, because it is used too many times in too many places, need time to change. Need to change to user
        session.removeAttribute(AttributeNames.SESSION_USERNAME);
        return "redirect:/index";
    }

    @RequestMapping(path = "/register")
    public String getUsers() {
        return "userreg/userreg";
    }
}
