/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.buffs;

import com.mygdx.game.model.*;
import com.mygdx.game.model.spaceships.SpaceShip;
import com.mygdx.game.view.SpriteRenderer;

/**
 * Баф увеличения количества жизней
 * @author Admin
 */
public class LifeIncrease extends Buff{
    /**
     * Конструктор
     * @param target 
     */
    public LifeIncrease(SpaceShip target) {
        super(target);
        
        this.setRenderer(new SpriteRenderer("LifeIncrease.png"));
        this.setComponent("Movement",new StraightMovement(100,false));
    }
    
    /**
     * Конструктор
     * @param target
     * @param t 
     */
    public LifeIncrease(SpaceShip target,Transform t) {
        super(target);
        
        setTransform(t);
        
        this.setRenderer(new SpriteRenderer("LifeIncrease.png"));
        this.setComponent("Movement",new StraightMovement(100,false));
    }
    
    /**
     * Обновление
     * @param delta 
     */
    @Override
    public void update(float delta){
        super.update(delta);
       
        if (transform().isCollision(target.transform())){
            target.setLifes(target.lifes() + 1);
            target.setHealth(target.maxHealth());
            dispose();
        }
    }
}
