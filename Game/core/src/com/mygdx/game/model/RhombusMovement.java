/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

/**
 * Движение по ромбу
 * @author Admin
 */
public class RhombusMovement extends Movement{
    
    /**
     * КОнструктор
     * @param speed
     * @param height
     * @param width
     * @param initialX
     * @param initialY 
     */
    public RhombusMovement(float speed,float height, float width, float initialX, float initialY){
        super(speed);
        
        float height_2 = height / 2;
        float width_2 = width / 2;
        
        edgeLength = (float)Math.sqrt(height_2 * height_2 + width_2 * width_2);
        
        _sin = height_2 / edgeLength;
        _cos = width_2 / edgeLength;
        
        rightNodeX = initialX;
        rightNodeY = initialY;
        
        topNodeX = initialX - width_2;
        topNodeY = initialY + height_2;
        
        leftNodeX = initialX - width_2;
        leftNodeY = initialY;
        
        bottomNodeX = initialX - width_2;
        bottomNodeY = initialY - height_2;
        
        consumedLength = 0;
        
        currentDirection = 1;
        targetNodeNumber = 2;
    }
    
    protected float rightNodeX;
    protected float rightNodeY;
    
    protected float topNodeX;
    protected float topNodeY;
    
    protected float leftNodeX;
    protected float leftNodeY;
    
    protected float bottomNodeX;
    protected float bottomNodeY;
    
    protected float edgeLength;
    
    protected float _sin;             /// height / edge
    protected float _cos;             /// width / edge
    
    
    protected float consumedLength;
    
    protected int currentDirection;         /// Номер диагонали, по которой осуществляется движение
    protected int targetNodeNumber;           /// Номер диагонали, по которой осуществляется движение
    
    
    protected int genereteNextDirection(int prevNode, int nextNode) {
        return prevNode;
    }
    
    protected int genereteNextNode(int prevNode, int currentDirection) {
        switch (prevNode) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 1;
        }
        return 1;
    }
        
    protected void moveByDirection(float length) {
        switch (currentDirection) {
            case 1:
                moveUpLeft(length);
                break;
            case 2:
                moveDownLeft(length);
                break;
            case 3:
                moveDownRight(length);
                break;
            case 4:
                moveUpRight(length);
                break;
        }
    }
    
    protected void moveToNearPoint() {
        //if ()
        
        
    }
    
    /**
     * Обновление
     * @param delta 
     */
    @Override
    public void update(float delta){
        float stepLength = delta * speed;
        consumedLength += stepLength;        
        
        if (consumedLength > edgeLength) {
            consumedLength = 0;
            int nextNodeNumber = genereteNextNode(targetNodeNumber,currentDirection);
            currentDirection = genereteNextDirection(targetNodeNumber,nextNodeNumber);
            targetNodeNumber = nextNodeNumber;
            return;
            
        } 
        
        moveByDirection(stepLength);
    }
    
    
    protected void moveUpLeft(float length) {
        _object.transform().X -= length * _cos;
        _object.transform().Y += length * _sin;  
    }
    protected void moveUpRight(float length) {
        _object.transform().X += length * _cos;
        _object.transform().Y += length * _sin;
    }
    protected void moveDownLeft(float length) {
        _object.transform().X -= length * _cos;
        _object.transform().Y -= length * _sin;  
    }
    protected void moveDownRight(float length) {
        _object.transform().X += length * _cos;
        _object.transform().Y -= length * _sin;
    }
    
        
    
    protected boolean isNear(float x1,float y1, float x2,float y2){
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2))<5;
    }
}
