/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientContact;
import hysen.ejb.entities.ClientDetail;
import hysen.ejb.entities.IndustryType;
import hysen.ejb.services.CrudService;
import hysen.ejb.services.CustomCrudService;
import hysen.web.utils.StringConstants;
import hysen.web.utils.UserSession;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author HDSL_MUMIN
 */
@Named(value = "companyDetailController")
@SessionScoped
public class CompanyDetailController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CrudService crudService;
    
    @Inject
    private CustomCrudService customCrudService;

    @Inject
    private Conversation conversation;

    @Inject
    private UserSession userSession;

    String saveEditButtonText = "Save", industryTypeId, selectedClient, companyStatus;

    boolean renderForm = false;

    ClientDetail clientDetail = new ClientDetail();
    ClientContact clientContact = new ClientContact();
    
    List<ClientContact> companyContactList;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public CompanyDetailController() {
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

    public void showCustomerDetail() {

        industryTypeId = clientDetail.getIndustryType().getIndustryTypeId();
        companyStatus = clientDetail.getCompanyStatus().getIndustryTypeId();
        
        companyContactList = customCrudService.
                findByParameter(ClientContact.class, "companyDetail.commonId", clientDetail.getCommonId(), 'N');        

        saveEditButtonText = "Update";

    }

    public void saveEditButtonAction() {

        beginConversation();

        if (industryTypeId.equals("null")) {
            StringConstants.showApprioprateMessage("Please select the industry type");
        } else if (companyStatus.equals("null")) {
            StringConstants.showApprioprateMessage("Please select the company status");
        } else {
            if (saveEditButtonText.equals("Save")) {

                clientDetail.setIndustryType(crudService.find(IndustryType.class, industryTypeId));
                clientDetail.setCompanyStatus(crudService.find(IndustryType.class, companyStatus));
                clientDetail.setCommonId(StringConstants.generateID());

                clientDetail.setLastModifiedBy(userSession.getUsername());

                if (crudService.save(clientDetail) != null) {

                    StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);
                    resetButtonAction();

//                    return "pretty:clientDetail";
                } else {
                    StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);

//                    return null;
                }

            } else {

                clientDetail.setIndustryType(crudService.find(IndustryType.class, industryTypeId));
                clientDetail.setCompanyStatus(crudService.find(IndustryType.class, companyStatus));

                if (crudService.update(clientDetail) == true) {

                    StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                    resetButtonAction();
//                    return "pretty:clientDetail";
                } else {
                    StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
//                    return null;
                }
            }
        }

    }

    public void resetButtonAction() {

        endConversation();
        saveEditButtonText = "Save";
        industryTypeId = null;
        companyStatus = null;
        clientDetail = new ClientDetail();
        clientContact = new ClientContact();

    }

    public void rowSelectButtonAction(String id) {
        beginConversation();
        System.out.println("id: " + id);
        this.clientDetail = crudService.find(ClientDetail.class, id);
        saveEditButtonText = "Update";
    }

    public void deleteButtonAction() {

        beginConversation();

        clientDetail.setLastModifiedBy(userSession.getUsername());

        if (crudService.delete(clientDetail, true) == true) {

            StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
            resetButtonAction();
        } else {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
        }
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public CrudService getCrudService() {
        return crudService;
    }

    public void setCrudService(CrudService crudService) {
        this.crudService = crudService;
    }

    public CustomCrudService getCustomCrudService() {
        return customCrudService;
    }

    public void setCustomCrudService(CustomCrudService customCrudService) {
        this.customCrudService = customCrudService;
    }

    public List<ClientContact> getCompanyContactList() {
        return companyContactList;
    }

    public void setCompanyContactList(List<ClientContact> companyContactList) {
        this.companyContactList = companyContactList;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public ClientContact getClientContact() {
        return clientContact;
    }

    public void setClientContact(ClientContact clientContact) {
        this.clientContact = clientContact;
    }

    public String getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(String selectedClient) {
        this.selectedClient = selectedClient;
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

    public ClientDetail getClientDetail() {
        return clientDetail;
    }

    public void setClientDetail(ClientDetail clientDetail) {
        this.clientDetail = clientDetail;
    }
//</editor-fold>

}
