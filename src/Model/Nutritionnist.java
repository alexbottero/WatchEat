/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Consumable;
import BL.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author julia
 */
public class Nutritionnist extends User {
    private String nutriDescription;
    public List <Menu> menus;
    
     public Nutritionnist() {
        super();
    }
    
        public Nutritionnist(String mail,String password) {
        super(mail, password);
    }
     
    public Nutritionnist(String mail,String password, String firstName, String lastName, Double height, Double weight, String nutriDescription) {
        super(mail, password, firstName, lastName, height, weight);
        this.nutriDescription=nutriDescription;
    }
    
    public void setNutriDesc(String nutriDesc){
        this.nutriDescription=nutriDesc;
    }
    
     

    /**
     * @param nom  
     * @param desc 
     * @param consomables
     */
    public void addMenu(String nom , String desc, List<Consumable> consomables) {
        // TODO implement here
    }

    /**
     * 
     */
    public void delMenu(Menu menu) {
        // TODO implement here
    }

    /**
     * @param nom  
     * @param desc 
     * @param consomables
     */
    public void UpdateMenu(String nom , String desc, List<Consumable> consomables, Menu menu) {
        // TODO implement here
    }

    /**
     * @param menus
     */
    public void setMenus(List<Menu> menus) {
        // TODO implement here
    }

}
