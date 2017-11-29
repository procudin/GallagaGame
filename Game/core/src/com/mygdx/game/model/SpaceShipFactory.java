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
public class SpaceShipFactory {
    
    public static SpaceShip getSpaceShip(String spaceshipType,boolean isFromLeftToRight,int lifes,float fireRate,float speed,float bulletSpeed){
        if (spaceshipType.equals("StraightShootingSpaceship")){
            return new StraightShootingSpaceship(lifes,isFromLeftToRight,fireRate,speed,bulletSpeed);
        }
        
        return null;
    }
}
