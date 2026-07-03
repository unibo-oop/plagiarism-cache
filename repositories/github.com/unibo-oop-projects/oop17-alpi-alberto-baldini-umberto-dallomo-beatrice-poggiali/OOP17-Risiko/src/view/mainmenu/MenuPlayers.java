package view.mainmenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.SetupGame;
import data.Colors;

public class MenuPlayers {
	 /**
     * 
     * @param n:the number of players
     */
    public static void menuPlayers(final int n) {
        LinkedList<JTextField> players = new LinkedList<>();
        
        MenuIni.getPanel().add(new JLabel("Enter Player Names"));

        for (int i = 0; i < n; i++) {
            players.add(new JTextField("  Player   " + i));
            players.get(i).setBackground(Colors.values()[i+1].getColor());
            players.get(i).addMouseListener(new MouseListener() {

            	@Override
                public void mouseClicked(final MouseEvent arg0) {
                }

                @Override
                public void mouseEntered(final MouseEvent arg0) {
                }

                @Override
                public void mouseExited(final MouseEvent arg0) {
                }

                @Override
                public void mousePressed(final MouseEvent arg0) {
                	((JTextField) (arg0.getSource())).setText("");
                }

                @Override
                public void mouseReleased(final MouseEvent arg0) {
                }
            });
        }

        players.forEach(a -> {
            MenuIni.getPanel().add(a);
        });

        JButton pronto = new JButton("READY");
        pronto.addActionListener(e -> {
                for (int i = 0; i < n; i++) {
                	MenuIni.getPlayers().put(players.get(i).getText(), i + 1);
                }
                MenuIni.getFrame().setVisible(false);
                SetupGame.buildGame(MenuIni.getPlayers());
        });
        MenuIni.getPanel().add(pronto);
        MenuIni.getPanel().add(MenuIni.getExit());
        MenuIni.getFrame().getContentPane().add(MenuIni.getPanel());
        MenuIni.getFrame().validate();
        MenuIni.getFrame().pack();
    }
}
