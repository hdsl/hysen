/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientContact;
import hysen.ejb.entities.ClientDetail;
import hysen.ejb.services.CrudService;
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
@Named(value = "clientContactController")
@ConversationScoped
public class ClientContactController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CrudService crudService;

    @Inject
    private Conversation conversation;

    String saveEditButtonText = "Save", industryTypeId, selectedClient;

    boolean renderForm = false;

    ClientDetail clientDetail = new ClientDetail();
    ClientContact clientContact = new ClientContact();
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public ClientContactController() {
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

    public void showClientDetail() {

        if (selectedClient != null) {

            clientDetail = crudService.find(ClientDetail.class, selectedClient);

        } else {
            clientDetail = new ClientDetail();
        }
    }

    public void saveEditButtonAction() {

        beginConversation();

        if (clientDetail == null) {
            StringConstants.showApprioprateMessage("Please select a client");
        } else {
            if (saveEditButtonText.equals("Save")) {

                System.out.println("client detail................." + clientDetail);
                clientContact.setCommonId(StringConstants.generateID());
                clientContact.setClientDetail(clientDetail);

                System.out.println("common id............" + clientContact.getCommonId());
                if (crudService.save(clientContact) != null) {

                    StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);
//                    resetButtonAction();
                } else {
                    StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);
                }
            } else {

                clientContact.setClientDetail(clientDetail);
                if (crudService.update(clientDetail) == true) {

                    StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                    resetButtonAction();
                } else {
                    StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
                }
            }
        }

    }

    public void resetButtonAction() {

        endConversation();
        saveEditButtonText = "Save";
        clientDetail = new ClientDetail();
        clientContact = new ClientContact();

    }

    public void rowSelectButtonAction(ClientDetail cd) {
        beginConversation();
        this.clientDetail = cd;
        saveEditButtonText = "Update";
    }

    public void deleteButtonAction(ClientDetail cd) {

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
