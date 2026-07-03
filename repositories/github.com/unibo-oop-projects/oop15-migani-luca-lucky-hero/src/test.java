import java.util.Scanner;

/**
 * Created by Disagio on 23/09/2015.

public class test {
    public static void main(String[] args){
        Scanner keyboard=new Scanner(System.in);
        String text="";
        System.out.println("Inserire nome eroe: ");
        text= keyboard.nextLine();
        org.lkyhro.Hero hero=new org.lkyhro.Hero(text);
        System.out.println(hero.toString());
        org.lkyhro.enemy.Boss b1=new org.lkyhro.enemy.Boss("Ranma",20,10,50,200,1);
        org.lkyhro.enemy.Boss b2=new org.lkyhro.enemy.Boss("Gino",30,20,80,300,2);
        while (!"exit".equals(text)){
            System.out.println("Cosa si vuole fare?");
            System.out.println("Sfida il boss: boss");
            System.out.println("Sfida mostro: mostro");
            System.out.println("Sali di livello: levelup");
            System.out.println("Mostra inventario: inventario");
            System.out.println("Chiudi: exit");
            text = keyboard.nextLine();
            switch (text) {
                case "boss":
                    org.lkyhro.Encounter e1=new org.lkyhro.Encounter(hero, b1);
                    while(!e1.isEncounterEnded()){
                        System.out.println("Combattere, usare oggetto o scappare?");
                        text = keyboard.nextLine();
                        switch (text) {
                            case "fight":
                                e1.fight();
                                break;
                            case "use item":
                                e1.useItem();
                                break;
                            case "run":
                                e1.run();
                                break;
                        }
                    }
                    text=null;
                    break;
                case "mostro":
                    org.lkyhro.Encounter e2=new org.lkyhro.Encounter(hero, new org.lkyhro.enemy.BasicMonster("bu",2));
                    while(!e2.isEncounterEnded()){
                        System.out.println("Combattere, usare oggetto o scappare?");
                        text = keyboard.nextLine();
                        switch (text) {
                            case "fight":
                                e2.fight();
                                break;
                            case "use item":
                                e2.useItem();
                                break;
                            case "run":
                                e2.run();
                                break;
                        }
                    }
                    text=null;
                    break;
                case "levelup":
                    hero.giveExperience(150);
                    break;
                case "inventario":
                    System.out.println(hero.getInventory().toString());
                    break;
                case "exit":
                    System.exit(0);
            }
        }
    }
}*/

