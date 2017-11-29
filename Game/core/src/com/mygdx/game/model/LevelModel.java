/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.view.SpriteRenderer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

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
    private ListIterator<GameObject> _objectsIter;
    
    private SpaceShip player;  
    
    public LevelModel(){
        gameSpeed = 30f;
        
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        
        init();
    }
    
    private void init(){
        // добаляем корабль игрока
        player = new PlayerSpaceship(new Transform(Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight()/ 2),3,true,0.1f,190f,200f);
                
        // добавляем его в объекты
        _objects.add(player);
    }
    
    public void update(float delta){
                
        //генерируем врагов
        generateEnemies(delta); 
        
        add();
        
        // обновляем текущие
        _objectsIter = _objects.listIterator();
        while (_objectsIter.hasNext()){
            GameObject obj = _objectsIter.next();
            if (obj.isOutOfWindow() || obj.disposed()){
                _objectsIter.remove();
                continue;
            }
            
            obj.update(delta);
        }
    }
    
    
    /**************************************Генерация врагов*********************************************/
    
    private float spawnTimeout = 0.8f;
    private float lastSpawnTime = 0f;
    private float minSpawnY = Gdx.graphics.getHeight() * 0.05f;
    private float maxSpawnY = Gdx.graphics.getHeight() * 0.95f;
    private float spawnX = Gdx.graphics.getWidth() * 1.2f;
    private final Random random = new Random();
    
    private void generateEnemies(float delta){
        lastSpawnTime+=delta;
                
        if (lastSpawnTime<spawnTimeout){
            return;
        }        
        
        // сгенерировать вражеский корабль
        SpaceShip ship = generateRandomShip();
        
        // задаем начальную позицию
        ship.transform().X = spawnX;
        ship.transform().Y = random.nextInt((int)(maxSpawnY - minSpawnY)) + minSpawnY;
        
        //добавляем в активные объекты
        addObject(ship);
        
        lastSpawnTime = 0f;
    }    
    
    private SpaceShip generateRandomShip(){
        int val = random.nextInt(2);
        
        switch (val){
            case 0:
                return SpaceShipFactory.getSpaceShip("StraightShootingSpaceship",false,1,1f,100f,250f);
            case 1:
                return SpaceShipFactory.getSpaceShip("BigSpaceShip",false,1,1f,50f,250f);
        }
        
        return null;
    }
    
    /***********************Добавление объектов*******************************/
    private LinkedList<GameObject> addingObjects = new LinkedList<GameObject>();
    
    public void addObject(GameObject obj){
        addingObjects.add(obj);
    }
    
    public void add(){
        _objects.addAll(addingObjects);
        addingObjects.clear();
    }
}
   
