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
    
        public Menu(String name, String description, Date date, User user) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.creator=user;

    }
        
     /**
     * @return the name
     */
    public String getName() {
        return name;
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
        return description;
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
        return date;
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
        return creator;
    }

    
}
