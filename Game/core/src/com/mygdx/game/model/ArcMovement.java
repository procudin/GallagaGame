/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

/**
 * Класс движения по дуге
 * @author Admin
 */
public class ArcMovement extends Movement{
    /**
     * Конструктор
     * @param speed 
     */
    public ArcMovement(float speed){
        super(speed);        
    }    
    
    private int targetX;    /// целевая позиция X
    private int targetY;    /// целевая позиция Y
    private float dx;       /// Прирост X
    private float dy;       /// Прирост Y
    private float dx_diag;  /// Прирост Х к диагонали
    private float dy_diag;  /// Прирост Y к диагонали
    
    /**
     * Задать цель
     * @param x
     * @param y 
     */
    public void setTarget(int x, int y){
        this.targetX = x;
        this.targetY = y;
        
        dx = targetX - _object.transform().X;
        dy = targetY - _object.transform().Y;
        
        float diag = (float)Math.sqrt(dx * dx + dy*dy);
        dx_diag = dx / diag;
        dy_diag = dy / diag;
        
        if (dx > 0) 
            _object.dispose();
    }
    
    /**
     * Обновление
     * @param delta 
     */
    @Override
    public void update(float delta){        
        _object.transform().X += speed * delta * dx_diag;
        _object.transform().Y += speed * delta * dy_diag;
    }
    
}
