package view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControlCore;
import controller.ControlCoreImpl;
import model.ModelCreateTable;
import model.ModelCreateTableImpl;

/**
 * GUI for create new tabel in the selcted database
 * @author Francesco
 *
 */
public class CreateTable {

    private final ModelCreateTable modelCreateTab;
    private final JFrame frame = new JFrame();
    //private List<Attibuto> attributi = new LinkedList<>();//va in model
    private final JLabel tableName;
    private final JButton ok = new JButton("ok");
    private final JButton add = new JButton();

    private final JPanel mainPane = new JPanel();
    private final JPanel attributoPane = new JPanel();
    private final List<JPanel> attributoPaneList = new LinkedList<>();
    
    private final int SIZE_X = 300;
    private final int SIZE_Y = 200;


    //ritorno a scelta creazione db, creazione relazione, interrogazione, gestione tuple(inserimento,modifica,cancellazione) 
    private final JButton back = new JButton();

    /**
     * contructor
     * 
     * @param cc
     *            ControlCore for language and other
     */
    public CreateTable(final ControlCore cc) {
        // TODO Auto-generated constructor stub
        this.modelCreateTab = new ModelCreateTableImpl(cc);
        //final FlowLayout fl = new FlowLayout();
        //fl.setAlignment(FlowLayout.LEFT);
        //this.mainPane.setLayout(fl);
        this.mainPane.setLayout(new BoxLayout(this.mainPane, BoxLayout.Y_AXIS));
        
        this.attributoPane.setLayout(new BoxLayout(this.attributoPane, BoxLayout.Y_AXIS));
        //this.attributoPane.setLayout(new GridLayout(5, 5));
        
        //this.attributoPane.setSize(this.SIZE_X, this.SIZE_Y);
        //this.reloadAttributoList();
        this.add.setText(this.modelCreateTab.getTrText("Add_Name"));


        this.add.addActionListener(e -> {
            this.attributoPaneList.add(this.getAttibutePane());
            this.reloadMainPane();
        });
        this.ok.addActionListener(e -> {
            //fare: check se char deve essere un NUMERO, compreso tra 1 e 255, senza spazi, che andrà convertito da stringa a int
            //this.modelCreateTab.buildTable();
        });
        
        this.frame.setTitle(modelCreateTab.getTrText("Frame_Name"));
        this.tableName = new JLabel(modelCreateTab.getTrText("Table_Name"));
        //dopo il nome della tabella, ci va un elenco di attributi
        //di cui almeno uno sia chiave primaria
        //dentro a un JScrollPane; ex:
        //final JScrollPane scroll = new JScrollPane(textArea); // Pannello con barra
        //l' attributo sarà formato da:
        //nome,tipo,PRIMARY KEY,NOT NULL ;tipo(INT-CHAR) ;se il tipo è una stringa va messa la lunghezza es(ADDRESS CHAR(50))-(City varchar(255))

        //e dopo l'attibuto ci andrà una "X" se si vuole rimuovere

        this.reloadMainPane();
        
        //this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
    
    private JPanel getAttibutePane() {
    // TODO Auto-generated method stub
        final JPanel attributo = new JPanel();
        attributo.setLayout(new GridLayout(1, 1));
        final JLabel nomeAttributo = new JLabel(this.modelCreateTab.getTrText("Nome_Attributo"));
        final JCheckBox primaryKeyBox = new JCheckBox();
        final JLabel primaryKeyLabel = new JLabel("PRIMARY KEY");
        final JCheckBox notNullBox = new JCheckBox();
        final JLabel notNullLabel = new JLabel("NOT NULL");
        final JTextField attributoTextField = new JTextField();
        attributoTextField.setSize(this.SIZE_X, this.SIZE_Y);
        final JComboBox<String> type = new JComboBox<>();
        final JTextField charLong = new JTextField("255");
        charLong.setSize(this.SIZE_X, this.SIZE_Y);

        this.modelCreateTab.getListType().forEach(type::addItem);
        type.setSelectedIndex(0);
        charLong.setEditable(this.modelCreateTab.needNumber(type.getSelectedIndex()));
        type.addActionListener(e -> {
            charLong.setEditable(this.modelCreateTab.needNumber(type.getSelectedIndex()));
        });
        final JButton deleteAttPane = new JButton("X");
        deleteAttPane.addActionListener(e -> {
            this.attributoPaneList.remove(attributo);
            this.reloadMainPane();
        });
        
        attributo.add(nomeAttributo);
        attributo.add(primaryKeyBox);
        attributo.add(primaryKeyLabel);
        attributo.add(attributoTextField);
        attributo.add(type);
        attributo.add(charLong);
        attributo.add(notNullBox);
        attributo.add(notNullLabel);
        attributo.add(deleteAttPane);
        return attributo;
    }

    private void reloadMainPane() {
        // TODO Auto-generated method stub
        System.out.println("reloadAttributoList");
        this.attributoPane.removeAll();
        this.mainPane.removeAll();
        /*
        //final JTextArea prova = new JTextArea("caoa");
        JPanel rtn = new JPanel();
        rtn.setLayout(new BoxLayout(rtn, BoxLayout.Y_AXIS));
        rtn.add(Box.createVerticalGlue());
        final JScrollPane scroll = new JScrollPane();
        scroll.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
        scroll.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
        this.attributoPaneList.forEach(a->scroll.add(a));*/
        this.attributoPaneList.forEach(a->this.attributoPane.add(a));
        
        //this.mainPane.add(scroll);
        this.mainPane.add(this.attributoPane);
        this.mainPane.add(this.add);
        this.mainPane.add(this.ok);
        this.frame.setContentPane(this.mainPane);
        this.frame.pack();
    }

    public static void main(final String[] args) {
        ControlCore cc = new ControlCoreImpl();
        CreateTable ct = new CreateTable(cc);
    }
}
