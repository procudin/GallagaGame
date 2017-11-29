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
    public PlayerBullet(float damage,float speed,boolean fromLeftToRightDirection){
        super(damage);        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.setRenderer(new SpriteRenderer("StraightFlyingBullet.png"));
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
    }
}
