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
 * Баф увеличения скорострельности
 * @author Admin
 */
public class FireRateIncrease extends Buff{
    
    private float fireRateMultiplier;               /// Множитель
    private float duration;                         /// Длительность
    private float time = 0f;                        /// Относительное время
    
    /**
     * Конструктор
     * @param target
     * @param fireRateMultiplier
     * @param duration 
     */
    public FireRateIncrease(SpaceShip target,float fireRateMultiplier,float duration) {
        super(target);
        
        this.fireRateMultiplier = fireRateMultiplier;
        this.duration = duration;
        
        this.setRenderer(new SpriteRenderer("bullets1.png",19,30));
        this.setComponent("Movement",new StraightMovement(100,false));
    }
    
    /**
     * Конструктор
     * @param target
     * @param t
     * @param fireRateMultiplier
     * @param duration 
     */
    public FireRateIncrease(SpaceShip target,Transform t,float fireRateMultiplier,float duration) {
        super(target);
        
        this.fireRateMultiplier = fireRateMultiplier;
        this.duration = duration;
        
        setTransform(t);
        
        this.setRenderer(new SpriteRenderer("bullets1.png",19,30));
        this.setComponent("Movement",new StraightMovement(100,false));
    }
    
    
    private boolean noCollision = true;         /// Был ли захвачен баф
    
    /**
     * Обновление
     * @param delta 
     */
    @Override
    public void update(float delta){  
        super.update(delta);
        
        // если объекты еще не пересекались и пересеклись
        if (noCollision && transform().isCollision(target.transform())){
           noCollision = false;
           
           // отключаем отрисовку
           this.renderer().isActive = false;
           
           // применяем бафф
           ArrayList<Gun> guns = target.guns();
           for (Gun gun : guns){
               gun.fireRate/=fireRateMultiplier;
           }
           
           // удаляем компоненту движения
           this.removeComponent("Movement");
           
           time = 0;
        }
        
        // проверка таймаута
        if (time > duration){
           // отменяем баф
           ArrayList<Gun> guns = target.guns();
           for (Gun gun : guns){
               gun.fireRate*=fireRateMultiplier;
           }
           
           this.dispose();
           return;
        }
        
        time+=delta;
    }
}
