/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientContact;
import hysen.ejb.entities.ClientDetail;
import hysen.ejb.services.CrudService;
import hysen.ejb.services.CustomCrudService;
import hysen.web.utils.StringConstants;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Named(value = "companyContactController")
@ConversationScoped
public class CompanyContactController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CrudService crudService;

    @Inject
    private CustomCrudService customCrudService;

    @Inject
    private Conversation conversation;

    String saveEditButtonText = "Save", industryTypeId, selectedCompany, selectedCompanyId;
    String selectedDepartment, selectedRegion;

    boolean renderForm = false;

    ClientDetail clientDetail = new ClientDetail();
    ClientContact clientContact = new ClientContact();
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public CompanyContactController() {
    }

    private void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }

        renderForm = true;
    }

    private void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }

        renderForm = false;
    }

    public void saveEditButtonAction() {

        beginConversation();

        if (selectedCompany.equals("null")) {

            StringConstants.showApprioprateMessage("Please select a company");

        } else {

            clientDetail = crudService.find(ClientDetail.class, selectedCompany);

            if (clientDetail == null) {
                StringConstants.showApprioprateMessage("Please select a client");
            } else {
                if (saveEditButtonText.equals("Save")) {

                    clientContact.setCommonId(StringConstants.generateID());
                    clientContact.setCompanyDetail(clientDetail);
                    clientContact.setContactDepartment(selectedDepartment);
                    clientContact.setContactRegion(selectedRegion);

                    if (crudService.save(clientContact) != null) {

                        StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);
                        resetButtonAction();

                    } else {
                        StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);
                    }
                } else {

                    clientContact.setCompanyDetail(clientDetail);
                    clientContact.setContactDepartment(selectedDepartment);
                    clientContact.setContactRegion(selectedRegion);

                    if (crudService.update(clientContact) == true) {

                        StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                        resetButtonAction();
                    } else {
                        StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
                    }
                }
            }

        }

    }

    public void resetButtonAction() {

        endConversation();
        saveEditButtonText = "Save";
        clientDetail = new ClientDetail();
        clientContact = new ClientContact();
        selectedCompany = null;
        selectedCompanyId = null;
        selectedDepartment = null;
        selectedRegion = null;

    }

    public void rowSelectButtonAction(ClientContact cc) {
        
        beginConversation();
        
        this.clientContact = cc;
        
        selectedCompany = clientContact.getCompanyDetail().getCommonId();
        selectedDepartment = clientContact.getContactDepartment();
        selectedRegion = clientContact.getContactRegion();
        
        saveEditButtonText = "Update";
        
    }

    public void deleteButtonAction(ClientContact cd) {

        beginConversation();

        if (crudService.delete(cd, true) == true) {

            StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
            resetButtonAction();
        } else {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
        }
    }
   
    public void saveEditContactButtonAction() {

    }

    public void deleteContactButtonAction() {

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public CrudService getCrudService() {
        return crudService;
    }

    public void setCrudService(CrudService crudService) {
        this.crudService = crudService;
    }

    public String getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(String selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    public ClientContact getClientContact() {
        return clientContact;
    }

    public void setClientContact(ClientContact clientContact) {
        this.clientContact = clientContact;
    }

    public CustomCrudService getCustomCrudService() {
        return customCrudService;
    }

    public void setCustomCrudService(CustomCrudService customCrudService) {
        this.customCrudService = customCrudService;
    }

    public String getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(String selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

    public String getSelectedCompanyId() {
        return selectedCompanyId;
    }

    public void setSelectedCompanyId(String selectedCompanyId) {
        this.selectedCompanyId = selectedCompanyId;
    }

    public String getIndustryTypeId() {
        return industryTypeId;
    }

    public void setIndustryTypeId(String industryTypeId) {
        this.industryTypeId = industryTypeId;
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

    public boolean isRenderForm() {
        return renderForm;
    }

    public void setRenderForm(boolean renderForm) {
        this.renderForm = renderForm;
    }

    public String getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(String selectedRegion) {
        this.selectedRegion = selectedRegion;
    }

    public ClientDetail getClientDetail() {
        return clientDetail;
    }

    public void setClientDetail(ClientDetail clientDetail) {
        this.clientDetail = clientDetail;
    }
//</editor-fold>
}
