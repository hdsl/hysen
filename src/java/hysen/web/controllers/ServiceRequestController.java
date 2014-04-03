/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientContact;
import hysen.ejb.entities.ClientDetail;
import hysen.ejb.entities.ClientProduct;
import hysen.ejb.entities.GeneratePk;
import hysen.ejb.entities.ProductTypes;
import hysen.ejb.entities.ServiceModelComponent;
import hysen.ejb.entities.ServiceRequest;
import hysen.ejb.entities.StaffDetail;
import hysen.ejb.services.CrudService;
import hysen.ejb.services.CustomCrudService;
import hysen.web.utils.StringConstants;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
//import javax.enterprise.context.ConversationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Named
@ConversationScoped
public class ServiceRequestController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CrudService crudService;

    @Inject
    private Conversation conversation;

    @Inject
    private CustomCrudService customCrudService;

    boolean renderRequestListForm = true;

    boolean renderRequestForm = false;

    Integer countId = 0;

    String selectedClientId, selectedProductType, selectedEquipLocation, serviceRequestId, searchRequestId;

    String saveEditButtonText = "Submit Request Detail", serviceComponentId, assignedEngineer;

    String parameterId = null;

    private SelectItem[] selectClientContactOption;

    private SelectItem[] clientEquipmentOption;

    private SelectItem[] modelComponentOption;

    ServiceRequest serviceRequest;

    List<ServiceRequest> serviceRequestList;

    GeneratePk generatePk = new GeneratePk();

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public ServiceRequestController() {
        //serviceRequest = new ServiceRequest();
    }

    public void init() {

        generatePk = customCrudService.getGenPk("REQ_KEY");

        int in = generatePk.getPkValue();
        countId = 1 + in;

        serviceRequestId = "HD(SR) " + countId.toString();
        serviceRequest = new ServiceRequest();
    }

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

    public void showNewRequestForm() {

        beginConversation();
        renderRequestListForm = false;
        renderRequestForm = true;
        init();
    }

    public void returnToHomePage() {
        beginConversation();
        renderRequestForm = false;
        renderRequestListForm = true;
    }
    
    public void addServiceComponent(){
        
    }

    public void showSelectedClientContacts() {

        beginConversation();

        List<ClientContact> clientContactList = customCrudService.findByParameter(ClientContact.class, "clientDetail.commonId", selectedClientId, 'N');

        selectClientContactOption = new SelectItem[clientContactList.size()];

        int count = 0;

        for (ClientContact cc : clientContactList) {

            selectClientContactOption[count] = new SelectItem(cc.getCommonId(), cc.getContactName());
            count++;
        }

        selectedProductType = "";
        selectedEquipLocation = "";
    }

    public void showClientProductModels() {

        beginConversation();

        int count = 0;

        List<ClientProduct> clientProductList = customCrudService.clientProductsList(selectedClientId, selectedProductType);

        clientEquipmentOption = new SelectItem[clientProductList.size()];

        for (ClientProduct cp : clientProductList) {
            clientEquipmentOption[count] = new SelectItem(cp.getCommonId(), cp.getSerialNumber() + " " + cp.getProductLocation());

            count++;
        }

        int modelCount = 0;
        List<ServiceModelComponent> modelComponentList
                = customCrudService.findByParameter(ServiceModelComponent.class, "productTypes.commonId", selectedProductType, 'N');

        modelComponentOption = new SelectItem[modelComponentList.size()];

        for (ServiceModelComponent cc : modelComponentList) {

            modelComponentOption[modelCount] = new SelectItem(cc.getCommonId(), cc.getComponentName());
            modelCount++;
        }

    }

    public void saveEditButtonAction() {

        if (selectedClientId == null) {
            StringConstants.showApprioprateMessage("Please select a Requester Name");
        } else if (selectedProductType == null) {
            StringConstants.showApprioprateMessage("Please select Service Product");
        } else if (selectedEquipLocation == null) {
            StringConstants.showApprioprateMessage("Please select Equipment/Location");
        } else {

            if (saveEditButtonText.equals("Submit Request Detail")) {

                ClientDetail clientDetail = crudService.find(ClientDetail.class, selectedClientId);
                ProductTypes productTypes = crudService.find(ProductTypes.class, selectedProductType);
                ClientProduct clientProduct = crudService.find(ClientProduct.class, selectedEquipLocation);
                ServiceModelComponent modelComponent = crudService.find(ServiceModelComponent.class, serviceComponentId);
                StaffDetail staffDetail = crudService.find(StaffDetail.class, assignedEngineer);

                serviceRequest.setCommonId(StringConstants.generateID());
                serviceRequest.setServiceRequestId(serviceRequestId);
                serviceRequest.setClientDetail(clientDetail);
                serviceRequest.setProductType(productTypes);
                serviceRequest.setClientProduct(clientProduct);
                serviceRequest.setServiceComponent(modelComponent);
                serviceRequest.setStaffDetail(staffDetail);

                if (crudService.save(serviceRequest) != null) {

                    generatePk.setPkValue(countId);
                    customCrudService.generatePkUpdate(generatePk);

                    StringConstants.showApprioprateMessage(StringConstants.SAVE_MESSAGE);
                    resetButtonAction();
                    returnToHomePage();
                } else {
                    StringConstants.showApprioprateMessage(StringConstants.SAVE_ERRORMESSAGE);
                }

            } else {

                ClientDetail clientDetail = crudService.find(ClientDetail.class, selectedClientId);
                ProductTypes productTypes = crudService.find(ProductTypes.class, selectedProductType);
                ClientProduct clientProduct = crudService.find(ClientProduct.class, selectedEquipLocation);
                ServiceModelComponent modelComponent = crudService.find(ServiceModelComponent.class, serviceComponentId);
                StaffDetail staffDetail = crudService.find(StaffDetail.class, assignedEngineer);

                serviceRequest.setClientDetail(clientDetail);
                serviceRequest.setProductType(productTypes);
                serviceRequest.setClientProduct(clientProduct);
                serviceRequest.setServiceComponent(modelComponent);
                serviceRequest.setStaffDetail(staffDetail);

                if (crudService.update(serviceRequest) == true) {

                    StringConstants.showApprioprateMessage(StringConstants.EDIT_MESSAGE);
                    searchButtonAction();
                    resetButtonAction();
                    returnToHomePage();
                } else {
                    StringConstants.showApprioprateMessage(StringConstants.EDIT_ERRORMESSAGE);
                }
            }
        }

    }

    public void deleteButtonAction() {

        if (serviceRequest == null) {
            StringConstants.showApprioprateMessage("Please select a service request");
        } else {
            if (crudService.delete(serviceRequest, true) == true) {

                StringConstants.showApprioprateMessage(StringConstants.DELETE_MESSAGE);
                searchButtonAction();

            } else {

                StringConstants.showApprioprateMessage(StringConstants.DELETE_ERRORMESSAGE);
            }
        }
    }

    public void searchButtonAction() {

        beginConversation();

        if (searchRequestId == null) {
            StringConstants.showApprioprateMessage("Service Request Id is required");
        } else {
            serviceRequestList
                    = customCrudService.findByParameter(ServiceRequest.class, "commonId", searchRequestId, 'N');

        }
    }

    public void resetButtonAction() {
        endConversation();
        serviceRequest = new ServiceRequest();
        serviceComponentId = "";
        assignedEngineer = "";
        selectedClientId = "";
        selectedProductType = "";
        selectedEquipLocation = "";
        clientEquipmentOption = null;
        saveEditButtonText = "Submit Request Detail";
    }

    public void selectToEditButtonAction() {

        beginConversation();

        if (serviceRequest == null) {
            StringConstants.showApprioprateMessage("Please select a service request");
        } else {

            selectedClientId = serviceRequest.getClientDetail().getCommonId();
            selectedProductType = serviceRequest.getProductType().getCommonId();
            serviceRequestId = serviceRequest.getServiceRequestId();
            assignedEngineer = serviceRequest.getStaffDetail().getCommonId();

            int count = 0;

            List<ClientProduct> clientProductList = customCrudService.clientProductsList(selectedClientId, selectedProductType);

            clientEquipmentOption = new SelectItem[clientProductList.size()];

            for (ClientProduct cp : clientProductList) {
                clientEquipmentOption[count] = new SelectItem(cp.getCommonId(), cp.getSerialNumber() + " " + cp.getProductLocation());

                count++;
            }

            selectedEquipLocation = serviceRequest.getClientProduct().getCommonId();

            int modelCount = 0;
            List<ServiceModelComponent> modelComponentList
                    = customCrudService.findByParameter(ServiceModelComponent.class, "productTypes.commonId", selectedProductType, 'N');

            modelComponentOption = new SelectItem[modelComponentList.size()];

            for (ServiceModelComponent cc : modelComponentList) {

                modelComponentOption[modelCount] = new SelectItem(cc.getCommonId(), cc.getComponentName());
                modelCount++;
            }

            serviceComponentId = serviceRequest.getServiceComponent().getCommonId();

            saveEditButtonText = "Update Request Detail";
            renderRequestListForm = false;
            renderRequestForm = true;

        }
    }

    public void queryServiceRequest() {
        serviceRequestList
                = customCrudService.findByParameter(ServiceRequest.class, "requestStatus", parameterId, 'N');

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public CrudService getCrudService() {
        return crudService;
    }

    public void setCrudService(CrudService crudService) {
        this.crudService = crudService;
    }

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    public String getSearchRequestId() {
        return searchRequestId;
    }

    public void setSearchRequestId(String searchRequestId) {
        this.searchRequestId = searchRequestId;
    }

    public List<ServiceRequest> getServiceRequestList() {
        return serviceRequestList;
    }

    public void setServiceRequestList(List<ServiceRequest> serviceRequestList) {
        this.serviceRequestList = serviceRequestList;
    }

    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public GeneratePk getGeneratePk() {
        return generatePk;
    }

    public void setGeneratePk(GeneratePk generatePk) {
        this.generatePk = generatePk;
    }

    public String getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public String getAssignedEngineer() {
        return assignedEngineer;
    }

    public void setAssignedEngineer(String assignedEngineer) {
        this.assignedEngineer = assignedEngineer;
    }

    public String getServiceComponentId() {
        return serviceComponentId;
    }

    public void setServiceComponentId(String serviceComponentId) {
        this.serviceComponentId = serviceComponentId;
    }

    public String getSaveEditButtonText() {
        return saveEditButtonText;
    }

    public void setSaveEditButtonText(String saveEditButtonText) {
        this.saveEditButtonText = saveEditButtonText;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public SelectItem[] getModelComponentOption() {
        return modelComponentOption;
    }

    public void setModelComponentOption(SelectItem[] modelComponentOption) {
        this.modelComponentOption = modelComponentOption;
    }

    public String getSelectedEquipLocation() {
        return selectedEquipLocation;
    }

    public void setSelectedEquipLocation(String selectedEquipLocation) {
        this.selectedEquipLocation = selectedEquipLocation;
    }

    public String getSelectedProductType() {
        return selectedProductType;
    }

    public void setSelectedProductType(String selectedProductType) {
        this.selectedProductType = selectedProductType;
    }

    public SelectItem[] getClientEquipmentOption() {
        return clientEquipmentOption;
    }

    public void setClientEquipmentOption(SelectItem[] clientEquipmentOption) {
        this.clientEquipmentOption = clientEquipmentOption;
    }

    public CustomCrudService getCustomCrudService() {
        return customCrudService;
    }

    public void setCustomCrudService(CustomCrudService customCrudService) {
        this.customCrudService = customCrudService;
    }

    public String getSelectedClientId() {
        return selectedClientId;
    }

    public void setSelectedClientId(String selectedClientId) {
        this.selectedClientId = selectedClientId;
    }

    public SelectItem[] getSelectClientContactOption() {
        return selectClientContactOption;
    }

    public void setSelectClientContactOption(SelectItem[] selectClientContactOption) {
        this.selectClientContactOption = selectClientContactOption;
    }
//
//    public Conversation getConversation() {
//        return conversation;
//    }
//
//    public void setConversation(Conversation conversation) {
//        this.conversation = conversation;
//    }

    public boolean isRenderRequestListForm() {
        return renderRequestListForm;
    }

    public void setRenderRequestListForm(boolean renderRequestListForm) {
        this.renderRequestListForm = renderRequestListForm;
    }

    public boolean isRenderRequestForm() {
        return renderRequestForm;
    }

    public void setRenderRequestForm(boolean renderRequestForm) {
        this.renderRequestForm = renderRequestForm;
    }
//</editor-fold>

}
