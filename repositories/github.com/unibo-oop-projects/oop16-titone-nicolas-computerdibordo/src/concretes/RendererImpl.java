package concretes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import abstracts.AbsService;
import abstracts.Renderer;
import akka.actor.ActorRef;
import components.ComponentRef;
import components.SerializedComponent;
import components.TButton;
import components.TComponent;
import components.TComponentToString;
import components.TComponentWithChildren;
import components.TFrame;
import components.TLabel;
import components.TPanel;
import concretes.ComponentBuilder.Position;
import main.Main;
import message.StructureRepaintMessage;
import message.ServiceStructureUpdateMessage;
import message.StructureMessage;
import message.UserInteractionMessage;

/**
 * This class represents a concrete implementation of a Renderer; it implements methods of {@link Renderer} interface.
 */
public class RendererImpl extends AbsService implements Renderer{
	
	/**
	 * The builder used to manage the user interface
	 */
	private ComponentBuilder builder;
	
	/**
	 * A structure used to maintain component references
	 * @see ComponentRef
	 * @see ComponentInMap
	 */
	private final Map<ComponentRef, ComponentInMap> components;
	public RendererImpl() {
		super();
		this.builder = ComponentBuilder.getInstance();
		this.components = new HashMap<>();		
	}
	
	public void onReceive(Object message) throws Throwable {
		
		super.onReceive(message);
		
		if(message instanceof StructureRepaintMessage) {
			StructureRepaintMessage msg = (StructureRepaintMessage)message;
			this.structureRepaintMessageReceived(msg);
		}
		
		else if(message instanceof StructureMessage) {
			StructureMessage msg = (StructureMessage)message;
			this.structureMessageReceived(msg);
		}
		
		else if(message instanceof ServiceStructureUpdateMessage) {
			ServiceStructureUpdateMessage msg = (ServiceStructureUpdateMessage)message;
			this.serviceStructureUpdateMessageReceived(msg);
		}
	}
	
	@Override
	public void structureMessageReceived(StructureMessage msg) {
		SerializedComponent c = msg.getValueAsJsonModel().getDeserializedValue(SerializedComponent.class);
		this.components.clear();
		this.recursiveUpdate(c, msg.getCreators());
		Main.log("Renderer: StructureMessage received; user interface painted.");
	}
	
	@Override
	public void structureRepaintMessageReceived(StructureRepaintMessage msg) {
		SerializedComponent c = msg.getValueAsJsonModel().getDeserializedValue(SerializedComponent.class);
		if(this.components.containsKey(c.getComponentRef())) {
			this.repaint(c, msg.getCreators());
			Main.log("Renderer: StructureRepaintMessage received; user interface repainted.");
		}
	}
	
	@Override
	public void serviceStructureUpdateMessageReceived(ServiceStructureUpdateMessage msg) {
		if(this.components.containsKey(msg.getRef())) {
			TComponent c = this.components.get(msg.getRef()).getComponent();
			c.setValue(msg.getValue());
			Main.log("Renderer: ServiceStructureUpdateMessage received; user interface updated.");
		}
	}
	
	/**
	 * This method is used to remove from the user interface a component (whose {@link ComponentRef} is comp) 
	 * and its children with recursion.
	 * @param comp the {@link } to remove
	 */
	private void recursiveClear(ComponentRef comp) {
		if(this.components.containsKey(comp)) {
			ComponentInMap c = this.components.get(comp);
			if(!c.getChildren().isEmpty()) {
				for(ComponentRef e: c.getChildren()){
					ComponentInMap eInList = this.components.get(e);
					if(!eInList.getChildren().isEmpty()) {
						this.recursiveClear(e);
					}
					this.components.remove(e);
				}
				c.clearChildren();
			}
		}
		
		
	}
	
