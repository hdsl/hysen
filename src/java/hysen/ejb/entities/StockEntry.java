/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.ejb.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author AbdulMumin
 */
@Entity
@Table(name = "stock_entry")
@NamedQueries({
    @NamedQuery(name = "StockEntry.findAll", query = "SELECT s FROM StockEntry s")})
public class StockEntry extends CommonEntity {
   
    @Column(name = "stock_item")
    private String stockItem;
    
    @Column(name = "supplier")
    private String supplier;
    
    @Column(name = "item_quantity")
    private Integer itemQuantity;
   
    @Column(name = "unit_cost_price")
    private Double unitCostPrice;
    
    @Column(name = "date_of_entry")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfEntry;
    
    @Column(name = "activity_type")
    private String activityType;
    
    @Column(name = "date_received")
    @Temporal(TemporalType.DATE)
    private Date dateReceived;
    
    @Column(name = "type_of_entry")
    private String typeOfEntry;
    
    @Column(name = "transaction_detail")
    private String transactionDetail;
  
    public StockEntry() {
    }

    public String getStockItem() {
        return stockItem;
    }

    public void setStockItem(String stockItem) {
        this.stockItem = stockItem;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Double getUnitCostPrice() {
        return unitCostPrice;
    }

    public void setUnitCostPrice(Double unitCostPrice) {
        this.unitCostPrice = unitCostPrice;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getTypeOfEntry() {
        return typeOfEntry;
    }

    public void setTypeOfEntry(String typeOfEntry) {
        this.typeOfEntry = typeOfEntry;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

}
