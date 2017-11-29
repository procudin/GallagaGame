/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.mygdx.game.view.Renderer;
import com.mygdx.game.view.SpriteRenderer;

/**
 *
 * @author Admin
 */
public class StraightShootingSpaceship extends SpaceShip{
    
    public StraightShootingSpaceship(int lifes,boolean fromLeftToRightDirection,float fireRate,float speed,float bulletSpeed) {
        super(1000, lifes);
        
        transform().angle = fromLeftToRightDirection ? 180f : 0f ;
        
        this.setRenderer(new SpriteRenderer("StraightShootingSpaceship.png"));        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.setComponent(Gun.class,new Gun(this,fromLeftToRightDirection?20:-20,0,fireRate,"StraightFlyingBullet",bulletSpeed,fromLeftToRightDirection));
    }
    
    public StraightShootingSpaceship(Transform initialpos,int lifes,boolean fromLeftToRightDirection,float fireRate,float speed,float bulletSpeed) {
        super(1000, lifes);
        
        this.setTransform(initialpos);
        
        transform().angle = fromLeftToRightDirection ? 180f : 0f ;
        
        this.setRenderer(new SpriteRenderer("StraightShootingSpaceship.png"));
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.setComponent(Gun.class,new Gun(this,fromLeftToRightDirection?20:-20,0,fireRate,"StraightFlyingBullet",bulletSpeed,fromLeftToRightDirection));
    }
    
    public StraightShootingSpaceship(boolean fromLeftToRightDirection,float fireRate,float speed,float bulletSpeed) {
        this(1,fromLeftToRightDirection,fireRate,speed,bulletSpeed);
    }
}
