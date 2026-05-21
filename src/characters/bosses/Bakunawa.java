package characters.bosses;

import characters.Character;

public class Bakunawa extends Character {

    public Bakunawa() {
        super("Bakunawa", "Boss", 5000, 310, 200,
                0.95, 1.0, 0.80, // accuracy (Basic, Special, Ultimate)
                "Sakmal ng Karimlan",
                "Poot ng Karagatan",
                "Lagim ng Lahong Buwan");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 30;
        this.ultimateSkillStaminaCost = 80;
        
        getStamina().setRegenRange(20, 40);
    }

    @Override
    public void basicAttack(Character target) {
        if (getStamina().spend(basicAttackStaminaCost)) {
            int damage = attack + random.nextInt(75); // chooses a value between 0-15
            System.out.println("Bumuka ang panga ng Bakunawa... isang mabilis at mabigat na atake mula sa dilim!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {
        if (getStamina().spend(specialSkillStaminaCost)) {
            int damage = attack * 2 + random.nextInt(75);
            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Nagyumiyugyog ang lupa at dagat. Isang malagim na alon ang babagsak sa kalaban!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (getStamina().spend(ultimateSkillStaminaCost)) {
            int damage = attack * 4 + random.nextInt(171);
            System.out.println(name + " unleashes its ultimate: " + skill3 + "!");
            System.out.println("Ang buwan ay naglaho. Binalot ng purong takot at kadiliman ang buong paligid!");
            target.takeDamage(damage);
            
            // Example boss mechanic: Heal slightly when using ultimate
            this.hp = Math.min(maxHp, this.hp + 50);
            System.out.println("Sa pagkawala ng liwanag, ang Bakunawa ay nagpanibagong lakas! (Restored 50 HP)");
        }
    }
}