	/**
	 * This method is used by the renderer in {@link RendererImpl#recursiveUpdate(SerializedComponent, Collection)} 
	 * to deserialize and instantiate a {@link TComponent} from a {@link SerializedComponent}.
	 * This method return a {@link TComponent} representing a {@link TFrame} created taking information
	 * from a {@link SerializedComponent} c passed as parameter. It also set references of this component created
	 * in a {@link ComponentInMap} compInMap passed.
	 * @see #recursiveUpdate(SerializedComponent, Collection)
	 * @param c the {@link SerializedComponent} to be deserialized.
	 * @param compInMap the {@link ComponentInMap} where the component serialized is to be put.
	 * @param creators collection of creators; is a parameter used in the recursion of 
	 * {@link #recursiveUpdate(SerializedComponent, Collection<ActorRef>)}.
	 * @return a {@link TComponent} representing the {@link SerializedComponent} passed.
	 */
	private TComponent recursiveUpdateOnTFrame(SerializedComponent c, ComponentInMap compInMap, Collection<ActorRef> creators) {
		TFrame f = builder.frame(c.getWidth(), c.getHeight());
		if(Objects.nonNull(c.getChildren())) {
			for(SerializedComponent child: c.getChildren()) {
				compInMap.addChild(child.getComponentRef());
				if(Objects.nonNull(Position.getPositionOf(child.getPosition()))) {
					f.addComponent(this.recursiveUpdate(child, creators), child.getPosition());
				} else {
					f.addComponent(this.recursiveUpdate(child, creators));
				}
			}
		}
		f.SetVisibility(true);
		return f;
	}
	
	/**
	 * This method is used by the renderer in {@link RendererImpl#recursiveUpdate(SerializedComponent, Collection)} 
	 * to deserialize and instantiate a {@link TComponent} from a {@link SerializedComponent}.
	 * This method return a {@link TComponent} representing a {@link TPanel} created taking information
	 * from a {@link SerializedComponent} c passed as parameter. It also set references of this component created
	 * in a {@link ComponentInMap} compInMap passed.
	 * @see #recursiveUpdate(SerializedComponent, Collection)
	 * @param c the {@link SerializedComponent} to be deserialized.
	 * @param compInMap the {@link ComponentInMap} where the component serialized is to be put.
	 * @param creators collection of creators; is a parameter used in the recursion of 
	 * {@link #recursiveUpdate(SerializedComponent, Collection<ActorRef>)}.
	 * @return a {@link TComponent} representing the {@link SerializedComponent} passed.
	 */
	private TComponent recursiveUpdateOnTPanel(SerializedComponent c, ComponentInMap compInMap, Collection<ActorRef> creators) {
		TPanel p = builder.panel(c.getWidth(), c.getHeight());
		if(Objects.nonNull(c.getChildren())) {
			for(SerializedComponent child: c.getChildren()) {
				compInMap.addChild(child.getComponentRef());
				if(Objects.nonNull(Position.getPositionOf(child.getPosition()))) {
					p.addComponent(this.recursiveUpdate(child, creators), child.getPosition());
				} else {
					p.addComponent(this.recursiveUpdate(child, creators));
				}			
			}
		}
		
		return p;
	}
	
	/**
	 * This method is used by the renderer in {@link RendererImpl#recursiveUpdate(SerializedComponent, Collection)} 
	 * to deserialize and instantiate a {@link TComponent} from a {@link SerializedComponent}.
	 * This method return a {@link TComponent} representing a {@link TButton} created taking information
	 * from a {@link SerializedComponent} c passed as parameter. It also set references of this component created
	 * in a {@link ComponentInMap} compInMap passed.
	 * @see #recursiveUpdate(SerializedComponent, Collection)
	 * @param c the {@link SerializedComponent} to be deserialized.
	 * @param compInMap the {@link ComponentInMap} where the component serialized is to be put.
	 * @param creators collection of creators; is a parameter used in the recursion of 
	 * {@link #recursiveUpdate(SerializedComponent, Collection<ActorRef>)}.
	 * @return a {@link TComponent} representing the {@link SerializedComponent} passed.
	 */
	private TComponent recursiveUpdateOnTButton(SerializedComponent c, ComponentInMap compInMap, Collection<ActorRef> creators) {
		TButton b = builder.button(c.getText());
		creators.stream().filter((elem) -> elem.toString().equals(c.getCreator()))
			    .forEach((e) -> b.addListener((event) -> {
			    	e.tell(new UserInteractionMessage(c.getId()), this.getSelf());
			    	Main.log("Renderer: user interaction listened; event told directly to the service involved.");
			    	}));
		return b;
	}
	
