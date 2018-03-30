/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

/**
 *
 * @author fabaz
 */
public interface UIController {
    
    /**
     * Data receive from the previous controller, works when it receive it
     * @param givenData Object
     */
    public void receiveData(Object givenData);
}
