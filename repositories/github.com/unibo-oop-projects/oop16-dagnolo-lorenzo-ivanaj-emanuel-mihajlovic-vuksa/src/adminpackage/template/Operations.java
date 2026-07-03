package adminpackage.template;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import admin.GuiFactory;

public abstract class Operations {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JButton camera = GuiFactory.createButton("Camera");
    private JButton piano = GuiFactory.createButton("Piano");
    private JButton account = GuiFactory.createButton("Account");
    private JButton supplementoSoggiorno = GuiFactory.createButton("Supplemento Soggiorno");
    private JButton supplementoCamera = GuiFactory.createButton("Supplemento Camera");
    private JButton tipoCamera = GuiFactory.createButton("Tipo Camera");
    private JButton tipoPensione = GuiFactory.createButton("Tipo Pensione");
    private JButton persone = GuiFactory.createButton("Persone");
    private JButton stagione = GuiFactory.createButton("Stagione");
    private JButton esci = new JButton("Esci");
    private Dimension screenSize;

    /**
     * 
     * @return
     */
    public abstract ActionListener camera();

    /**
     * 
     * @return
     */
    public abstract ActionListener piano();

    /**
     * 
     * @return
     */
    public abstract ActionListener account();

    /**
     * 
     * @return
     */
    public abstract ActionListener supplementoSoggiorno();

    /**
     * 
     * @return
     */
    public abstract ActionListener supplementoCamera();

    /**
     * 
     * @return
     */
    public abstract ActionListener tipoCamera();

    /**
     * 
     * @return
     */
    public abstract ActionListener tipoPensione();

    /**
     * 
     * @return
     */
    public abstract ActionListener persone();

    /**
     * 
     * @return
     */
    public abstract ActionListener stagione();

    /**
     * 
     * @return
     */
    public abstract ActionListener esci();

    /**
     * 
     */
    public Operations() {
        this.frame = new JFrame();
        this.frame.setSize(new Dimension(800, 600));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel(new GridLayout(3, 3));
        this.southPanel = new JPanel();
        this.panel.add(this.camera);
        this.panel.add(this.piano);
        this.panel.add(this.account);
        this.panel.add(this.supplementoCamera);
        this.panel.add(this.supplementoSoggiorno);
        this.panel.add(this.tipoCamera);
        this.panel.add(this.tipoPensione);
        this.panel.add(this.persone);
        this.panel.add(this.stagione);
        this.southPanel.add(this.esci);
        this.camera.addActionListener(this.camera());
        this.piano.addActionListener(this.piano());
        this.account.addActionListener(this.account());
        this.supplementoSoggiorno.addActionListener(this.supplementoSoggiorno());
        this.supplementoCamera.addActionListener(this.supplementoCamera());
        this.tipoCamera.addActionListener(this.tipoCamera());
        this.tipoPensione.addActionListener(this.tipoPensione());
        this.persone.addActionListener(this.persone());
        this.stagione.addActionListener(this.stagione());
        this.esci.addActionListener(this.esci());
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public void shut() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }

}
