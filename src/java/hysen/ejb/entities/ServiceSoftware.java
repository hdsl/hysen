/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Entity
@Table(name = "service_software")
@NamedQueries({
    @NamedQuery(name = "ServiceSoftware.findAll", query = "SELECT s FROM ServiceSoftware s")})
public class ServiceSoftware extends CommonEntity {

    @Column(name = "software_desc")
    private String softwareDesc;

    public String getSoftwareDesc() {
        return softwareDesc;
    }

    public void setSoftwareDesc(String softwareDesc) {
        this.softwareDesc = softwareDesc;
    }

}
