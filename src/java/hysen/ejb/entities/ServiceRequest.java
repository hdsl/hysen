/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.ejb.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Entity
@Table(name = "service_request")
@NamedQueries({
    @NamedQuery(name = "ServiceRequest.findAll", query = "SELECT s FROM ServiceRequest s")})
public class ServiceRequest extends CommonEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_detail")
    private ClientDetail clientDetail;
    
    @Column(name = "client_contact_person")
    private String clientContactPerson;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type")
    private ProductTypes productType;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_product")
    private ClientProduct clientProduct;
   
    @Column(name = "request_mode")
    private String requestMode;
    
    @Column(name = "service_resolution")
    private String serviceResolution;
    
    @Column(name = "attached_document")
    private String attachedDocument;
    
    @Column(name = "request_status")
    private String requestStatus;
    
    @Column(name = "request_priority")
    private String requestPriority;
   
    @Column(name = "service_request_id")
    private Integer serviceRequestId;
    
    @Column(name = "pm_period")
    private String pmPeriod;
    
    @Column(name = "pm_year")
    private Integer pmYear;
   
    @Column(name = "email_to_notify")
    private String emailToNotify;
  
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_component")
    private ServiceModelComponent serviceComponent;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_detail")
    private StaffDetail staffDetail;
    
    @Column(name = "request_description")
    private String requestDescription;
    
    @Column(name = "request_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date requestDate;
    
    @Column(name = "service_start_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date serviceStartDate;
    
    @Column(name = "service_end_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date serviceEndDate;
    
    public ServiceRequest() {
    }

    public ClientDetail getClientDetail() {
        return clientDetail;
    }

    public void setClientDetail(ClientDetail clientDetail) {
        this.clientDetail = clientDetail;
    }

    public String getClientContactPerson() {
        return clientContactPerson;
    }

    public void setClientContactPerson(String clientContactPerson) {
        this.clientContactPerson = clientContactPerson;
    }

    public String getPmPeriod() {
        return pmPeriod;
    }

    public void setPmPeriod(String pmPeriod) {
        this.pmPeriod = pmPeriod;
    }

    public Integer getPmYear() {
        return pmYear;
    }

    public void setPmYear(Integer pmYear) {
        this.pmYear = pmYear;
    }

    public Integer getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(Integer serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public ProductTypes getProductType() {
        return productType;
    }

    public void setProductType(ProductTypes productType) {
        this.productType = productType;
    }

    public ClientProduct getClientProduct() {
        return clientProduct;
    }

    public void setClientProduct(ClientProduct clientProduct) {
        this.clientProduct = clientProduct;
    }

    public String getRequestMode() {
        return requestMode;
    }

    public void setRequestMode(String requestMode) {
        this.requestMode = requestMode;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestPriority() {
        return requestPriority;
    }

    public void setRequestPriority(String requestPriority) {
        this.requestPriority = requestPriority;
    }

    public String getEmailToNotify() {
        return emailToNotify;
    }

    public void setEmailToNotify(String emailToNotify) {
        this.emailToNotify = emailToNotify;
    }

    public ServiceModelComponent getServiceComponent() {
        return serviceComponent;
    }

    public void setServiceComponent(ServiceModelComponent serviceComponent) {
        this.serviceComponent = serviceComponent;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public StaffDetail getStaffDetail() {
        return staffDetail;
    }

    public void setStaffDetail(StaffDetail staffDetail) {
        this.staffDetail = staffDetail;
    }

    public Date getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(Date serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public Date getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(Date serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public String getServiceResolution() {
        return serviceResolution;
    }

    public void setServiceResolution(String serviceResolution) {
        this.serviceResolution = serviceResolution;
    }

    public String getAttachedDocument() {
        return attachedDocument;
    }

    public void setAttachedDocument(String attachedDocument) {
        this.attachedDocument = attachedDocument;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

}
