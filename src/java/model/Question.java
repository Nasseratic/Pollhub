/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author y
 */
public class Question {
    
    public int questionid;
    public String content;
    public String type;
    public int poll;
    public String answer;
    public LinkedList<Answer> answers;
    
    
}

