/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.ResultSet;

/**
 *
 * @author fabaz
 */
public abstract class DBConnection {
    
    public abstract ResultSet select(String reqSQL);
    
    public abstract void update(String reqSQL);    
}
