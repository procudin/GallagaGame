/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.mygdx.game.model.bullets.BulletFactory;
import com.mygdx.game.model.bullets.Bullet;
import com.mygdx.game.model.spaceships.SpaceShip;

/**
 * Класс оружия
 * @author Admin
 */
public class Gun extends Component{    
    /**
     * Конструктор
     * @param parent
     * @param ofsetX
     * @param ofsetY
     * @param fireRate
     * @param bulletType
     * @param bulletSpeed
     * @param isFromLeftToRightDirection 
     */
    public Gun(GameObject parent,float ofsetX, float ofsetY,float fireRate,String bulletType,float bulletSpeed,boolean isFromLeftToRightDirection){
        super();
        
        this.parent = parent;
        this.isFromLeftToRightDirection = isFromLeftToRightDirection;
        this.ofsetX = ofsetX;
        this.ofsetY= ofsetY;
        this.fireRate = fireRate;
        this.bulletType = bulletType;
        this.time=0f;
        this.bulletSpeed = bulletSpeed;
        this.target = null;
    }
    
    /**
     * Конструктор
     * @param parent
     * @param target
     * @param ofsetX
     * @param ofsetY
     * @param fireRate
     * @param bulletType
     * @param bulletSpeed 
     */
    public Gun(GameObject parent,GameObject target,float ofsetX, float ofsetY,float fireRate,String bulletType,float bulletSpeed){
        super();
        
        this.parent = parent;
        this.isFromLeftToRightDirection = isFromLeftToRightDirection;
        this.ofsetX = ofsetX;
        this.ofsetY= ofsetY;
        this.fireRate = fireRate;
        this.bulletType = bulletType;
        this.time=0f;
        this.bulletSpeed = bulletSpeed;
        this.target = target;
    }
    
    public boolean isEnable = true;         /// Активность оружия
    
    private GameObject parent;              /// Родитель
    private GameObject target = null;       /// Цель
    
    private float ofsetX;                   /// Смещение относительно центра родителя X
    private float ofsetY;                   /// Смещение относительно центра родителя Y
    private boolean isFromLeftToRightDirection; /// Является ли направление слева-направо
    public float fireRate;                  /// Скорострельность
    public float bulletSpeed;               /// Скорость пули
    
    public String bulletType;               /// Тип пули
    
    
    private float time;                     /// Относительное время
    
    /**
     * Обновление
     * @param delta 
     */
    @Override
    public void update(float delta){        
        time+=delta;
        
        if (time<fireRate){
            return;
        }        
        
        Transform t = new Transform(ofsetX + parent.transform().X,ofsetY + parent.transform().Y);
        
        Bullet newB = BulletFactory.getBullet(t,parent,target,bulletType,bulletSpeed,isFromLeftToRightDirection);    
        
        GameObject.levelModel.addBullet(newB);
        time=0;
    }
}
