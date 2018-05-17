/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.buffs;

import com.mygdx.game.model.Gun;
import com.mygdx.game.model.Movement;
import com.mygdx.game.model.StraightMovement;
import com.mygdx.game.model.Transform;
import com.mygdx.game.model.spaceships.SpaceShip;
import com.mygdx.game.view.SpriteRenderer;
import java.util.ArrayList;

/**
 * Баф бессмертия
 * @author Admin
 */
public class UnlimitedHealth extends Buff{
    
    private float duration;     /// Длительность
    private float time = 0f;    /// Локальное время   
    
    /**
     * КОнструктор
     * @param target
     * @param duration 
     */
    public UnlimitedHealth(SpaceShip target, float duration) {
        super(target);
        
        this.duration = duration;
        this.transform().r = 5;
        
        this.setRenderer(new SpriteRenderer("UnlimitedHealth.png",19,30));
        this.setComponent("Movement",new StraightMovement(100,false));
    }
    
    /**
     * Конструктор
     * @param target
     * @param duration
     * @param t 
     */
    public UnlimitedHealth(SpaceShip target, float duration,Transform t) {
        super(target);
        
        setTransform(t);
        
        this.duration = duration;
        this.transform().r = 5;
        
        this.setRenderer(new SpriteRenderer("UnlimitedHealth.png",19,30));
        this.setComponent("Movement",new StraightMovement(100,false));
    }
    
    private boolean noCollision = true;         /// Был ли захвачен целью
    
    private float oldHealth=0;                  /// Старое значение здоровья
    
    /**
     * Обновление
     * @param delta 
     */
    @Override
    public void update(float delta){
        super.update(delta);  
        
        if (target.health()>9999999) {
            // если баф был использован
            this.dispose();
            return;
        }
        
        if (noCollision && transform().isCollision(target.transform())){
           noCollision = false;
           
           this.renderer().isActive = false;
           // удаляем компоненту движения
           this.removeComponent("Movement");
           
           oldHealth = target.health();
           
           target.setHealth(Float.MAX_VALUE);
           
           time = 0;
        }
        
        // проверка таймаута
        if (!noCollision && time > duration){
           // отменяем баф
           target.setHealth(oldHealth);                      
           this.dispose();
           return;
        }
        
        time+=delta;
    }
}
