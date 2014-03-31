/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.AccessRight;
import hysen.ejb.entities.Department;
import hysen.ejb.services.CrudService;
import hysen.web.utils.StringConstants;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author AbdulMumin
 */
@Named(value = "accessRightController")
@ConversationScoped
public class AccessRightController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    
    @Inject
    private CrudService crudService;
    
    @Inject
    private Conversation conversation;

    String saveEditButtonText = "Save";
    String departmentId;

    private List<AccessRight> accessRightList;
    AccessRight accessRight = new AccessRight();

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public AccessRightController() {
    }

    private void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public void saveEditButtonAction() {

        if (saveEditButtonText.equals("Save")) {

            accessRight.setCommonId(StringConstants.generateID());

            Department department = crudService.find(Department.class, departmentId);
            accessRight.setDepartment(department);

            if (crudService.save(accessRight) != null) {
                StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);

                resetButtonAction();
            } else {
                StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);
            }

        } else {
            accessRight.setDepartment(crudService.find(Department.class, departmentId));

            if (crudService.save(accessRight) != null) {
                StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);

                resetButtonAction();
            } else {
                StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
            }
        }
    }

    public void selectAccessRight(AccessRight acr) {
        
        beginConversation();
        
        this.accessRight = acr;
        
        departmentId = acr.getDepartment().getCommonId();
        
        saveEditButtonText = "Update";
        
    }

    public void deleteButtonAction(AccessRight ar) {
        beginConversation();

        if (crudService.delete(ar,true)) {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
            resetButtonAction();
        } else {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
        }
    }

    public void resetButtonAction() {
        endConversation();
        departmentId = "";
        accessRight = new AccessRight();
        saveEditButtonText = "Save";
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public CrudService getCrudService() {
        return crudService;
    }

    public void setCrudService(CrudService crudService) {
        this.crudService = crudService;
    }

    public List<AccessRight> getAccessRightList() {
        return accessRightList;
    }

    public void setAccessRightList(List<AccessRight> accessRightList) {
        this.accessRightList = accessRightList;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getSaveEditButtonText() {
        return saveEditButtonText;
    }

    public void setSaveEditButtonText(String saveEditButtonText) {
        this.saveEditButtonText = saveEditButtonText;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public AccessRight getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(AccessRight accessRight) {
        this.accessRight = accessRight;
    }
    //</editor-fold>

}
