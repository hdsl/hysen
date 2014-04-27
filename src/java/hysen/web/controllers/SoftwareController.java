/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.GeneratePk;
import hysen.ejb.entities.ProductTypes;
import hysen.ejb.entities.ServiceSoftware;
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
@Named(value = "softwareController")
@ConversationScoped
public class SoftwareController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialization">
    @Inject
    private CrudService crudService;

    @Inject
    private CustomCrudService customCrudService;

    @Inject
    private Conversation conversation;

    ServiceSoftware software = new ServiceSoftware();
    String saveEditButtonText = "Save", serviceTypeId;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public SoftwareController() {
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

            GeneratePk generatePk = customCrudService.getGenPk("SOFTWARE");

            int in = generatePk.getPkValue();

            Integer countId = 1 + in;

            software.setCommonId(countId.toString());

            ProductTypes productTypes = crudService.find(ProductTypes.class, serviceTypeId);

            software.setProductTypes(productTypes);

            if (crudService.save(software) != null) {
                
                StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);
                
                generatePk.setPkValue(countId);
                customCrudService.generatePkUpdate(generatePk);
                
                resetButtonAction();
                
            } else {
                StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);
            }
        } else {

            ProductTypes productTypes = crudService.find(ProductTypes.class, serviceTypeId);

            software.setProductTypes(productTypes);

            if (crudService.update(software) == true) {
                StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                resetButtonAction();
            } else {
                StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
            }
        }
    }

    public void deleteButtonAction(ServiceSoftware st) {

        beginConversation();

        if (crudService.delete(st, true) == true) {

            StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
            resetButtonAction();
        } else {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
        }
    }

    public void resetButtonAction() {
        saveEditButtonText = "Save";
        software = new ServiceSoftware();
    }

    public void rowSelectButtonAction(ServiceSoftware pt) {
        beginConversation();
        this.software = pt;
        saveEditButtonText = "Update";
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
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

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public ServiceSoftware getSoftware() {
        return software;
    }

    public void setSoftware(ServiceSoftware software) {
        this.software = software;
    }

    public String getSaveEditButtonText() {
        return saveEditButtonText;
    }

    public void setSaveEditButtonText(String saveEditButtonText) {
        this.saveEditButtonText = saveEditButtonText;
    }

//</editor-fold>
}
