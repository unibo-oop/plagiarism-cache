package util;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import main.Loader;

import notwist.database.DBDiscussion;
import notwist.database.DBDiscussionImpl;

public class Header extends JPanel {

	private static final long serialVersionUID = 1L;
	private DBDiscussion dbdiscussion = new DBDiscussionImpl();
//	private User actualUser = null;
	private Loader loader;
	private CardLayout card;
	private JPanel parent;

	public Header(TableDiscussion tableDiscussion, JPanel parent) {
		this.parent = parent;
		this.card = (CardLayout) parent.getLayout();
		drawComp(tableDiscussion);
	}

	private void drawComp(TableDiscussion tableDiscussion) {

		header_panel = new JPanel();
		homepage_button = new JLabel();
		category_filter = new JButton(); // Combobox per le categorie
		profile_icon = new JLabel(); // TBA
		bell_icon = new JLabel(); // TBA
		new_discussion = new JButton();
		search_field = new JTextField(); // Spazio filtro
		search_button = new JButton();

		homepage_button.setFont(new Font("Bauhaus 93", 0, 18)); // NOI18N
		homepage_button.setText("NOTWIST");
	//	homepage_button.setIcon(new ImageIcon("img/logo.png"));
	//	homepage_button.setHorizontalAlignment(SwingConstants.CENTER);
	//	homepage_button.setHorizontalTextPosition(SwingConstants.CENTER);
		
        homepage_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                homepage_buttonMouseClicked(evt);
            }
        });

		category_filter.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		category_filter.setText("Temporanea tabella");

		category_filter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				category_filterMouseClicked(evt);
			}
		});

		profile_icon.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		profile_icon.setText("P");
		profile_icon.setToolTipText("Futura icona profilo");

		bell_icon.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		bell_icon.setText("B");
		bell_icon.setToolTipText("Futura icona notifica");

		new_discussion.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		new_discussion.setText("Crea una nuova discussione");

		new_discussion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				new_discussionMouseClicked(evt);
			}
		});

		search_field.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		search_field.setText("Search..");
		search_field.setMargin(new Insets(2, 2, 2, 3));
		search_field.setPreferredSize(new Dimension(7, 25));
		search_field.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (search_field.getText().equals("Search.."))
					search_field.setText("");

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (search_field.getText().equals(""))
					search_field.setText("Search..");
			}

		});
		search_button.setText("Go");

		search_button.setPreferredSize(new Dimension(45, 25));
		search_button.addActionListener(e -> {
			if (search_field.getText().equals("") || search_field.getText().equals("Search.."))
				tableDiscussion.refreshTableDiscussion();
			else
				tableDiscussion.refreshTableDiscussion(search_field.getText());
		});
 
        javax.swing.GroupLayout header_panelLayout = new javax.swing.GroupLayout(header_panel);
        header_panel.setLayout(header_panelLayout);
        header_panelLayout.setHorizontalGroup(
            header_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(header_panelLayout.createSequentialGroup()
            		 .addGap(20, 20, 20)
                .addComponent(homepage_button)
                .addGap(40, 40, 40)
                .addComponent(category_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addComponent(search_field, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(new_discussion)
                .addGap(27, 27, 27)
                .addComponent(bell_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(profile_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        header_panelLayout.setVerticalGroup(
            header_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header_panelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(header_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(new_discussion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(header_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(category_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(profile_icon)
                        .addComponent(bell_icon)
                        .addComponent(search_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(homepage_button)))
                .addGap(16, 16, 16))
        );

		  profile_icon.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent evt) {
	                profile_iconMouseClicked(evt);
	            }
	        });
		add(header_panel);

	}

	private void new_discussionMouseClicked(MouseEvent evt) {
		card.show(parent, "newtopic_panel");
	}
	private void homepage_buttonMouseClicked(MouseEvent evt) {
		card.show(parent, "homepage_panel");
	}
	private void profile_iconMouseClicked(MouseEvent evt) {
		card.show(parent, "profile_panel");
	}

	private void category_filterMouseClicked(MouseEvent evt) {
		card.show(parent, "topic_panel");
	}
	/*
	 * private void search_buttonActionPerformed(java.awt.event.ActionEvent evt)
	 * {//GEN-FIRST:event_search_buttonActionPerformed //Button Search
	 * search_button.addActionListener(e ->{ if(search_field.getText().equals("") ||
	 * search_field.getText().equals("Search.."))
	 * jTable1.setModel(this.loadDiscussion()); else
	 * jTable1.setModel(this.loadDiscussion(search_field.getText())); }); }
	 */

	private JLabel bell_icon;
	private JButton category_filter;
	private JPanel header_panel;
	private JLabel homepage_button;
	private JButton new_discussion;
	private JLabel profile_icon;
	private JButton search_button;
	private JTextField search_field;
}
