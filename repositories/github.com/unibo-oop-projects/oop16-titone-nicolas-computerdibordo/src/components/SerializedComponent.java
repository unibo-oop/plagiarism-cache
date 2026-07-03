package components;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * This class is a JSON serializable representation of the {@link TComponent}.
 * It is composed by different values; each of them is a different attribute of the {@link TComponent}.
 * Attributes: id, type, creator are used by every type of Component;
 * position is used by TimeFrame, TimePanel, TimeLabel, TimeButton
 * width, height are used by TimePanel
 * text is used by TimeButton and TimeLabel
 */
public class SerializedComponent {
	
	/**
	 * A string representation of the component
	 */
	private String id;
	
	/**
	 * a String representation of the type of the component
	 * @see TComponentToString
	 */
	private String type;
	
	/**
	 * a String representation of the creator of the component
	 */
	private String creator;
	
	/**
	 * A String representation of the position of the component
	 * @see TPosition
	 */
	private String position;
	
	/**
	 * A int representation of the width of the component
	 */
	private int width;
	
	/**
	 * A int representation of the height of the component
	 */
	private int height;
	
	/**
	 * A String representation of the text to show of the component
	 */
	private String text;
	
	/**
	 * An array representation of the components to be added to this one.
	 */
	private SerializedComponent[] children;
	
	public SerializedComponent(String id, String type, String position,
								int width, int height, String text, SerializedComponent[] children, String creator) {
		this.id = id;
		this.type = type;
		this.position = position;
		this.width = width;
		this.height = height;
		this.text = text;
		this.children = children;
		this.creator = creator;
	}
	/**
	 * This method is used to get a {@TComponentRef} representation of the component
	 * @return the representation of the component
	 */
	public ComponentRef getComponentRef() {
		return new ComponentRef(this.id, this.creator, this.type);
	}
	
	/**
	 * Getter for the component children
	 * @return A collection of {@link SerializedComponent} sons of this one.
	 */
	public Collection<SerializedComponent> getChildren() {
		
		if(Objects.nonNull(this.children)) {
			return Arrays.asList(this.children);
		}
		
		return null;
	}

	/**
	 * Getter for the component id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter for the component type
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Getter for the component creator
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Getter for the component position
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Getter for the component width
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter for the component height
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Getter for the component text
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	
}
