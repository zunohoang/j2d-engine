package engine.objects;

import engine.components.Component;
import engine.components.Transform;
import engine.graphics.Renderer;
import engine.interfaces.ICollisionListener;
import engine.maths.Vector2D;
import engine.utils.LOG_TYPE;
import engine.utils.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameObject {
    public String name;
    public Transform transform;
    public String currentState;
    public int layer;

    private List<GameObject> gameObjects = new ArrayList<>();
    private List<Component> components = new ArrayList<>();

    public GameObject(String name, Transform transform) {
        this.name = name;
        this.transform = transform;
    }

    public String getName() {
        return name;
    }

    // Them gameobject con
    public void addObject(GameObject obj) {
        gameObjects.add(obj);
    }

    // Xoa gameobject con
    public void removeObject(GameObject obj) {
        gameObjects.remove(obj);
    }

    // Lay gameobject con theo ten
    public GameObject findObject(String name) {
        for (GameObject obj : gameObjects) {
            if (obj.name.equals(name)) {
                return obj;
            }
        }
        return null;
    }

    // Thêm component vào GameObject
    public void addComponent(Component component) {
        component.setGameObject(this); // Gán GameObject cho component
        components.add(component);
        component.start(); // Gọi start() kh        i thêm component
    }

    // Lấy component theo kiểu
    public <T extends Component> T getComponent(Class<T> type) {
        for (Component c : components) {
            if (type.isInstance(c)) {
                return type.cast(c);
            }
        }
        return null;
    }

    // Cập nhật tất cả component
    public void update(float deltaTime) {
        for (Component c : components) {
            c.update(deltaTime);
        }
        for(GameObject obj : gameObjects) {
            obj.update(deltaTime);
        }
    }

    // cap nhat thoi gian co dinh
    public void fixedUpdate(float deltaTime) {
        for (Component c : components) {
            c.fixedUpdate(deltaTime);
        }
        for(GameObject obj : gameObjects) {
            obj.fixedUpdate(deltaTime);
        }
    }

    // cap nhat cuoi frame
    public void lateUpdate(float deltaTime) {
        for (Component c : components) {
            c.lateUpdate(deltaTime);
        }
        for(GameObject obj : gameObjects) {
            obj.lateUpdate(deltaTime);
        }
    }

    // Vẽ tất cả component có chức năng render
    public void draw(Renderer g) {
        for (Component c : components) {
            c.draw(g);
        }
        for (GameObject obj : gameObjects) {
            obj.draw(g);
        }
    }

    public void onCollision(GameObject other) {
//        Logger.log(this, "Collision detected between " + this.name + " and " + other.name);
        for (Component c : this.components) {
            if (c instanceof ICollisionListener) {
                ((ICollisionListener)c).onCollision(other);
            }
        }

    }
}
