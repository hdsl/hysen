/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.InstitutionSetup;
import hysen.ejb.entities.UserAccount;
import hysen.ejb.services.CrudService;
import hysen.ejb.services.CustomCrudService;
import hysen.web.utils.NavHandler;
import hysen.web.utils.StringConstants;
import hysen.web.utils.UserSession;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author AbdulMumin
 */
@Named
@RequestScoped
public class LoginCtrl implements Serializable {

    @Inject
    private UserSession userSession;
    @Inject
    private NavHandler navHandler;
    @Inject
    private CrudService crudService;
    @Inject
    private CustomCrudService customCrudService;

    private String username;
    private String password;

    UserAccount userAccount = new UserAccount();

    public String login() {

        if (username == null || "".equals(username)) {
            StringConstants.showApprioprateMessage("Username field is empty");
            return "pretty:login";
        } else if (password == null || "".equals(password)) {
            StringConstants.showApprioprateMessage("Password field is empty");
            return "pretty:login";
        } else {

            List<InstitutionSetup> configuration = crudService.findAll(InstitutionSetup.class, true);

            if (username.equals("admin") && password.equals("hysen")) {

                userSession.setUsername(username);
                userSession.setSessionDate(Calendar.getInstance().getTime());
                userSession.setInstList(configuration);
                navHandler.setModule("sysadmin");
                navHandler.setPage("welcome");

                return "pretty:home";

            } else {

                String hashedPassword = StringConstants.hashGeneratedPassword(password);
//
                userAccount = customCrudService.getUserAccount(username, hashedPassword);

                if (userAccount == null) {
                    StringConstants.showApprioprateMessage("Username or Password is invalid");
                    return "pretty:login";
                } else {

                    userSession.setUsername(username);
                    userSession.setSessionDate(Calendar.getInstance().getTime());
                    userSession.setInstList(configuration);

                    if (userAccount.getAccessRight().getCommonId().equals("004")) {
                        navHandler.setModule("engineer");
                        navHandler.setPage("welcome");

                        return "pretty:home";

                    }
                }
                return "pretty:login";
            }
        }

//        userSession.setUsername(username);
//        navHandler.setModule("teller");
//        navHandler.setPage("details");
//
//        return "pretty:home";
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public NavHandler getNavHandler() {
        return navHandler;
    }

    public void setNavHandler(NavHandler navHandler) {
        this.navHandler = navHandler;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
