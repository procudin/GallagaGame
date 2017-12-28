/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.spaceships;

import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.spaceships.StraightShootingSpaceship;

/**
 * ФАбрика космических кораблей
 * @author Admin
 */
public class SpaceShipFactory {
    
    /**
     * Получить космический корабль
     * @param spaceshipType
     * @param isFromLeftToRight
     * @param lifes
     * @param fireRate
     * @param speed
     * @param bulletSpeed
     * @param target
     * @return 
     */
    public static SpaceShip getSpaceShip(String spaceshipType,boolean isFromLeftToRight,int lifes,float fireRate,float speed,float bulletSpeed,GameObject target){
        if (spaceshipType.equals("StraightShootingSpaceship")){
            return new StraightShootingSpaceship(lifes,isFromLeftToRight,fireRate,speed,bulletSpeed);
        }
        
        if (spaceshipType.equals("BigSpaceShip")){
            return new BigSpaceShip(lifes,isFromLeftToRight,speed);
        }
        
        if (spaceshipType.equals("ArcShootingSpaceShip")){
            return new ArcShootingSpaceShip(lifes,speed,fireRate,bulletSpeed,isFromLeftToRight,target);
        }
        
        return null;
    }
}
