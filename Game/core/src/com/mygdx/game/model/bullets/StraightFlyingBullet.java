/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.bullets;

import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.Movement;
import com.mygdx.game.model.StraightMovement;
import com.mygdx.game.model.Transform;
import com.mygdx.game.model.spaceships.SpaceShip;
import com.mygdx.game.view.SpriteRenderer;

/**
 * Прямо летящая пуля
 * @author Admin
 */
public class StraightFlyingBullet extends Bullet {
    /**
     * Конструктор
     * @param sender
     * @param damage
     * @param initialpos
     * @param fromLeftToRightDirection 
     */
    public StraightFlyingBullet(GameObject sender,float damage,Transform initialpos,boolean fromLeftToRightDirection){
        super(damage,sender);
        
        this.setTransform(initialpos);
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        this.transform().r = 15;
        
        this.setComponent(Movement.class,new StraightMovement(fromLeftToRightDirection));
        this.setRenderer(new SpriteRenderer("StraightFlyingEnemieBullet.png"));
    }
    
    /**
     * КОнструктор
     * @param sender
     * @param damage
     * @param speed
     * @param initialpos
     * @param fromLeftToRightDirection 
     */
    public StraightFlyingBullet(GameObject sender,float damage,float speed,Transform initialpos,boolean fromLeftToRightDirection){
        super(damage,sender);
        
        this.setTransform(initialpos);
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        this.transform().r = 5;
        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.setRenderer(new SpriteRenderer("StraightFlyingEnemieBullet.png"));
        
    }
    
    /**
     * Конструктор
     * @param sender
     * @param damage
     * @param speed
     * @param fromLeftToRightDirection 
     */
    public StraightFlyingBullet(GameObject sender,float damage,float speed,boolean fromLeftToRightDirection){
        super(damage,sender);        
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        this.transform().r = 5;
        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.setRenderer(new SpriteRenderer("StraightFlyingEnemieBullet.png"));
    }
}
