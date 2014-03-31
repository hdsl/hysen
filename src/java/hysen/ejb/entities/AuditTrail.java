/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AbdulMumin
 */
@Entity
@Table(name = "audit_trail")
@NamedQueries({
    @NamedQuery(name = "AuditTrail.findAll", query = "SELECT a FROM AuditTrail a")})
public class AuditTrail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "audit_trail_id")
    private String auditTrailId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "system_user")
    private String systemUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "school_reference_id")
    private String schoolReferenceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "academic_term")
    private String academicTerm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "system_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemTime;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "trail_activity")
    private String trailActivity;
    @Column(name = "deleted")
    private Character deleted;
    @Column(name = "updated")
    private Character updated;
    @Column(name = "come")
    private Character come;

    public AuditTrail() {
    }

    public AuditTrail(String auditTrailId) {
        this.auditTrailId = auditTrailId;
    }

    public AuditTrail(String auditTrailId, String systemUser, String schoolReferenceId, String academicTerm, Date systemTime, String trailActivity) {
        this.auditTrailId = auditTrailId;
        this.systemUser = systemUser;
        this.schoolReferenceId = schoolReferenceId;
        this.academicTerm = academicTerm;
        this.systemTime = systemTime;
        this.trailActivity = trailActivity;
    }

    public String getAuditTrailId() {
        return auditTrailId;
    }

    public void setAuditTrailId(String auditTrailId) {
        this.auditTrailId = auditTrailId;
    }

    public String getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(String systemUser) {
        this.systemUser = systemUser;
    }

    public Character getCome() {
        return come;
    }

    public void setCome(Character come) {
        this.come = come;
    }

    public String getSchoolReferenceId() {
        return schoolReferenceId;
    }

    public void setSchoolReferenceId(String schoolReferenceId) {
        this.schoolReferenceId = schoolReferenceId;
    }

    public String getAcademicTerm() {
        return academicTerm;
    }

    public void setAcademicTerm(String academicTerm) {
        this.academicTerm = academicTerm;
    }

    public Date getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Date systemTime) {
        this.systemTime = systemTime;
    }

    public String getTrailActivity() {
        return trailActivity;
    }

    public void setTrailActivity(String trailActivity) {
        this.trailActivity = trailActivity;
    }

    public Character getDeleted() {
        return deleted;
    }

    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }

    public Character getUpdated() {
        return updated;
    }

    public void setUpdated(Character updated) {
        this.updated = updated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auditTrailId != null ? auditTrailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditTrail)) {
            return false;
        }
        AuditTrail other = (AuditTrail) object;
        if ((this.auditTrailId == null && other.auditTrailId != null) || (this.auditTrailId != null && !this.auditTrailId.equals(other.auditTrailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hysen.ejb.entities.AuditTrail[ auditTrailId=" + auditTrailId + " ]";
    }
    
}
