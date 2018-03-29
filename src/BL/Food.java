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
public class Food extends Consumable{
    
    private ArrayList<NFQuantity> nutritiveValues;
    
    public Food(String name, ArrayList<NFQuantity> nutritiveValues) {
        super(name);
        this.nutritiveValues = nutritiveValues;
    }
    
    public Food(String name) {
        super(name);
        this.nutritiveValues = new ArrayList<>();
    }

    @Override
    public void setIngredients(ArrayList<Ingredient> ingredients) {
    }

    @Override
    public ArrayList<NFQuantity> getNutritiveValues() {
        return this.nutritiveValues;
    }
    
}
