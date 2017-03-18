package ua.com.codefire.cms.db.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by human on 12/6/16.
 */
@Entity
@Table(name = "pages")
public class PageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    private Long id;

    @Column(name = "page_title")
    private String title;

    @Lob
    @Column(name = "page_content")
    private String content;

    public PageEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageEntity page = (PageEntity) o;

        return id.equals(page.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Page{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
