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

/**
 *
 * @author AbdulMumin
 */
@Entity
@Table(name = "client_detail")
@NamedQueries({
    @NamedQuery(name = "ClientDetail.findAll", query = "SELECT a FROM ClientDetail a")})
public class ClientDetail extends CommonEntity {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "primary_contact")
    private String primaryContact;

    @Column(name = "other_contact")
    private String otherContact;  
    
    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "primary_address")
    private String primaryAddress;
    
    @Column(name = "other_address")
    private String otherAddress;
    
    @Column(name = "company_website")
    private String companyWebsite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_status")
    private IndustryType companyStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "industry_type")
    private IndustryType industryType;

    public ClientDetail() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
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

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
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

    public IndustryType getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(IndustryType companyStatus) {
        this.companyStatus = companyStatus;
    }

    public IndustryType getIndustryType() {
        return industryType;
    }

    public void setIndustryType(IndustryType industryType) {
        this.industryType = industryType;
    }

}
