/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.ArrayList;

/**
 *
 * @author fabaz
 */
public abstract class Consumable implements Comparable {
    
    /**
     * The name of the consumable
     */
    String name;
    
    public Consumable(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    @Override
    public int compareTo(Object c){
        return (this.name.compareTo(((Consumable)c).getName()));
    }
    
    /**
     * Return the nutritive values of the consumable
     * @return ArrayList NFQuantity
     */
    public abstract ArrayList<NFQuantity> getNutritiveValues();
}