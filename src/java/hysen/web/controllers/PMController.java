/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientProduct;
import hysen.ejb.entities.GeneratePk;
import hysen.ejb.entities.ServiceRequest;
import hysen.ejb.entities.StaffDetail;
import hysen.ejb.services.CrudService;
import hysen.ejb.services.CustomCrudService;
import hysen.web.tableModel.ServiceRequestTableModel;
import hysen.web.utils.StringConstants;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Named(value = "pMController")
@ConversationScoped
public class PMController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CrudService crudService;

    @Inject
    private Conversation conversation;

    @Inject
    private CustomCrudService customCrudService;

    boolean renderPMListForm = true;

    boolean renderPMForm = false;

    GeneratePk generatePk = new GeneratePk();

    Integer countId = 0, pmYear;

    String serviceRequestId, customerId = null, productId = null, assignedEngineer, pmPeriod = null;

    Date scheduledDate;

    ServiceRequest serviceRequest;

    List<ClientProduct> clientProductsList;

    List<ServiceRequestTableModel> srtmList;

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public PMController() {
    }

    public void init() {

        generatePk = customCrudService.getGenPk("PM_KEY");

        int in = generatePk.getPkValue();
        countId = 1 + in;

        serviceRequestId = "HD(PM) " + countId.toString();
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
        renderPMListForm = false;
        renderPMForm = true;

    }

    public void returnToHomePage() {
        beginConversation();
        renderPMForm = false;
        renderPMListForm = true;
    }

    public void showSelectedCustomerServices() {

        if (customerId == null || customerId.equals("null")) {
            StringConstants.showApprioprateMessage("Please select a customer");
        } else if (productId == null || productId.equals("null")) {
            StringConstants.showApprioprateMessage("Please select the service type");
        } else if (pmPeriod == null || pmPeriod.equals("null")) {
            StringConstants.showApprioprateMessage("Please select PM period");
        } else if (pmYear == null) {
            StringConstants.showApprioprateMessage("Please select PM year");
        } else {

            srtmList = new ArrayList<>();

            clientProductsList = customCrudService.clientProductsList(customerId, productId);

            for (ClientProduct cp : clientProductsList) {

                ServiceRequest sr = customCrudService.clientPMScheduleList(pmPeriod, pmYear, cp.getCommonId());

                ServiceRequestTableModel srtm = new ServiceRequestTableModel();

                srtm.setSerialNumber(cp.getSerialNumber());
                srtm.setServiceModel(cp.getProductTypeModel().getProductModel());
                srtm.setServiceLocation(cp.getProductLocation());
                srtm.setClientProductId(cp.getCommonId());

                if (sr != null) {

                    srtm.setServiceEngineer(sr.getStaffDetail().getStaffName());
                    srtm.setServiceEngineerId(sr.getStaffDetail().getCommonId());
                    srtm.setPmScheduledDate(sr.getServiceStartDate());

                } else {
                    srtm.setServiceEngineer(null);
                    srtm.setServiceEngineerId(null);
                    srtm.setPmScheduledDate(null);
                }

                srtmList.add(srtm);

            }
        }
    }

    public void submitPMSchedules() {

        if (customerId == null) {
            StringConstants.showApprioprateMessage("Please select a customer");
        } else if (productId == null) {
            StringConstants.showApprioprateMessage("Please select the service type");
        } else if (pmPeriod == null) {
            StringConstants.showApprioprateMessage("Please select PM period");
        } else if (pmYear == null) {
            StringConstants.showApprioprateMessage("Please select PM year");
        } else if (srtmList.isEmpty()) {
            StringConstants.showApprioprateMessage("List is empty");
        } else {
            for (ServiceRequestTableModel srtm : srtmList) {

                ServiceRequest sr = customCrudService.clientPMScheduleList(pmPeriod, pmYear, srtm.getClientProductId());

                if (sr != null) {

                    sr.setStaffDetail(crudService.find(StaffDetail.class, srtm.getServiceEngineerId()));
                    sr.setServiceStartDate(srtm.getPmScheduledDate());

                    crudService.update(sr);

                } else {

                    ServiceRequest serviceReq = new ServiceRequest();

                    generatePk = customCrudService.getGenPk("PM_KEY");

                    int in = generatePk.getPkValue();
                    countId = 1 + in;

                    serviceRequestId = "HD(PM) " + countId.toString();

                    generatePk.setPkValue(countId);
                    customCrudService.generatePkUpdate(generatePk);

                    serviceReq.setCommonId(StringConstants.generateID());
                    serviceReq.setServiceRequestId(serviceRequestId);
                    serviceReq.setClientDetail(crudService.find(ClientProduct.class, srtm.getClientProductId()).getClientDetail());
                    serviceReq.setProductType(crudService.find(ClientProduct.class, srtm.getClientProductId()).getProductTypeModel().getProductTypes());
                    serviceReq.setClientProduct(crudService.find(ClientProduct.class, srtm.getClientProductId()));
                    serviceReq.setRequestStatus("Opened");

                    serviceReq.setStaffDetail(crudService.find(StaffDetail.class, srtm.getServiceEngineerId()));
                    serviceReq.setServiceStartDate(srtm.getPmScheduledDate());

                    serviceReq.setPmPeriod(pmPeriod);
                    serviceReq.setPmYear(pmYear);

                    if (crudService.save(serviceReq) == null) {

                        StringConstants.showApprioprateMessage("Unable to save " + srtm.getSerialNumber() + " " + srtm.getServiceLocation());
                    }

                }

            }
            StringConstants.showApprioprateMessage("Schedule submitted successfully");
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

    public List<ServiceRequestTableModel> getSrtmList() {
        return srtmList;
    }

    public void setSrtmList(List<ServiceRequestTableModel> srtmList) {
        this.srtmList = srtmList;
    }

    public Integer getPmYear() {
        return pmYear;
    }

    public void setPmYear(Integer pmYear) {
        this.pmYear = pmYear;
    }

    public String getAssignedEngineer() {
        return assignedEngineer;
    }

    public void setAssignedEngineer(String assignedEngineer) {
        this.assignedEngineer = assignedEngineer;
    }

    public String getPmPeriod() {
        return pmPeriod;
    }

    public void setPmPeriod(String pmPeriod) {
        this.pmPeriod = pmPeriod;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public GeneratePk getGeneratePk() {
        return generatePk;
    }

    public void setGeneratePk(GeneratePk generatePk) {
        this.generatePk = generatePk;
    }

    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public String getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public List<ClientProduct> getClientProductsList() {
        return clientProductsList;
    }

    public void setClientProductsList(List<ClientProduct> clientProductsList) {
        this.clientProductsList = clientProductsList;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public CustomCrudService getCustomCrudService() {
        return customCrudService;
    }

    public void setCustomCrudService(CustomCrudService customCrudService) {
        this.customCrudService = customCrudService;
    }

    public boolean isRenderPMListForm() {
        return renderPMListForm;
    }

    public void setRenderPMListForm(boolean renderPMListForm) {
        this.renderPMListForm = renderPMListForm;
    }

    public boolean isRenderPMForm() {
        return renderPMForm;
    }

    public void setRenderPMForm(boolean renderPMForm) {
        this.renderPMForm = renderPMForm;
    }

//</editor-fold>
}
