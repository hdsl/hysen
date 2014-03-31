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
@Table(name = "kin_relation")
@NamedQueries({
    @NamedQuery(name = "KinRelation.findAll", query = "SELECT k FROM KinRelation k")})
public class KinRelation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "kin_relation_id")
    private String kinRelationId;
    @Size(max = 35)
    @Column(name = "relation_desc")
    private String relationDesc;

    public KinRelation() {
    }

    public KinRelation(String kinRelationId) {
        this.kinRelationId = kinRelationId;
    }

    public String getKinRelationId() {
        return kinRelationId;
    }

    public void setKinRelationId(String kinRelationId) {
        this.kinRelationId = kinRelationId;
    }

    public String getRelationDesc() {
        return relationDesc;
    }

    public void setRelationDesc(String relationDesc) {
        this.relationDesc = relationDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kinRelationId != null ? kinRelationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KinRelation)) {
            return false;
        }
        KinRelation other = (KinRelation) object;
        if ((this.kinRelationId == null && other.kinRelationId != null) || (this.kinRelationId != null && !this.kinRelationId.equals(other.kinRelationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hysen.ejb.entities.KinRelation[ kinRelationId=" + kinRelationId + " ]";
    }
    
}
