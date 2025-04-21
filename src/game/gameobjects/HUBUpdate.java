package game.gameobjects;

import engine.components.Component;
import engine.objects.GameObject;
import game.scripts.HealthPoint;

public class HUBUpdate extends Component {
    private GameObject player1;
    private GameObject player2;
    private HealthPoint hp1;
    private HealthPoint hp2;
    private GameObject left;
    private GameObject right;

    public HUBUpdate(GameObject player1, GameObject player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void start() {
        super.start();
        if(player1 == null || player2 == null) {
            throw new RuntimeException("HUBUpdate requires two players");
        }
        if(player1.getComponent(HealthPoint.class) == null || player2.getComponent(HealthPoint.class) == null) {
            throw new RuntimeException("HUBUpdate requires HealthPoint component on players");
        }
        hp1 = player1.getComponent(HealthPoint.class);
        hp2 = player2.getComponent(HealthPoint.class);
        if(player1.findObject("Left") == null || player2.findObject("Right") == null) {
            throw new RuntimeException("HUBUpdate requires left and right objects");
        }
        left = player1.findObject("Left");
        right = player2.findObject("Right");
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

    }
}
