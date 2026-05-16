package characters.heroes;
import characters.Character;


public class Kaptan extends Character{
    // kaptan specific
    private int stormCharge = 0;

    public Kaptan(){
        super("Kaptan", "Hero", 180, 30, 100,
                "KiDLAT",
                "BAGYOH!",
                "Hampas-Langit!");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 60;
        getStamina().setRegenRange(20, 35);
    }

    @Override
    public void basicAttack(Character target){
        int damage = attack + random.nextInt(15);
        stormCharge++;

        System.out.println("Umaatake ang KIDLAT!");
        System.out.println("Kaptan gathers static energy in the air! (Storm Charges: " + stormCharge + ")");
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target){
       if (getStamina().spend(basicAttackStaminaCost)){
           int damage = attack + random.nextInt(15);
           stormCharge++;
           System.out.print("Pinaulanan ka ng KIDLAT!");
           target.takeDamage(damage);
       }
    }

    @Override
    public void ultimateSkill(Character target){
        if(getStamina().spend(ultimateSkillStaminaCost)){
            int damage = attack + random.nextInt(15);
            // logic dire
            System.out.print(name + "uses HAMPAS LANGIT!");
            target.takeDamage(damage);
            // reset stormcharge after use
            stormCharge = 0;
        }
    }
}
