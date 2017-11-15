/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Transform;

/**
 *
 * @author Admin
 */
public class SpriteRenderer extends Renderer{
    
    public SpriteRenderer(String assetName) {       
        
        sprite = new Sprite(new Texture(assetName));        
        width  = sprite.getWidth();
        height = sprite.getHeight();
        
        sprite.setScale(0.5f);
    }
    
    public SpriteRenderer(String assetName, int width, int height) {       
        
        sprite = new Sprite(new Texture(assetName),width,height);        
        this.width  = sprite.getWidth();
        this.height = sprite.getHeight();
    }
    
    
    private Sprite sprite;    
    private float width;
    private float height;
    
    
    
    @Override
    public void render(float delta, SpriteBatch batch){
        Transform t = _object.transform();   
        ///sprite.rotate(50f/360);
        batch.draw(sprite, t.X, t.Y, (t.X + width) , (t.Y + height),width,height,1, 1, t.angle, false );
    }
    
}
