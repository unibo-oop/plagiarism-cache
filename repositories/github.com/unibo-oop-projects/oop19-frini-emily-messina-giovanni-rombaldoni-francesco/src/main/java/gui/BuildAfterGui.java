package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import newtopic.NewTopicPan;
import notwist.base.User;
import profile.Profile;
import topic.Topic;
import util.Header;
import util.HolderPan;
import util.TableDiscussion;
import util.UpperPan;

public class BuildAfterGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private TableDiscussion tableDiscussion;
	private User user;
	
	public BuildAfterGui(final User user) {
		this.user = user;
		this.tableDiscussion = new TableDiscussion(user);
		initComponents(tableDiscussion);

	}

	private void initComponents(TableDiscussion tableDiscussion) {

		bodyHolder = new JPanel(); // Holder for body

		// JFrame properties
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		getContentPane().setLayout(new AbsoluteLayout());

		// Get upper panel and add it to the main frame
		upper_panel = new UpperPan();
		upper_panel.setVisible(true);

		getContentPane().add(upper_panel, new AbsoluteConstraints(670, 0, -1, 40));
		


		bodyHolder.setLayout(new CardLayout());
		// Add HolderPan with the homepage tables to card
		holder_panel = new HolderPan(tableDiscussion);
		holder_panel.setVisible(true);
		bodyHolder.add(holder_panel, "homepage_panel");
		newtopic_panel = new NewTopicPan(user);
		newtopic_panel.setVisible(true);
		bodyHolder.add(newtopic_panel, "newtopic_panel");
		profile_panel = new Profile(user);
		profile_panel.setVisible(true);
		bodyHolder.add(profile_panel, "profile_panel");
		topic_panel = new Topic();
		topic_panel.setVisible(true);
		bodyHolder.add(topic_panel, "topic_panel");
		bodyHolder.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));

		// Adding the card!
		getContentPane().add(bodyHolder, new AbsoluteConstraints(0, 120, 1080, 490));

		header_panel = new Header(tableDiscussion, bodyHolder);
		header_panel.setVisible(true);

		getContentPane().add(header_panel, new AbsoluteConstraints(0, 20, 1080, -1));



		// Qua chiamo il pannello no filter con la tabella principale non filtrata,
		// basta togliere il commento /* sotto
		/*
		 * Nofilter drag_panel = new Nofilter(this.actualUser);
		 * drag_panel.setVisible(true);
		 * 
		 * 
		 * 
		 * getContentPane().add(drag_panel, new AbsoluteConstraints(820, 0, 260, 40));
		 * //qua era dove addavo la card, ma per i test è rimasta commentata. /*
		 * CategoryPan category_panel = new CategoryPan();
		 * category_panel.setVisible(true); bodyHolder.add(category_panel, new
		 * org.netbeans.lib.awtextra.AbsoluteConstraints(820, 290, 240, 200));
		 * 
		 */
		pack();

		// <--------------METHODS---------------------------------------->

		// Call Drag the frame
		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				FrameMouseDragged(evt);
			}
		});
		getContentPane().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				FrameMousePressed(evt);
			}
		});
	}

	// <------------------------END---------------------------->

	// Drag the frame
	private int xy;
	private int xx;

	private void FrameMouseDragged(MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xx, y - xy);
	}

	private void FrameMousePressed(MouseEvent evt) {
		xx = evt.getX();
		xy = evt.getY();
	}

	public void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private JPanel bodyHolder;
	private HolderPan holder_panel;
	private UpperPan upper_panel;
	private Header header_panel;
	private NewTopicPan newtopic_panel;
	private Profile profile_panel;
	private Topic topic_panel;

	// End of variables declaration
}