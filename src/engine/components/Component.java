package engine.components;

import engine.graphics.Renderer;
import engine.objects.GameObject;

import java.awt.*;

public abstract class Component {
    protected GameObject gameObject; // Tham chiếu đến đối tượng chứa component

    // Gán GameObject khi được thêm vào
    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject(){
        return gameObject;
    }

    public void collisionWith(GameObject gObj) {}

    public void start() {} // Gọi khi bắt đầu
    public void update(float deltaTime) {} // Gọi mỗi frame
    public void fixedUpdate(float deltaTime) {} // Gọi theo khoảng thời gian cố định
    public void lateUpdate(float deltaTime) {} // Gọi cuối frame
    public void draw(Renderer g) {} // Vẽ lên màn hình (nếu có)

}
