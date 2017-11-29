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
        width2 = width/2;
        height2 = height/2;
    }
    
    public SpriteRenderer(String assetName, int width, int height) {       
        
        sprite = new Sprite(new Texture(assetName),width,height);        
        this.width  = sprite.getWidth();
        this.height = sprite.getHeight();
        width2 = width/2;
        height2 = height/2;
    }
    
    
    private Sprite sprite;    
    private float width;
    private float height;
    private float width2;
    private float height2;
    
    
    @Override
    public void render(float delta, SpriteBatch batch){
        Transform t = _object.transform();        
        batch.draw(sprite, t.X - width2 , t.Y - height2 ,width2,height2,width,height,1, 1, t.angle, false );
    }
    
}
