package game.scripts;

import engine.components.Component;
import engine.utils.Logger;

public class HealthPoint extends Component {
    private int maxHealth;
    private int currentHealth;

    public HealthPoint(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        Logger.log(this, "PLAYER TAKE DAMAGE: " + damage);
    }

    public void heal(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
