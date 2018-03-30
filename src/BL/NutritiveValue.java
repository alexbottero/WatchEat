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
public class NutritiveValue {
    /**
     * The name of the nutritive value
     */
    private String name;
    
    /**
     * The unité link to this nutritive value
     */
    private String unity;
    
    public NutritiveValue(String name, String unity){
        this.name = name;
        this.unity = unity;
    }
    
    public String getName(){
        return this.name;
    }

    public String getUnity() {
        return this.unity;
    }
}
