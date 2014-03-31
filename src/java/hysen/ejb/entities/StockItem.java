/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author AbdulMumin
 */
@Entity
@Table(name = "stock_item")
@NamedQueries({
    @NamedQuery(name = "StockItem.findAll", query = "SELECT s FROM StockItem s")})
public class StockItem extends CommonEntity {
   private static final long serialVersionUID = 1L;
   
    @Column(name = "stock_item_code")
    private String stockItemCode;
    
    @Column(name = "item_description")
    private String itemDescription;
    
    @JoinColumn(name = "stock_category")
    private StockCategory stockCategory;
    
    
    @Column(name = "unit_selling_price")
    private Double unitSellingPrice;
    
    @Column(name = "item_quantity")
    private Integer itemQuantity;

    public StockItem() {
    }
    
    public String getStockItemCode() {
        return stockItemCode;
    }

    public void setStockItemCode(String stockItemCode) {
        this.stockItemCode = stockItemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Double getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public void setUnitSellingPrice(Double unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }

    public StockCategory getStockCategory() {
        return stockCategory;
    }

    public void setStockCategory(StockCategory stockCategory) {
        this.stockCategory = stockCategory;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (super.getId() != null ? super.getId().hashCode() : 0);
//        return hash;
//    }

}
