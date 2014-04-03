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
public class ServiceRequestTableModel {
    
    private String serialNumber;
    private String serviceLocation;
    private String serviceType;
    private String serviceModel;
    private String clientName;
    private String requestId;
    private Date requestDate;
    private String serviceComponent;
    private String requestDescription;
    private String serviceEngineer;
    private String serviceEngineerId;
    private String requestStatus;
    private String clientProductId;
    private Date closedDate;
    
    private Date pmScheduledDate;
    private Date pmCompletedDate;
    

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClientProductId() {
        return clientProductId;
    }

    public void setClientProductId(String clientProductId) {
        this.clientProductId = clientProductId;
    }

    public String getServiceEngineerId() {
        return serviceEngineerId;
    }

    public void setServiceEngineerId(String serviceEngineerId) {
        this.serviceEngineerId = serviceEngineerId;
    }

    public Date getPmScheduledDate() {
        return pmScheduledDate;
    }

    public void setPmScheduledDate(Date pmScheduledDate) {
        this.pmScheduledDate = pmScheduledDate;
    }

    public Date getPmCompletedDate() {
        return pmCompletedDate;
    }

    public void setPmCompletedDate(Date pmCompletedDate) {
        this.pmCompletedDate = pmCompletedDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getServiceComponent() {
        return serviceComponent;
    }

    public void setServiceComponent(String serviceComponent) {
        this.serviceComponent = serviceComponent;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getServiceEngineer() {
        return serviceEngineer;
    }

    public void setServiceEngineer(String serviceEngineer) {
        this.serviceEngineer = serviceEngineer;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }
    
    
}
