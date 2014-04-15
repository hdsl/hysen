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

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_location")
    private String clientLocation;

    @Column(name = "client_address")
    private String clientAddress;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "client_primary_contact")
    private String clientPrimaryContact;

    @Column(name = "client_other_contact")
    private String clientOtherContact;

    @Column(name = "client_website")
    private String clientWebsite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "industry_type")
    private IndustryType industryType;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_person_email")
    private String contactPersonEmail;

    @Column(name = "contact_person_phone_number")
    private String contactPersonPhoneNumber;

    @Column(name = "contact_job_title")
    private String contactJobTitle;

    public ClientDetail() {
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactJobTitle() {
        return contactJobTitle;
    }

    public void setContactJobTitle(String contactJobTitle) {
        this.contactJobTitle = contactJobTitle;
    }

    public String getClientLocation() {
        return clientLocation;
    }

    public void setClientLocation(String clientLocation) {
        this.clientLocation = clientLocation;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPrimaryContact() {
        return clientPrimaryContact;
    }

    public void setClientPrimaryContact(String clientPrimaryContact) {
        this.clientPrimaryContact = clientPrimaryContact;
    }

    public String getClientOtherContact() {
        return clientOtherContact;
    }

    public void setClientOtherContact(String clientOtherContact) {
        this.clientOtherContact = clientOtherContact;
    }

    public String getClientWebsite() {
        return clientWebsite;
    }

    public void setClientWebsite(String clientWebsite) {
        this.clientWebsite = clientWebsite;
    }

    public IndustryType getIndustryType() {
        return industryType;
    }

    public void setIndustryType(IndustryType industryType) {
        this.industryType = industryType;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getContactPersonPhoneNumber() {
        return contactPersonPhoneNumber;
    }

    public void setContactPersonPhoneNumber(String contactPersonPhoneNumber) {
        this.contactPersonPhoneNumber = contactPersonPhoneNumber;
    }

}
