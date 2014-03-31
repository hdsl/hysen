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
@Table(name = "sale_summary")
@NamedQueries({
    @NamedQuery(name = "SaleSummary.findAll", query = "SELECT s FROM SaleSummary s")})
public class SaleSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 79)
    @Column(name = "sale_summary_id")
    private String saleSummaryId;
    @Size(max = 35)
    @Column(name = "invoice_id")
    private String invoiceId;
    @Column(name = "sale_date")
    @Temporal(TemporalType.DATE)
    private Date saleDate;
    @Size(max = 35)
    @Column(name = "payment_mode")
    private String paymentMode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount_applied")
    private Double discountApplied;
    @Column(name = "amount_due")
    private Double amountDue;
    @Column(name = "amount_paid")
    private Double amountPaid;
    @Size(max = 79)
    @Column(name = "sale_person")
    private String salePerson;
    @Column(name = "other_charges")
    private Double otherCharges;
    @Column(name = "nhis_vat")
    private Double nhisVat;
    @Size(max = 93)
    @Column(name = "updated_reason")
    private String updatedReason;
    @Size(max = 93)
    @Column(name = "deleted_reason")
    private String deletedReason;
    @Column(name = "updated")
    private Character updated;
    @Column(name = "deleted")
    private Character deleted;
    @Size(max = 79)
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public SaleSummary() {
    }

    public SaleSummary(String saleSummaryId) {
        this.saleSummaryId = saleSummaryId;
    }

    public String getSaleSummaryId() {
        return saleSummaryId;
    }

    public void setSaleSummaryId(String saleSummaryId) {
        this.saleSummaryId = saleSummaryId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Double getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(Double discountApplied) {
        this.discountApplied = discountApplied;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getSalePerson() {
        return salePerson;
    }

    public void setSalePerson(String salePerson) {
        this.salePerson = salePerson;
    }

    public Double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Double getNhisVat() {
        return nhisVat;
    }

    public void setNhisVat(Double nhisVat) {
        this.nhisVat = nhisVat;
    }

    public String getUpdatedReason() {
        return updatedReason;
    }

    public void setUpdatedReason(String updatedReason) {
        this.updatedReason = updatedReason;
    }

    public String getDeletedReason() {
        return deletedReason;
    }

    public void setDeletedReason(String deletedReason) {
        this.deletedReason = deletedReason;
    }

    public Character getUpdated() {
        return updated;
    }

    public void setUpdated(Character updated) {
        this.updated = updated;
    }

    public Character getDeleted() {
        return deleted;
    }

    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saleSummaryId != null ? saleSummaryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleSummary)) {
            return false;
        }
        SaleSummary other = (SaleSummary) object;
        if ((this.saleSummaryId == null && other.saleSummaryId != null) || (this.saleSummaryId != null && !this.saleSummaryId.equals(other.saleSummaryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hysen.ejb.entities.SaleSummary[ saleSummaryId=" + saleSummaryId + " ]";
    }
    
}
