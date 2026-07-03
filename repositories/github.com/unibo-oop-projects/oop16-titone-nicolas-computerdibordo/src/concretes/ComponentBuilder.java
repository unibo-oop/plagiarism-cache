package concretes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.TButton;
import components.TComponent;
import components.TFrame;
import components.TLabel;
import components.TPanel;
import components.TPosition;

/**
 * This class is used to generate {@link TComponent} instances.
 * @see TFrameImpl 
 * @see TPanelImpl
 * @see TButtonImpl
 * @see TLabelImpl
 */
public class ComponentBuilder{ //pattern builder
	
	/**
	 * This enum maps {@link TPosition TPositions} with the related {@link BorderLayout} position
	 * Provides getter method for each position
	 */
	public enum Position {
		
		TOP(TPosition.TOP.getStringValue() ,BorderLayout.PAGE_START), BOT(TPosition.BOT.getStringValue(), BorderLayout.PAGE_END), 
		LEFT(TPosition.LEFT.getStringValue(), BorderLayout.LINE_START), RIGHT(TPosition.RIGHT.getStringValue(), BorderLayout.LINE_END),
		MID(TPosition.MID.getStringValue(), BorderLayout.CENTER);
		
		/**
		 * String representation of TimePosition
		 */
		private final String tPosition;
		
		/**
		 * String representation of BorderLayout position
		 */
		private final String position;
		
		private Position(String tPosition, String position) {
			this.tPosition = tPosition;
			this.position = position;
		}
		
		/**
		 * getter for the {@link TPosition} representation of thie enum value
		 * @return a string
		 */
		private String getTPosition() {
			return this.tPosition;
		}
		
		/**
		 * * getter for the {@link Position} representation of this enum value
		 * @return a string
		 */
		private String getPosition() {
			return this.position;
		}
		
		/**
		 * Static method to get the {@link Position} string representation of the {@link TPosition} passed as parameter.
		 * @param tPosition position to convert
		 * @return the related Position as string or null if there are no matches.
		 */
		public static String getPositionOf(String tPosition) {
			
			for(Position e : Position.values()) {
				if(e.getTPosition().equals(tPosition)) {
					return e.getPosition();
				}
			}
			
			return null;
		}
		
		
	}
	
	/**
	 * Singleton of {@link ComponentBuilder}
	 */
	private static ComponentBuilder builder; //pattern singleton
	
	private ComponentBuilder() {
		
		super();
	}
	
	/**
	 * Static method to get the {@link ComponentBuilder} instance.
	 * @return the TBuilder instance
	 */
	public static ComponentBuilder getInstance() {
		
		if(Objects.isNull(builder)) {
			builder = new ComponentBuilder();
		}
		
		return builder;
	}
	
	/**
	 * method to build a {@link TFrame}
	 * @return a {@link TFrame}
	 */
	public TFrame frame() {
		return new TFrameImpl();
	}
	
	/**
	 * method to build a {@link TButton}
	 * @return a {@link TButton}
	 */
	public TButton button() {
		return new TButtonImpl();
	}
	
	/**
	 * method to build a {@link TPanel}
	 * @return a {@link TPanel}
	 */
	public TPanel panel() {
		return new TPanelImpl();
	}
	
	/**
	 * method to build a {@link TLabel}
	 * @return a {@link TLabel}
	 */
	public TLabel label() {
		return new TLabelImpl();
	}
	
	/**
	 * method to build a frame with specific parameters
	 * @param width
	 * @param height
	 * @return a {@link TFrame}
	 */
	public TFrame frame(int width, int height) {
		
		return new TFrameImpl(width, height);
	}
	
	/**
	 * method to build a button with specific parameters
	 * @param text
	 * @return a {@link TButton}
	 */
	public TButton button(String text) {
		
		return new TButtonImpl(text);
	}
	
