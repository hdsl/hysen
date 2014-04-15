/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.utils;

import com.sabonay.common.utils.DateTimeUtils;
import com.sabonay.common.utils.NumberRange;
import com.sabonay.modules.web.jsf.utilities.JsfUtil;
import hysen.ejb.entities.AccessRight;
import hysen.ejb.entities.ClientDetail;
import hysen.ejb.entities.Department;
import hysen.ejb.entities.IndustryType;
import hysen.ejb.entities.KinRelation;
import hysen.ejb.entities.MaritalStatus;
import hysen.ejb.entities.Nationality;
import hysen.ejb.entities.ProductTypes;
import hysen.ejb.entities.Regions;
import hysen.ejb.entities.ServiceSoftware;
import hysen.ejb.entities.StaffDetail;
import hysen.ejb.services.CrudService;
import hysen.ejb.services.CustomCrudService;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 *
 */
@Named
@RequestScoped
public class CommonOptions implements Serializable {

    @Inject
    private CrudService crudService;

    @Inject
    private CustomCrudService customCrudService;

    private SelectItem[] calendarYears;
    private SelectItem[] calenderMonthNamesOptions;
    private SelectItem[] nationality;
    private SelectItem[] maritalStatus;
    private SelectItem[] identificationTypes;
    private SelectItem[] accessRightTypes;
    private SelectItem[] kinRelationOptions;
    private SelectItem[] regionsOptions;
    private SelectItem[] levelOfEducationOptions;
    private SelectItem[] stockCategoryOptions;
    private SelectItem[] stockItemsOptions;
    private SelectItem[] suppliersOptions;
    private SelectItem[] departmentOptions;
    private SelectItem[] staffDetailOptions;
    private SelectItem[] industryTypeOptions;
    private SelectItem[] productTypesOptions;
    private SelectItem[] clientsDetailOptions;
    private SelectItem[] engineeringStaffOption;
    private SelectItem[] serviceSoftwareOption;

