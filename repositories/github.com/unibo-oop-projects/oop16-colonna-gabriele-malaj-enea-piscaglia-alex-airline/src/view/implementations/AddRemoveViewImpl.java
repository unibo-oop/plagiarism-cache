package view.implementations;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import view.interfaces.AddRemoveView;

public abstract class AddRemoveViewImpl implements AddRemoveView {

	private JFrame abstractFrame;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnBack;
	private JLabel lblViewList;
	private JLabel lblTitle;
	private JScrollPane srcList;
	private JList<Object> lstView;
	private DefaultListModel<Object> printedOutList;

	public AddRemoveViewImpl() {

		initializeComponets();

		new FrameSettings(abstractFrame, 700, 590, true);

		createLayout();

	}
	
	private void initializeComponets() {
		abstractFrame = new JFrame();
		btnAdd = new JButton("Aggiungi");
		btnRemove = new JButton("Rimuovi");
		btnBack = new JButton("Indietro");
		lblViewList = new JLabel();
		lblViewList.setText("List Title:");
		lblTitle = new JLabel();
		lblTitle.setText("Title");
		lblTitle.setFont(new Font("Dialog", Font.ITALIC, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		printedOutList = new DefaultListModel<>();
		lstView = new JList<>(printedOutList);
		srcList = new JScrollPane(lstView);
	}

	private void createLayout() {
		
		GroupLayout groupLayout = new GroupLayout(abstractFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(72)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
								.addComponent(lblViewList, Alignment.LEADING)
								.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(srcList, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
							.addGap(27)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblViewList)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(srcList, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnRemove))
					.addGap(27)
					.addComponent(btnBack)
					.addContainerGap())
		);

		abstractFrame.getContentPane().setLayout(groupLayout);
	}

	public void addButtonListener(ActionListener insertListener) {
		btnAdd.addActionListener(insertListener);
	}

	public void removeButtonListener(ActionListener removeListener) {
		btnRemove.addActionListener(removeListener);
	}

	public void backButtonListener(ActionListener backListener) {
		btnBack.addActionListener(backListener);
	}

	public void displayErrorMessage(String error) {
		JOptionPane.showMessageDialog(this.abstractFrame, error);
	}

	public void addItemsToList(Object element) {
		printedOutList.addElement(element);
	}

	public Object getSelectedObjectOfList() {
	        return lstView.getSelectedValue();
	}

        public int getSelectedIndexOfList() {
                return lstView.getSelectedIndex();
        }

	public void close() {
		abstractFrame.dispose();
	}

	protected void setLabelTitle(String newLabelTitle) {
		lblTitle.setText(newLabelTitle);
	}

	protected void setLabelViewedListTitle(String newLabelViewdListTitle) {
		lblViewList.setText(newLabelViewdListTitle);
	}

	protected void setFrameTitle(String newFrameTitle) {
		abstractFrame.setTitle(newFrameTitle);
	}
}
