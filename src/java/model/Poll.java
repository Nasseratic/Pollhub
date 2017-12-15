/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author y
 */
public class Poll {
    
    public String title;
    public int pollid;
    public int user;
    public boolean aissuspended;
    public boolean uissuspended;
    public boolean close;
    public ArrayList<Question> questions ;
    
}
