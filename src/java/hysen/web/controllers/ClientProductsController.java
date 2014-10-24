/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientDetail;
import hysen.ejb.entities.ClientProduct;
import hysen.ejb.entities.ProductTypesModel;
import hysen.ejb.entities.Regions;
import hysen.ejb.entities.ServiceModelComponent;
import hysen.ejb.entities.ServiceRequest;
import hysen.ejb.entities.ServiceSoftware;
import hysen.ejb.entities.StaffDetail;
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

    ClientProduct clientProduct;

    private ProductTypesModel ptm = new ProductTypesModel();

    @Inject
    private Conversation conversation;

    @Inject
    private UserSession userSession;

    private String[] selectedModelComponent;

    private String[] selectedSoftwares;

    private String[] selectedEngineers;

    private SelectItem[] clientEquipmentOption;

    private String productId, saveEditButtonText = "Save";

    private String productModelId, serialNumber, regionId, selectedEquipLocation;

    boolean renderClientServicePanel = false;

    boolean renderClientServiceListPanel = true;

    boolean renderClientServiceSearchPanel = false;

    private String clientDetailId, selectedClientId = null, selectedProductType;

    private String serviceEngineers = "", softwaresInstalled = "", modelComponents = "";

    private SelectItem[] productModelOption;

    private SelectItem[] modelComponentOption;

    private SelectItem[] serviceSoftwareOption;

    List<ServiceRequest> serviceRequestList;
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

        if (!productId.equals("null")) {

            List<ProductTypesModel> productModelList = customCrudService.findByParameter(ProductTypesModel.class, "productTypes.commonId", productId, 'N');

            productModelOption = new SelectItem[productModelList.size()];

            int count = 0;

            for (ProductTypesModel ptm : productModelList) {

                productModelOption[count] = new SelectItem(ptm.getCommonId(), ptm.getProductModel());
                count++;
            }

            int modelCount = 0;

            List<ServiceModelComponent> modelComponentList
                    = customCrudService.findByParameter(ServiceModelComponent.class, "productTypes.commonId", productId, 'N');

            modelComponentOption = new SelectItem[modelComponentList.size()];

            for (ServiceModelComponent cc : modelComponentList) {

                modelComponentOption[modelCount] = new SelectItem(cc.getCommonId(), cc.getComponentName());
                modelCount++;
            }

            int softCount = 0;

            List<ServiceSoftware> serviceSoftwareList
                    = customCrudService.findByParameter(ServiceSoftware.class, "productTypes.commonId", productId, 'N');

            serviceSoftwareOption = new SelectItem[serviceSoftwareList.size()];

            for (ServiceSoftware cc : serviceSoftwareList) {

                serviceSoftwareOption[softCount] = new SelectItem(cc.getCommonId(), cc.getSoftwareDesc());
                softCount++;

            }
        } else {
            productModelOption = null;
            modelComponentOption = null;
            serviceSoftwareOption = null;
        }
    }

    public void addClientService() {

        renderClientServicePanel = true;
        renderClientServiceListPanel = false;
        renderClientServiceSearchPanel = false;
        clientProduct = new ClientProduct();

    }

    public void cancelClientService() {

        renderClientServicePanel = false;
        renderClientServiceListPanel = true;
        renderClientServiceSearchPanel = false;
        clientProduct = new ClientProduct();

    }

    public void searchClientService() {

        renderClientServicePanel = false;
        renderClientServiceListPanel = false;
        renderClientServiceSearchPanel = true;

    }

    public void saveEditButtonAction() {

        beginConversation();

        if (clientDetailId.equals("null")) {
            StringConstants.showApprioprateMessage("Please select client");
        } else if (productId.equals("null")) {
            StringConstants.showApprioprateMessage("Please select service type");
        } else if (productModelId.equals("null")) {
            StringConstants.showApprioprateMessage("Please select service model");
        } else if (regionId.equals("null")) {
            StringConstants.showApprioprateMessage("Please select region");
        } else if (serialNumber.equals("") || serialNumber == null) {
            StringConstants.showApprioprateMessage("Please enter serial number");
        } else {
            if (saveEditButtonText.equals("Save")) {

                if (serialNumber == null) {
                    StringConstants.showApprioprateMessage("Please enter serial number");
                } else {

                    String combinedModels = "";

                    if (selectedModelComponent != null) {

                        for (String s : selectedModelComponent) {

                            combinedModels = s + "/" + combinedModels;

                        }
                    }

                    String combinedSoftware = "";

                    if (selectedSoftwares != null) {

                        for (String s : selectedSoftwares) {

                            combinedSoftware = s + "/" + combinedSoftware;

                        }
                    }

                    String combinedEngineers = "";

                    if (selectedEngineers != null) {

                        for (String s : selectedEngineers) {

                            combinedEngineers = s + "/" + combinedEngineers;

                        }
                    }

                    clientProduct.setCommonId(StringConstants.generateID());
                    clientProduct.setModelComponents(combinedModels);
                    clientProduct.setSoftwareInstalled(combinedSoftware);
                    clientProduct.setServiceEngineer(combinedEngineers);
                    clientProduct.setClientDetail(crudService.find(ClientDetail.class, clientDetailId));
                    clientProduct.setProductTypeModel(crudService.find(ProductTypesModel.class, productModelId));
                    clientProduct.setRegions(crudService.find(Regions.class, regionId));
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

                String combinedModels = "";

                if (selectedModelComponent != null) {

                    for (String s : selectedModelComponent) {

                        combinedModels = s + "/" + combinedModels;

                    }
                }

                String combinedSoftware = "";

                if (selectedSoftwares != null) {

                    for (String s : selectedSoftwares) {

                        combinedSoftware = s + "/" + combinedSoftware;

                    }
                }

                String combinedEngineers = "";

                if (selectedEngineers != null) {

                    for (String s : selectedEngineers) {

                        combinedEngineers = s + "/" + combinedEngineers;

                    }
                }

                clientProduct.setModelComponents(combinedModels);
                clientProduct.setSoftwareInstalled(combinedSoftware);
                clientProduct.setClientDetail(crudService.find(ClientDetail.class, clientDetailId));
                clientProduct.setProductTypeModel(crudService.find(ProductTypesModel.class, productModelId));
                clientProduct.setRegions(crudService.find(Regions.class, regionId));
                clientProduct.setSerialNumber(serialNumber);
                clientProduct.setServiceEngineer(combinedEngineers);

                if (crudService.update(clientProduct) == true) {
                    StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                    resetButtonAction();
                } else {
                    StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
                }

            }

        }
    }

    public void rowSelectButtonAction() {

        if (clientProduct == null) {
            StringConstants.showApprioprateMessage("Please select client service");
        } else {
            renderClientServicePanel = true;
            renderClientServiceListPanel = false;
            renderClientServiceSearchPanel = false;

            clientDetailId = clientProduct.getClientDetail().getCommonId();
            productId = clientProduct.getProductTypeModel().getProductTypes().getCommonId();
            regionId = clientProduct.getRegions().getRegionId();
            serialNumber = clientProduct.getSerialNumber();

            selectedModelComponent = new String[clientProduct.getSmcList().size()];

            selectedModelComponent = clientProduct.getSmcList().toArray(selectedModelComponent);

            selectedSoftwares = new String[clientProduct.getSoftwareList().size()];

            selectedSoftwares = clientProduct.getSoftwareList().toArray(selectedSoftwares);

            selectedEngineers = new String[clientProduct.getServiceEngineersList().size()];

            selectedEngineers = clientProduct.getServiceEngineersList().toArray(selectedEngineers);

            loadProductModel();

            productModelId = clientProduct.getProductTypeModel().getCommonId();

            saveEditButtonText = "Update";

        }
    }

    public String showCompanyServiceRequest() {

        return "pretty:companyServiceRequest";
    }

    public void resetButtonAction() {

        endConversation();
        saveEditButtonText = "Save";
        productId = "";
        productModelId = "";
        clientDetailId = "";
        serialNumber = "";
        regionId = "";
        selectedModelComponent = null;
        selectedEngineers = null;
        selectedSoftwares = null;
        clientProduct = new ClientProduct();
        cancelClientService();

    }

    public void returnFromViewClientService() {
        clientProduct = new ClientProduct();

        renderClientServicePanel = false;
        renderClientServiceListPanel = true;
        renderClientServiceSearchPanel = false;

        serviceEngineers = "";
        softwaresInstalled = "";
    }

    public void selectToViewRowData(ClientProduct cp) {

        this.clientProduct = cp;

        renderClientServicePanel = false;
        renderClientServiceListPanel = false;
        renderClientServiceSearchPanel = true;

        modelComponents = "";

        if (clientProduct.getModelComponents() == null) {
            modelComponents = "";
        } else {

            String[] modelComponentId = clientProduct.getModelComponents().split("/");

            for (String mc : modelComponentId) {

                ServiceModelComponent smc = crudService.find(ServiceModelComponent.class, mc);

                if (smc != null) {
                    modelComponents = smc.getComponentName() + " / " + modelComponents;
                }
            }
        }

        //softwares installed
        softwaresInstalled = "";
        if (clientProduct.getSoftwareInstalled() == null) {
            softwaresInstalled = "";
        } else {
            String[] installedSoftwares = clientProduct.getSoftwareInstalled().split("/");

            for (String mc : installedSoftwares) {

                ServiceSoftware ss = crudService.find(ServiceSoftware.class, mc);

                if (ss != null) {
                    softwaresInstalled = ss.getSoftwareDesc() + " / " + softwaresInstalled;
                }
            }
        }

        //engineers
        serviceEngineers = "";
        if (clientProduct.getServiceEngineer() == null) {
            serviceEngineers = "";
        } else {
            String[] engineers = clientProduct.getServiceEngineer().split("/");

            for (String mc : engineers) {

                StaffDetail sd = crudService.find(StaffDetail.class, mc);

                if (sd != null) {
                    serviceEngineers = sd.getStaffName() + " / " + serviceEngineers;
                }
            }

            serviceRequestList
                    = customCrudService.serviceRequestList(clientProduct.getCommonId());
        }
    }

    public void deleteButtonAction() {

        beginConversation();

        if (clientProduct == null) {
            StringConstants.showApprioprateMessage("Please select client service");
        } else {

            clientProduct.setLastModifiedBy(userSession.getUsername());

            if (crudService.delete(clientProduct, true) == true) {

                StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);

                returnFromViewClientService();

                resetButtonAction();

            } else {
                StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
            }
        }
    }

    public void renderEditPanel() {

        renderClientServicePanel = true;
        renderClientServiceListPanel = false;
        renderClientServiceSearchPanel = false;

        clientDetailId = clientProduct.getClientDetail().getCommonId();
        productId = clientProduct.getProductTypeModel().getProductTypes().getCommonId();
        regionId = clientProduct.getRegions().getRegionId();
        serialNumber = clientProduct.getSerialNumber();

        selectedModelComponent = new String[clientProduct.getSmcList().size()];

        selectedModelComponent = clientProduct.getSmcList().toArray(selectedModelComponent);

        selectedSoftwares = new String[clientProduct.getSoftwareList().size()];

        selectedSoftwares = clientProduct.getSoftwareList().toArray(selectedSoftwares);

        selectedEngineers = new String[clientProduct.getServiceEngineersList().size()];

        selectedEngineers = clientProduct.getServiceEngineersList().toArray(selectedEngineers);

        loadProductModel();

        productModelId = clientProduct.getProductTypeModel().getCommonId();

        saveEditButtonText = "Update";
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public CustomCrudService getCustomCrudService() {
        return customCrudService;
    }

    public void setCustomCrudService(CustomCrudService customCrudService) {
        this.customCrudService = customCrudService;
    }

    public List<ServiceRequest> getServiceRequestList() {
        return serviceRequestList;
    }

    public void setServiceRequestList(List<ServiceRequest> serviceRequestList) {
        this.serviceRequestList = serviceRequestList;
    }

    public String getServiceEngineers() {
        return serviceEngineers;
    }

    public void setServiceEngineers(String serviceEngineers) {
        this.serviceEngineers = serviceEngineers;
    }

    public String getSoftwaresInstalled() {
        return softwaresInstalled;
    }

    public void setSoftwaresInstalled(String softwaresInstalled) {
        this.softwaresInstalled = softwaresInstalled;
    }

    public String getModelComponents() {
        return modelComponents;
    }

    public void setModelComponents(String modelComponents) {
        this.modelComponents = modelComponents;
    }

    public String getSelectedEquipLocation() {
        return selectedEquipLocation;
    }

    public void setSelectedEquipLocation(String selectedEquipLocation) {
        this.selectedEquipLocation = selectedEquipLocation;
    }

    public SelectItem[] getClientEquipmentOption() {
        return clientEquipmentOption;
    }

    public void setClientEquipmentOption(SelectItem[] clientEquipmentOption) {
        this.clientEquipmentOption = clientEquipmentOption;
    }

    public String getSelectedClientId() {
        return selectedClientId;
    }

    public void setSelectedClientId(String selectedClientId) {
        this.selectedClientId = selectedClientId;
    }

    public String getSelectedProductType() {
        return selectedProductType;
    }

    public void setSelectedProductType(String selectedProductType) {
        this.selectedProductType = selectedProductType;
    }

    public boolean isRenderClientServiceListPanel() {
        return renderClientServiceListPanel;
    }

    public void setRenderClientServiceListPanel(boolean renderClientServiceListPanel) {
        this.renderClientServiceListPanel = renderClientServiceListPanel;
    }

    public boolean isRenderClientServiceSearchPanel() {
        return renderClientServiceSearchPanel;
    }

    public void setRenderClientServiceSearchPanel(boolean renderClientServiceSearchPanel) {
        this.renderClientServiceSearchPanel = renderClientServiceSearchPanel;
    }

    public SelectItem[] getServiceSoftwareOption() {
        return serviceSoftwareOption;
    }

    public void setServiceSoftwareOption(SelectItem[] serviceSoftwareOption) {
        this.serviceSoftwareOption = serviceSoftwareOption;
    }

    public String[] getSelectedModelComponent() {
        return selectedModelComponent;
    }

    public void setSelectedModelComponent(String[] selectedModelComponent) {
        this.selectedModelComponent = selectedModelComponent;
    }

    public String[] getSelectedSoftwares() {
        return selectedSoftwares;
    }

    public void setSelectedSoftwares(String[] selectedSoftwares) {
        this.selectedSoftwares = selectedSoftwares;
    }

    public String[] getSelectedEngineers() {
        return selectedEngineers;
    }

    public void setSelectedEngineers(String[] selectedEngineers) {
        this.selectedEngineers = selectedEngineers;
    }

    public boolean isRenderClientServicePanel() {
        return renderClientServicePanel;
    }

    public void setRenderClientServicePanel(boolean renderClientServicePanel) {
        this.renderClientServicePanel = renderClientServicePanel;
    }

    public SelectItem[] getModelComponentOption() {
        return modelComponentOption;
    }

    public void setModelComponentOption(SelectItem[] modelComponentOption) {
        this.modelComponentOption = modelComponentOption;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
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
