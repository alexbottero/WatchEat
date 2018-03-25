/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.fxml.Initializable;

/**
 *
 * @author fabaz
 */
public abstract class AbstractUIController implements Initializable {
    private Object givenData;
    
    public void setGivenData(Object givenData){
        this.givenData = givenData;
    }
}
