/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.spaceships;

import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.Gun;
import com.mygdx.game.model.Movement;
import com.mygdx.game.model.RhombusMovement;
import com.mygdx.game.model.StraightMovement;
import com.mygdx.game.view.SpriteRenderer;

/**
 *
 * @author Admin
 */
public class BossShip extends SpaceShip{    
    public BossShip(boolean fromLeftToRightDirection,float fireRate,float speed,float bulletSpeed, GameObject target){
        super(10000,1);
        
        transform().angle = fromLeftToRightDirection ? 180f : 0f ;
        transform().r = 70;
        
        this.setRenderer(new SpriteRenderer("boss.png",120,200));
        this.setComponent(Movement.class,new RhombusMovement(speed,200,100,550,200));        
        
        this.addGun(new Gun(this,target,0,25,fireRate/3,"ArcMovementBullet",bulletSpeed));
        this.addGun(new Gun(this,target,0,-25,fireRate/3,"ArcMovementBullet",bulletSpeed));
        this.addGun(new Gun(this,0,50,fireRate,"StraightFlyingBullet",bulletSpeed,fromLeftToRightDirection));
        this.addGun(new Gun(this,0,-50,fireRate,"StraightFlyingBullet",bulletSpeed,fromLeftToRightDirection));
    }
    
}
