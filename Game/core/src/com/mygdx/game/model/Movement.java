/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

/**
 *
 * @author Admin
 */
public abstract class Movement extends Component{    
    
    public Movement(){
        this(2f);
    }
    
    public Movement(float speed){
        this.speed = speed;
    }
    
    public float speed;    
}
