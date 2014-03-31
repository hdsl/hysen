/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.web.utils;

import hysen.ejb.entities.InstitutionSetup;
import hysen.ejb.entities.UserAccount;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author AbdulMumin
 */
@Named
@SessionScoped
public class UserSession implements Serializable
{
    private String username;
    private String password;
    private Date sessionDate;
    private UserAccount userAccount;
    private List<InstitutionSetup> instList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<InstitutionSetup> getInstList() {
        return instList;
    }

    public void setInstList(List<InstitutionSetup> instList) {
        this.instList = instList;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    
}
