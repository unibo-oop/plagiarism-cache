package boardgames.view;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//abstract factory
public interface GameFrameFactory {
    
    JFrame createFrame(String title);


    JPanel cratePanel(String title);
    
    
    JButton createButton(ActionListener al, String label);
    
    JLabel createJLabel(String s);

}
