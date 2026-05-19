package characters;
import java.util.Random;

public class StaminaSystem {
    private int current;
    private int max;
    private int regenMin;
    private int regenMax;

    private Random random = new Random();

    public StaminaSystem(int max, int regenMin, int regenMax){
        this.max = max;
        this.regenMin = regenMin;
        this.regenMax = regenMax;
        this.current = max; // Initialize current stamina to max
    }

    public boolean spend(int cost){
        if (current >= cost){
            current -= cost;
            return true;
        }

        System.out.println("Insufficient stamina");
        return false;
    }

    public void regen(){
        int amount = regenMin + random.nextInt(regenMax - regenMin + 1); // Random regen between min and max
        int oldStamina = current; // Copy of old stamina
        current = Math.min(max, current + amount);
        int actualRegen = current - oldStamina;
        System.out.println("Regenerated " + actualRegen + " stamina! (Current: " + current + "/" + max + ")");
    }

    public void add(int amount){
        current = Math.min(max, current + amount); // Prevent exceeding max stamina
    }

    public void reduce(int amount){
        current = Math.max(0, current - amount); // Prevent negative stamina
    }

    public void reset(){
        current = max;
    }

    public int getCurrent() { return current; }
    public int getMax() { return max; }

    public void setRegenRange(int min, int max){
        this.regenMin = min;
        this.regenMax = max;
    }
    public int getRegenMin(){return regenMin; }
    public int getRegenMax(){return regenMax; }

}
