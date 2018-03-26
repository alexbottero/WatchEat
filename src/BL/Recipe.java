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
public class Recipe extends Consumable{

    private final String description;
    private final String instructions;
    private final String time;
    private final String peopleAmount;
    

    public Recipe(String name, String description, String instructions, String time, String peopleAmount) {
        super(name);
        this.description = description;
        this.instructions = instructions;
        this.time = time;
        this.peopleAmount = peopleAmount;
    }
    
}
