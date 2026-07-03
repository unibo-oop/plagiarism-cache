package view;

import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class ChoseMenuBar extends JMenuBar {

    private FileMenu theFileChoser;
    private AboutMenu about;
    
    public ChoseMenuBar() {
        theFileChoser = new FileMenu();
        about = new AboutMenu();
        this.add(theFileChoser);
        this.add(about);
    }
    
}
