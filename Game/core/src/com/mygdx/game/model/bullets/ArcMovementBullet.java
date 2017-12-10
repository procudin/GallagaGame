/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.bullets;

import com.mygdx.game.model.ArcMovement;
import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.Movement;
import com.mygdx.game.model.StraightMovement;
import com.mygdx.game.model.Transform;
import com.mygdx.game.view.SpriteRenderer;

/**
 *
 * @author Admin
 */
public class ArcMovementBullet extends Bullet{
    public ArcMovementBullet(GameObject sender,GameObject target,float damage,float speed,boolean fromLeftToRightDirection) {
        super(damage, sender);
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        this.transform().r = 5;
        
        
        ArcMovement m = new ArcMovement(speed);     
        this.setComponent(Movement.class,m);
        m.setTarget((int)target.transform().X,(int)target.transform().Y);
         
        this.setRenderer(new SpriteRenderer("ArcMovementBullet.png"));
    }
    
    public ArcMovementBullet(GameObject sender,GameObject target,float damage,float speed,boolean fromLeftToRightDirection,Transform t) {
        super(damage, sender);
        
        setTransform(t);
        
        this.transform().angle = fromLeftToRightDirection ? 180 : 0;
        this.transform().width = 14;
        this.transform().height = 11;
        this.transform().r = 5;
        
        
        ArcMovement m = new ArcMovement(speed);     
        this.setComponent(Movement.class,m);
        m.setTarget((int)target.transform().X,(int)target.transform().Y);
         
        this.setRenderer(new SpriteRenderer("ArcMovementBullet.png"));
    }
}
