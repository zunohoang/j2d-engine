package engine.maths;

public class Vector2D {
    public float x, y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }
}
