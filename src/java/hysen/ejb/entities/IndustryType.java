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
@Table(name = "industry_type")
@NamedQueries({
    @NamedQuery(name = "IndustryType.findAll", query = "SELECT i FROM IndustryType i")})
public class IndustryType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "industry_type_id")
    private String industryTypeId;
    @Size(max = 255)
    @Column(name = "industry_name")
    private String industryName;
    @Column(name = "status")
    private String status;
    @Column(name = "deleted")
    private Character deleted;

    public IndustryType() {
    }

    public IndustryType(String industryTypeId) {
        this.industryTypeId = industryTypeId;
    }

    public String getIndustryTypeId() {
        return industryTypeId;
    }

    public void setIndustryTypeId(String industryTypeId) {
        this.industryTypeId = industryTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Character getDeleted() {
        return deleted;
    }

    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (industryTypeId != null ? industryTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndustryType)) {
            return false;
        }
        IndustryType other = (IndustryType) object;
        if ((this.industryTypeId == null && other.industryTypeId != null) || (this.industryTypeId != null && !this.industryTypeId.equals(other.industryTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hysen.ejb.entities.IndustryType[ industryTypeId=" + industryTypeId + " ]";
    }
    
}
