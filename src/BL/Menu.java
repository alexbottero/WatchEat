
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Polytech
 */
public class Menu {

    private String name;
    private String description;
    private Date date;
    private User creator;
    private ArrayList <Consumable> consumableList= new ArrayList <Consumable>();
    
    public Menu(String name, String description, Date date, User user, ArrayList <Consumable> cons) {
    this.name = name;
    this.description = description;
    this.date = date;
    this.creator=user;
    this.consumableList = cons;
    }
    
    public Menu(String name, String description, Date date) {
    this.name = name;
    this.description = description;
    this.date = date;

    }
    
    public Menu(String name, String description, Date date, User creator) {
    this.name = name;
    this.description = description;
    this.date = date;
    this.creator = creator;

    }
        
     /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the creator
     */
    public User getCreator() {
        return this.creator;
    }
    
    public void setConsumableList (ArrayList <Consumable> consumableList){
        this.consumableList = consumableList;
    }
    public ArrayList <Consumable> getConsumableList(){
        return this.consumableList;
    }
    
    public void addConsumable ( Consumable c){
        this.consumableList.add(c);
    }

    
}