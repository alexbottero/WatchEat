/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.List;

/**
 *
 * @author fabaz
 */
public class Recipe extends Consumable{

    private String description;
    private String instructions;
    private String time;
    private String peopleAmount;
    private String type;
    private List<Ingredient> ingredients;
    

    public Recipe(String name, String description, String instructions, String time, String peopleAmount, String type, List<Ingredient> ingredients) {
        super(name);
        this.description = description;
        this.instructions = instructions;
        this.time = time;
        this.peopleAmount = peopleAmount;
        this.type = type;
        this.ingredients = ingredients;
    }
    
    public Recipe(String name, String description, String instructions, String time, String peopleAmount, String type) {
        super(name);
        this.description = description;
        this.instructions = instructions;
        this.time = time;
        this.peopleAmount = peopleAmount;
        this.type = type;
        this.ingredients = null;
    }
    
    public void setIngredients(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }
}
