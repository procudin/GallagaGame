/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.bullets;

import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.Transform;
import com.mygdx.game.model.bullets.Bullet;
import com.mygdx.game.model.bullets.StraightFlyingBullet;
import com.mygdx.game.model.spaceships.SpaceShip;

/**
 * Фабрика пуль
 * @author Admin
 */
public class BulletFactory {  
    /**
     * Получить пулю
     * @param initialPos
     * @param sender
     * @param target
     * @param bulletType
     * @param bulletSpeed
     * @param isFromLeftToRight
     * @return 
     */
    public static Bullet getBullet(Transform initialPos,GameObject sender,GameObject target,String bulletType,float bulletSpeed,boolean isFromLeftToRight){
        if (bulletType.equals("StraightFlyingBullet")){
            return new StraightFlyingBullet(sender,120,bulletSpeed,initialPos,isFromLeftToRight);
        }       
        
        if (bulletType.equals("PlayerBullet")){
            return new PlayerBullet(sender,120,bulletSpeed,isFromLeftToRight,initialPos);
        }       
        
        if (bulletType.equals("ArcMovementBullet")){
            return new ArcMovementBullet(sender,target,100,bulletSpeed,isFromLeftToRight,initialPos);            
        }
        
        return null;
    }
}
