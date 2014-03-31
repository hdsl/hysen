/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.ejb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AbdulMumin
 */
@Entity
@Table(name = "marital_status")
@NamedQueries({
    @NamedQuery(name = "MaritalStatus.findAll", query = "SELECT m FROM MaritalStatus m")})
public class MaritalStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "marital_status_id")
    private String maritalStatusId;
    @Size(max = 35)
    @Column(name = "status_desc")
    private String statusDesc;

    public MaritalStatus() {
    }

    public MaritalStatus(String maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(String maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maritalStatusId != null ? maritalStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaritalStatus)) {
            return false;
        }
        MaritalStatus other = (MaritalStatus) object;
        if ((this.maritalStatusId == null && other.maritalStatusId != null) || (this.maritalStatusId != null && !this.maritalStatusId.equals(other.maritalStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hysen.ejb.entities.MaritalStatus[ maritalStatusId=" + maritalStatusId + " ]";
    }
    
}
