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
    private User creator;
    

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
        this.ingredients = new ArrayList<>();
    }

    public Recipe(String name, String description, String instructions, int time, int peopleAmount, String type, User user) {
 super(name);
        this.description = description;
        this.instructions = instructions;
        this.time = time;
        this.peopleAmount = peopleAmount;
        this.type = type;
        this.creator = user;
        this.ingredients = new ArrayList<>();    
    }
    public Recipe(String name){
        super(name);
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

    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public User getCreator() {
        return this.creator; 
    }
    
    @Override
    public ArrayList<NFQuantity> getNutritiveValues(){
        //Récupération des valeurs nutritives en vrac
        ArrayList<NFQuantity> nutritiveValues = new ArrayList<>();
        int totalQuantity = 0;
        for(Ingredient ingredient : this.getIngredients()){
            totalQuantity += ingredient.getQuantity();
            Consumable consumable = ingredient.getConsumable();
            ArrayList<NFQuantity> ingredientNutritiveValues;
            
            if(consumable instanceof Food){
                Food food = ((Food)consumable);
                ingredientNutritiveValues = food.getNutritiveValues();
            }
            else{
                Recipe recipe = ((Recipe)consumable);
                ingredientNutritiveValues = recipe.getNutritiveValues();
            }
            //Les multiplier par rapport aux quantitées
            for(NFQuantity nfQuantity : ingredientNutritiveValues){
                nfQuantity.setQuantity(nfQuantity.getQuantity()* ingredient.getQuantity()/100);
            }
            nutritiveValues.addAll(ingredientNutritiveValues);
        }
        
        //On rassemble valeurs nutritives entre elles
        ArrayList<NFQuantity> sortedNutritiveValues = new ArrayList<>();
        for(int i = 0; i < nutritiveValues.size(); i++){
            NFQuantity nfQuantity = nutritiveValues.get(i);
            boolean sorted = false;
            for(int j = 0; j < sortedNutritiveValues.size(); j++){
                NFQuantity sortedNFQuantity = sortedNutritiveValues.get(j);
                if(nfQuantity.getNutritiveValue().getName().equals(sortedNFQuantity.getNutritiveValue().getName())){
                    sortedNFQuantity.addQuantity(nfQuantity.getQuantity());
                    sorted = true;
                }
            }
            if(!sorted){
                sortedNutritiveValues.add(new NFQuantity(nfQuantity.getNutritiveValue(),nfQuantity.getQuantity()));
            }
            
        }
        
        //On divise tout par la quantité
        for(NFQuantity nfQuantity : sortedNutritiveValues){
            System.out.println(nfQuantity.getNutritiveValue().getName() + " " + nfQuantity.getQuantity());

            nfQuantity.setQuantity(100*nfQuantity.getQuantity()/totalQuantity);
        }
        return sortedNutritiveValues;
    }
}