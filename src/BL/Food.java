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
    
    public Food(String name) {
        super(name);
    }

    @Override
    public void setIngredients(ArrayList<Ingredient> ingredients) {
    }
    
}
