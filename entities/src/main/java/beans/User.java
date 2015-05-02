package beans;

import org.hibernate.validator.constraints.NotEmpty;



import javax.persistence.*;

/**
 * User: ahirs
 * Date: 01.05.15
 */
@Entity
public class User {
    private static final long serialVersionUID = 1L;
    @Column
    @NotEmpty
    private Integer id;
    @Column
    @NotEmpty
    private String email;
    @Column
    @NotEmpty
    private String pass;
    @Column
    @NotEmpty
    private String role;


    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User person = (User) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (role != null ? !role.equals(person.role) : person.role != null) return false;
        if (pass != null ? !pass.equals(person.pass) : person.pass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User : id: " + id + " E-mail: " + email + " Role: " + role;
    }
}
