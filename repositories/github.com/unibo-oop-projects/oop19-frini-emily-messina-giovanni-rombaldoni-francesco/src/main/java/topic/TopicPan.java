package topic;

import javax.swing.JEditorPane;
import javax.swing.JPanel;

import notwist.base.Discussion;
import notwist.base.User;
import notwist.base.Discussion;
import notwist.base.User;

public class TopicPan extends JPanel{
	private static final long serialVersionUID = 1L;
	/**
     * Creates new Profile Panel
     */
	private User user = null;
	private Discussion discussion = null;
    public TopicPan() {
        initComponents();

    }  
 
    private void initComponents() {

        discussion_panel = new javax.swing.JPanel();
        discussion_part = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        discussion_area = new javax.swing.JEditorPane();
        title_label = new javax.swing.JLabel();
        n_likes = new javax.swing.JLabel();
        like = new javax.swing.JButton();
        dislike = new javax.swing.JButton();
        menu = new javax.swing.JButton();
        date_user = new javax.swing.JLabel();

        discussion_panel.setPreferredSize(new java.awt.Dimension(450, 231));
        discussion_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        discussion_part.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        discussion_area.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(discussion_area);

        title_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        title_label.setText("Titolo");
        title_label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        n_likes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        n_likes.setText("like");
        n_likes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        like.setText("Y");
        like.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dislike.setText("N");
        dislike.setToolTipText("");
        dislike.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menu.setText(".");
        menu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        date_user.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        date_user.setText("Date + Username");
        date_user.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout discussion_partLayout = new javax.swing.GroupLayout(discussion_part);
        discussion_part.setLayout(discussion_partLayout);
        discussion_partLayout.setHorizontalGroup(
            discussion_partLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discussion_partLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(n_likes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(like, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dislike, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 527, Short.MAX_VALUE)
                .addComponent(date_user, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(discussion_partLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(discussion_partLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(discussion_partLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(title_label, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        discussion_partLayout.setVerticalGroup(
            discussion_partLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, discussion_partLayout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addGroup(discussion_partLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(n_likes)
                    .addGroup(discussion_partLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(like)
                        .addComponent(dislike))
                    .addComponent(date_user))
                .addContainerGap())
            .addGroup(discussion_partLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(discussion_partLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(title_label)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(discussion_partLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(40, Short.MAX_VALUE)))
        );

        discussion_panel.add(discussion_part, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, -1, 210));


    }
    public void start() {
        discussion_area.setText(discussion.getDescription());
        this.title_label.setText(discussion.getTitle());
        uploadComments();
       }
    private void uploadComments() {
		// TODO Auto-generated method stub
		
	}

 

    private JEditorPane discussion_area;
  

    private javax.swing.JLabel title_label;

    private javax.swing.JLabel date_user;
    private javax.swing.JPanel discussion_panel;
    private javax.swing.JPanel discussion_part;
    private javax.swing.JButton dislike;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton like;
    private javax.swing.JButton menu;
    private javax.swing.JLabel n_likes;


}
