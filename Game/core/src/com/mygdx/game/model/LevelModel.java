/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private LinkedList<SpaceShip> enemies = new LinkedList<SpaceShip>();
    private LinkedList<Bullet> playerBullets = new LinkedList<Bullet>();
    private LinkedList<Bullet> enemieBullets = new LinkedList<Bullet>();
    
    private SpaceShip player;  
    
    public LevelModel(){
        gameSpeed = 30f;
        
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        
        init();
    }
    
    private void init(){
        // добаляем корабль игрока
        player = new PlayerSpaceship(new Transform(Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight()/ 2),3,true,0.1f,190f,400f);
    }
    
    
    public void addBullet(Bullet bullet){
        if (bullet.senderShip() == player){
            playerBullets.add(bullet);
        }else{
            enemieBullets.add(bullet);        
        }
    }
    
    
    private void checkPlayerBulletCollisions(Bullet bul){        
        ListIterator<SpaceShip> iter = enemies.listIterator();
        
        while (iter.hasNext()){
            SpaceShip enm = iter.next();
            
            if (enm.transform().isCollision(bul.transform())){
                enm.hit(bul.damage());
                bul.dispose();
            }
        }        
    }
    
//    private void checkEnemieBulletCollisions(Bullet bul){ 
//        if (player.transform().isCollision()){
//            
//        }
//    }
    
    private void updatePlayerBullets(float delta){
        ListIterator<Bullet> iter = playerBullets.listIterator();
        
        while (iter.hasNext()){
            Bullet bul = iter.next();
            
            checkPlayerBulletCollisions(bul);
            
            if (isOutOfWindow(bul) || bul.disposed()){
                iter.remove();
                continue;
            }
            
            bul.update(delta);
        }
    }    
    private void updateEnemieBullets(float delta){
        ListIterator<Bullet> iter = enemieBullets.listIterator();
        
        while (iter.hasNext()){
            Bullet bul = iter.next();
            
            if (isOutOfWindow(bul) || bul.disposed()){
                iter.remove();
                continue;
            }
            
            bul.update(delta);
        }
    }    
    private void updateEnemies(float delta){
        ListIterator<SpaceShip> iter = enemies.listIterator();
        
        while (iter.hasNext()){
            SpaceShip ship = iter.next();
            
            if (isOutOfWindow(ship) || ship.disposed()){
                iter.remove();
                continue;
            }
            
            ship.update(delta);
        }
    }        
    public void update(float delta){
       //генерируем врагов
        generateEnemies(delta); 
        
        // обновляем игрока и врагов
        updateEnemies(delta);
        player.update(delta);
        
        //обновляем пули
        updateEnemieBullets(delta); 
        updatePlayerBullets(delta);
    }
    
    public void updateRenderer(float delta,SpriteBatch batch){
        // отрисовать врагов
        ListIterator<SpaceShip> iter = enemies.listIterator();        
        while (iter.hasNext()){
            iter.next().renderer().render(delta, batch);
        }
        
        // отрисоватьб пули врагов
        ListIterator<Bullet> iter1 = enemieBullets.listIterator();        
        while (iter1.hasNext()){
            iter1.next().renderer().render(delta, batch);
        }
        
        //отрисовать пули игрока
        iter1 = playerBullets.listIterator();        
        while (iter1.hasNext()){
            iter1.next().renderer().render(delta, batch);
        }
        
        // отрисовать игрока
        player.renderer().render(delta, batch);
    }
    
    /**************************************Генерация врагов*********************************************/
    
    private float spawnTimeout = 1f;
    private float lastSpawnTime = 0f;
    private float minSpawnY = Gdx.graphics.getHeight() * 0.05f;
    private float maxSpawnY = Gdx.graphics.getHeight() * 0.95f;
    private float spawnX = Gdx.graphics.getWidth() * /*1.2f*/1;
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
        enemies.add(ship);
        
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
    
    public boolean isOutOfWindow(GameObject o){
        Transform t = o.transform();        
        return t.X < -width/4 || t.X > width * 1.5 
                || t.Y < -height/3 || t.Y > height * 1.5;
    }   
    
}
   
