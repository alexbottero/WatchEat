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
public abstract class Consumable implements Comparable {
    String name;
    Food food;
    Recipe recipe;
    
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
}
