package engine.components;

import engine.graphics.Renderer;

import java.awt.*;

public class CoordinateXY extends Component{

    @Override
    public void draw(Renderer g) {
        super.draw(g);
        // ve truc Oxy
        g.setColor(Color.BLUE);
        g.drawLine((int) getGameObject().transform.position.x, (int) getGameObject().transform.position.y, (int) (getGameObject().transform.position.x), (int) (getGameObject().transform.position.y - 100));
        g.setColor(Color.RED);
        g.drawLine((int) getGameObject().transform.position.x, (int) getGameObject().transform.position.y, (int) (getGameObject().transform.position.x + 100), (int) (getGameObject().transform.position.y));
    }
}
