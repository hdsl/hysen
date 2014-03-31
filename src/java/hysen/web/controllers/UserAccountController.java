/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.AccessRight;
import hysen.ejb.entities.StaffDetail;
import hysen.ejb.entities.UserAccount;
import hysen.ejb.services.CrudService;
import hysen.web.utils.StringConstants;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Abdul Mumin
 */
@Named
@ConversationScoped
public class UserAccountController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CrudService crudService;
    @Inject
    private Conversation conversation;

    String password, confirmPassword, saveEditButtonText = "Save", accessRight;
    String staffPosition, staffDepartment, staffDetailId;

    UserAccount userAccount = new UserAccount();

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public UserAccountController() {
    }

    public void beginConversation() {

        if (conversation.isTransient()) {

            conversation.begin();
        }
    }

    public void endConversation() {

        if (!conversation.isTransient()) {

            conversation.end();
        }
    }

    public void staffDetailSelect() {

        if (staffDetailId == null) {

            staffDepartment = "";
            staffPosition = "";
        } else {
            StaffDetail staffDetail = crudService.find(StaffDetail.class, staffDetailId);

            staffDepartment = staffDetail.getDepartment().getDepartmentName();
            staffPosition = staffDetail.getOccupationType();
        }
    }

    public void saveEditButtonAction() {

        beginConversation();
        if (saveEditButtonText.equals("Save")) {

            if (accessRight == null) {
                StringConstants.showApprioprateMessage("Please select the access right");
            } else {
                if (password.equals(confirmPassword)) {

                    userAccount.setCommonId(StringConstants.generateID());

                    String hashedPassword = StringConstants.hashGeneratedPassword(password);

                    userAccount.setUserPassword(hashedPassword);
                    userAccount.setStaffDetail(crudService.find(StaffDetail.class, staffDetailId));
                    userAccount.setAccessRight(crudService.find(AccessRight.class, accessRight));

                    if (crudService.save(userAccount) != null) {
                        StringConstants.showApprioprateMessage("User Account created successfully");
                        resetButtonAction();
                    } else {
                        StringConstants.showApprioprateMessage("Unable to create user account");
                    }

                } else {
                    StringConstants.showApprioprateMessage("Your passwords do not match");
                }
            }
        } else if (saveEditButtonText.equals("Update")) {

            if (accessRight == null) {
                StringConstants.showApprioprateMessage("Please select the access right");
            } else {
                if (password.equals(confirmPassword)) {

                    String hashedPassword = StringConstants.hashGeneratedPassword(password);

                    userAccount.setUserPassword(hashedPassword);
                    userAccount.setStaffDetail(crudService.find(StaffDetail.class, staffDetailId));
                    userAccount.setAccessRight(crudService.find(AccessRight.class, accessRight));

                    if (crudService.update(userAccount) == true) {
                        StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                        resetButtonAction();
                    } else {
                        StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
                    }

                } else {
                    StringConstants.showApprioprateMessage("Your passwords do not match");
                }
            }
        }
    }

    public void rowSelectedData(UserAccount userAccount) {

        beginConversation();

        this.userAccount = userAccount;

        staffDetailId = userAccount.getStaffDetail().getCommonId();

        accessRight = userAccount.getAccessRight().getCommonId();

        staffDepartment = userAccount.getStaffDetail().getDepartment().getDepartmentName();
        staffPosition = userAccount.getStaffDetail().getOccupationType();

        saveEditButtonText = "Update";
    }

    public void resetButtonAction() {
        endConversation();
        userAccount = new UserAccount();
        staffDetailId = "";
        accessRight = "";
        password = "";
        confirmPassword = "";
        saveEditButtonText = "Save";
        staffPosition = "";
        staffDepartment = "";
    }

    public void deleteButtonAction(UserAccount userAccount) {

        beginConversation();

        if (crudService.delete(userAccount, true)) {

            StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
            resetButtonAction();

        } else {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(String accessRight) {
        this.accessRight = accessRight;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
    }

    public String getStaffDepartment() {
        return staffDepartment;
    }

    public void setStaffDepartment(String staffDepartment) {
        this.staffDepartment = staffDepartment;
    }

    public String getSaveEditButtonText() {
        return saveEditButtonText;
    }

    public void setSaveEditButtonText(String saveEditButtonText) {
        this.saveEditButtonText = saveEditButtonText;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public CrudService getCrudService() {
        return crudService;
    }

    public void setCrudService(CrudService crudService) {
        this.crudService = crudService;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getStaffDetailId() {
        return staffDetailId;
    }

    public void setStaffDetailId(String staffDetailId) {
        this.staffDetailId = staffDetailId;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    //</editor-fold>

}
