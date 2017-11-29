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
        gameSpeed = 100f;
        
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        
        init();
    }
    
    private void init(){
        // добаляем корабль игрока
        player = new StraightShootingSpaceship(new Transform(Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight()/ 2),3,true,0.33f,50f);
                
        // добавляем компоненту ручного управления 
        player.setComponent(Movement.class,new ManualMoviement(200));
        
        // добавляем его в объекты
        _objects.add(player);
    }
    
    public void update(float delta){
        
        //генерируем врагов
        generateEnemies(delta);        
        
        // Добавляем новые объекты
        add();
        
        
        
        // обновляем текущие
        _objectsIter = _objects.listIterator();
        while (_objectsIter.hasNext()){
            GameObject obj = _objectsIter.next();
            if (obj.isOutOfWindow()){
                _objectsIter.remove();
                continue;
            }
            
            obj.update(delta);
        }
    }
    
    
    /**************************************Генерация врагов*********************************************/
    
    private float spawnTimeout = 1f;
    private float lastSpawnTime = 0f;
    private float minSpawnY = Gdx.graphics.getHeight() * 0.2f;
    private float maxSpawnY = Gdx.graphics.getHeight() * 0.8f;
    private float spawnX = Gdx.graphics.getWidth() * 1.2f;
    private final Random random = new Random();
    
    private void generateEnemies(float delta){
        lastSpawnTime+=delta;
                
        if (lastSpawnTime<spawnTimeout){
            return;
        }        
        
        // сгенерировать вражеский корабль
        SpaceShip ship = SpaceShipFactory.getSpaceShip("StraightShootingSpaceship", false,1,0.7f,30f);
        
        // задаем начальную позицию
        ship.transform().X = spawnX;
        ship.transform().Y = random.nextInt((int)(maxSpawnY - minSpawnY)) + minSpawnY;
        
        //добавляем в активные объекты
        _objects.add(ship);
        
        lastSpawnTime = 0f;
    }    
    
    /******************Удаление объектов*************/
//    private LinkedList<GameObject> disposableObjects = new LinkedList<GameObject>();
//    
//    public void addDisposableObject(GameObject obj){
//        disposableObjects.add(obj);
//    }
//    
//    private void dispose(){
//        for (GameObject o : disposableObjects){
//            _objects.remove(o);
//        }
//        
//        disposableObjects.clear();
//    }
    
    /***********************Добавление объектов*******************************/
    private LinkedList<GameObject> addingObjects = new LinkedList<GameObject>();
    
    public void addObject(GameObject obj){
        addingObjects.add(obj);
    }
    
    private void add(){
        _objects.addAll(addingObjects);
    }
}
   
