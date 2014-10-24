/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Entity
@Table(name = "client_contact")
@NamedQueries({
    @NamedQuery(name = "ClientContact.findAll", query = "SELECT c FROM ClientContact c")})
public class ClientContact extends CommonEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_detail")
    private ClientDetail companyDetail;

    @Size(max = 255)
    @Column(name = "contact_name")
    private String contactName;
    
    @Size(max = 255)
    @Column(name = "contact_email")
    private String contactEmail;
    
    @Size(max = 255)
    @Column(name = "primary_contact")
    private String primaryContact;
    
    @Size(max = 255)
    @Column(name = "other_contact")
    private String otherContact;    
    
    @Size(max = 255)
    @Column(name = "primary_address")
    private String primaryAddress;
    
    @Size(max = 255)
    @Column(name = "other_address")
    private String otherAddress;
    
    @Size(max = 255)
    @Column(name = "contact_department")
    private String contactDepartment;
    
    @Size(max = 255)
    @Column(name = "job_title")
    private String jobTitle;
    
    @Size(max = 255)
    @Column(name = "contact_region")
    private String contactRegion;

    public ClientContact() {
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public ClientDetail getCompanyDetail() {
        return companyDetail;
    }

    public void setCompanyDetail(ClientDetail companyDetail) {
        this.companyDetail = companyDetail;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(String otherContact) {
        this.otherContact = otherContact;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }

    public String getContactDepartment() {
        return contactDepartment;
    }

    public void setContactDepartment(String contactDepartment) {
        this.contactDepartment = contactDepartment;
    }

    public String getContactRegion() {
        return contactRegion;
    }

    public void setContactRegion(String contactRegion) {
        this.contactRegion = contactRegion;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

}
