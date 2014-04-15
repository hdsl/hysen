/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

    @Column(name = "client_detail")
    private String clientDetail;

    @Size(max = 255)
    @Column(name = "contact_name")
    private String contactName;
    @Size(max = 255)
    @Column(name = "contact_email")
    private String contactEmail;
    @Size(max = 255)
    @Column(name = "contact_mobile")
    private String contactMobile;
    @Size(max = 255)
    @Column(name = "job_title")
    private String jobTitle;

    public ClientContact() {
    }

    public String getClientDetail() {
        return clientDetail;
    }

    public void setClientDetail(String clientDetail) {
        this.clientDetail = clientDetail;
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

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

}
