/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Admin
 */
public class LevelModel {
    public float gameSpeed;
     
    public final int height;
    public final int width;
    
    
    /************************ Объекты игрового поля ************************************/
    private LinkedList<GameObject> _objects = new LinkedList<GameObject>();    
    public LinkedList<GameObject> objects() { return _objects;} 
    
    private SpaceShip player;  
    
    public LevelModel(){
        gameSpeed = 100f;
        
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        
        init();
    }
    
    private void init(){
        // добаляем корабль игрока
        player = new StraightShootingSpaceship(new Transform(Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight()/ 2),3,true);
                
        // добавляем компоненту ручного управления 
        player.setComponent(Movement.class,new ManualMoviement(200));
        
        // добавляем его в объекты
        _objects.add(player);
    }
    
    public void update(float delta){
        // удаляем старые объекты
        dispose();
        
        add();
        
        // обновляем текущие
        for (GameObject obj : _objects){
            if (obj.isOutOfWindow()){
                obj.dispose();
                continue;
            }
            
            obj.update(delta);
        }
    }
    
    
    /******************Удаление объектов*************/
    private LinkedList<GameObject> disposableObjects = new LinkedList<GameObject>();
    
    public void addDisposableObject(GameObject obj){
        disposableObjects.add(obj);
    }
    
    private void dispose(){
        for (GameObject o : disposableObjects){
            _objects.remove(o);
        }
        
        disposableObjects.clear();
    }
    
    /***********************Добавление объектов*******************************/
    private LinkedList<GameObject> addingObjects = new LinkedList<GameObject>();
    
    public void addObject(GameObject obj){
        addingObjects.add(obj);
    }
    
    private void add(){
        for (GameObject o : addingObjects){
            _objects.add(o);
        }
    }
}
   
