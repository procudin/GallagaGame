/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.buffs;

import com.mygdx.game.model.Transform;
import com.mygdx.game.model.spaceships.SpaceShip;

/**
 *
 * @author Admin
 */
public class BuffFactory {
    public static Buff getBuff(String buffName,SpaceShip target, Transform t){
        
        if (buffName.equals("FireRateIncrease")){
            return new FireRateIncrease(target,t,3,20);
        }
        
        if (buffName.equals("LifeIncrease")){
            return new LifeIncrease(target,t);
        }
        
        return null;
    }
    
    public static Buff getBuff(String buffName,SpaceShip target){
        
        if (buffName.equals("FireRateIncrease")){
            return new FireRateIncrease(target,3,20);
        }
        
        if (buffName.equals("LifeIncrease")){
            return new LifeIncrease(target);
        }
        
        return null;
    }
}
