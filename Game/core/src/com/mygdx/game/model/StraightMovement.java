/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

/**
 * Класс прямолинейного движения
 * @author Admin
 */
public class StraightMovement extends Movement{    
    /**
     * Конструктор
     * @param speed
     * @param fromLeftToRight 
     */
    public StraightMovement(float speed,boolean fromLeftToRight){
        super(speed);
        this.fromLeftToRight=fromLeftToRight;
    }
    
    /**
     * Конструктор
     * @param fromLeftToRight 
     */
    public StraightMovement(boolean fromLeftToRight){
        super();
        this.fromLeftToRight=fromLeftToRight;
    }
    
    private final boolean fromLeftToRight;      /// Направление движения
    
    /**
     * Обновление
     * @param delta 
     */
    @Override
    public void update(float delta){        
        if (fromLeftToRight)
            _object.transform().X+= speed * delta; 
        else
            _object.transform().X-= speed * delta; 
    }
}
