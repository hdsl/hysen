/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientContact;
import hysen.ejb.entities.ClientDetail;
import hysen.ejb.entities.ClientProduct;
import hysen.ejb.entities.Department;
import hysen.ejb.entities.ProductTypes;
import hysen.ejb.entities.Regions;
import hysen.ejb.entities.ServiceRequest;
import hysen.ejb.services.CrudService;
import hysen.ejb.services.CustomCrudService;
import hysen.web.reports.HysenReportMgr;
import hysen.web.tableModel.ClientProductTableModel;
import hysen.web.tableModel.ClientTableModel;
import hysen.web.tableModel.ServiceRequestTableModel;
import hysen.web.utils.StringConstants;
import hysen.web.utils.UserSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Named(value = "generalReportContoller")
@SessionScoped
public class GeneralReportContoller implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declaration and Initialisation">
    @Inject
    private CrudService crudService;

    @Inject
    private UserSession userSession;

    @Inject
    private Conversation conversation;

    @Inject
    private CustomCrudService customCrudService;

    String clientDetailId, reportParameter, serviceRequestParameter, serviceModelTypeId;

    String serviceClientId, serviceModelClient, selectedProductType, reportTitle;

    private Date activityFrom, activityTo;
    private Date serviceModelActivityFrom, serviceModelActivityTo;
    private SelectItem[] clientEquipmentOption;

    boolean renderEquipPanel = false;
    boolean renderIndividualEquipPanel = false;
    boolean renderEquipRegionPanel = false;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public GeneralReportContoller() {
    }

    public void beginConversation() {
//
//        if (conversation.isTransient()) {
//            conversation.begin();
//        }

    }

    public void endConversation() {

        clientDetailId = "";
        selectedProductType = "";
//        if (!conversation.isTransient()) {
//            conversation.end();
//        }
    }

    //<editor-fold defaultstate="collapsed" desc="Panel Rendering">
    public void showEqipReportPanel() {
        endConversation();
        renderEquipPanel = true;
        renderIndividualEquipPanel = false;
        renderEquipRegionPanel = false;
        reportTitle = "Report : Company Equipments List";
    }

    public void showIndividualEqipReportPanel() {
        endConversation();
        renderEquipPanel = false;
        renderIndividualEquipPanel = true;
        renderEquipRegionPanel = false;
        reportTitle = "Report : Company Equipment Detail";
    }

