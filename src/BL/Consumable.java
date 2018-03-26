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
public class Consumable {
    String name;
    Food food;
    Recipe recipe;
    
    public Consumable(String name, Food food, Recipe recipe){
        this.name = name;
        this.food = food;
        this.recipe = recipe;
    }

    public String getName() {
        return this.name;
    }
}
