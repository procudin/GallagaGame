/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.*;
import com.mygdx.game.model.LevelModel;
import com.mygdx.game.model.modules.ModuleLoader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;

/**
 * Отрисовщик игры
 * @author Admin
 */
public class LevelRenderer implements Screen{
    
    private final Gallaga game;             /// Игра
    private final ILevelModel model;         /// Модель игры
    
    /**
     * Конструктор
     * @param game 
     */
    public LevelRenderer(Gallaga game){
       
        _backgroundImg = new Texture("background.png");
        hurtImg = new Texture("heart.png");
        go = new Texture("go.png");
        win = new Texture("win.png");
        
        model = new LevelModel();
        GameObject.setLevelModel(model);
        
        loadModules();
        
        this.game = game; 
    }    
    private Texture hurtImg;                /// Текстура сердца
    private Texture _backgroundImg;         /// Задний фон
    private Texture go;                     /// Спрайт проигрыша
    private Texture win;                    /// Спрайт выигрыша
    private float backgroundX = 0;          /// Позиция фона X
    private float backgroundY = 0;          /// Позиция фона Y
        
    /**
     * Проверка выхода фона за границу
     * @return 
     */
    private boolean isBackgroundOut(){
        return backgroundX + _backgroundImg.getWidth() < Gdx.graphics.getWidth();
    }
    
    private void loadModules(){
        ModuleLoader loader = new ModuleLoader(ClassLoader.getSystemClassLoader());
        JFileChooser fileopen = new JFileChooser("F:\\Gallaga\\Game\\core\\build\\classes\\main");
        fileopen.setMultiSelectionEnabled(true);
        int ret = fileopen.showDialog(null, "Загрузить модуль");
        
        if (ret == JFileChooser.APPROVE_OPTION) {            
            File [] modules = fileopen.getSelectedFiles();
            
            for (File module : modules){
                String name = module.getAbsolutePath();                
                try{
                    Class clazz = loader.loadClass(name);
                    Component execute = (Component)clazz.newInstance();  
                    //execute.load();
                    model.addModule(execute);
                }catch (Exception ex){
                    System.out.println("Cann't load module " + name);
                }
            }
        }
    }
    
    
    int hurtOfsetX = (int)(Gdx.graphics.getWidth() * 0.02); /// Смещение позиции сердца X
    int hurtOfsetY = (int)(Gdx.graphics.getHeight() * 0.95);/// Смещение позиции сердца Y
    
    /**
     * Отрисовка жизней игрока
     * @param batch 
     */
    private void drawPlayerLifes(SpriteBatch batch){
        for (int i = 0; i< model.playerShip().lifes();i++){
            batch.draw(hurtImg, hurtOfsetX + (int)(hurtOfsetX*i*1.5),hurtOfsetY);
        }
    }
    
    /**
     * Отрисовка
     * @param f 
     */
    @Override
    public void render(float f) {
        switch (model.gameState()){
            case INGAME:
            case BOSSFIGHT:
                renderModel(f);
                break;
            case GAMEOVER:
                renderGameOverScreen();
                break;
            case WIN:
                renderWinScreen();
                break;
        }
    }
    
    /**
     * Отрисовка модели игры
     * @param f 
     */
    private void renderModel(float f){
        Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (isBackgroundOut())
            backgroundX = 0f;
        
        // обновление модели
        model.update(f);
        
        // обновление позиции фона
        backgroundX -= model.gameSpeed() * f;        
        
        game.batch.begin();        
        
        // обновить фон
	game.batch.draw(_backgroundImg, backgroundX, 0);
        
        // обновить объекты
        model.updateRenderer(f,game.batch);
        
        // нарисовать количество жизней игрока
        drawPlayerLifes(game.batch);
        
	game.batch.end();
    }
    
    /**
     * Отрисовка экрана проигрыша
     */
    private void renderGameOverScreen(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.begin();       
        
        // обновить фон
	game.batch.draw(go, model.width()/4, model.height()/3);
        
	game.batch.end();
    }
    
    /**
     * Отрисовка экрана выигрыша
     */
    private void renderWinScreen(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.begin();       
        
        // обновить фон
	game.batch.draw(win, model.width()/4, model.height()/2);
        
	game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void show() { }
    
    /**
     * Уничтожение
     */
    @Override
    public void dispose() {
        this._backgroundImg.dispose();
    }
    
}
