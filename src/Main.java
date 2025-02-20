import engine.core.GameLoop;
import engine.core.KeyInput;
import engine.physics.CollisionManager;
import engine.scenes.SceneManager;
import game.GameScene;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Engine with Rigidbody");
        SceneManager.loadScene(new GameScene());
        GameLoop gamePanel = new GameLoop();

        frame.addKeyListener(new KeyInput());
        frame.setFocusable(true);
        frame.requestFocus();


        frame.add(gamePanel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        new Thread(
                () -> {
                    while (true) {
                        CollisionManager.checkCollisions();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
        SwingUtilities.invokeLater(() -> {
            new Thread(gamePanel).start();
        });
    }
}
