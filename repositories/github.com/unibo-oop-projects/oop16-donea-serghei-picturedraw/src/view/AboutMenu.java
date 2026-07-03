package view;

import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Classe che contiene il JMenu About, si preoccupa di dare informazioni utili riguardante la licenza e modalità di uso.
 * @author redim
 *
 */

@SuppressWarnings("serial")
public class AboutMenu extends JMenu {

    private static JDialog dialog;
    
    public AboutMenu() {
        super("About");
        this.addMenuListener(new MenuListener() {


            @Override
            public void menuSelected(MenuEvent arg0) {

                dialog = new JDialog(dialog, "About");
                
                JTextArea text = new JTextArea(5, 10);               
                text.setEditable(false);
                text.setFont(new Font("Serif", Font.ITALIC, 16));
                text.setLineWrap(true);
                text.setWrapStyleWord(true);
                text.setText(" This software is licensed under the MIT License. Copyright (c) 2017\n");
                text.append("Permission is hereby granted, free of charge, to any person obtaining a copy"
                        + "of this software and associated documentation files (the Software), to deal"
                        + "in the Software without restriction, including without limitation the rights"
                        + "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell"
                        + "copies of the Software, and to permit persons to whom the Software is"
                        + "furnished to do so, subject to the following conditions:"
                        + "The above copyright notice and this permission notice shall be included in all"
                        + "+copies or substantial portions of the Software.\n");
                text.append("THE SOFTWARE IS PROVIDED AS IS, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR"
                        + "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,"
                        + "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE"
                        + "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER"
                        + "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,"
                        + "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE"
                        + "SOFTWARE.");
                        
               
                JScrollPane scrollPane = new JScrollPane(text); 
                scrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                dialog.add(scrollPane);
                dialog.setModal(true);
                dialog.setLocationByPlatform(true);
                dialog.setSize(800,400);;
                dialog.setVisible(true);  
                
            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });
    }
}
