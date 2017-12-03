/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Admin
 */
public class Transform extends Component{    
    public float angle = 0f;    
    public float X;
    public float Y;
    public float width;
    public float height;
    public float r ;
        
    public Transform(float X, float Y,float width, float height, float angle){
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.r = (float)Math.sqrt(width * width + height*height)/2;
    }
    
    public Transform(float X, float Y,float width, float height){
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;
        this.angle = 0;
        this.r = (float)Math.sqrt(width * width + height*height)/2;
    }
    
    public Transform(float X, float Y){
        this.X = X;
        this.Y = Y;
        this.width = 10;
        this.height = 10;
        this.angle = 0;
        this.r = (float)Math.sqrt(width * width + height*height)/2;
    }
    
    public Transform(float X, float Y,float angle){
        this.X = X;
        this.Y = Y;
        this.width = 10;
        this.height = 10;
        this.angle = angle;
        this.r = (float)Math.sqrt(width * width + height*height)/2;
    }
    
    public Transform(){
        this.X = 0;
        this.Y = 0;
        this.width = 10;
        this.height = 10;
        this.angle = 0;
        this.r = (float)Math.sqrt(width * width + height*height)/2;
    }
    
    public boolean isCollision(Transform other){        
        return (float)Math.sqrt(Math.pow(X - other.X, 2) + Math.pow(Y - other.Y, 2)) < r + other.r;
    }
}
