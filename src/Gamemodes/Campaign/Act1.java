package Gamemodes.Campaign;

import characters.Character;
import characters.heroes.*;
import characters.villains.*;
import characters.CharacterFactory;
import Gamemodes.BattleSystem;
import java.util.*;


public class Act1 {
    private Scanner scanner;
    private BattleSystem battleSystem;

    public Act1(Scanner scanner) {
        this.scanner = scanner;
        this.battleSystem = new BattleSystem(scanner);
    }
    public void start() {
        displayIntro();

        //Get User and Randomized Enemy
        Character player = chooseCampaignCharacter();
        Character stage1Enemy = generateRandomHeroEnemy();

        displayFight1(stage1Enemy);

        //startfight1
        battleSystem.startSingleplayer(player, stage1Enemy);
            //if lose then ends
            if (!player.isAlive()) {
                System.out.println("\n[GAME OVER] Yung mundo ay naging ka diliman...");
                return;
            }

        displayWin1(player, stage1Enemy);

        Character corruptedApolaki = new Apolaki();

        displayFight2();
        //startfight2
        battleSystem.startSingleplayer(player, corruptedApolaki);
            //if lose then ends
            if (!player.isAlive()) {
                System.out.println("\n[GAME OVER] Nilamon ka ng bagsik ni Apolaki.");
                return;
            }
        displayWin2();
        displayOutro();
    }

    private Character chooseCampaignCharacter() {
        System.out.println("\nChoose your side for the Campaign:");
        System.out.println("1. Hero");
        System.out.println("2. Villain");
        System.out.print("Choice: ");

        int side = scanner.nextInt();
        scanner.nextLine();

        if (side == 1) {
            return selectCharacter(CharacterFactory.getAllHeroes(), "Hero");
        } else if (side == 2) {
            return selectCharacter(CharacterFactory.getAllVillains(), "Villain");
        } else {
            System.out.println("Invalid choice! Defaulting to Hero.");
            return CharacterFactory.getAllHeroes().get(0);
        }
    }
    private Character selectCharacter(ArrayList<Character> characters, String type) {
        System.out.println("\nChoose your " + type + ":");
        System.out.println("--------------------------------------");

        for (int i = 0; i < characters.size(); i++) {
            Character c = characters.get(i);
            System.out.printf("%d. %-15s HP: %3d | ATK: %3d | STA: %3d%n",
                    (i + 1), c.getName(), c.getMaxHp(), c.getAttack(), c.getStaminaMax());
            System.out.printf("   Skills: %s, %s, %s%n",
                    c.getBasic(), c.getSpecial(), c.getUltimate());
        }

        System.out.print("\nChoice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice! Defaulting to first character.");
            return characters.get(0);
        }

        Character selected = characters.get(choice - 1);
        System.out.println("\n[You chose: " + selected.getName() + "]");
        return selected;
    }
    private Character generateRandomHeroEnemy() {
        ArrayList<Character> randomHeroes = new ArrayList<>();
        randomHeroes.add(new Kaptan());
        randomHeroes.add(new Magwayen());
        randomHeroes.add(new MariaMakiling());

        Collections.shuffle(randomHeroes);
        return randomHeroes.get(0);
    }
    public void displayIntro() {
        System.out.println();
        System.out.println("         ARCADE CAMPAIGN: ACT 1            ");
        System.out.println("         PAGKUKULAM SA KORAPSYON           ");
        System.out.println("Ang mga Hari ng Lupa at Langit ay nawalan ng kontrol");
        System.out.println("GOAL: TULONGIN MO ANG MGA HEROES AT ILIGTAS MO SILA!");
        System.out.println();
        System.out.print("Press Enter to start and choose your Character..");
        scanner.nextLine();
    }
    public void displayFight1(Character stage1Enemy) {
     System.out.println("STAGE 1: ANG PAG SISIMULA NG DILIM ");
        System.out.println("");
        System.out.println("'Ang liwanag... unti-unting nawawala'");
        System.out.println("Isang nagwawalang tagapangalaga ang humaharang sa iyo!");
        System.out.println("Si " + stage1Enemy.getName() + " ay nilamon ng pulang hamog!");
        System.out.print("Press Enter to defend and fight!");
        scanner.nextLine();
     }

     public void displayWin1(Character player, Character stage1Enemy){
            System.out.println("✨ " + stage1Enemy.getName() + " collapses... Naalis ang korapsyon sa kanyang katawan!");
            System.out.println(stage1Enemy.getName() + ": 'Salamat... nabasag ang sumpa sa aking isipan.'");
            System.out.println(stage1Enemy.getName() + ": 'Ngunit magmadali ka! Ang Bathalang Mandirigma ay nasa dulo, tuluyan nang nawalan ng bait!'");
            System.out.print("Press Enter to continue and face the final threat...");
            scanner.nextLine();
            //clean stats
            player.resetAll();
     }
     public void displayFight2() {
        System.out.println("STAGE 2: Nilamon ng Natuyong Sumpa ang Araw");
        System.out.println("Nagliliyab ang paligid sa galit at init ni Apolaki!");
        System.out.println("Apolaki: 'HINDI NIYO MAPIPIGILAN ANG KADILIMAN! UMALIS KAYO DITO!'");
        System.out.print("Press Enter to engage to save Apolaki!");
        scanner.nextLine();
     }
    public void displayWin2() {
        System.out.println();
        System.out.println("Apolaki drops to his knees. The crimson eyes fade back to regular light.");
        System.out.println("Apolaki: 'Aking isipan... bumalik na sa normal. Teka... ang kapatid ko!'");
        System.out.println("Apolaki: 'Ang korapsyon sa amin ay isang patibong lamang...'");
        System.out.println("Apolaki: 'Si Mayari! Ang aking kapatid ay inaatake ngayon! Kailangan niya ng tulong!'");
        System.out.print("Press Enter to look up at the corrupted heavenly sky...");
        scanner.nextLine();
    }
    public void displayOutro(){
        System.out.println("          !!! WARNING !!!                  ");
        System.out.println("Tumingala ka sa kalangitan kasama si Apolaki.");
        System.out.println("Ang gabi ay unti-unting binabalot ng kawalan.");
        System.out.println("Habang nakikipaglaban kayo, ang mga buwan ay unti-unting ninanakaw!");
        System.out.println("Ang mga pitong(7) nga mga buwan ni Mayari ay nawawala!");
        System.out.println("");
        System.out.println("===========================================");
        System.out.println("            ACT 1 COMPLETED!               ");
        System.out.println("===========================================");
    }
}
