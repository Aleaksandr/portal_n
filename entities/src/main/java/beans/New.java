package beans;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * User: ahirs
 * Date: 01.05.15
 */

@Entity
public class New implements Identifiable<Integer> {
    private static final long serialVersionUID = 1L;
    @Column
    @NotEmpty
    private Integer id;
    @Column
    @NotEmpty
    private String title;
    @Column
    @NotEmpty
    private String title4menu;
    @Column
    @NotEmpty
    private String author;
    @Column
    @NotEmpty
    private Date date;
    @Column
    @NotEmpty
    private String item;

    public New() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle4menu() {
        return title4menu;
    }

    public void setTitle4menu(String title4menu) {
        this.title4menu = title4menu;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof New)) return false;

        New anew = (New) o;

        if (id != null ? !id.equals(anew.id) : anew.id != null) return false;
        if (title != null ? !title.equals(anew.title) : anew.title != null) return false;
        if (title4menu != null ? !title4menu.equals(anew.title4menu) : anew.title4menu != null) return false;
        if (author != null ? !author.equals(anew.author) : anew.author != null) return false;
        if (date != null ? !date.equals(anew.date) : anew.date != null) return false;
        if (item != null ? !item.equals(anew.item) : anew.item != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (title4menu != null ? title4menu.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person : id: " + id + " Title: " + title + " Title4menu: " + title4menu + " Author: " + author + " Date: " + date;// + personAddress;
    }
}
