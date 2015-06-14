package pojos;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * UserDetail bean
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "userdetail")

public class UserDetail implements Serializable {
    private static final long serialVersionUID = 9128314778543609157L;


    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "generator")
    private Integer uid;

    @Column(name = "fname")
    private String fname;

    @Column(name = "sname")
    private String sname;

    @Column(name = "bdate")
    private Date bdate;

    @OneToOne(fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @PrimaryKeyJoinColumn
    private User user;


    public UserDetail() {
    }

    public String getFName() {
        return fname;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }

    public String getSName() {
        return sname;
    }

    public void setSName(String sname) {
        this.sname = sname;
    }

    public Date getBDate() {
        return bdate;
    }

    public void setBDAte(Date bdate) {
        this.bdate = bdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetail)) return false;

        UserDetail userDetail = (UserDetail) o;

        //if (id != null ? !id.equals(userDetail.id) : userDetail.id != null) return false;
        if (fname != null ? !fname.equals(userDetail.fname) : userDetail.fname != null) return false;
        if (sname != null ? !sname.equals(userDetail.sname) : userDetail.sname != null) return false;
        if (bdate != null ? !bdate.equals(userDetail.bdate) : userDetail.bdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (fname != null ? fname.hashCode() : 0);
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return fname + " " + sname;
    }
}
