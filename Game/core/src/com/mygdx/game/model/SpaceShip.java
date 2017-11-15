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
public abstract class SpaceShip extends GameObject{        
    public SpaceShip(float health,int lifes){
        super();  
        
        _lifes = lifes;
        _health = health;   
    }
    
    public SpaceShip(float health){
        this(health,1);  
    }
    
    private final float _health;    public float health(){ return _health;}
    private final int _lifes;       public int lifes(){ return _lifes;}
}
