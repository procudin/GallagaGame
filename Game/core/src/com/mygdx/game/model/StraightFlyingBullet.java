/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.mygdx.game.view.SpriteRenderer;

/**
 *
 * @author Admin
 */
public class StraightFlyingBullet extends Bullet {
    
    public StraightFlyingBullet(float damage,Transform initialpos,boolean fromLeftToRightDirection){
        super(damage);
        
        this.setTransform(initialpos);
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        
        this.setComponent(Movement.class,new StraightMovement(fromLeftToRightDirection));
        this.setRenderer(new SpriteRenderer("StraightFlyingEnemieBullet.png"));
    }
    
    public StraightFlyingBullet(float damage,float speed,Transform initialpos,boolean fromLeftToRightDirection){
        super(damage);
        
        this.setTransform(initialpos);
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.setRenderer(new SpriteRenderer("StraightFlyingEnemieBullet.png"));
        
    }
    
    public StraightFlyingBullet(float damage,float speed,boolean fromLeftToRightDirection){
        super(damage);        
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.setRenderer(new SpriteRenderer("StraightFlyingEnemieBullet.png"));
    }
}
