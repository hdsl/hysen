/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.ejb.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Entity
@Table(name = "client_product")
@NamedQueries({
    @NamedQuery(name = "ClientProduct.findAll", query = "SELECT c FROM ClientProduct c")})
public class ClientProduct extends CommonEntity {

    @ManyToOne
    @JoinColumn(name = "client_detail")//
    private ClientDetail clientDetail;

    @ManyToOne
    @JoinColumn(name = "product_model")//
    private ProductTypesModel productTypeModel;

    @ManyToOne
    @JoinColumn(name = "regions")//
    private Regions regions;

    @Column(name = "product_location")//
    private String productLocation;

    @Column(name = "serial_number")//
    private String serialNumber;

    @Column(name = "software_installed")
    private String softwareInstalled;

    @Column(name = "service_engineer")
    private String serviceEngineer;

    @Column(name = "installation_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date installationDate;

    @Column(name = "deployment_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deploymentDate;

    @Column(name = "service_comment")
    private String serviceComment;

    @Column(name = "model_components")
    private String modelComponents;

    public ClientProduct() {
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public String getModelComponents() {
        return modelComponents;
    }

    public void setModelComponents(String modelComponents) {
        this.modelComponents = modelComponents;
    }

    public String getSoftwareInstalled() {
        return softwareInstalled;
    }

    public void setSoftwareInstalled(String softwareInstalled) {
        this.softwareInstalled = softwareInstalled;
    }

    public String getServiceEngineer() {
        return serviceEngineer;
    }

    public void setServiceEngineer(String serviceEngineer) {
        this.serviceEngineer = serviceEngineer;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public Date getDeploymentDate() {
        return deploymentDate;
    }

    public void setDeploymentDate(Date deploymentDate) {
        this.deploymentDate = deploymentDate;
    }

    public String getServiceComment() {
        return serviceComment;
    }

    public void setServiceComment(String serviceComment) {
        this.serviceComment = serviceComment;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ClientDetail getClientDetail() {
        return clientDetail;
    }

    public void setClientDetail(ClientDetail clientDetail) {
        this.clientDetail = clientDetail;
    }

    public Regions getRegions() {
        return regions;
    }

    public void setRegions(Regions regions) {
        this.regions = regions;
    }

    public ProductTypesModel getProductTypeModel() {
        return productTypeModel;
    }

    public void setProductTypeModel(ProductTypesModel productTypeModel) {
        this.productTypeModel = productTypeModel;
    }

    @Transient
    private List<String> smcList = new ArrayList<>();

    public List<String> getSmcList() {

        smcList.clear();

        if (modelComponents == null) {
            return smcList;
        }

        String[] modelComponentId = modelComponents.split("/");

        for (String mc : modelComponentId) {
            smcList.add(mc);
        }

        return smcList;
    }

    public void setSmcList(List<String> smcList) {
        this.smcList = smcList;
    }

    @Transient
    private List<String> softwareList = new ArrayList<>();

    public List<String> getSoftwareList() {

        softwareList.clear();

        if (softwareInstalled == null) {
            return softwareList;
        }

        String[] softwaresInstalled = softwareInstalled.split("/");

        for (String mc : softwaresInstalled) {
            softwareList.add(mc);
        }

        return softwareList;
    }

    public void setSoftwareList(List<String> softwareList) {
        this.softwareList = softwareList;
    }

    @Transient
    private List<String> serviceEngineersList = new ArrayList<>();

    public List<String> getServiceEngineersList() {

        serviceEngineersList.clear();

        if (serviceEngineer == null) {
            return serviceEngineersList;
        }

        String[] serviceEngineers = serviceEngineer.split("/");

        for (String mc : serviceEngineers) {
            serviceEngineersList.add(mc);
        }

        return serviceEngineersList;
    }

    public void setServiceEngineersList(List<String> serviceEngineersList) {
        this.serviceEngineersList = serviceEngineersList;
    }

}
