/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Transform;

/**
 * Отрисовка спрайтов-картинок
 * @author Admin
 */
public class SpriteRenderer extends Renderer{
    /**
     * Конструктор
     * @param assetName 
     */
    public SpriteRenderer(String assetName) {               
        texture = new Texture(assetName);
        sprite = new Sprite(texture);           
        width  = sprite.getWidth();
        height = sprite.getHeight();
        width2 = width/2;
        height2 = height/2;
    }
    
    /**
     * Конструктор
     * @param assetName
     * @param width
     * @param height 
     */
    public SpriteRenderer(String assetName, int width, int height) {       
        texture = new Texture(assetName);
        sprite = new Sprite(texture);        
        this.width  = width;
        this.height = height;
        width2 = width/2;
        height2 = height/2;
    }
    
    private Texture texture;            /// Текстура
    private Sprite sprite;              /// Спрайт
    private float width;                /// Ширина
    private float height;               /// Высота
    private float width2;               /// Ширина/2
    private float height2;              /// Высота/2
    
    public float alpha = 0.1f;          /// Прозрачность
    
    /**
     * Отрисовка
     * @param delta
     * @param batch 
     */
    @Override
    public void render(float delta, SpriteBatch batch){
        if (!isActive)
            return;
        
        Transform t = _object.transform();    
        //batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        //batch.enableBlending();
        //sprite.setAlpha(alpha);
        batch.draw(sprite, t.X - width2 , t.Y - height2 ,width2,height2,width,height,1, 1, t.angle, false );
    }
    
}
