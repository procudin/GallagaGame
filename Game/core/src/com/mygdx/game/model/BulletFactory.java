/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

/**
 *
 * @author Admin
 */
public class BulletFactory {  
    
    public static Bullet getBullet(SpaceShip sender,String bulletType,float bulletSpeed,boolean isFromLeftToRight){
        if (bulletType.equals("StraightFlyingBullet")){
            return new StraightFlyingBullet(sender,100,bulletSpeed,isFromLeftToRight);
        }       
        
        if (bulletType.equals("PlayerBullet")){
            return new PlayerBullet(sender,100,bulletSpeed,isFromLeftToRight);
        }       
        
        return null;
    }
}
