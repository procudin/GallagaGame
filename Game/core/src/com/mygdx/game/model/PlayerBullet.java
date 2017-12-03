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
public class PlayerBullet extends Bullet{
    public PlayerBullet(SpaceShip sender,float damage,float speed,boolean fromLeftToRightDirection){
        super(damage,sender);        
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        this.transform().r = 5;
        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.setRenderer(new SpriteRenderer("StraightFlyingBullet.png"));
    }
}
