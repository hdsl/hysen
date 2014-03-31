/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientDetail;
import hysen.ejb.entities.ClientProduct;
import hysen.ejb.entities.ProductTypesModel;
import hysen.ejb.services.CrudService;
import hysen.ejb.services.CustomCrudService;
import hysen.web.utils.StringConstants;
import hysen.web.utils.UserSession;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Named
@SessionScoped
public class ClientProductsController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CustomCrudService customCrudService;

    @Inject
    private CrudService crudService;

    private ClientProduct clientProduct = new ClientProduct();

    private ProductTypesModel ptm = new ProductTypesModel();

    @Inject
    private Conversation conversation;

    @Inject
    private UserSession userSession;

    private String productId, saveEditButtonText = "Save";
    private String productModelId, serialNumber;
    private String clientDetailId;
    private SelectItem[] productModelOption;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
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

    public void loadProductModel() {

        beginConversation();

        List<ProductTypesModel> productModelList = customCrudService.findByParameter(ProductTypesModel.class, "productTypes.commonId", productId, 'N');

        productModelOption = new SelectItem[productModelList.size()];

        int count = 0;

        for (ProductTypesModel ptm : productModelList) {

            productModelOption[count] = new SelectItem(ptm.getCommonId(), ptm.getProductModel());
            count++;
        }

    }

    public void saveEditButtonAction() {

        beginConversation();

        if (saveEditButtonText.equals("Save")) {

            if (serialNumber == null) {
                StringConstants.showApprioprateMessage("Please enter serial number");
            } else {

                clientProduct.setCommonId(StringConstants.generateID());
                clientProduct.setClientDetail(crudService.find(ClientDetail.class, clientDetailId));
                clientProduct.setProductTypeModel(crudService.find(ProductTypesModel.class, productModelId));
                clientProduct.setSerialNumber(serialNumber);
                clientProduct.setLastModifiedBy(userSession.getUsername());

                if (crudService.save(clientProduct) != null) {
                    StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);
                    resetButtonAction();
                } else {
                    StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);
                }
            }

        } else {

            clientProduct.setClientDetail(crudService.find(ClientDetail.class, clientDetailId));
            clientProduct.setProductTypeModel(crudService.find(ProductTypesModel.class, productModelId));
            clientProduct.setSerialNumber(serialNumber);

            if (crudService.update(clientProduct) == true) {
                StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                resetButtonAction();
            } else {
                StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
            }

        }
    }

    public void deleteButtonAction(ClientProduct cp) {

        beginConversation();

        cp.setLastModifiedBy(userSession.getUsername());

        this.clientProduct = cp;

        if (crudService.delete(cp, true) == true) {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
            resetButtonAction();
        } else {
            StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
        }
    }

    public void rowSelectButtonAction(ClientProduct cp) {

        this.clientProduct = cp;
        clientDetailId = clientProduct.getClientDetail().getCommonId();
        productId = clientProduct.getProductTypeModel().getProductTypes().getCommonId();
        serialNumber = clientProduct.getCommonId();

        loadProductModel();

        productModelId = clientProduct.getProductTypeModel().getCommonId();

        saveEditButtonText = "Update";

    }

    public void resetButtonAction() {

        endConversation();
        saveEditButtonText = "Save";
        productId = "";
        productModelId = "";
        clientDetailId = "";
        serialNumber = "";
        clientProduct = new ClientProduct();

    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public CustomCrudService getCustomCrudService() {
        return customCrudService;
    }

    public void setCustomCrudService(CustomCrudService customCrudService) {
        this.customCrudService = customCrudService;
    }

    public ProductTypesModel getPtm() {
        return ptm;
    }

    public void setPtm(ProductTypesModel ptm) {
        this.ptm = ptm;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSaveEditButtonText() {
        return saveEditButtonText;
    }

    public void setSaveEditButtonText(String saveEditButtonText) {
        this.saveEditButtonText = saveEditButtonText;
    }

    public ClientProduct getClientProduct() {
        return clientProduct;
    }

    public void setClientProduct(ClientProduct clientProduct) {
        this.clientProduct = clientProduct;
    }

    public String getClientDetailId() {
        return clientDetailId;
    }

    public void setClientDetailId(String clientDetailId) {
        this.clientDetailId = clientDetailId;
    }

    public String getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(String productModelId) {
        this.productModelId = productModelId;
    }

    public CrudService getCrudService() {
        return crudService;
    }

    public void setCrudService(CrudService crudService) {
        this.crudService = crudService;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public SelectItem[] getProductModelOption() {
        return productModelOption;
    }

    public void setProductModelOption(SelectItem[] productModelOption) {
        this.productModelOption = productModelOption;
    }
//</editor-fold>

}
