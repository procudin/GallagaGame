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
public class StraightShootingSpaceship extends SpaceShip{
    public StraightShootingSpaceship() {
        super(1000, 1);
        
        transform().X = -10 ;
        transform().Y = 40 ;
        transform().angle = 180f;
        
        this.setRenderer(new SpriteRenderer("StraightShootingSpaceship.png"));
        this.setComponent(new ManualMoviement());
    }
}
