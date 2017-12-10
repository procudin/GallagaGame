/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.bullets;

import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.Movement;
import com.mygdx.game.model.StraightMovement;
import com.mygdx.game.model.Transform;
import com.mygdx.game.model.bullets.Bullet;
import com.mygdx.game.model.spaceships.SpaceShip;
import com.mygdx.game.view.SpriteRenderer;

/**
 *
 * @author Admin
 */
public class PlayerBullet extends Bullet{
    public PlayerBullet(GameObject sender,float damage,float speed,boolean fromLeftToRightDirection){
        super(damage,sender);        
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        this.transform().r = 5;
        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.setRenderer(new SpriteRenderer("StraightFlyingBullet.png"));
    }
    
    public PlayerBullet(GameObject sender,float damage,float speed,boolean fromLeftToRightDirection,Transform t){
        super(damage,sender); 
        
        setTransform(t);
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        this.transform().r = 5;
        
        this.setComponent(Movement.class,new StraightMovement(speed,fromLeftToRightDirection));
        this.setRenderer(new SpriteRenderer("StraightFlyingBullet.png"));
    }
}
