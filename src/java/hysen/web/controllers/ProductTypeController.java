/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ProductTypes;
import hysen.ejb.entities.ProductTypesModel;
import hysen.ejb.services.CrudService;
import hysen.web.utils.StringConstants;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author AbdulMumin
 */
@Named
@ConversationScoped
public class ProductTypeController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CrudService crudService;

    @Inject
    private Conversation conversation;

    String saveEditButtonText = "Save", productTypeId;

    ProductTypes productTypes = new ProductTypes();

    ProductTypesModel productTypesModel = new ProductTypesModel();

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public ProductTypeController() {
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

        if (saveEditButtonText.equals("Save")) {

            productTypes.setCommonId(StringConstants.generateID());

            if (crudService.save(productTypes) != null) {

                StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);
                resetButtonAction();
            } else {
                StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);
            }

        } else {

            if (crudService.update(productTypes) == true) {

                StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                resetButtonAction();
            } else {
                StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
            }
        }

    }

    public void resetButtonAction() {

        endConversation();
        saveEditButtonText = "Save";
        productTypes = new ProductTypes();

    }

    public void rowSelectButtonAction(ProductTypes pt) {
        beginConversation();
        this.productTypes = pt;
        saveEditButtonText = "Update";
    }

    public void deleteButtonAction(ProductTypes pt) {

        beginConversation();

        if (crudService.delete(pt, true) == true) {

            StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
            resetButtonAction();
        } else {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Product Type Models Methods">
    public void saveEditProductModelButtonAction() {

        beginConversation();

        if (saveEditButtonText.equals("Save")) {

            productTypesModel.setCommonId(StringConstants.generateID());

            productTypesModel.setProductTypes(crudService.find(ProductTypes.class, productTypeId));

            if (crudService.save(productTypesModel) != null) {

                StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);
                resetProductModelButtonAction();
            } else {
                StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);
            }

        } else {

            if (crudService.update(productTypesModel) == true) {

                productTypesModel.setProductTypes(crudService.find(ProductTypes.class, productTypeId));

                StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);

                resetProductModelButtonAction();

            } else {
                StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
            }
        }

    }

    public void resetProductModelButtonAction() {

        endConversation();
        saveEditButtonText = "Save";
        productTypeId = "";
        productTypesModel = new ProductTypesModel();

    }

    public void rowSelectProductModelButtonAction(ProductTypesModel typesModel) {
        beginConversation();
        this.productTypesModel = typesModel;
        productTypeId = productTypesModel.getProductTypes().getCommonId();
        saveEditButtonText = "Update";
    }

    public void deleteProductModelButtonAction(ProductTypesModel typesModel) {

        beginConversation();

        if (crudService.delete(typesModel, true) == true) {

            StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
            resetButtonAction();
        } else {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
        }
    }
//</editor-fold>

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

    public ProductTypes getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(ProductTypes productTypes) {
        this.productTypes = productTypes;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public ProductTypesModel getProductTypesModel() {
        return productTypesModel;
    }

    public void setProductTypesModel(ProductTypesModel productTypesModel) {
        this.productTypesModel = productTypesModel;
    }

    public String getSaveEditButtonText() {
        return saveEditButtonText;
    }

    public void setSaveEditButtonText(String saveEditButtonText) {
        this.saveEditButtonText = saveEditButtonText;
    }

//</editor-fold>
    
}
