package engine.components;

import engine.maths.Position;
import engine.maths.Vector2D;

public class Transform extends Component {
    public Vector2D position;
    public float rotation;
    public Vector2D scale;

    public Transform(Vector2D position, float rotation, Vector2D scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
}
