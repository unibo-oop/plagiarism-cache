package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.character.Character;
import model.character.Stats;
import model.container.Box;
import model.container.Shop;
import model.date.DateManager;
import model.ranking.Ranking;

/**
 * 
 *
 */
public final class Serializator {

    /**
     * empty constructor.
     */
    private Serializator() {

    }

    /**
     * 
     * @param ois
     *            is the input stream
     * @param model
     *            is the model to load
     * @throws IOException
     *             if the file is empty
     */
    @SuppressWarnings("unchecked")
    public static void loadFirstInformation(final ObjectInputStream ois, final ModelImpl model) throws IOException {
        List<Stats> list;
        try {
            list = (List<Stats>) ois.readObject();
            model.getCharacter().setList(list);

            List<String> categoryList = list.stream().map(e -> e.getName()).collect(Collectors.toList());
            Map<String, List<Box>> map = new HashMap<>();
            for (String category : categoryList) {
                List<Box> boxList = (List<Box>) ois.readObject();
                Collections.sort(boxList, new Comparator<Box>() {
                    public int compare(final Box o1, final Box o2) {
                        return o1.getFirst().getPrice() - o2.getFirst().getPrice();
                    }
                });
                map.put(category, boxList);
            }
            model.initializeContainers(map);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 
     * @param ois
     *            is the input stream
     * @param model
     *            is the model to load
     * @throws IOException
     *             if the file is empty
     */
    public static void loadInfoCharacter(final ObjectInputStream ois, final ModelImpl model) throws IOException {

        try {
            model.setCharacter((Character) ois.readObject());
            model.setShop((Shop) ois.readObject());
            model.setManager((DateManager) ois.readObject());
            model.setDifficulty((Integer) ois.readObject());
            System.out.println(model.getItemMap());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * used to serialize file initial.
     * 
     * @param oos
     *            is the outputstream
     * @param model
     *            is the model to serialize
     */
    public static void serializeFile(final ObjectOutputStream oos, final ModelImpl model) {
        try {
            oos.writeObject(model.getCharacter());
            oos.writeObject(new Shop(model.getShopMap()));
            oos.writeObject(model.getManager());
            oos.writeObject(model.getDifficulty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * used to serialize file initial.
     * 
     * @param oos
     *            is the outputstream
     * @param model
     *            is the model to serialize
     */
    public static void saveRanking(final ObjectOutputStream oos, final ModelImpl model) {
        try {
            oos.writeObject(new Ranking(model.getRanking()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param ois
     *            is the input stream
     * @param model
     *            is the model to load
     * @throws IOException
     *             if the file is empty
     */
    public static void loadRanking(final ObjectInputStream ois, final ModelImpl model) {
        try {
            model.setRanking((Ranking) ois.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**this method is used for seriallize initial file.
     * @param oos is the outputstream for this method
     *//*
    public static void mainSerialize(final ObjectOutputStream oos) {
        String happiness = "Happiness";
        Item ball = new Item("Ball", 20, 5,"ball.png"); //nome, valore, prezzo
        Item basketball = new Item("Basket Ball", 90, 50,"basket_ball.png");
        Item rugbyball = new Item("Rugby Ball", 140, 90,"rugby_ball.png");
        Item soccerball = new Item("Soccer Ball", 200, 150,"soccer_ball.png");
        Item goldball = new Item("Gold Ball", 320, 250,"gold_ball.png");

        List<Box> happyList = new LinkedList<>(Arrays.asList(new Box(ball), new Box(basketball), new Box(rugbyball), new Box(soccerball), new Box(goldball)));

        String hungry = "Hungry";
        Item hamburger = new Item("Hamburger", 50, 5,"hamburger.png");
        Item strawberry = new Item("Strawberry", 30, 3,"strawberry.png");
        Item chocolate = new Item("Chocolate", 100, 8,"chocolate.png");
        Item pasta = new Item("Pasta", 120, 10, "pasta.png");
        Item meat = new Item("Meat", 200, 20,"meat.png");
        List<Box> foodList = new LinkedList<>(Arrays.asList(new Box(hamburger), new Box(strawberry), new Box(chocolate), new Box(pasta), new Box(meat)));

        String health = "Health";
        Item syrup = new Item("Syrup", 80, 10,"syrup.png");
        Item band = new Item("Band", 140, 20,"bandage.png");
        Item pills = new Item("Pills", 200, 35,"pills.png");
        Item syringe = new Item("Syringe", 300, 50,"syringe.png");
        Item balzarbean = new Item("Balzar's bean", 800, 200,"magical_beans.png");
        List<Box> medicineList = new LinkedList<>(Arrays.asList(new Box(syrup), new Box(band), new Box(pills), new Box(syringe), new Box(balzarbean)));

        String cleanness = "Cleanness";
        Item soap = new Item("Soap", 100, 8,"soap.png");
        Item cream = new Item("Cream", 150, 15,"cream.png");
        Item geldouche = new Item("Gel Douche", 350, 40,"gel_douche.png");
        Item shampoo = new Item("Shampoo", 400, 45,"shampoo.png");
        Item bactDestruction = new Item("Bacterial Destruction", 800, 100,"amuchina.png");
        List<Box> cleanList = new LinkedList<>(Arrays.asList(new Box(soap), new Box(cream), new Box(geldouche), new Box(shampoo), new Box(bactDestruction)));



        try {
            oos.writeObject(new LinkedList<Stats>(Arrays.asList(new Stats(happiness, 1000), new Stats(hungry, 1000), new Stats(health, 1000), new Stats(cleanness, 1000))));
            oos.writeObject(happyList);
            oos.writeObject(foodList);
            oos.writeObject(medicineList);
            oos.writeObject(cleanList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

}
