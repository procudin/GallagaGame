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
 *
 * @author Admin
 */
public class FireRateIncrease extends Buff{
    
    private float fireRateMultiplier;
    private float duration;
    private float time = 0f;
    
    public FireRateIncrease(SpaceShip target,float fireRateMultiplier,float duration) {
        super(target);
        
        this.fireRateMultiplier = fireRateMultiplier;
        this.duration = duration;
        
        this.setRenderer(new SpriteRenderer("bullets1.png",19,30));
        this.setComponent(Movement.class,new StraightMovement(100,false));
    }
    
    public FireRateIncrease(SpaceShip target,Transform t,float fireRateMultiplier,float duration) {
        super(target);
        
        this.fireRateMultiplier = fireRateMultiplier;
        this.duration = duration;
        
        setTransform(t);
        
        this.setRenderer(new SpriteRenderer("bullets1.png",19,30));
        this.setComponent(Movement.class,new StraightMovement(100,false));
    }
    
    
    private boolean noCollision = true;
    
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
           this.removeComponent(Movement.class);
           
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
