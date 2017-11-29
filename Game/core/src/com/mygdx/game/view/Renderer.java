/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.view;

import com.mygdx.game.model.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.*;

/**
 *
 * @author Admin
 */
public abstract class Renderer extends Component{        
    public boolean isActive = true;
    public abstract void render(float delta, SpriteBatch batch);
}
