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
public class Ingredient {
    /**
     * The consumable link to this ingredient
     */
    private Consumable consumable;
    
    /**
     * The quantity of the consumable link to this ingredient
     */
    private int quantity;
    
    public Ingredient(Consumable consumable, int quantity){
        this.consumable = consumable;
        this.quantity = quantity;
    }

    public Consumable getConsumable() {
        return this.consumable;
    }

    public int getQuantity() {
        return this.quantity;
    }
}