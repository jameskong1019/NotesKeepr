/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.domainmodel;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author james
 */
@Entity
@Table(name = "valid_email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValidEmail.findAll", query = "SELECT v FROM ValidEmail v")
    , @NamedQuery(name = "ValidEmail.findByUsername", query = "SELECT v FROM ValidEmail v WHERE v.username = :username")
    , @NamedQuery(name = "ValidEmail.findByValidUserUUID", query = "SELECT v FROM ValidEmail v WHERE v.validUserUUID = :validUserUUID")})
public class ValidEmail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "validUserUUID")
    private String validUserUUID;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public ValidEmail() {
    }

    public ValidEmail(String username) {
        this.username = username;
    }

    public ValidEmail(String username, String validUserUUID) {
        this.username = username;
        this.validUserUUID = validUserUUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getValidUserUUID() {
        return validUserUUID;
    }

    public void setValidUserUUID(String validUserUUID) {
        this.validUserUUID = validUserUUID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValidEmail)) {
            return false;
        }
        ValidEmail other = (ValidEmail) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nk.domainmodel.ValidEmail[ username=" + username + " ]";
    }
    
}
