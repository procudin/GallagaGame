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
    
    @Override
    public void update(float delta){
        Transform t = _object.transform();
        
        if (Gdx.input.isKeyPressed(Keys.LEFT)){
            t.X -= speed * delta;
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)){
            t.X += speed * delta;
        }
        
        if (Gdx.input.isKeyPressed(Keys.UP)){
            t.Y += speed * delta;
        } else if (Gdx.input.isKeyPressed(Keys.DOWN)){
            t.Y -= speed * delta;
        }
    }
}
