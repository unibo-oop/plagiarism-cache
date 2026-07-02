package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.gui.activity.Square;
import model.gui.person.CircleImpl;
import model.gui.position.Position;
import model.gui.position.RandomPosition;
import model.person.ticket.PersonTicket;
import model.ticket.Ticket;
import view.controller.ViewController;



/**
 * 
 * In this panel there is the creation of the simulation and the drawing of the circles and squares.
 *
 */
public class SimulationPanel extends JPanel {

    private static final long serialVersionUID = 7114066347061701832L;
    private final ViewController controller;
    private Map<PersonTicket, Position<Integer, Integer>> map;
    private List<CircleImpl> adult = new ArrayList<>();
    private List<CircleImpl> baby = new ArrayList<>();
    private List<CircleImpl> pass = new ArrayList<>();

    private static final int ADULT_RADIUS = 20;
    private static final int BABY_RADIUS = 15;
    private static final int PASS_RADIUS = 20;
    private final JFrame frame;
    private final JTextArea legend = new JTextArea();

    /**
     * 
     * @param controller Method that creates the frame with dimension.
     */
    public SimulationPanel(final ViewController controller) {
        this.controller = controller;
        this.frame = new JFrame();
        frame.setTitle("LiveLand");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));


        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.setContentPane(panel);
        panel.setBackground(new Color(216, 216, 216));

        /**
         * Creating the legend
         */
        legend.setText("BLUE = person with pass\n" + "PURPLE = babies\n" + "GREEN = adults");
        legend.setForeground(Color.WHITE);
        legend.setBackground(Color.DARK_GRAY);
        legend.setEditable(false);
        final Font legendFont = legend.getFont().deriveFont(Font.ITALIC, 15f);
        legend.setFont(legendFont);
        this.add(legend);

        /**
         * Creating the stop button.
         */
        final JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        final JButton stop = new JButton("STOP");
        frame.setVisible(true);
        container.add(stop, BorderLayout.NORTH);
        panel.add(container, BorderLayout.EAST);
        //Set Return
        this.map = new HashMap<PersonTicket, Position<Integer, Integer>>();
        panel.add(this, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
        container.add(legend, BorderLayout.CENTER);

        stop.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                SimulationPanel.this.frame.dispose();
                controller.stop();
            }

        });

    }

    /**
     * Method used to update the simulation by passing it the three types of ticket
     * and associating them with the three lists created.
     */
    public void updateSimulation() {
        final Map<PersonTicket, Position<Integer, Integer>> people = this.map;
        final List<CircleImpl> adult = new ArrayList<>();
        final List<CircleImpl> baby = new ArrayList<>();
        final List<CircleImpl> pass = new ArrayList<>();
        for (PersonTicket p : people.keySet()) {
            if (p.getTicket().equals(Ticket.SEASON_PASS)) {
                pass.add(DesignPerson.createPass(people.get(p).getFirst(), people.get(p).getSecond(), PASS_RADIUS));
                System.out.println("Posizione di " + p.toString() + "= x: " + map.get(p).getFirst() + "y: "
                        + map.get(p).getSecond());
            } else if (p.getAge() < 13) {
                baby.add(DesignPerson.createBaby(people.get(p).getFirst(), people.get(p).getSecond(), BABY_RADIUS));
            } else {
                adult.add(DesignPerson.createAdult(people.get(p).getFirst(), people.get(p).getSecond(), ADULT_RADIUS));
            }
            this.adult = adult;
            this.baby = baby;
            this.pass = pass;
            this.repaint();
        }
    }

    /**
     * 
     * Method used to draw the circles and squares that are associated with people
     * and activities respectively.
     */
    public final void paintComponent(final Graphics g) {
        try {
            super.paintComponent(g);
            Image background = Toolkit.getDefaultToolkit().createImage("C:\\Users\\enric\\OneDrive\\Desktop\\Parco.jpeg");
            g.drawImage(background, 0, 0, null);
            if (this.adult.size() != 0) {
                for (CircleImpl adult : adult) {
                    g.setColor(adult.getColor());
                    g.fillOval(adult.getX(), adult.getY(), (int) adult.getRadius(), (int) adult.getRadius());
                }
            }
            if (this.baby.size() != 0) {
                for (CircleImpl baby : baby) {
                    g.setColor(baby.getColor());
                    g.fillOval(baby.getX(), baby.getY(), (int) baby.getRadius(), (int) baby.getRadius());
                }
            }

            if (this.pass.size() != 0) {
                for (CircleImpl pass : pass) {
                    g.setColor(pass.getColor());
                    g.fillOval(pass.getX(), pass.getY(), (int) pass.getRadius(), (int) pass.getRadius());
                }
            }

            for (Square babyFair : this.controller.getModelActivity().getBabyFair()) {
                g.setColor(babyFair.getColor());
                g.fillRect(babyFair.getX(), babyFair.getY(), babyFair.getWidth(), babyFair.getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(babyFair.getX(), babyFair.getY(), babyFair.getWidth(), babyFair.getHeight());
                g.setColor(Color.BLACK);
                g.drawString(babyFair.getName(), babyFair.getX(), babyFair.getY() + 30);
            }

            for (Square adultFair : this.controller.getModelActivity().getAdultFair()) {
                g.setColor(adultFair.getColor());
                g.fillRect(adultFair.getX(), adultFair.getY(), adultFair.getWidth(), adultFair.getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(adultFair.getX(), adultFair.getY(), adultFair.getWidth(), adultFair.getHeight());
                g.setColor(Color.BLACK);
                g.drawString(adultFair.getName(), adultFair.getX(), adultFair.getY() + 30);
            }

            for (Square restaurant : this.controller.getModelActivity().getRestaurant()) {
                g.setColor(restaurant.getColor());
                g.fillRect(restaurant.getX(), restaurant.getY(), restaurant.getWidth(), restaurant.getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(restaurant.getX(), restaurant.getY(), restaurant.getWidth(), restaurant.getHeight());
                g.setColor(Color.BLACK);
                g.drawString(restaurant.getName(), restaurant.getX(), restaurant.getY() + 30);
            }

            for (Square shop : this.controller.getModelActivity().getShop()) {
                g.setColor(shop.getColor());
                g.fillRect(shop.getX(), shop.getY(), shop.getWidth(), shop.getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(shop.getX(), shop.getY(), shop.getWidth(), shop.getHeight());
                g.setColor(Color.BLACK);
                g.drawString(shop.getName(), shop.getX(), shop.getY() + 30);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(e.getMessage());
        }

    }

    public final Map<PersonTicket, Position<Integer, Integer>> getPeopleMap() {
        System.out.println(this.map.size());
        return this.map;
    }

    public final void close() {
        this.frame.dispose();
        this.controller.stop();
    }

    public Dimension getFrameSize() {
        return frame.getSize();
    }

}


