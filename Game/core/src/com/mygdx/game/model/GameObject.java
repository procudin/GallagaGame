/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.mygdx.game.view.Renderer;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public abstract class GameObject extends Component{    
    private Transform _transform;    
    public Transform transform(){return _transform;}
    public void setTransform(Transform t){  _transform = t;  _transform._object = this;  }
    
    private Renderer _renderer;
    public Renderer renderer() { return _renderer;}
    public void setRenderer(Renderer r) { _renderer = r;  _renderer._object = this; }
    
    
    
    // Конструктор
    public GameObject(){
        _object = this;
        
        setTransform(new Transform());        
    }
    
    @Override
    public void update(float delta){
        for (Component value : _components.values()){
            value.update(delta);
        }
    }    
    
    /*******************Компоненты***************//////////
    
    public Component getComponent(Class componentclass){        
        for (Class key : _components.keySet()){
            if (componentclass.isAssignableFrom(key)){
                return _components.get(key);
            }
        }
        
        return null;
    }
    
    public Component setComponent(Component component){  
        return setComponent(component.getClass(),component);
    }     
    
    public Component setComponent(Class componentClass,Component component){  
        component._object = this;  
        _components.put(componentClass, component);  
        return _components.get(componentClass);
    }    
    
    public void removeComponent(Component component){
        removeComponent(component.getClass(),component);
    }
    
    public void removeComponent(Class componentClass,Component component){
        for (Class key : _components.keySet()){
            if (componentClass.isAssignableFrom(key)){
                _components.remove(key);
                return;
            }
        }
    }
    
    
    private HashMap<Class,Component> _components = new HashMap<Class,Component>();
    
    //************************************* все объекты/////////////////////////////////////////////
    private static void addObject(GameObject obj){
        allObjects.put(ID++, obj);
    }
    
    
    private static int ID = 0;
    private static HashMap<Integer,GameObject> allObjects;
}
    
