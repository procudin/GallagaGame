/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.mygdx.game.model.GameObject;

/**
 *
 * @author Admin
 */
public abstract class Component {
    
    //protected GameObject _parent; public GameObject parent(){return _parent;}
    protected GameObject _object; public GameObject object(){ return _object;}
    
    public void update(float delta){
        
    }
    
    public  void dispose(){ 
        _object.removeComponent(this);
    }    
}
