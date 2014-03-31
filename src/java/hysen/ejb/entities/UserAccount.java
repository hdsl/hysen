/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author AbdulMumin
 */
@Entity
@Table(name = "user_account")
@NamedQueries({
    @NamedQuery(name = "UserAccount.findAll", query = "SELECT u FROM UserAccount u")})
public class UserAccount extends CommonEntity {
  
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_detail")
    private StaffDetail staffDetail;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "user_password")
    private String userPassword;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "access_right")
    private AccessRight accessRight;
    
    @Column(name = "account_status")
    private Character accountStatus;
  
    @Column(name = "security_hint")
    private String securityHint;
    
    public UserAccount() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public StaffDetail getStaffDetail() {
        return staffDetail;
    }

    public void setStaffDetail(StaffDetail staffDetail) {
        this.staffDetail = staffDetail;
    }

    public AccessRight getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(AccessRight accessRight) {
        this.accessRight = accessRight;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Character getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Character accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getSecurityHint() {
        return securityHint;
    }

    public void setSecurityHint(String securityHint) {
        this.securityHint = securityHint;
    }

}