//    public void renderEqipRegionReportPanel() {
//        System.out.println("in here.................");
//        endConversation();
//        renderEquipPanel = true;
//        reportTitle = "Report : Client Equipments by Region(Detailed)";
//    }
//</editor-fold>
    public void generateClientDetailReport() {

        List<ClientTableModel> clientTableModelList = new ArrayList<>();

        beginConversation();

        List<ClientDetail> clientDetailList = crudService.findAll(ClientDetail.class, true, "companyName");

        for (ClientDetail cd : clientDetailList) {

            ClientTableModel ctm = new ClientTableModel();

            ctm.setClientName(cd.getCompanyName());
            ctm.setIndustryName(cd.getIndustryType().getIndustryName());
            ctm.setClientPrimaryContact(cd.getPrimaryContact());
            ctm.setClientEmail(cd.getCompanyEmail());
            ctm.setClientAddress(cd.getPrimaryAddress());

            clientTableModelList.add(ctm);

        }

        HysenReportMgr.instance().initDefaultParamenters(userSession.getInstList());

        HysenReportMgr.instance().addParam("reportTitle", "Company(Clients) Detailed List");

        HysenReportMgr.instance().showPDFReport(clientTableModelList, HysenReportMgr.CLIENT_DETAILED_REPORT);

//        endConversation();
    }

    public void generateCompanyContactReport() {

        List<ClientTableModel> clientTableModelList = new ArrayList<>();

        List<ClientDetail> clientDetailList = crudService.findAll(ClientDetail.class, false);

        if (clientDetailList != null) {

            for (ClientDetail cd : clientDetailList) {

                List<ClientContact> companyContactList = customCrudService.
                        findByParameter(ClientContact.class, "companyDetail.commonId", cd.getCommonId(), 'N');

                for (ClientContact cc : companyContactList) {

                    ClientTableModel ctm = new ClientTableModel();

                    ctm.setClientName(cd.getCompanyName());
                    ctm.setContactName(cc.getContactName());

                    Department department = crudService.find(Department.class, cc.getContactDepartment());

                    if (department != null) {
                        ctm.setContactDepartment(department.getDepartmentName());
                    } else {
                        ctm.setContactDepartment(null);
                    }

                    ctm.setJobTitle(cc.getJobTitle());
                    ctm.setPhoneNumber(cc.getPrimaryContact() + "/" + cc.getOtherContact());
                    ctm.setEmailAddress(cc.getContactEmail());

                    clientTableModelList.add(ctm);
                }
            }

            HysenReportMgr.instance().initDefaultParamenters(userSession.getInstList());

            HysenReportMgr.instance().addParam("reportTitle", "Company Contacts List");
            
            HysenReportMgr.instance().showPDFReport(clientTableModelList, HysenReportMgr.COMPANY_CONTACT_REPORT);

        }
    }

    public void generateClientReportByParameter() {

        reportParameter = "EQUIPMENTS";
        beginConversation();

        List<ClientProductTableModel> productTableModelList = new ArrayList<>();

        if (clientDetailId == null || clientDetailId.equals("")) {
            StringConstants.showApprioprateMessage("Please select client");
        } else {
            if (reportParameter.equals("EQUIPMENTS")) {

                if (clientDetailId.equals("ALL_CLIENTS")) {

                    List<ClientDetail> clientDetailList = crudService.findAll(ClientDetail.class, false);

                    for (ClientDetail cd : clientDetailList) {

                    }

                } else {

                    List<ClientProduct> clientProductList = customCrudService.clientProductsList(clientDetailId, selectedProductType);

                    ClientDetail clientDetail = crudService.find(ClientDetail.class, clientDetailId);

                    for (ClientProduct cp : clientProductList) {

                        ClientProductTableModel cptm = new ClientProductTableModel();

                        cptm.setClientName(clientDetail.getCompanyName());
                        cptm.setServiceType(cp.getProductTypeModel().getProductTypes().getProductName());
                        cptm.setServiceModel(cp.getProductTypeModel().getProductModel());
                        cptm.setSerialNumber(cp.getSerialNumber());
                        cptm.setServiceLocation(cp.getProductLocation());
                        cptm.setServiceRegion(cp.getRegions().getRegionName());
                        cptm.setDateDeployed(cp.getDeploymentDate());

                        productTableModelList.add(cptm);

                    }
                }

                HysenReportMgr.instance().initDefaultParamenters(userSession.getInstList());

                HysenReportMgr.instance().addParam("reportTitle", "Client Equipments List");
                HysenReportMgr.instance().addParam("equipmentType", crudService.find(ProductTypes.class, selectedProductType).getProductName());

                HysenReportMgr.instance().showPDFReport(productTableModelList, HysenReportMgr.CLIENT_EQUIPMENT_REPORT);

            }
        }
//        endConversation();
    }

    public void generateClientEquipByRegion() {
        reportParameter = "EQUIPMENTS";
        beginConversation();

        List<ClientProductTableModel> productTableModelList = new ArrayList<>();

        if (clientDetailId == null || clientDetailId.equals("")) {
            StringConstants.showApprioprateMessage("Please select client");
        } else {
            if (reportParameter.equals("EQUIPMENTS")) {

                if (clientDetailId.equals("ALL_CLIENTS")) {

                    List<ClientDetail> clientDetailList = crudService.findAll(ClientDetail.class, false);

                    for (ClientDetail cd : clientDetailList) {

                    }

                } else {

                    List<Regions> regionsList = crudService.findAll(Regions.class, false);

                    for (Regions r : regionsList) {

                        List<ClientProduct> clientProductList = customCrudService.
                                findByParameter(ClientProduct.class, "clientDetail.commonId", clientDetailId, "regions.regionId", r.getRegionId(), 'N');

                        for (ClientProduct cp : clientProductList) {

                            ClientProductTableModel cptm = new ClientProductTableModel();

                            cptm.setServiceType(cp.getProductTypeModel().getProductTypes().getProductName());
                            cptm.setServiceModel(cp.getProductTypeModel().getProductModel());
                            cptm.setSerialNumber(cp.getSerialNumber());
                            cptm.setServiceLocation(cp.getProductLocation());
                            cptm.setServiceRegion(r.getRegionName());
                            cptm.setDateDeployed(cp.getDeploymentDate());

                            productTableModelList.add(cptm);

                        }

                    }

                }

                ClientDetail clientDetail = crudService.find(ClientDetail.class, clientDetailId);

                HysenReportMgr.instance().initDefaultParamenters(userSession.getInstList());

                HysenReportMgr.instance().addParam("reportTitle", "Client Equipments List");
                HysenReportMgr.instance().addParam("clientName", clientDetail.getCompanyName());
                HysenReportMgr.instance().addParam("industryType", clientDetail.getIndustryType().getIndustryName());
                HysenReportMgr.instance().addParam("phoneContact", clientDetail.getPrimaryContact());
                HysenReportMgr.instance().addParam("emailAddress", clientDetail.getCompanyEmail());
                HysenReportMgr.instance().addParam("clientContact", "");
                HysenReportMgr.instance().addParam("equipmentType", crudService.find(ProductTypes.class, selectedProductType).getProductName());

                HysenReportMgr.instance().showPDFReport(productTableModelList, HysenReportMgr.EQUIPMENT_BY_REGION);

            }
        }
//        endConversation();
    }

    public void generateClientServiceRequestReport() {

        beginConversation();

        List<ServiceRequestTableModel> srtmList = new ArrayList<>();

        if (activityFrom == null || activityTo == null) {
            StringConstants.showApprioprateMessage("Please select required date");
        } else {

            if (serviceRequestParameter.equals("CLIENT_SERVICE_REQUEST")) {

                if (serviceClientId.equals("ALL_CLIENTS")) {

                } else {
                    List<ServiceRequest> serviceRequestList = customCrudService.clientServiceRequestList(activityFrom, activityTo, serviceClientId);

                    ClientDetail clientDetail = crudService.find(ClientDetail.class, serviceClientId);

                    for (ServiceRequest sr : serviceRequestList) {

                        ServiceRequestTableModel srtm = new ServiceRequestTableModel();

                        srtm.setClientName(sr.getClientDetail().getCompanyName());
                        srtm.setRequestId(sr.getCommonId());
                        srtm.setSerialNumber(sr.getClientProduct().getProductTypeModel().getProductTypes().getProductName() + " - " + sr.getClientProduct().getSerialNumber());
                        srtm.setServiceLocation(sr.getClientProduct().getProductLocation());
                        srtm.setServiceType(sr.getClientProduct().getProductTypeModel().getProductTypes().getProductName());
                        srtm.setServiceModel(sr.getClientProduct().getProductTypeModel().getProductModel());
                        srtm.setRequestDate(sr.getRequestDate());
                        srtm.setServiceComponent(sr.getServiceComponent().getComponentName());

//                        String requestDesc = ;
                        srtm.setRequestDescription(sr.getRequestDescription().replaceAll("<[^>]*>", ""));
                        srtm.setServiceEngineer(sr.getStaffDetail().getStaffName());
                        srtm.setRequestStatus(sr.getRequestStatus());
                        srtm.setClosedDate(sr.getServiceEndDate());

                        srtmList.add(srtm);

                    }

                    HysenReportMgr.instance().initDefaultParamenters(userSession.getInstList());

                    HysenReportMgr.instance().addParam("reportTitle", "Client Service Requests Report");
                    HysenReportMgr.instance().addParam("transactionTo", activityFrom);
                    HysenReportMgr.instance().addParam("transactionFrom", activityTo);

                    HysenReportMgr.instance().showPDFReport(srtmList, HysenReportMgr.CLIENT_REQUEST_REPORT);

                }
            } else {

            }
        }

//        endConversation();
    }

    public void customerDetailPDP() {
        System.out.println("selected client................." + serviceModelClient);
    }

    public void populateClientServiceModel() {

        int count = 0;

        List<ClientProduct> clientProductList = customCrudService.clientProductsList(serviceModelClient, selectedProductType);

        clientEquipmentOption = new SelectItem[clientProductList.size()];

        for (ClientProduct cp : clientProductList) {
            clientEquipmentOption[count] = new SelectItem(cp.getCommonId(), cp.getSerialNumber() + " " + cp.getProductLocation());

            count++;
        }

    }

    public void generateServiceModelReport() {

        List<ServiceRequestTableModel> srtmList = new ArrayList<>();

        ClientProduct clientProduct = crudService.find(ClientProduct.class, serviceModelTypeId);

        if (clientProduct != null) {

            List<ServiceRequest> serviceRequestList = customCrudService.clientServiceModelRequestList(activityFrom, activityTo, clientProduct.getCommonId());

            for (ServiceRequest sr : serviceRequestList) {

                ServiceRequestTableModel srtm = new ServiceRequestTableModel();

                srtm.setRequestId(sr.getCommonId());
                srtm.setRequestDate(sr.getRequestDate());
                srtm.setServiceComponent(sr.getServiceComponent().getComponentName());
                srtm.setRequestDescription(sr.getRequestDescription().replaceAll("<[^>]*>", ""));
                srtm.setServiceEngineer(sr.getStaffDetail().getStaffName());
                srtm.setRequestStatus(sr.getRequestStatus());
                srtm.setClosedDate(sr.getServiceEndDate());

                srtmList.add(srtm);
            }

            HysenReportMgr.instance().initDefaultParamenters(userSession.getInstList());

            HysenReportMgr.instance().addParam("clientName", clientProduct.getClientDetail().getCompanyName());
            HysenReportMgr.instance().addParam("serviceLocation", clientProduct.getProductLocation());
            HysenReportMgr.instance().addParam("serviceType", clientProduct.getProductTypeModel().getProductTypes().getProductName());
            HysenReportMgr.instance().addParam("serviceModel", clientProduct.getProductTypeModel().getProductModel());
            HysenReportMgr.instance().addParam("serialNumber", clientProduct.getSerialNumber());
            HysenReportMgr.instance().addParam("transactionTo", serviceModelActivityFrom);
            HysenReportMgr.instance().addParam("transactionFrom", serviceModelActivityTo);

            HysenReportMgr.instance().addParam("reportTitle", "Client Service Model Requests Report");

            HysenReportMgr.instance().showPDFReport(srtmList, HysenReportMgr.CLIENT_SERVICE_MODEL_REQUEST_REPORT);

        } else {
            StringConstants.showApprioprateMessage("Serial Number does not exist");
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

    public boolean isRenderIndividualEquipPanel() {
        return renderIndividualEquipPanel;
    }

    public void setRenderIndividualEquipPanel(boolean renderIndividualEquipPanel) {
        this.renderIndividualEquipPanel = renderIndividualEquipPanel;
    }

    public boolean isRenderEquipRegionPanel() {
        return renderEquipRegionPanel;
    }

    public void setRenderEquipRegionPanel(boolean renderEquipRegionPanel) {
        this.renderEquipRegionPanel = renderEquipRegionPanel;
    }

    public boolean isRenderEquipPanel() {
        return renderEquipPanel;
    }

    public void setRenderEquipPanel(boolean renderEquipPanel) {
        this.renderEquipPanel = renderEquipPanel;
    }

    public Date getServiceModelActivityFrom() {
        return serviceModelActivityFrom;
    }

    public void setServiceModelActivityFrom(Date serviceModelActivityFrom) {
        this.serviceModelActivityFrom = serviceModelActivityFrom;
    }

    public Date getServiceModelActivityTo() {
        return serviceModelActivityTo;
    }

    public void setServiceModelActivityTo(Date serviceModelActivityTo) {
        this.serviceModelActivityTo = serviceModelActivityTo;
    }

    public String getServiceModelTypeId() {
        return serviceModelTypeId;
    }

    public void setServiceModelTypeId(String serviceModelTypeId) {
        this.serviceModelTypeId = serviceModelTypeId;
    }

    public String getServiceModelClient() {
        return serviceModelClient;
    }

    public void setServiceModelClient(String serviceModelClient) {
        this.serviceModelClient = serviceModelClient;
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

    public String getServiceClientId() {
        return serviceClientId;
    }

    public void setServiceClientId(String serviceClientId) {
        this.serviceClientId = serviceClientId;
    }

    public String getServiceRequestParameter() {
        return serviceRequestParameter;
    }

    public void setServiceRequestParameter(String serviceRequestParameter) {
        this.serviceRequestParameter = serviceRequestParameter;
    }

    public Date getActivityFrom() {
        return activityFrom;
    }

    public void setActivityFrom(Date activityFrom) {
        this.activityFrom = activityFrom;
    }

    public Date getActivityTo() {
        return activityTo;
    }

    public void setActivityTo(Date activityTo) {
        this.activityTo = activityTo;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
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

    public String getClientDetailId() {
        return clientDetailId;
    }

    public void setClientDetailId(String clientDetailId) {
        this.clientDetailId = clientDetailId;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportParameter() {
        return reportParameter;
    }

    public void setReportParameter(String reportParameter) {
        this.reportParameter = reportParameter;
    }
//</editor-fold>

}
