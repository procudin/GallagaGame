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
     * ************************Модель уровня******************************
     */
    protected static ILevelModel levelModel;
    public static void setLevelModel(ILevelModel model) {
        levelModel = model;
    }
    
    public Component() {
        _componentID = getClass().getSimpleName();
        _object = null;
        load();
    }
    
    /**
     * Объект, которому принадлежит компонент
     */
    protected GameObject _object; public GameObject object(){ return _object;}
    
    /**
     * Идентификатор компонента
     */
    protected String _componentID; public String componentID(){ return _componentID;}
    
    /**
     * Загрузка компонента
     */
    public void load(){
        
    }
    
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
