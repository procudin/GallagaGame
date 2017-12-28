/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.spaceships;

import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.Gun;
import com.mygdx.game.model.ManualMoviement;
import com.mygdx.game.model.Movement;
import com.mygdx.game.model.StraightMovement;
import com.mygdx.game.view.SpriteRenderer;

/**
 * Корабль стреляющий по дуге
 * @author Admin
 */
public class ArcShootingSpaceShip extends SpaceShip{
    /**
     * Конструктор
     * @param lifes
     * @param speed
     * @param fireRate
     * @param bulletSpeed
     * @param fromLeftToRightDirection
     * @param target 
     */
    public ArcShootingSpaceShip(int lifes,float speed,float fireRate,float bulletSpeed,boolean fromLeftToRightDirection, GameObject target) {
        super(150, lifes);
        
        transform().angle = fromLeftToRightDirection ? 180f : 0f ;
        transform().r = 13;
        
        this.setRenderer(new SpriteRenderer("ArcShootingSpaceship.png",60,40));        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));        
        this.addGun(new Gun(this,target,fromLeftToRightDirection?20:-20,0,fireRate,"ArcMovementBullet",bulletSpeed));
    }
}
