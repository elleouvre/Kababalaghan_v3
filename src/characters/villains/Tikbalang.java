package characters.villains;
import characters.Character;

public class Tikbalang extends Character {

    public Tikbalang() {
        super("Tikbalang", "Hero", 120, 30, 100, 100,
                "Hoof Stomp",
                "Labyrinth of the Forest",
                "Gingagayuma (Bewitched)");

        // High agility stats - lower health but high damage
        this.specialSkillStaminaCost = 25;
        this.ultimateSkillStaminaCost = 50;
    }

    @Override
    public void basicAttack(Character target) {
        int damage = attack + random.nextInt(15);
        System.out.println("A powerful kick from iron hooves!");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(specialSkillStaminaCost)) {
            // Confusion/Trickster logic: Small damage + high miss chance
            int damage = attack + 10;
            System.out.println(name + " uses " + specialSkillName + "!");
            System.out.println("The forest paths begin to loop. The enemy is disoriented!");
            target.takeDamage(damage);

            // In a real battle manager, you'd add a "Confused" status here
            System.out.println(target.getName() + "'s accuracy has dropped!");
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(ultimateSkillStaminaCost)) {
            // Massive damage based on the myth of the Tikbalang's immense strength
            int damage = (attack * 4) + random.nextInt(20);
            System.out.println(name + " uses " + ultimateSkillName + "!");
            System.out.println("A thick smoke of tobacco fills the air! The Tikbalang strikes from the shadows!");
            target.takeDamage(damage);

            // Speed buff: Restore some stamina to represent supernatural speed
            this.stamina = Math.min(maxStamina, this.stamina + 20);
            System.out.println(name + " moves with supernatural speed, regaining some stamina!");
        }
    }

    @Override
    public void takeDamage(int damage) {
        // Passive: "Trickster's Haze"
        // 20% chance to take 0 damage by "vanishing" into the forest
        if (random.nextInt(100) < 20) {
            System.out.println(name + " vanished into the trees! The attack missed!");
            return;
        }
        super.takeDamage(damage);
    }
}
