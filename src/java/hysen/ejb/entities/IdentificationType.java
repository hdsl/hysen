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
@Table(name = "identification_type")
@NamedQueries({
    @NamedQuery(name = "IdentificationType.findAll", query = "SELECT i FROM IdentificationType i")})
public class IdentificationType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "card_code")
    private String cardCode;
    @Size(max = 56)
    @Column(name = "card_desc")
    private String cardDesc;

    public IdentificationType() {
    }

    public IdentificationType(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardCode != null ? cardCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdentificationType)) {
            return false;
        }
        IdentificationType other = (IdentificationType) object;
        if ((this.cardCode == null && other.cardCode != null) || (this.cardCode != null && !this.cardCode.equals(other.cardCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hysen.ejb.entities.IdentificationType[ cardCode=" + cardCode + " ]";
    }
    
}
