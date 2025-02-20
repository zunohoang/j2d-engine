package engine.objects;

import engine.components.Component;
import engine.maths.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObject {
    public String name;
    public Vector2D position;
    private List<Component> components = new ArrayList<>();

    public GameObject(String name, float x, float y) {
        this.name = name;
        this.position = new Vector2D(x, y);
    }

    // Thêm component vào GameObject
    public void addComponent(Component component) {
        component.setGameObject(this); // Gán GameObject cho component
        components.add(component);
        component.start(); // Gọi start() khi thêm component
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
    }

    // Vẽ tất cả component có chức năng render
    public void draw(Graphics g) {
        for (Component c : components) {
            c.draw(g);
        }
    }
}
