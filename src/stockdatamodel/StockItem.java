/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockdatamodel;
import java.util.*;
import utilities.*;
/**
 *
 * @author dansc
 */
public abstract class StockItem implements ISubject {

    protected String name = "UNKNOWN";
    protected Integer quantityInStock = 0;
    protected Double sellingPrice = 1000000.00;
    protected Double costPrice = 10000000.00;
    private ArrayList<IObserver> observers = null;
    
    public StockItem(){
    }
    
    public StockItem(String name){
        this.name = name;
    }
    
    public StockItem(String name, Integer qty) {
        this.name = name;
        this.quantityInStock = qty;
    }
    
    public abstract StockType getItemType();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
            notifyObservers();
        }
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        if (quantityInStock != null & quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
            notifyObservers();
        }
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        if (costPrice == 10000000.00) this.costPrice = sellingPrice;
        if (sellingPrice != null && sellingPrice >= costPrice && sellingPrice >= 0){
            this.sellingPrice = sellingPrice;
            notifyObservers();
        }
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        if (costPrice != null && costPrice >= 0) {
            this.costPrice = costPrice;
            notifyObservers();
        }
    }
    
    public Boolean isInStock() {
        if(this.quantityInStock > 0)
            return true;
        else 
            return false;
    }
    
    @Override
    public Boolean registerObserver(IObserver o) {
        if (o != null) {
            if (this.observers == null) {
                this.observers = new ArrayList<>();
            }
        }
        return this.observers.add(o);
    }
    
    @Override 
    public Boolean removeObserver(IObserver o) {
        if (o != null) {
            if (this.observers != null && this.observers.contains(o))
                return observers.remove(o);
        }
        return false;
    }
    
    @Override 
    public void notifyObservers() {
        if (this.observers != null && this.observers.size() > 0) {
            //foreach loop
            for (IObserver currentObserver : this.observers) {
                currentObserver.update();
            }
        }
    }
}
