package user;

import javax.swing.JPanel;

import notwist.base.User;

public class Infos extends JPanel{
	

	private static final long serialVersionUID = 1L;
	/*
	 * Creating the user infos panel
	 */
	public Infos(final User user) {
		
		drawComps(user);
	}
	
	private void drawComps(User user) {
        myinfos_panel = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        mail = new javax.swing.JLabel();
		
        myinfos_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "My Infos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        name.setText("Name:" + user.getUsername());
        name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        mail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mail.setText("Email: " + user.getEmail());
        mail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout myinfos_panelLayout = new javax.swing.GroupLayout(myinfos_panel);
        myinfos_panel.setLayout(myinfos_panelLayout);
        myinfos_panelLayout.setHorizontalGroup(
            myinfos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myinfos_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(myinfos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        myinfos_panelLayout.setVerticalGroup(
            myinfos_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myinfos_panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(mail)
                .addGap(18, 18, 18)
                .addComponent(name)
                .addContainerGap(21, Short.MAX_VALUE))
        );
		
		add(myinfos_panel);
		
	}
	
    private javax.swing.JLabel mail;

    private javax.swing.JPanel myinfos_panel;
    private javax.swing.JLabel name;

}
