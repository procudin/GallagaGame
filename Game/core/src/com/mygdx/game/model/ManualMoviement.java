/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 *
 * @author Admin
 */
public class ManualMoviement extends Movement{
    
    public static boolean isOutOfWindow(Transform t){        
        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();
        
        return t.X > width || t.X < 0 || t.Y > height || t.Y < 0;
    }
    
    private float tmp;
    
    @Override
    public void update(float delta){
        Transform t = _object.transform();
        
        tmp = t.X;      
        
        if (Gdx.input.isKeyPressed(Keys.LEFT)){
            tmp -= speed * delta;
            if (!(tmp < 0 || tmp > Gdx.graphics.getWidth()))
                t.X = tmp;
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)){
            tmp += speed * delta;
            
            if (!(tmp < 0 || tmp > Gdx.graphics.getWidth()))
                t.X = tmp;
        }
        
        tmp = t.Y;   
        
        if (Gdx.input.isKeyPressed(Keys.UP)){
            tmp += speed * delta;
            
            if (!(tmp < 0 || tmp > Gdx.graphics.getHeight()))
                t.Y = tmp;            
        } else if (Gdx.input.isKeyPressed(Keys.DOWN)){
            tmp -= speed * delta;
            
            if (!(tmp < 0 || tmp > Gdx.graphics.getHeight()))
                t.Y = tmp; 
        }
    }
}
