/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.domainmodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author james
 */
@Entity
@Table(name = "reset_password")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResetPassword.findAll", query = "SELECT r FROM ResetPassword r")
    , @NamedQuery(name = "ResetPassword.findByUsername", query = "SELECT r FROM ResetPassword r WHERE r.username = :username")
    , @NamedQuery(name = "ResetPassword.findByResetTime", query = "SELECT r FROM ResetPassword r WHERE r.resetTime = :resetTime")
    , @NamedQuery(name = "ResetPassword.findByResetPasswordUUID", query = "SELECT r FROM ResetPassword r WHERE r.resetPasswordUUID = :resetPasswordUUID")})
public class ResetPassword implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resetTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resetTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "resetPasswordUUID")
    private String resetPasswordUUID;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public ResetPassword() {
    }

    public ResetPassword(String username) {
        this.username = username;
    }

    public ResetPassword(String username, Date resetTime, String resetPasswordUUID) {
        this.username = username;
        this.resetTime = resetTime;
        this.resetPasswordUUID = resetPasswordUUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getResetTime() {
        return resetTime;
    }

    public void setResetTime(Date resetTime) {
        this.resetTime = resetTime;
    }

    public String getResetPasswordUUID() {
        return resetPasswordUUID;
    }

    public void setResetPasswordUUID(String resetPasswordUUID) {
        this.resetPasswordUUID = resetPasswordUUID;
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
        if (!(object instanceof ResetPassword)) {
            return false;
        }
        ResetPassword other = (ResetPassword) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nk.domainmodel.ResetPassword[ username=" + username + " ]";
    }
    
}