	/**
	 * method to build a panel with specific parameters
	 * @param width
	 * @param height
	 * @return a {@link TPanel}
	 */
	public TPanel panel(int width, int height) {
		
		return new TPanelImpl(width, height);
	}
	
	/**
	 * method to build a label with specific parameters
	 * @param text
	 * @return a {@link TPanel}
	 */
	public TLabel label(String text) {
		return new TLabelImpl(text);
	}
	
	/**
	 * This class is an implementation of {@link TButton}
	 * @see JButton
	 * @see TButton
	 */
	private class TButtonImpl extends JButton implements TButton {

		public TButtonImpl() {
			super();
		}
		
		public TButtonImpl(String text) {
			this();
			this.addText(text);
		}
		
		@Override
		public void addText(String text) {
			
			this.setText(text);
		}

		@Override
		public void addListener(ActionListener l) {
			
			this.addActionListener(l);
		}

		@Override
		public void setValue(Object o) {
			if(o instanceof String) {
				this.addText((String)o);
			}
		}	
	}
	
	/**
	 * This class is an implementation of {@link TFrame}
	 * @see JFrame
	 * @see TFrame
	 */
	private class TFrameImpl extends JFrame implements TFrame {
		
		public TFrameImpl() {
			super();
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.SetVisibility(true);
		}
		
		public TFrameImpl(int width, int height) {
			this();
			this.setDimension(width, height);
		}
		
		@Override
		public void addComponent(TComponent c) {
			if(c instanceof Component) {	
				this.add( (Component)c );
				super.revalidate();
			} 
		}
		
		@Override
		public void addComponent(TComponent c, String position) {
			if(c instanceof Component) {
				String pos = Position.getPositionOf(position);
				if(Objects.nonNull(pos)) {
					this.add((Component) c, pos);
					super.revalidate();
				}
			}
		}

		@Override
		public void SetVisibility(Boolean b) {	
			this.setVisible(b);
		}

		@Override
		public void setDimension(int width, int height) {
			
			super.setSize(width, height);
		}

		@Override
		public void setValue(Object o) {
			
		}
		
		@Override
		public void clear() {
			this.getContentPane().removeAll();
			this.revalidate();
			this.repaint();
		}
	}
	
	/**
	 * This class is an implementation of {@link TPanel}
	 * @see JPanel
	 * @see TPanel
	 */
	private class TPanelImpl extends JPanel implements TPanel {
		
		public TPanelImpl() {
			super();
		}
		
		public TPanelImpl(int width, int height) {
			this();
			this.setDimension(width, height);
			this.setBorder(BorderFactory.createEtchedBorder());
		}
		
		@Override
		public void addComponent(TComponent c) {
			if(c instanceof Component) {	
				super.add((Component)c );
				super.revalidate();
			}
		}
		
		public void addComponent(TComponent c, String position) {
			this.setLayout(new BorderLayout());
			if(c instanceof Component) {
				String pos = Position.getPositionOf(position);
				if(Objects.nonNull(pos)) {
					super.add((Component) c, pos);
					super.revalidate();
				}
			}
		}
		
		@Override
		public void clear() {
			this.removeAll();
			this.revalidate();
			this.repaint();
		}
		
		@Override
		public void setDimension(int width, int height) {
			
			this.setPreferredSize(new Dimension(width, height));
		}

		@Override
		public void setValue(Object o) {	
			if(o instanceof int[]) {
				int[] values = (int[])o;
			}
		}
	}
	
	/**
	 * This class is an implementation of {@link TLabel}
	 * @see JLabel
	 * @see TLabel
	 */
	private class TLabelImpl extends JLabel implements TLabel{
		
		public TLabelImpl() {
			super();
		}
		
		public TLabelImpl(String text) {
			super(text);
		}
		
		public void setText(String text) {
			super.setText(text);
		}
		
		public void setValue(Object v) {		
			if(v instanceof String) {
				this.setText((String)v);
			}
		}
	}
	
	
}
