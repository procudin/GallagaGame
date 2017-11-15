/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.badlogic.gdx.Screen;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LevelModel {
    
    public float gameSpeed;
    
    private ArrayList<GameObject> _objects = new ArrayList<GameObject>();    
    public ArrayList<GameObject> objects() { return _objects;}
    
        
    public LevelModel(){
        gameSpeed = 17f;
        
        init();
    }
    
    private void init(){
        // добаляем корабль игрока
        _objects.add(new StraightShootingSpaceship());
    }
    
    
    
    public void update(float delta){
        for (GameObject obj : _objects){
            obj.update(delta);
        }
    }
}
