package application.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.TransformerException;

import application.controller.tabs.FuelsEditorCtrl;
import application.controller.tabs.FuelsEditorCtrlImpl;
import application.controller.tabs.MovementsViewerCtrl;
import application.controller.tabs.MovementsViewerCtrlImpl;
import application.controller.tabs.OverviewCtrl;
import application.controller.tabs.OverviewCtrlImpl;
import application.controller.tabs.PumpsEditorCtrl;
import application.controller.tabs.PumpsEditorCtrlImpl;
import application.controller.tabs.ReservesEditorCtrl;
import application.controller.tabs.ReservesEditorCtrlImpl;
import application.controller.tabs.StationEditorCtrl;
import application.controller.tabs.StationEditorCtrlImpl;
import application.model.Station;
import application.model.buildables.area.Area;
import application.model.buildables.pump.Pump;
import application.model.buildables.reserve.Reserve;
import application.model.services.Fuel;
import application.view.MainContent;
import javafx.scene.paint.Color;

/**
 * Class that implements the logic of the main controller.
 */
public class MainControllerImpl implements MainController {

    private Station station;
    private MainContent view;
    
    private final FuelsEditorCtrl fuelsEditorCtrl;
    private final MovementsViewerCtrl movementsViewerCtrl;
    private final OverviewCtrl overviewCtrl;
    private final PumpsEditorCtrl pumpsEditorCtrl;
    private final ReservesEditorCtrl reservesEditorCtrl;
    private final StationEditorCtrl stationEditorCtrl;
    
    /**
     * Initialize the reference of controllers.
     */
    public MainControllerImpl() {
	this.fuelsEditorCtrl = new FuelsEditorCtrlImpl(this);
	this.movementsViewerCtrl = new MovementsViewerCtrlImpl(this);
	this.overviewCtrl = new OverviewCtrlImpl(this);
	this.pumpsEditorCtrl = new PumpsEditorCtrlImpl(this);
	this.reservesEditorCtrl = new ReservesEditorCtrlImpl(this);
        this.stationEditorCtrl = new StationEditorCtrlImpl(this);
    }

    @Override
    public FuelsEditorCtrl getFuelsEditorController() {
	return this.fuelsEditorCtrl;
    }

    @Override
    public MovementsViewerCtrl getMovementsViewerController() {
	return this.movementsViewerCtrl;
    }
    
    @Override
    public OverviewCtrl getOverviewController() {
	return this.overviewCtrl;
    }

    @Override
    public PumpsEditorCtrl getPumpsEditorController() {
	return this.pumpsEditorCtrl;
    }

    @Override
    public ReservesEditorCtrl getReservesEditorController() {
	return this.reservesEditorCtrl;
    }

    @Override
    public StationEditorCtrl getStationEditorController() {
	return this.stationEditorCtrl;
    }

    @Override
    public void setModel(final Station stn) {
	this.station = stn;
    }

    @Override
    public Station getModel() {
	return this.station;
    }

    @Override
    public void setView(final MainContent mainContent) {
	this.view = mainContent;
	
	this.fuelsEditorCtrl.setView(this.view.getFuelsEditorTab());
	this.movementsViewerCtrl.setView(this.view.getMovementsViewerTab());
	this.overviewCtrl.setView(this.view.getOverviewTab());
	this.pumpsEditorCtrl.setView(this.view.getPumpsEditorTab());
	this.reservesEditorCtrl.setView(this.view.getReservesEditorTab());
	this.stationEditorCtrl.setView(this.view.getStationEditorTab());
    }

