package engine.objects;

import engine.maths.Vector2D;

public class Camera {

    private static Camera camera;

    public static Camera getCamera() {
        if(camera == null) {
            camera = new Camera();
        }
        return camera;
    }

    private Vector2D position;

    public Camera() {
        position = new Vector2D(0, 0);
    }

    public Camera(Vector2D position) {
        this.position = position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }
}