	/**
	 * This method is used by the renderer in {@link RendererImpl#recursiveUpdate(SerializedComponent, Collection)} 
	 * to deserialize and instantiate a {@link TComponent} from a {@link SerializedComponent}.
	 * This method return a {@link TComponent} representing a {@link TLabel} created taking information
	 * from a {@link SerializedComponent} c passed as parameter. It also set references of this component created
	 * in a {@link ComponentInMap} compInMap passed.
	 * @see #recursiveUpdate(SerializedComponent, Collection)
	 * @param c the {@link SerializedComponent} to be deserialized.
	 * @param compInMap the {@link ComponentInMap} where the component serialized is to be put.
	 * @param creators collection of creators; is a parameter used in the recursion of 
	 * {@link #recursiveUpdate(SerializedComponent, Collection<ActorRef>)}.
	 * @return a {@link TComponent} representing the {@link SerializedComponent} passed.
	 */
	private TComponent recursiveUpdateOnTimeLabel(SerializedComponent c, ComponentInMap compInMap, Collection<ActorRef> creators) {
		TLabel l = builder.label(c.getText());
		return l;
	}
	
	/**
	 * This method is a recursive method used to instantiate the user interface represented by a [@link SerializedComponent}.
	 */
	private TComponent recursiveUpdate(SerializedComponent c, Collection<ActorRef> creators) {
		
		TComponent comp = null;	
		ComponentRef ref = new ComponentRef(c.getId(), c.getCreator(), c.getType());
		ComponentInMap compInMap= new ComponentInMap();
		
		if(c.getType().equals(TComponentToString.TFrame.getType())) {
			comp = this.recursiveUpdateOnTFrame(c, compInMap, creators);
		}
		
		if(c.getType().equals(TComponentToString.TPanel.getType())) {
			comp = this.recursiveUpdateOnTPanel(c, compInMap, creators);
		}
		
		if(c.getType().equals(TComponentToString.TButton.getType())) {
			comp = this.recursiveUpdateOnTButton(c, compInMap, creators);
		}
		
		if(c.getType().equals(TComponentToString.TLabel.getType())) {
			comp = this.recursiveUpdateOnTimeLabel(c, compInMap, creators);
		}
		
		compInMap.setComponent(comp);
		this.components.put(ref, compInMap);
		return comp;
	}
	
	/**
	 * This method repaint the current user interface from a component (represented by a [@link SerializedComponent parameter
	 * using {@link RendererImpl#recursiveUpdate(SerializedComponent, Collection)}
	 * @see RendererImpl#recursiveUpdate(SerializedComponent, Collection)
	 * @param c the component to be repainted
	 * @param creators list of creators used in the recursion, they represent the owners of the components of the user interface
	 */
	private void repaint(SerializedComponent c, Collection<ActorRef> creators) {
		if(c.getType().equals(TComponentToString.TFrame.getType())
				|| c.getType().equals(TComponentToString.TPanel.getType())) {
			this.recursiveClear(c.getComponentRef());
			ComponentInMap cInList = this.components.get(c.getComponentRef());
			TComponentWithChildren ccc = ((TComponentWithChildren)cInList.getComponent());
			ccc.clear();
			
			if(!c.getChildren().isEmpty()) {
				for(SerializedComponent elem: c.getChildren()) {
					this.recursiveUpdate(elem, creators);
					ccc.addComponent(this.recursiveUpdate(elem, creators));
					cInList.addChild(elem.getComponentRef());
				}
			}
			
			ccc.repaint();
		}
	}
}
