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
@Table(name = "trans_type")
@NamedQueries({
    @NamedQuery(name = "TransType.findAll", query = "SELECT t FROM TransType t")})
public class TransType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "trans_type_id")
    private String transTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 38)
    @Column(name = "type_desc")
    private String typeDesc;

    public TransType() {
    }

    public TransType(String transTypeId) {
        this.transTypeId = transTypeId;
    }

    public TransType(String transTypeId, String typeDesc) {
        this.transTypeId = transTypeId;
        this.typeDesc = typeDesc;
    }

    public String getTransTypeId() {
        return transTypeId;
    }

    public void setTransTypeId(String transTypeId) {
        this.transTypeId = transTypeId;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transTypeId != null ? transTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransType)) {
            return false;
        }
        TransType other = (TransType) object;
        if ((this.transTypeId == null && other.transTypeId != null) || (this.transTypeId != null && !this.transTypeId.equals(other.transTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hysen.ejb.entities.TransType[ transTypeId=" + transTypeId + " ]";
    }
    
}
