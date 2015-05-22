package beans;


import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Comment bean
 */

@javax.persistence.Entity
public class Comment implements Serializable, Identifiable<Integer> {
    private static final long serialVersionUID = 1L;
    @Column
    @NotEmpty
    private Integer id;
    @Column
    @NotEmpty
    private Integer user_id;
    @Column
    @NotEmpty
    private Integer news_id;
    @Column
    @NotEmpty
    private String comment;
    @Column
    @NotEmpty
    private Date date;



    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment person = (Comment) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (user_id != null ? !user_id.equals(person.user_id) : person.user_id != null) return false;
        if (news_id != null ? !news_id.equals(person.news_id) : person.news_id != null) return false;
        if (comment != null ? !comment.equals(person.comment) : person.comment != null) return false;
        if (date != null ? !date.equals(person.date) : person.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (news_id != null ? news_id.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentId: " + id + " UserId: " + user_id + " NewsId: " + news_id + " Date: " + date;
    }
}
