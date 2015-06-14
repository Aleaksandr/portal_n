package pojos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



/**
 * Created by hirs akeaksandr on 25.04.15.
 * News bean
 */

@Entity
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "news")
public class News implements Serializable {

    private static final long serialVersionUID = 8785629573211348834L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer new_id;

    @Column (name = "title")
    private String title;

    @Column (name = "title4menu")
    private String title4menu;

    @Column (name = "author")
    private String author;

    @Column (name = "date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;

    @Column (name = "item", columnDefinition="text")
    private String item;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "nw", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Comment> comments;

    public News() {
    }

    public News(String title, String menutitle, String author, Date date, String item){
         this.title = title;
         this.title4menu = menutitle;
         this.author = author;
         this.date = date;
         this.item = item;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> persons) {
        this.comments = persons;
    }

    public Integer getId() {
        return new_id;
    }

    public void setId(Integer id) {
        this.new_id = id;
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
        if (!(o instanceof News)) return false;

        News anew = (News) o;

        if (new_id != null ? !new_id.equals(anew.new_id) : anew.new_id != null) return false;
        if (title != null ? !title.equals(anew.title) : anew.title != null) return false;
        if (title4menu != null ? !title4menu.equals(anew.title4menu) : anew.title4menu != null) return false;
        if (author != null ? !author.equals(anew.author) : anew.author != null) return false;
        if (date != null ? !date.equals(anew.date) : anew.date != null) return false;
        if (item != null ? !item.equals(anew.item) : anew.item != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = new_id != null ? new_id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (title4menu != null ? title4menu.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "News: id: " + new_id + " Title: " + title + " Title4menu: " + title4menu + " Author: " + author + " Date: " + date;
    }
}
