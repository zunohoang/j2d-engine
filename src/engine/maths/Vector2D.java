package engine.maths;

public class Vector2D {
    public float x;
    public float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D zero() {
        return new Vector2D(0, 0);
    }

    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }
}
