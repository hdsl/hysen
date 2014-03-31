/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.Department;
import hysen.ejb.entities.StaffDetail;
import hysen.ejb.services.CrudService;
import hysen.web.utils.StringConstants;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author AbdulMumin
 */
@Named
@ConversationScoped
public class StaffDetailController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CrudService crudService;
    @Inject
    private Conversation conversation;

    private boolean renderForm = false;

    String saveEditButtonText = "Save", searchAttribute, searchText, departmentId;
    StaffDetail staffDetail = new StaffDetail();
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public StaffDetailController() {
    }

    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }

        renderForm = true;
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }

        renderForm = false;
    }

    public void addStaffDetail() {
        beginConversation();

        staffDetail.setCommonId(StringConstants.generateID());
    }

    public void saveEditButtonAction() {

        if (saveEditButtonText.equals("Save")) {

            staffDetail.setDepartment(crudService.find(Department.class, departmentId));

            if (crudService.save(staffDetail) != null) {
                StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);
                resetButtonAction();
            } else {
                StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);
            }
        } else {

            staffDetail.setDepartment(crudService.find(Department.class, departmentId));

            if (crudService.update(staffDetail) == true) {
                StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                resetButtonAction();
            } else {
                StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
            }
        }

    }

    public void deleteButtonAction(StaffDetail sd) {

        beginConversation();
        if (crudService.delete(sd, true)) {

            StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
            resetButtonAction();

        } else {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
        }

    }

    public void resetButtonAction() {
        endConversation();
        staffDetail = new StaffDetail();
        saveEditButtonText = "Save";
    }

    public void rowSelectDataAction(StaffDetail sd) {
        beginConversation();
        this.staffDetail = sd;
        saveEditButtonText = "Update";
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
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

    public boolean isRenderForm() {
        return renderForm;
    }

    public void setRenderForm(boolean renderForm) {
        this.renderForm = renderForm;
    }

    public StaffDetail getStaffDetail() {
        return staffDetail;
    }

    public void setStaffDetail(StaffDetail staffDetail) {
        this.staffDetail = staffDetail;
    }

    public String getSearchAttribute() {
        return searchAttribute;
    }

    public void setSearchAttribute(String searchAttribute) {
        this.searchAttribute = searchAttribute;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    //</editor-fold>
}
