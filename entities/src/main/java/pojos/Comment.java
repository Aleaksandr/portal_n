package pojos;


import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Comment bean
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "comments")

public class Comment implements Serializable {
    private static final long serialVersionUID = -256343997503246240L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer com_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "comment", columnDefinition="text")
    private String comment;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private News nw;

    public Comment() {
    }

    public Integer getId() {
        return com_id;
    }

    public void setId(Integer id) {
        this.com_id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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

    public News getNew() {
        return nw;
    }

    public void setNew(News nw) {
        this.nw = nw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment person = (Comment) o;

        if (com_id != null ? !com_id.equals(person.com_id) : person.com_id != null) return false;
        if (user_id != null ? !user_id.equals(person.user_id) : person.user_id != null) return false;
    //    if (news_id != null ? !news_id.equals(person.news_id) : person.news_id != null) return false;
        if (comment != null ? !comment.equals(person.comment) : person.comment != null) return false;
        if (date != null ? !date.equals(person.date) : person.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = com_id != null ? com_id.hashCode() : 0;
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
      //  result = 31 * result + (news_id != null ? news_id.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentId: " + com_id + " UserId: " + user_id + " Date: " + date;
    }
}