    @Override
    public void saveConfiguration() {
	try {
	    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    
	    //root element
	    Document doc = docBuilder.newDocument();
	    Element rootElement = doc.createElement("station");
	    doc.appendChild(rootElement);
	    
	    //name station
	    Element name = doc.createElement("name");
	    name.appendChild(doc.createTextNode(this.getModel().getStationName()));
	    doc.appendChild(name);
	    
	    //open
	    Element open = doc.createElement("open");
	    open.appendChild(doc.createTextNode("false"));
	    doc.appendChild(open);
	    
	    //max areas
	    Element maxareas = doc.createElement("maxareas");
	    maxareas.appendChild(doc.createTextNode(String.valueOf(this.getModel().getMaxAreas())));
	    doc.appendChild(maxareas);
	    
	    //max pumps for areas
	    Element maxpumps = doc.createElement("maxpumps");
	    maxpumps.appendChild(doc.createTextNode(String.valueOf(this.getModel().getMaxAreas())));
	    doc.appendChild(maxpumps);
	    
	    //balance
	    Element balance = doc.createElement("balance");
	    balance.appendChild(doc.createTextNode(String.valueOf(this.getModel().getMoneyManager().getActualBalance())));
	    doc.appendChild(balance);
	    
	    //fuels elements
	    Element fuels = doc.createElement("fuels");
	    rootElement.appendChild(fuels);
	    
	    for (Fuel f : this.getModel().getFuelManager().getAllFuels()) {
		Element fuel = doc.createElement("fuel");
		fuels.appendChild(fuel);
		
		Element fName = doc.createElement("name");
		fName.appendChild(doc.createTextNode(f.getName()));
		fuel.appendChild(fName);
		
		Element price = doc.createElement("price");
		price.appendChild(doc.createTextNode(String.valueOf(f.getPrice())));
		fuel.appendChild(price);
		
		Element wholesaleprice = doc.createElement("wholesaleprice");
		wholesaleprice.appendChild(doc.createTextNode(String.valueOf(f.getWholeSalePrice())));
		fuel.appendChild(wholesaleprice);
		
		Element color = doc.createElement("color");
		color.appendChild(doc.createTextNode(String.valueOf(f.getColor())));
		fuel.appendChild(color);
	    }
	    
	    //reserves elements
	    Element reserves = doc.createElement("reserves");
	    rootElement.appendChild(reserves);
	    
	    for (Reserve r : this.getModel().getReserveManager().getAllReserves()) {
		Element reserve = doc.createElement("reserve");
		reserves.appendChild(reserve);
		
		Element maxDur = doc.createElement("maxDurability");
		maxDur.appendChild(doc.createTextNode(String.valueOf(r.getMaxDurability())));
		reserve.appendChild(maxDur);
		
		Element dur = doc.createElement("durability");
		dur.appendChild(doc.createTextNode(String.valueOf(r.getDurability())));
		reserve.appendChild(dur);
		
		Element cost = doc.createElement("price");
		cost.appendChild(doc.createTextNode(String.valueOf(r.getCost())));
		reserve.appendChild(cost);
		
		Element repair = doc.createElement("repairCost");
		repair.appendChild(doc.createTextNode(String.valueOf(r.getRepairCost())));
		reserve.appendChild(repair);
		
		Element type = doc.createElement("type");
		type.appendChild(doc.createTextNode(r.getType().getName()));
		reserve.appendChild(type);
		
		Element capacity = doc.createElement("capacity");
		capacity.appendChild(doc.createTextNode(String.valueOf(r.getCapacity())));
		reserve.appendChild(capacity);
		
		Element remaining = doc.createElement("remaining");
		remaining.appendChild(doc.createTextNode(String.valueOf(r.getRemaining())));
		reserve.appendChild(remaining);
	    }
	    
	    //pumps elements
	    Element pumps = doc.createElement("pumps");
	    rootElement.appendChild(pumps);
	    
	    for (Pump p : this.getModel().getPumpManager().getAllPumps()) {
		Element pump = doc.createElement("pump");
		pumps.appendChild(pump);
		
		Element pName = doc.createElement("name");
		pName.appendChild(doc.createTextNode(p.getName()));
		pump.appendChild(pName);
		
		Element pFuel = doc.createElement("fuel");
		pFuel.appendChild(doc.createTextNode(p.getType().getName()));
		pump.appendChild(pFuel);
		
		Element speed = doc.createElement("speed");
		speed.appendChild(doc.createTextNode(String.valueOf(p.getSpeed())));
		pump.appendChild(speed);
		
		Element durability = doc.createElement("durability");
		durability.appendChild(doc.createTextNode(String.valueOf(p.getMaxDurability())));
		pump.appendChild(durability);
		
		Element pPrice = doc.createElement("price");
		pPrice.appendChild(doc.createTextNode(String.valueOf(p.getCost())));
		pump.appendChild(pPrice);
		
		Element repairCost = doc.createElement("repairCost");
		repairCost.appendChild(doc.createTextNode(String.valueOf(p.getRepairCost())));
		pump.appendChild(repairCost);
		
		Element actualDurability = doc.createElement("actualDurability");
		actualDurability.appendChild(doc.createTextNode(String.valueOf(p.getDurability())));
		pump.appendChild(actualDurability);
	    }

	    //areas elements
	    Element areas = doc.createElement("areas");
	    rootElement.appendChild(areas);
	    
	    for (Area a : this.getModel().getAreaManager().getAllAreas()) {
		Element area = doc.createElement("area");
		areas.appendChild(area);
		
		Element xpos = doc.createElement("xpos");
		xpos.appendChild(doc.createTextNode(String.valueOf(a.getXPosition())));
		area.appendChild(xpos);
		
		Element ypos = doc.createElement("ypos");
		ypos.appendChild(doc.createTextNode(String.valueOf(a.getYPosition())));
		area.appendChild(ypos);
		
		//pumps of area
		Element aPumps = doc.createElement("pumps");
		area.appendChild(aPumps);
		
		for (Pump ap : a.getAllPumps()) {
		    Element aPump = doc.createElement("pump");
		    aPumps.appendChild(aPump);
		    
		    Element aPName = doc.createElement("name");
		    aPName.appendChild(doc.createTextNode(ap.getName()));
		    aPump.appendChild(aPName);
		}
	    }
	    
	    // write the content into xml file
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    DOMSource source = new DOMSource(doc);
	    StreamResult result = new StreamResult(new File(getClass()
		    .getResource("/resources/configuration.xml").toURI()));
	    transformer.transform(source, result);
	} catch (ParserConfigurationException pce) {
	    pce.printStackTrace();
	} catch (TransformerException te) {
	    te.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    @Override
    public void reconfiguration() {
	this.overviewCtrl.loadData(this.getModel().getAreaManager().getAllAreas());
        
        this.stationEditorCtrl.loadData(this.getModel().getMaxAreas(), 
                                        this.getModel().getMaxAreas(),
                                        this.getModel().getPumpManager().getAllPumps(),
                                        this.getModel().getAreaManager().getAllAreas());
        
        this.fuelsEditorCtrl.loadData(this.getModel().getFuelManager().getAllFuels());
        
        this.reservesEditorCtrl.loadData(this.getModel().getFuelManager().getAllFuels(),
                                         this.getModel().getReserveManager().getAllReserves());
        
        this.pumpsEditorCtrl.loadData(this.getModel().getFuelManager().getAllFuels(),
    	                          this.getModel().getPumpManager().getAllPumps());
        
        this.movementsViewerCtrl.loadData();
    }

    @Override
    public void loadConfiguration() {
	boolean name = false;
	boolean open = false;
	boolean maxareas = false;
	boolean maxpumps = false;
	boolean balance = false;
	
	try {	    
	    InputStream in = getClass().getResourceAsStream("/resources/configuration.xml"); 
	    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	    
	    XMLInputFactory factory = XMLInputFactory.newInstance();
	    XMLEventReader eventReader =
	    factory.createXMLEventReader(reader);
	    
	    while (eventReader.hasNext()) {
		XMLEvent event = eventReader.nextEvent();
		switch (event.getEventType()) {
		    case XMLStreamConstants.START_ELEMENT:
		    StartElement startElement = event.asStartElement();
                    String sName = startElement.getName().getLocalPart();
                    
		    if (sName.equalsIgnoreCase("name")) {
			name = true;
		    } else if (sName.equalsIgnoreCase("open")) {
			open = true;
		    } else if (sName.equalsIgnoreCase("maxareas")) {
			maxareas = true;
		    } else if (sName.equalsIgnoreCase("maxpumps")) {
			maxpumps = true;
		    } else if (sName.equalsIgnoreCase("balance")) {
			balance = true;
		    } else if (sName.equalsIgnoreCase("fuels")) {
			fileFuel(eventReader);
		    }
		    break;
		    case XMLStreamConstants.CHARACTERS:
	            Characters characters = event.asCharacters();
	            if (name) {
	        	this.getModel().setStationName(characters.getData());
	        	name = false;
	            }
	            if (open) {
	        	if (Boolean.parseBoolean(characters.getData())) {
	        	    this.station.open();
	        	} else {
	        	    this.station.close();
	        	}
	        	open = false;
	            }
	            if (maxareas) {
	        	this.getModel().setMaxAreas(Integer.parseInt(characters.getData()));
	        	maxareas = false;
	            }
	            if (maxpumps) {
	        	this.getModel().setMaxPumps(Integer.parseInt(characters.getData()));
	        	maxpumps = false;
	            }
	            if (balance) {
	        	this.getModel().getMoneyManager().loadBalance(Integer.parseInt(characters.getData()));
	        	balance = false;
	            }
	            break;
	        }
	    }
	} catch (FileNotFoundException e) {
            e.printStackTrace();  
            
	} catch (XMLStreamException e) {
	    e.printStackTrace();

	} catch (Exception e) {
	    e.printStackTrace();
	    this.view.showErrorAlert("", "", e.getMessage());
        }
    }
    
    private void fileFuel(final XMLEventReader eventReader) throws Exception {
	boolean name = false;
	boolean price = false;
	boolean wholesaleprice = false;
	boolean color = false;
	String dName = "";
	int dPrice = 0, dWPrice = 0;
	Color dColor = null;
	
	while (eventReader.hasNext()) {
	    XMLEvent event = eventReader.nextEvent();
	    switch (event.getEventType()) {
	        case XMLStreamConstants.START_ELEMENT:
		StartElement startElement = event.asStartElement();
                String sName = startElement.getName().getLocalPart();
                
                if (sName.equalsIgnoreCase("name")) {
                    name = true;
		} else if (sName.equalsIgnoreCase("price")) {
		    price = true;
		} else if (sName.equalsIgnoreCase("wholesaleprice")) {
		    wholesaleprice = true;
		} else if (sName.equalsIgnoreCase("color")) {
		    color = true;
		} else if (sName.equalsIgnoreCase("reserves")) {
		    fileReserve(eventReader);
		}
                break;
	        case XMLStreamConstants.CHARACTERS:
	        Characters characters = event.asCharacters();
	        if (name) {
	            dName = characters.getData();
	            name = false;
	        }
	        if (price) {
	            dPrice = Integer.parseInt(characters.getData());
	            price = false;
	        }
	        if (wholesaleprice) {
	            dWPrice = Integer.parseInt(characters.getData());
	            wholesaleprice = false;
	        }
	        if (color) {
	            dColor = Color.valueOf(characters.getData());
	            color = false;
	        }
	        break;
	        case  XMLStreamConstants.END_ELEMENT:
	        EndElement endElement = event.asEndElement();
	        if (endElement.getName().getLocalPart().equalsIgnoreCase("fuel")) {
	            this.getModel().getFuelManager().addFuel(dName, dPrice, dWPrice, dColor);
	        }
	        break;
            }
	}
    }
    
    private void fileReserve(final XMLEventReader eventReader) throws Exception {
	boolean maxDurability = false;
	boolean durability = false;
	boolean price = false;
	boolean repairCost = false;
	boolean type = false;
	boolean capacity = false;
	boolean remaining = false;
	Fuel dType = null;
	int dCapacity = 0, dRemaining = 0, dMaxD = 0;
	int dDurab = 0, dPrice = 0, dRepair = 0;
	
	while (eventReader.hasNext()) {
	    XMLEvent event = eventReader.nextEvent();
	    switch (event.getEventType()) {
	        case XMLStreamConstants.START_ELEMENT:
		StartElement startElement = event.asStartElement();
                String sName = startElement.getName().getLocalPart();
                
                if (sName.equalsIgnoreCase("maxDurability")) {
                    maxDurability = true;
                } else if (sName.equalsIgnoreCase("durability")) {
                    durability = true;
                } else if (sName.equalsIgnoreCase("price")) {
                    price = true;
                } else if (sName.equalsIgnoreCase("repairCost")) {
                    repairCost = true;
                } else if (sName.equalsIgnoreCase("type")) {
                    type = true;
		} else if (sName.equalsIgnoreCase("capacity")) {
		    capacity = true;
		} else if (sName.equalsIgnoreCase("remaining")) {
		    remaining = true;
		} else if (sName.equalsIgnoreCase("pumps")) {
		    filePump(eventReader);
		}
                break;
	        case XMLStreamConstants.CHARACTERS:
	        Characters characters = event.asCharacters();
	        if (maxDurability) {
	            dMaxD = Integer.parseInt(characters.getData());
	            maxDurability = false;
	        }
	        if (durability) {
	            dDurab = Integer.parseInt(characters.getData());
	            durability = false;
	        }
	        if (price) {
	            dPrice = Integer.parseInt(characters.getData());
	            price = false;
	        }
	        if (repairCost) {
	            dRepair = Integer.parseInt(characters.getData());
	            repairCost = false;
	        }
	        if (type) {
	            dType = this.getModel().getFuelManager().getFuel(characters.getData());
	            type = false;
	        }
	        if (capacity) {
	            dCapacity = Integer.parseInt(characters.getData());
	            capacity = false;
	        }
	        if (remaining) {
	            dRemaining = Integer.parseInt(characters.getData());
	            remaining = false;
	        }
	        break;
	        case  XMLStreamConstants.END_ELEMENT:
	        EndElement endElement = event.asEndElement();
		if (endElement.getName().getLocalPart().equalsIgnoreCase("reserve")) {
		    this.getModel().getReserveManager().addReserve(dMaxD, dDurab, dPrice, dRepair,
			                                           dType, dCapacity, dRemaining);
		}
		break;
            }
	}
    }

    private void filePump(final XMLEventReader eventReader) throws Exception {
	boolean name = false;
	boolean fuel = false;
	boolean speed = false;
	boolean durability = false;
	boolean price = false;
	boolean repairCost = false;
	boolean actualDurability = false;
	String dName = "";
	Fuel dFuel = null;
	int dSpeed = 0, dDurability = 0, dPrice = 0;
	int dRepairCost = 0, dActualDurability = 0;
	
	while (eventReader.hasNext()) {
	    XMLEvent event = eventReader.nextEvent();
	    switch (event.getEventType()) {
	        case XMLStreamConstants.START_ELEMENT:
		StartElement startElement = event.asStartElement();
                String sName = startElement.getName().getLocalPart();
                
                if (sName.equalsIgnoreCase("name")) {
                    name = true;
		} else if (sName.equalsIgnoreCase("fuel")) {
		    fuel = true;
		} else if (sName.equalsIgnoreCase("speed")) {
		    speed = true;
		} else if (sName.equalsIgnoreCase("durability")) {
		    durability = true;
		} else if (sName.equalsIgnoreCase("price")) {
		    price = true;
		} else if (sName.equalsIgnoreCase("repairCost")) {
		    repairCost = true;
		} else if (sName.equalsIgnoreCase("actualDurability")) {
		    actualDurability = true;
		} else if (sName.equalsIgnoreCase("areas")) {
		    fileArea(eventReader);
		}
                break;
	        case XMLStreamConstants.CHARACTERS:
	        Characters characters = event.asCharacters();
	        if (name) {
	            dName = characters.getData();
	            name = false;
	        }
	        if (fuel) {
	            dFuel = this.getModel().getFuelManager().getFuel(characters.getData());
	            fuel = false;
	        }
	        if (speed) {
	            dSpeed = Integer.parseInt(characters.getData());
	            speed = false;
	        }
	        if (durability) {
	            dDurability = Integer.parseInt(characters.getData());
	            durability = false;
	        }
	        if (price) {
	            dPrice = Integer.parseInt(characters.getData());
	            price = false;
	        }
	        if (repairCost) {
	            dRepairCost = Integer.parseInt(characters.getData());
	            repairCost = false;
	        }
	        if (actualDurability) {
	            dActualDurability = Integer.parseInt(characters.getData());
	            actualDurability = false;
	        }
	        break;
	        case  XMLStreamConstants.END_ELEMENT:
	        EndElement endElement = event.asEndElement();
	        if (endElement.getName().getLocalPart().equalsIgnoreCase("pump")) {
		    this.getModel().getPumpManager().addPump(dDurability, dActualDurability, dPrice, dRepairCost, dName, dFuel, dSpeed);
		}
		break;
	    }
	}
    }

    private void fileArea(final XMLEventReader eventReader) throws Exception {
	boolean name = false;
	boolean xPos = false;
	boolean yPos = false;
        int dXPos = 0;
        int dYPos = 0;
        final List<Pump> list = new ArrayList<>();
        
	while (eventReader.hasNext()) {
	    XMLEvent event = eventReader.nextEvent();
	    switch (event.getEventType()) {
	        case XMLStreamConstants.START_ELEMENT:
		StartElement startElement = event.asStartElement();
                String sName = startElement.getName().getLocalPart();
                
                if (sName.equalsIgnoreCase("xpos")) {
                    xPos = true;
		} else if (sName.equalsIgnoreCase("ypos")) {
		    yPos = true;
		} else if (sName.equalsIgnoreCase("name")) {
		    name = true;
		}
                break;
	        case XMLStreamConstants.CHARACTERS:
	        Characters characters = event.asCharacters();
	        if (xPos) {
	            dXPos = Integer.parseInt(characters.getData());
	            xPos = false;
	        }
	        if (yPos) {
	            dYPos = Integer.parseInt(characters.getData());
	            yPos = false;
	        }
	        if (name) {
	            for (Pump p : this.getModel().getPumpManager().getAllPumps()) {
	        	if (p.getName().equals(characters.getData())) {
	        	    list.add(p);
	        	}
	            }
	            name = false;
	        }
	        break;
	        case  XMLStreamConstants.END_ELEMENT:
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equalsIgnoreCase("area")) {
                    this.getModel().getAreaManager().addArea(dXPos, dYPos, list);
                    list.clear();
                }
                //load configuration
                if (endElement.getName().getLocalPart().equalsIgnoreCase("station")) {
                    this.overviewCtrl.loadData(this.getModel().getAreaManager().getAllAreas());
                    
                    this.stationEditorCtrl.loadData(this.getModel().getMaxAreas(), 
	                                            this.getModel().getMaxAreas(),
	                                            this.getModel().getPumpManager().getAllPumps(),
	                                            this.getModel().getAreaManager().getAllAreas());
                    
                    this.fuelsEditorCtrl.loadData(this.getModel().getFuelManager().getAllFuels());
                    
                    this.reservesEditorCtrl.loadData(this.getModel().getFuelManager().getAllFuels(),
	                                             this.getModel().getReserveManager().getAllReserves());
                    
                    this.pumpsEditorCtrl.loadData(this.getModel().getFuelManager().getAllFuels(),
                	                          this.getModel().getPumpManager().getAllPumps());
                    
                    this.movementsViewerCtrl.loadData();
                }
                break;
            }
	}
    }
}
