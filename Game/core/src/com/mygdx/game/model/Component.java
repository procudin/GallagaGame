/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.mygdx.game.model.GameObject;

/**
 * Класс компонента
 * @author Admin
 */
public abstract class Component {
    /**
     * Объект, которому принадлежит компонент
     */
    protected GameObject _object; public GameObject object(){ return _object;}
    
    /**
     * Обновление компонента
     * @param delta 
     */
    public void update(float delta){
        
    }
    
    /**
     * Удаление компонента
     */
    public  void dispose(){ 
        _object.removeComponent(this);
    }    
}
