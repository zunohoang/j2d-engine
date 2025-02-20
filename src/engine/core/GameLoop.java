package engine.core;

import engine.physics.CollisionManager;
import engine.scenes.SceneManager;
import engine.scenes.SceneManager;
import game.GameScene;

import javax.swing.*;
import java.awt.*;

public class GameLoop extends JPanel implements Runnable {
    private boolean running = true;

    public GameLoop() {
        setFocusable(true);
    }

    @Override
    public void run() {
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1_000_000_000 / TARGET_FPS; // 16.67ms mỗi frame

        long lastLoopTime = System.nanoTime();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            long elapsedTime = now - lastLoopTime;
            lastLoopTime = now;

            delta += (double) elapsedTime / OPTIMAL_TIME;

            while (delta >= 1) {
                SceneManager.update(1.0f / TARGET_FPS); // Cập nhật logic game
                delta--;
            }

            repaint(); // Vẽ lại màn hình

            // Đợi đến khi đủ thời gian cho frame tiếp theo
            while (System.nanoTime() - lastLoopTime < OPTIMAL_TIME) {
                // Không làm gì, chỉ chờ đủ thời gian
            }
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        SceneManager.draw(g);
    }
}

