/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.mygdx.game.view.Renderer;
import java.util.HashMap;

/**
 * Класс объекта игрового поля
 * @author Admin
 */
public abstract class GameObject extends Component{  
    /**************************Модель уровня*******************************/
    protected static LevelModel levelModel;
    public static void setLevelModel(LevelModel model) { levelModel = model; }    
    
    /******************************Компонент Transform****************************************/
    private Transform _transform;    
    public Transform transform(){return _transform;}
    public void setTransform(Transform t){  _transform = t;  _transform._object = this;  }
    
    /******************************Компонент Renderer****************************************/
    private Renderer _renderer;
    public Renderer renderer() { return _renderer;}
    public void setRenderer(Renderer r) { _renderer = r;  _renderer._object = this; }
    
    // Конструктор
    public GameObject(){
        _object = this;
        
        setTransform(new Transform());        
    }
    
    /**
     * Обновление объекта
     * @param delta 
     */
    @Override
    public void update(float delta){
        for (Component value : _components.values()){
            value.update(delta);
        }
    }    
    
    
    /**
     * Удаление объекта
     */
    private boolean disposed = false;
    public boolean disposed(){ return disposed;}
    @Override    
    public void dispose(){
        disposed = true;  
        _components.clear();
    }
    
    /*******************Работа с компонентами*********************************************/ 
    /**
     * Получение компонента
     * @param componentclass
     * @return 
     */
    public Component getComponent(Class componentclass){        
        for (Class key : _components.keySet()){
            if (componentclass.isAssignableFrom(key)){
                return _components.get(key);
            }
        }
        
        return null;
    }    
    
    /**
     * Задание компонента
     * @param component
     * @return 
     */
    public Component setComponent(Component component){  
        return setComponent(component.getClass(),component);
    }     
    
    /**
     * Задание компонента
     * @param componentClass
     * @param component
     * @return 
     */
    public Component setComponent(Class componentClass,Component component){  
        component._object = this;  
        _components.put(componentClass, component);  
        return _components.get(componentClass);
    }    
    
    /**
     * Удаление компонента
     * @param component 
     */
    public void removeComponent(Component component){
        removeComponent(component.getClass());
    }
    
    /**
     * Удаление компонента
     * @param componentClass 
     */
    public void removeComponent(Class componentClass){
        for (Class key : _components.keySet()){
            if (componentClass.isAssignableFrom(key)){
                _components.remove(key);
                return;
            }
        }
    }  
    
    /// Контейнер с компонентами
    private HashMap<Class,Component> _components = new HashMap<Class,Component>();
}
    
