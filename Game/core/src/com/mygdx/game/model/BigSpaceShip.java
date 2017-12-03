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
public class BigSpaceShip extends SpaceShip{
    
    public BigSpaceShip(int lifes,boolean fromLeftToRightDirection,float speed) {
        super(500,lifes);
        
        transform().angle = fromLeftToRightDirection ? 180f : 0f ;
        transform().r = 18;
        
        this.setRenderer(new SpriteRenderer("BigSpaceship.png"));
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
    }
}
