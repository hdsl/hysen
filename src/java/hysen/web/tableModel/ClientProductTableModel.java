/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.web.tableModel;

import java.util.Date;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
public class ClientProductTableModel {
    
    String clientName;
    String serviceType;
    String serviceModel;
    String serialNumber;
    String serviceLocation;
    String serviceRegion;
    Date dateDeployed;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getServiceRegion() {
        return serviceRegion;
    }

    public void setServiceRegion(String serviceRegion) {
        this.serviceRegion = serviceRegion;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceModel() {
        return serviceModel;
    }

    public void setServiceModel(String serviceModel) {
        this.serviceModel = serviceModel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public Date getDateDeployed() {
        return dateDeployed;
    }

    public void setDateDeployed(Date dateDeployed) {
        this.dateDeployed = dateDeployed;
    }
    
    
}
