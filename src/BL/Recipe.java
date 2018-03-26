/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabaz
 */
public class Recipe extends Consumable{

    private String description;
    private String instructions;
    private int time;
    private int peopleAmount;
    private String type;
    private ArrayList<Ingredient> ingredients;
    

    public Recipe(String name, String description, String instructions, int time, int peopleAmount, String type, ArrayList<Ingredient> ingredients) {
        super(name);
        this.description = description;
        this.instructions = instructions;
        this.time = time;
        this.peopleAmount = peopleAmount;
        this.type = type;
        this.ingredients = ingredients;
    }
    
    public Recipe(String name, String description, String instructions, int time, int peopleAmount, String type) {
        super(name);
        this.description = description;
        this.instructions = instructions;
        this.time = time;
        this.peopleAmount = peopleAmount;
        this.type = type;
        this.ingredients = null;
    }
    

    @Override
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public int getTimeRecipe() {
       return this.time; 
    }

    public int getPeopleAmount() {
        return this.peopleAmount;
    }

    public Iterable<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public String getType() {
        return this.type;
    }
}
