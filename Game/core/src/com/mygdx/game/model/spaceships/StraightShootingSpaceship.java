/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.spaceships;

import com.mygdx.game.model.Gun;
import com.mygdx.game.model.Movement;
import com.mygdx.game.model.StraightMovement;
import com.mygdx.game.model.Transform;
import com.mygdx.game.view.Renderer;
import com.mygdx.game.view.SpriteRenderer;

/**
 * Корабль стреляющий по прфямой
 * @author Admin
 */
public class StraightShootingSpaceship extends SpaceShip{
    /**
     * Конструктор
     * @param lifes
     * @param fromLeftToRightDirection
     * @param fireRate
     * @param speed
     * @param bulletSpeed 
     */
    public StraightShootingSpaceship(int lifes,boolean fromLeftToRightDirection,float fireRate,float speed,float bulletSpeed) {
        super(300, lifes);
        
        transform().angle = fromLeftToRightDirection ? 180f : 0f ;
        transform().r = 18;
        
        this.setRenderer(new SpriteRenderer("StraightShootingSpaceship.png"));        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.addGun(new Gun(this,fromLeftToRightDirection?20:-20,0,fireRate,"StraightFlyingBullet",bulletSpeed,fromLeftToRightDirection));
    }
    
    /**
     * Конструктор
     * @param initialpos
     * @param lifes
     * @param fromLeftToRightDirection
     * @param fireRate
     * @param speed
     * @param bulletSpeed 
     */
    public StraightShootingSpaceship(Transform initialpos,int lifes,boolean fromLeftToRightDirection,float fireRate,float speed,float bulletSpeed) {
        super(1000, lifes);
        
        this.setTransform(initialpos);
        
        transform().angle = fromLeftToRightDirection ? 180f : 0f ;
        transform().r = 18;
        
        this.setRenderer(new SpriteRenderer("StraightShootingSpaceship.png"));
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.addGun(new Gun(this,fromLeftToRightDirection?20:-20,0,fireRate,"StraightFlyingBullet",bulletSpeed,fromLeftToRightDirection));
    }
    
    /**
     * Конструктор
     * @param fromLeftToRightDirection
     * @param fireRate
     * @param speed
     * @param bulletSpeed 
     */
    public StraightShootingSpaceship(boolean fromLeftToRightDirection,float fireRate,float speed,float bulletSpeed) {
        this(1,fromLeftToRightDirection,fireRate,speed,bulletSpeed);
    }
}