    public CommonOptions() {

        List<Integer> years = NumberRange.generateRange(1980,
                DateTimeUtils.getCurrentYear());

        Collections.reverse(years);

        calendarYears = JsfUtil.createSelectItems(years, false);

        calenderMonthNamesOptions = JsfUtil.createSelectItems(DateTimeUtils.getMonthNames(), true);

    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public SelectItem[] getCalendarYears() {
        return calendarYears;
    }

    public void setCalendarYears(SelectItem[] calendarYears) {
        this.calendarYears = calendarYears;
    }

    public SelectItem[] getServiceSoftwareOption() {        
        
        List<ServiceSoftware> serviceSoftwareList
                = crudService.findAll(ServiceSoftware.class, false);

        serviceSoftwareOption = new SelectItem[serviceSoftwareList.size()];

        int count = 0;

        for (ServiceSoftware ss : serviceSoftwareList) {

            serviceSoftwareOption[count] = new SelectItem(ss.getCommonId(), ss.getSoftwareDesc());

            count++;
        }
        
        return serviceSoftwareOption;
        
    }

    public void setServiceSoftwareOption(SelectItem[] serviceSoftwareOption) {
        this.serviceSoftwareOption = serviceSoftwareOption;
    }

    public CustomCrudService getCustomCrudService() {
        return customCrudService;
    }

    public void setCustomCrudService(CustomCrudService customCrudService) {
        this.customCrudService = customCrudService;
    }

    public SelectItem[] getRegionsOptions() {

        List<Regions> regionsList
                = crudService.findAll(Regions.class, true);

        regionsOptions = new SelectItem[regionsList.size()];

        int count = 0;

        for (Regions si : regionsList) {

            regionsOptions[count] = new SelectItem(si.getRegionId(), si.getRegionName());

            count++;
        }
        return regionsOptions;
    }

    public void setRegionsOptions(SelectItem[] regionsOptions) {
        this.regionsOptions = regionsOptions;
    }

    public SelectItem[] getEngineeringStaffOption() {

        int count = 0;

        List<StaffDetail> staffDetailList
                = customCrudService.findByParameter(StaffDetail.class, "department.commonId", "206992d5-5655-4d8c", 'N');

        engineeringStaffOption = new SelectItem[staffDetailList.size()];

        for (StaffDetail cc : staffDetailList) {

            String gender;

            if (cc.getGender().equals('F')) {
                gender = "(Mrs.)";
            } else {
                gender = "(Mr.)";
            }

            engineeringStaffOption[count] = new SelectItem(cc.getCommonId(), cc.getStaffName().toUpperCase() + gender);

            count++;

        }

        return engineeringStaffOption;
    }

    public void setEngineeringStaffOption(SelectItem[] engineeringStaffOption) {
        this.engineeringStaffOption = engineeringStaffOption;
    }

    public SelectItem[] getClientsDetailOptions() {

        List<ClientDetail> clientDetailList
                = crudService.findAll(ClientDetail.class, true, "clientName");

        clientsDetailOptions = new SelectItem[clientDetailList.size()];

        int count = 0;

        for (ClientDetail si : clientDetailList) {

            clientsDetailOptions[count] = new SelectItem(si.getCommonId(), si.getClientName().toUpperCase());

            count++;
        }
        return clientsDetailOptions;
    }

    public void setClientsDetailOptions(SelectItem[] clientsDetailOptions) {
        this.clientsDetailOptions = clientsDetailOptions;
    }

    public SelectItem[] getProductTypesOptions() {

        List<ProductTypes> productTypesList
                = crudService.findAll(ProductTypes.class, true, "productName");

        productTypesOptions = new SelectItem[productTypesList.size()];

        int count = 0;

        for (ProductTypes si : productTypesList) {

            productTypesOptions[count] = new SelectItem(si.getCommonId(), si.getProductName());

            count++;
        }
        return productTypesOptions;
    }

    public void setProductTypesOptions(SelectItem[] productTypesOptions) {
        this.productTypesOptions = productTypesOptions;
    }

    public SelectItem[] getIndustryTypeOptions() {

        List<IndustryType> industryTypeList
                = crudService.findAll(IndustryType.class, true, "industryName");

        industryTypeOptions = new SelectItem[industryTypeList.size()];

        int count = 0;

        for (IndustryType si : industryTypeList) {

            industryTypeOptions[count] = new SelectItem(si.getIndustryTypeId(), si.getIndustryName());

            count++;
        }
        return industryTypeOptions;
    }

    public void setIndustryTypeOptions(SelectItem[] industryTypeOptions) {
        this.industryTypeOptions = industryTypeOptions;
    }

    public CrudService getCrudService() {
        return crudService;
    }

    public void setCrudService(CrudService crudService) {
        this.crudService = crudService;
    }

    public SelectItem[] getStaffDetailOptions() {
        List<StaffDetail> staffDetailList
                = crudService.findAll(StaffDetail.class, true);

        staffDetailOptions = new SelectItem[staffDetailList.size()];

        int count = 0;

        for (StaffDetail si : staffDetailList) {

            String gender;

            if (si.getGender().equals('F')) {
                gender = "(Mrs.)";
            } else {
                gender = "(Mr.)";
            }
            staffDetailOptions[count] = new SelectItem(si.getCommonId(), si.getStaffName() + "" + gender);

            count++;
        }
        return staffDetailOptions;
    }

    public void setStaffDetailOptions(SelectItem[] staffDetailOptions) {
        this.staffDetailOptions = staffDetailOptions;
    }

    public SelectItem[] getDepartmentOptions() {
        List<Department> departmentList
                = crudService.findAll(Department.class, true);

        departmentOptions = new SelectItem[departmentList.size()];

        int count = 0;

        for (Department si : departmentList) {

            departmentOptions[count] = new SelectItem(si.getCommonId(), si.getDepartmentName());

            count++;
        }
        return departmentOptions;
    }

    public void setDepartmentOptions(SelectItem[] departmentOptions) {
        this.departmentOptions = departmentOptions;
    }

    public SelectItem[] getSuppliersOptions() {
//          List<Supplier> supplierList
//                = dataSource.getCommonQry().supplierGetAll(false);
//
//        if (supplierList.size() > 0) {
//
//            suppliersOptions = new SelectItem[supplierList.size()];
//
//            int count = 0;
//
//            for (Supplier si : supplierList) {
//                suppliersOptions[count] = new SelectItem(si.getSupplierId(), si.getSupplierName());
//
//                count++;
//            }
//        }
        return suppliersOptions;
    }

    public void setSuppliersOptions(SelectItem[] suppliersOptions) {
        this.suppliersOptions = suppliersOptions;
    }

    public SelectItem[] getStockItemsOptions() {
//         List<StockItem> stockItemListList
//                = dataSource.getCommonQry().stockItemGetAll();
//
//        if (stockItemListList.size() > 0) {
//
//            stockItemsOptions = new SelectItem[stockItemListList.size()];
//
//            int count = 0;
//
//            for (StockItem si : stockItemListList) {
//                stockItemsOptions[count] = new SelectItem(si.getStockItemId(), si.getItemDescription());
//
//                count++;
//            }
//        }
        return stockItemsOptions;
    }

    public void setStockItemsOptions(SelectItem[] stockItemsOptions) {
        this.stockItemsOptions = stockItemsOptions;
    }

    public SelectItem[] getStockCategoryOptions() {
//         List<StockCategory> stockCategoryListList
//                = dataSource.getCommonQry().stockCategoryGetAll(false);
//
//        if (stockCategoryListList.size() > 0) {
//
//            stockCategoryOptions = new SelectItem[stockCategoryListList.size()];
//
//            int count = 0;
//
//            for (StockCategory sc : stockCategoryListList) {
//                stockCategoryOptions[count] = new SelectItem(sc.getStockCategoryId(), sc.getCategoryName());
//
//                count++;
//            }
//        }
        return stockCategoryOptions;
    }

    public void setStockCategoryOptions(SelectItem[] stockCategoryOptions) {
        this.stockCategoryOptions = stockCategoryOptions;
    }

    public SelectItem[] getLevelOfEducationOptions() {
//        List<LevelOfEducation> levelOfEducationList
//                = dataSource.getCommonQry().levelOfEducationGetAll();
//
//        if (levelOfEducationList.size() > 0) {
//
//            levelOfEducationOptions = new SelectItem[levelOfEducationList.size()];
//
//            int count = 0;
//
//            for (LevelOfEducation estProp : levelOfEducationList) {
//                levelOfEducationOptions[count] = new SelectItem(estProp.getLevelId(), estProp.getLevelDescription());
//
//                count++;
//            }
//        }
        return levelOfEducationOptions;
    }

    public void setLevelOfEducationOptions(SelectItem[] levelOfEducationOptions) {
        this.levelOfEducationOptions = levelOfEducationOptions;
    }

    public SelectItem[] getKinRelationOptions() {

        List<KinRelation> kinRelationList = crudService.findAll(KinRelation.class, true);

        kinRelationOptions = new SelectItem[kinRelationList.size()];

        int count = 0;

        for (KinRelation kinRelation : kinRelationList) {
            kinRelationOptions[count] = new SelectItem(kinRelation.getKinRelationId(), kinRelation.getRelationDesc());

            count++;
        }

        return kinRelationOptions;
    }

    public void setKinRelationOptions(SelectItem[] kinRelationOptions) {
        this.kinRelationOptions = kinRelationOptions;
    }

    public SelectItem[] getAccessRightTypes() {

        List<AccessRight> accessRightList = crudService.findAll(AccessRight.class, false);

        accessRightTypes = new SelectItem[accessRightList.size()];

        int count = 0;

        for (AccessRight at : accessRightList) {
            accessRightTypes[count] = new SelectItem(at.getCommonId(), at.getAccessRight());

            count++;
        }
        return accessRightTypes;
    }

    public void setAccessRightTypes(SelectItem[] accessRightTypes) {
        this.accessRightTypes = accessRightTypes;
    }

    public SelectItem[] getNationality() {

        List<Nationality> nationalityList = crudService.findAll(Nationality.class, true);

        nationality = new SelectItem[nationalityList.size()];

        int count = 0;

        for (Nationality n : nationalityList) {

            nationality[count] = new SelectItem(n.getNationalityId(), n.getNationalityDesc());
            count++;

        }

        return nationality;
    }

    public void setNationality(SelectItem[] nationality) {
        this.nationality = nationality;
    }

    public SelectItem[] getIdentificationTypes() {

//        List<IdentificationType> idTypesList = dataSource.getCommonQry().identificationTypeGetAll(true);
//
//        identificationTypes = new SelectItem[idTypesList.size()];
//        int count = 0;
//        for (IdentificationType g : idTypesList) {
//            identificationTypes[count] = new SelectItem(g.getCardCode(), g.getCardDesc());
//            count++;
//        }
        return identificationTypes;
    }

    public void setIdentificationTypes(SelectItem[] identificationTypes) {
        this.identificationTypes = identificationTypes;
    }

    public SelectItem[] getMaritalStatus() {

        List<MaritalStatus> maritalStatusList = crudService.findAll(MaritalStatus.class, true);

        maritalStatus = new SelectItem[maritalStatusList.size()];

        int count = 0;

        for (MaritalStatus ms : maritalStatusList) {
            maritalStatus[count] = new SelectItem(ms.getMaritalStatusId(), ms.getStatusDesc());
            count++;
        }

        return maritalStatus;
    }

    public void setMaritalStatus(SelectItem[] maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public SelectItem[] getCalenderMonthNamesOptions() {
        return calenderMonthNamesOptions;
    }

    public void setCalenderMonthNamesOptions(SelectItem[] calenderMonthNamesOptions) {
        this.calenderMonthNamesOptions = calenderMonthNamesOptions;
    }

//    //</editor-fold>
}
