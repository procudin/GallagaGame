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
public class RhombusMovement extends Movement{
    
    public RhombusMovement(float speed,float height, float width, float initialX, float initialY){
        super(speed);
        
        this.height = height;
        this.width = width;
        height_2 = height /2;
        width_2 = width /2;
        this.initialX = initialX;
        this.initialY = initialY;
        
        this.edge = (float)Math.sqrt(height * height + width * width);
        this._sin = height / edge;
        this._cos = width / edge;
    }
    
    public float height;
    public float width;
    public float initialX;
    public float initialY;
    
    private float height_2;
    private float width_2;
    private float edge;
    private float _sin;
    private float _cos;
    
    private int mode = 0;
    
    @Override
    public void update(float delta){
        
        switch (mode){
            case 0:
                _object.transform().X -= speed * delta;
                if (isNear(_object.transform().X,_object.transform().Y,initialX,initialY))
                    mode = 1;                
                break;  
                
            case 1:
                _object.transform().X -= speed * delta * _cos;
                _object.transform().Y += speed * delta * _sin;      
                
                if (isNear(_object.transform().X,_object.transform().Y,initialX-width_2,initialY + height_2))
                    mode = 2; 
                
                break;
                
            case 2:
                _object.transform().X -= speed * delta * _cos;
                _object.transform().Y -= speed * delta * _sin;      
                
                 if (isNear(_object.transform().X,_object.transform().Y,initialX-width,initialY))
                    mode = 3; 
                break;
                
            case 3:
                _object.transform().X += speed * delta * _cos;
                _object.transform().Y -= speed * delta * _sin;   
                
                 if (isNear(_object.transform().X,_object.transform().Y,initialX-width_2,initialY - height_2))
                    mode = 4; 
                break;
                
            case 4:
                _object.transform().X += speed * delta * _cos;
                _object.transform().Y += speed * delta * _sin;     
                
                if (isNear(_object.transform().X,_object.transform().Y,initialX,initialY))
                    mode = 1; 
                break;    
            
        }
    }
    
    
    private boolean isNear(float x1,float y1, float x2,float y2){
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2))<1;
    }
}
