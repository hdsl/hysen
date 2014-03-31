/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ProductTypes;
import hysen.ejb.entities.ServiceModelComponent;
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
@Named(value = "modelComponentController")
@ConversationScoped
public class ModelComponentController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CrudService crudService;

    @Inject
    Conversation conversation;

    String saveEditButtonText = "Save", productTypeId;

    ServiceModelComponent modelComponent = new ServiceModelComponent();
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public ModelComponentController() {
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

        beginConversation();

        if (productTypeId == null) {
            StringConstants.showApprioprateMessage("Please select a service type");
        } else {
            if (saveEditButtonText.equals("Save")) {

                ProductTypes productTypes = crudService.find(ProductTypes.class, productTypeId);

                modelComponent.setCommonId(StringConstants.generateID());
                modelComponent.setProductTypes(productTypes);

                if (crudService.save(modelComponent) != null) {

                    StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);
                    resetButtonAction();
                } else {
                    StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);
                }

            } else {

                ProductTypes productTypes = crudService.find(ProductTypes.class, productTypeId);

                modelComponent.setProductTypes(productTypes);

                if (crudService.update(modelComponent) == true) {

                    StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                    resetButtonAction();
                } else {
                    StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
                }
            }
        }
    }

    public void deleteButtonAction(ServiceModelComponent pt) {

        beginConversation();

        if (crudService.delete(pt, true) == true) {

            StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
            resetButtonAction();
        } else {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
        }
    }

    public void resetButtonAction() {

        endConversation();
        saveEditButtonText = "Save";
        productTypeId = "";
        modelComponent = new ServiceModelComponent();

    }

    public void searchButtonAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void rowSelectButtonAction(ServiceModelComponent pt) {

        beginConversation();

        this.modelComponent = pt;

        productTypeId = modelComponent.getProductTypes().getCommonId();

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

    public String getSaveEditButtonText() {
        return saveEditButtonText;
    }

    public void setSaveEditButtonText(String saveEditButtonText) {
        this.saveEditButtonText = saveEditButtonText;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public ServiceModelComponent getModelComponent() {
        return modelComponent;
    }

    public void setModelComponent(ServiceModelComponent modelComponent) {
        this.modelComponent = modelComponent;
    }
//</editor-fold>
    
}
