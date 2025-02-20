package engine.components;

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

    public void start() {} // Gọi khi bắt đầu
    public void update(float deltaTime) {} // Gọi mỗi frame
    public void draw(Graphics g) {} // Vẽ lên màn hình (nếu có)
}
