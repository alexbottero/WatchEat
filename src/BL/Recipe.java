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

    private String description;
    private String instructions;
    private String time;
    private String peopleAmount;
    private String type;
    

    public Recipe(String name, String description, String instructions, String time, String peopleAmount, String type) {
        super(name);
        this.description = description;
        this.instructions = instructions;
        this.time = time;
        this.peopleAmount = peopleAmount;
        this.type = type;
    }
    
}
