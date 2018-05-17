/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Класс формы объекта
 * @author Admin
 */
public class Transform extends Component{    
    public float angle = 0f;        /// Угол поворота
    public float X;                 /// Позиция X
    public float Y;                 /// Позиция Y
    public float width;             /// Ширина
    public float height;            /// Высота
    public float r ;                /// Радиус
        
    /**
     * КОнструктор
     * @param X
     * @param Y
     * @param width
     * @param height
     * @param angle 
     */
    public Transform(float X, float Y,float width, float height, float angle){
        super();
        
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.r = (float)Math.sqrt(width * width + height*height)/2;
    }
    
    /**
     * Конструктор
     * @param X
     * @param Y
     * @param width
     * @param height 
     */
    public Transform(float X, float Y,float width, float height){
        super();
        
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;
        this.angle = 0;
        this.r = (float)Math.sqrt(width * width + height*height)/2;
    }
    
    /**
     * Конструктор
     * @param X
     * @param Y 
     */
    public Transform(float X, float Y){
        super();
        
        this.X = X;
        this.Y = Y;
        this.width = 10;
        this.height = 10;
        this.angle = 0;
        this.r = (float)Math.sqrt(width * width + height*height)/2;
    }
    
    /**
     * КОнструктор
     * @param X
     * @param Y
     * @param angle 
     */
    public Transform(float X, float Y,float angle){
        super();
        
        this.X = X;
        this.Y = Y;
        this.width = 10;
        this.height = 10;
        this.angle = angle;
        this.r = (float)Math.sqrt(width * width + height*height)/2;
    }
    
    /**
     * КОнструктор
     */
    public Transform(){
        super();
        
        this.X = 0;
        this.Y = 0;
        this.width = 10;
        this.height = 10;
        this.angle = 0;
        this.r = (float)Math.sqrt(width * width + height*height)/2;
    }
    
    /**
     * Проверка на коллизии
     * @param other
     * @return 
     */
    public boolean isCollision(Transform other){        
        return (float)Math.sqrt(Math.pow(X - other.X, 2) + Math.pow(Y - other.Y, 2)) < r + other.r;
    }
}
