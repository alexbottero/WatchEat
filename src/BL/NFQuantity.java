/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

/**
 *
 * @author fabaz
 */
public class NFQuantity {
    private NutritiveValue nutritiveValue;
    private int quantity;
    
    public NFQuantity(NutritiveValue nutritiveValue, int quantity){
        this.nutritiveValue = nutritiveValue;
        this.quantity = quantity;
    }

    public NutritiveValue getNutritiveValue() {
        return nutritiveValue;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }
}