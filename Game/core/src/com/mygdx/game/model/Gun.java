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
public class Gun extends Component{    
    public Gun(SpaceShip parent,float ofsetX, float ofsetY,float fireRate,String bulletType,float bulletSpeed,boolean isFromLeftToRightDirection){
        this.parent = parent;
        this.isFromLeftToRightDirection = isFromLeftToRightDirection;
        this.ofsetX = ofsetX;
        this.ofsetY= ofsetY;
        this.fireRate = fireRate;
        this.bulletType = bulletType;
        this.time=0f;
        this.bulletSpeed = bulletSpeed;
    }
    
    public boolean isEnable = true;
    
    private SpaceShip parent;
    
    private float ofsetX;
    private float ofsetY;
    private boolean isFromLeftToRightDirection;
    public float fireRate;
    public float bulletSpeed;
    
    public String bulletType;
    
    
    private float time;
    
    @Override
    public void update(float delta){        
        time+=delta;
        
        if (time<fireRate){
            return;
        }
        
        Bullet newB = BulletFactory.getBullet(bulletType,bulletSpeed,isFromLeftToRightDirection);        
        
        newB.transform().X=ofsetX + parent.transform().X;
        newB.transform().Y=ofsetY + parent.transform().Y;
        newB.transform().angle = isFromLeftToRightDirection? 180 : 0;
        
        GameObject.levelModel.addObject(newB);
        time=0;
    }
    
    
}
