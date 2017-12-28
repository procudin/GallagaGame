/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

/**
 * Класс движения
 * @author Admin
 */
public abstract class Movement extends Component{    
    /**
     * Конструктор
     */
    public Movement(){
        this(2f);
    }
    
    /**
     * Конструктор
     * @param speed 
     */
    public Movement(float speed){
        this.speed = speed;
    }
    
    public float speed;    /// Скорость
}
