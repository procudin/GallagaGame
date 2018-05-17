/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class Teleportation extends Component{

    protected float cooldown = 2f;
    protected float time = 0f;
    
    protected Random ransomizer = new Random();
    
    protected void teleportate() {
        if (_object.transform().X <= 0) {
            return;
        }
        
        switch(ransomizer.nextInt(4)) {
            case 0: // left
                _object.transform().X -= ransomizer.nextInt((int)(_object.transform().X / 1.1));
                break;
            case 1: // right
                _object.transform().X += ransomizer.nextInt((int)((levelModel.width() - _object.transform().X) / 1.1));
                break;
            case 2: // down
                _object.transform().Y -= ransomizer.nextInt((int)(_object.transform().Y / 1.1));
                break;
            case 3: // up
                _object.transform().Y += ransomizer.nextInt((int)((levelModel.height() - _object.transform().Y) / 1.1));
                break;
        }
    }
    
    @Override
    public void update(float delta) {
        time += delta;
        
        if (time >= cooldown) {
            time = 0;
            teleportate();
        }
    }
}
