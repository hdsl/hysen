/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.web.tableModel;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
public class ClientTableModel {
    
    private String clientName;
    private String industryName;
    private String clientPrimaryContact;
    private String clientEmail;
    private String clientAddress;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getClientPrimaryContact() {
        return clientPrimaryContact;
    }

    public void setClientPrimaryContact(String clientPrimaryContact) {
        this.clientPrimaryContact = clientPrimaryContact;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }
    
    
}
