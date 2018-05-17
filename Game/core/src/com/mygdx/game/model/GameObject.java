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
 *
 * @author Admin
 */
public abstract class GameObject extends Component {

    

    /**
     * ****************************Компонент Transform***************************************
     */
    private Transform _transform;

    public Transform transform() {
        return _transform;
    }

    public void setTransform(Transform t) {
        _transform = t;
        _transform._object = this;
    }

    /**
     * ****************************Компонент Renderer***************************************
     */
    private Renderer _renderer;

    public Renderer renderer() {
        return _renderer;
    }

    public void setRenderer(Renderer r) {
        _renderer = r;
        _renderer._object = this;
    }

    // Конструктор
    public GameObject() { 
        super();
        
        _object = this;
        setTransform(new Transform());
    }

    /**
     * Обновление объекта
     *
     * @param delta
     */
    @Override
    public void update(float delta) {
        for (Component value : _components.values()) {
            value.update(delta);
        }
    }

    /**
     * Удаление объекта
     */
    private boolean disposed = false;

    public boolean disposed() {
        return disposed;
    }

    @Override
    public void dispose() {
        disposed = true;
        _components.clear();
    }
    
    public boolean isOutOfWindow() {
        return _transform.X < -levelModel.width() / 4 || _transform.X > levelModel.width() * 1.5
                || _transform.Y < -levelModel.height() / 3 || _transform.Y > levelModel.height() * 1.5;
    }

    /**
     * *****************Работа с компонентами********************************************
     */
    /**
     * Получение компонента
     *
     * @param componentclass
     * @return
     */
    public Component getComponent(String componentID) {
        if (_components.containsKey(componentID)) {
            return _components.get(componentID);
        }
        return null;
    }

    /**
     * Задание компонента
     *
     * @param componentClass
     * @param component
     * @return
     */
    public Component setComponent(String componentID, Component component) {
        component._object = this;
        component._componentID = componentID;
        _components.put(componentID, component);
        return _components.get(componentID);
    }
    
    /**
     * Задание компонента
     * @param component
     * @return 
     */
    public Component setComponent(Component component) {
        return setComponent(component._componentID,component);
    }

    /**
     * Удаление компонента
     *
     * @param componentClass
     */
    public void removeComponent(String componentID) {
        if (_components.containsKey(componentID)) {
            _components.remove(componentID);
        }
    }

    /**
     * Удаление компонента
     * @param component 
     */
    public void removeComponent(Component component) {
        removeComponent(component._componentID);
    }

    /// Контейнер с компонентами
    private HashMap<String, Component> _components = new HashMap<String, Component>();
}
