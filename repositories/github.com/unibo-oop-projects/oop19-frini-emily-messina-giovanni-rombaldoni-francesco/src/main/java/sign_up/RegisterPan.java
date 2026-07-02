package sign_up;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import com.formdev.flatlaf.FlatLightLaf;

import notwist.database.DBUserImpl;

public class RegisterPan extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public RegisterPan() {
		
		drawComp();
	}
	
	private void drawComp() {
		register_label = new JEditorPane();
		
		termsDialog = new JDialog();
    	termsPanel = new JPanel();
        termsTitle = new JLabel();
        jScrollPane1 = new JScrollPane();
        termsText = new JEditorPane("text/html", "");
        termsAccept = new JButton();
        register_panel = new JPanel();
        pssw_label = new JLabel();
        mail_field = new JTextField();
        jSeparator3 = new JSeparator();
        jSeparator5 = new JSeparator();
        pssw_field = new JPasswordField();
        user_label = new JLabel();
        signup_button = new JButton();
        
        mail_label = new JLabel();
        jSeparator4 = new JSeparator();
        username_field = new JTextField();
        jCheckBox1 = new JCheckBox();
        terms_button = new JButton();
      
		
		
		
 //<------------------Draw register panel ------------>
        
        //Dialog
        termsTitle.setText("Informativa Privacy:");
        termsTitle.setFont(new Font("Tahoma", 0, 18));
        importTerms();
        termsText.setEditable(false);
        termsText.setOpaque(false);
        jScrollPane1.setViewportView(termsText);
     

        termsAccept.setText("Ok, ho letto");
        termsAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                termsAcceptActionPerformed(evt);
            }
        });

        GroupLayout termsPanelLayout = new GroupLayout(termsPanel);
        termsPanel.setLayout(termsPanelLayout);
        termsPanelLayout.setHorizontalGroup(
            termsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(termsPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(termsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
                    .addComponent(termsTitle)
                    .addComponent(termsAccept, GroupLayout.Alignment.TRAILING))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        termsPanelLayout.setVerticalGroup(
            termsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(termsPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(termsTitle)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(termsAccept)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        GroupLayout termsDialogLayout = new GroupLayout(termsDialog.getContentPane());
        termsDialog.getContentPane().setLayout(termsDialogLayout);
        termsDialogLayout.setHorizontalGroup(
            termsDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(termsPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        termsDialogLayout.setVerticalGroup(
            termsDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(termsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        //Setting panel
        register_panel.setLayout(new AbsoluteLayout());

        
        //Username
        user_label.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        user_label.setText("Username");
        register_panel.add(user_label, new AbsoluteConstraints(53, 80, -1, -1));
        
        username_field.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        username_field.setActionCommand("<Not Set>");
        username_field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                username_fieldFocusGained(evt);
            }
        });

        register_panel.add(username_field, new AbsoluteConstraints(53, 110, 290, 25));
        
        //Separator
        jSeparator5.setBackground(new Color(0,0,0));
        jSeparator5.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(194, 192, 192)));
        jSeparator5.setMinimumSize(new Dimension(30, 20));
        jSeparator5.setPreferredSize(new Dimension(30, 20));
        jSeparator5.setBackground(new Color(255, 255, 255));
        register_panel.add(jSeparator5, new AbsoluteConstraints(53, 140, 290, -1));

        //Mail
        
        mail_label.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        mail_label.setText("Mail");
        register_panel.add(mail_label, new AbsoluteConstraints(53, 170, -1, -1));
        
        mail_field.setFont(new Font("Tahoma", 0, 14)); // NOI18N      
        mail_field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                mail_fieldFocusGained(evt);
            }
        });   
        register_panel.add(mail_field, new AbsoluteConstraints(53, 200, 290, 25));
        
        //Separator
        jSeparator3.setBackground(new Color(0,0,0));
        jSeparator3.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(194, 192, 192)));
        jSeparator3.setMinimumSize(new Dimension(30, 20));
        jSeparator3.setPreferredSize(new Dimension(30, 20));
        jSeparator3.setBackground(new Color(255, 255, 255));
        register_panel.add(jSeparator3, new AbsoluteConstraints(53, 230, 290, -1));
        
        //Password 
        pssw_label.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        pssw_label.setText("Password");       
        register_panel.add(pssw_label, new AbsoluteConstraints(53, 260, -1, -1));

        pssw_field.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        pssw_field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                pssw_fieldFocusGained(evt);
            }
        });
        register_panel.add(pssw_field, new AbsoluteConstraints(53, 290, 290, 25));



        //Separator
        
        jSeparator4.setBackground(new Color(0,0,0));
        jSeparator4.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(194, 192, 192)));
        jSeparator4.setMinimumSize(new Dimension(30, 20));
        jSeparator4.setPreferredSize(new Dimension(30, 20));
        jSeparator4.setBackground(new Color(255, 255, 255));
        register_panel.add(jSeparator4, new AbsoluteConstraints(53, 320, 290, -1));

        signup_button.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        signup_button.setText("REGISTER");
      
        register_panel.add(signup_button, new AbsoluteConstraints(53, 360, 290, 60));

        //Informativa
        jCheckBox1.setFont(new Font("Tahoma", 0, 10)); // NOI18N
        jCheckBox1.setText("Accetto i termini e le condizioni.");
        register_panel.add(jCheckBox1, new AbsoluteConstraints(53, 330, -1, -1));

        terms_button.setText("Informativa");
        terms_button.setForeground(new Color(255, 99, 71));
        terms_button.setFont(new Font("Tahoma", 0, 10));
        terms_button.setBorderPainted(false);
        terms_button.setContentAreaFilled(false);
        
        
        terms_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                terms_buttonActionPerformed(evt);
            }
        });
        register_panel.add(terms_button, new AbsoluteConstraints(220, 330, -1, -1));
        
        //Infos
        register_label.setContentType("text/html");
       	register_label.setOpaque(false);
        register_label.setText("Hai già un account? Loggati <font color =FF6347>qui </font>.");
        register_label.getCaret().setVisible(false);
        register_label.setToolTipText("Se hai già un account, passa al login.");
        register_panel.add(register_label, new AbsoluteConstraints(53, 440, -1, -1));



        add(register_panel);
        
        
        
        register_label.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent evt) {
                login_linkMouseClicked(evt);
             }
         });
        
        
        mail_field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                mail_fieldFocusGained(evt);
            }
        });
        
        pssw_field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                pssw_fieldFocusGained(evt);
            }
        });
        
        username_field.addFocusListener(new FocusAdapter(){
        	public void focusGained(FocusEvent evt) {
        		username_fieldFocusGained(evt);
        	}
        });
        
        
        
        //Register method
        signup_button.addActionListener(e ->{
        	if(!jCheckBox1.isSelected())
        		JOptionPane.showMessageDialog(null, "You have to accept our rules before sign up :0");
        	else if(new DBUserImpl().register(this.getUsername(), this.getrPassword(), this.getEmail(), false)) {
        		JOptionPane.showMessageDialog(null, "Account created succesfully! :) ");
        		
        	}
        		
        	else
        		JOptionPane.showMessageDialog(null, "Error, something goes wrong!:(\nRetry ");
        		
        });
      }
        
        
        
        private void termsAcceptActionPerformed(ActionEvent evt) {

        	termsDialog.dispose();     
        }
        private void terms_buttonActionPerformed(ActionEvent evt) {
            try {
            	UIManager.getLookAndFeel();
            	 SwingUtilities.updateComponentTreeUI(termsDialog);    	
                termsDialog.getContentPane();         
                termsDialog.setSize(650, 450);  
                termsDialog.pack();
                termsDialog.setLocationRelativeTo(getParent()); 
                termsDialog.setVisible(true);
                Image icon = Toolkit.getDefaultToolkit().getImage("img/icon.jpg");    
                termsDialog.setIconImage(icon);  
                
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            	ex.printStackTrace();
            }
        }
        
        
        public String getUsername() {
        	return username_field.getText();
        }
        
        public String getEmail() {
        	return mail_field.getText();
        }
        
        public String getrPassword() {
        	return pssw_field.getText();
        }
       
    	private void importTerms() {
    		BufferedReader buf;
    		try {
    			buf = new BufferedReader(new FileReader("docs/Terms.txt"));
    			termsText.read(buf, null);
    		} catch (IOException e) {
    			System.out.println("File Terms.txt not found! ");
    			e.printStackTrace();
    		}
    	}
        
        
      
        
        //Focus
        private void mail_fieldFocusGained(FocusEvent evt) {
            pssw_label.setForeground(new Color(0, 0, 0));
            mail_label.setForeground(new Color(135, 175, 218));
           user_label.setForeground(new Color(0,0,0));
        }
        private void pssw_fieldFocusGained(FocusEvent evt) {
            pssw_label.setForeground(new Color(135, 175, 218));
            mail_label.setForeground(new Color(0, 0, 0));
           user_label.setForeground(new Color(0,0,0));
        }
        
        private void username_fieldFocusGained(FocusEvent evt) {
            pssw_label.setForeground(new Color(0, 0, 0));
            mail_label.setForeground(new Color(0, 0, 0));
           user_label.setForeground(new Color(135, 175, 218));
        } 
        


        private void login_linkMouseClicked(MouseEvent evt) {
            CardLayout card = (CardLayout)getParent().getLayout();
            card.show(getParent(), "login");
        }
        
       
  
	
    private JCheckBox jCheckBox1;
    private JDialog termsDialog;
    private JLabel pssw_label;
    private JLabel user_label;

    private JButton signup_button;

    private JPanel register_panel;
    private JTextField mail_field;
    private JPasswordField pssw_field;
    private JTextField username_field;
    private JEditorPane register_label;
    private JLabel mail_label;
    private JLabel termsTitle;
    private JPanel termsPanel;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator3;
    private JSeparator jSeparator4;
    private JEditorPane termsText;
    private JButton termsAccept;
    private JButton terms_button;
    private JSeparator jSeparator5;
}
