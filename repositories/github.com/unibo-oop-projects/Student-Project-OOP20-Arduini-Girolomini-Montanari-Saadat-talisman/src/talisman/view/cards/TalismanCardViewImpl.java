package talisman.view.cards;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;

import talisman.util.PathUtils;
import talisman.view.ImagePanel;

/**
 * The implementation for TalismanCardView.
 * 
 * @author Abtin Saadat
 *
 */
public class TalismanCardViewImpl extends ImagePanel implements TalismanCardView {
    private ImagePanel image;
    private JTextArea text;
    private JTextArea name;

    public TalismanCardViewImpl(final String imagePath, final String text, final String name) {
        super(PathUtils.getDevImagePath("cardbg"));
        this.image = new ImagePanel(imagePath);
        final LayoutManager layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.text = new JTextArea(1, 1);
        this.text.setText(text);
        this.text.setForeground(Color.WHITE);
        this.text.setBackground(Color.BLACK);
        this.text.setLineWrap(true);
        this.text.setWrapStyleWord(true);
        this.text.setEditable(false);
        this.text.setAlignmentX(CENTER_ALIGNMENT);
        this.text.setAlignmentY(CENTER_ALIGNMENT);
        this.text.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.name = new JTextArea(1, 1);
        this.name.setText(name);
        this.name.setForeground(Color.WHITE);
        this.name.setBackground(Color.BLACK);
        this.name.setLineWrap(true);
        this.name.setWrapStyleWord(true);
        this.name.setEditable(false);
        this.name.setAlignmentX(CENTER_ALIGNMENT);
        this.name.setAlignmentY(CENTER_ALIGNMENT);
        this.name.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.setMaximumSize(new Dimension(50, 50));
        this.add(this.name, BorderLayout.PAGE_START);
        this.add(this.image);
        this.add(this.text, BorderLayout.PAGE_END);
        final Dimension size = new Dimension(256, 384);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setBorder(BorderFactory.createLineBorder(Color.white, 5));
    }

    /**
     * Used to change the viewing card.
     * 
     * @param imagePath
     * @param text
     * @param name
     */
    public void setView(final String imagePath, final String text, final String name) {
        this.image.setImage(imagePath);
        this.text.setText(text);
        this.name.setText(name);
    }
}
