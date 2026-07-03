package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.DrawTool;
import controller.SelectToolObserverView;
import controller.ShapeTool;
import images.ImageLoader;

/**
 * Il  principale JPanel su cui risiedono tutti i componenti 
 * @author redim
 */

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements MainPanelView {

    private BufferedImage colorImage = new BufferedImage(
            16,16,BufferedImage.TYPE_INT_RGB);
    
    private BufferedImage fillImage = new BufferedImage(
            16,16,BufferedImage.TYPE_INT_RGB);
    

        public static CanvasBoard imageView = CanvasBoard.getInstance();
        
        private SelectToolObserverView observer;
        
        JSlider transSlider;        
        JLabel transLabel;
        float transparentVal = 1.0f;       
        DecimalFormat dec = new DecimalFormat("#.##");
    
    public MainPanel() {
        
        this.setLayout(new BorderLayout(4,4));
        this.setBorder(new EmptyBorder(5,3,5,3));
        
        JScrollPane imageScroll = new JScrollPane(imageView);
        
        this.add(imageScroll,BorderLayout.CENTER);

        final JPanel optionTools = new JPanel(new FlowLayout (FlowLayout.LEADING, 2, 0));
        JButton colorButton = colorButtonGenerator(true);
        colorButton.setToolTipText("Choose a Color");   
        colorButton.setText("Stroke Color");
        colorButton.setIcon(new ImageIcon(colorImage));
        
        JButton fillColorButton = colorButtonGenerator(false);
        fillColorButton.setToolTipText("Choose a Color");   
        fillColorButton.setText("Fill Color");
        fillColorButton.setIcon(new ImageIcon(fillImage));
        optionTools.add(colorButton);
        optionTools.add(fillColorButton);


        final SpinnerNumberModel strokeModel = 
                new SpinnerNumberModel(3,1,60,1);
        JSpinner strokeSize = new JSpinner(strokeModel);
        ChangeListener strokeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                Object o = strokeModel.getValue();
                Integer i = (Integer)o; 
                observer.setStroke(new BasicStroke(
                        i.intValue(),
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND,
                        1.7f));
            }
        };
        strokeSize.addChangeListener(strokeListener);
        strokeSize.setMaximumSize(strokeSize.getPreferredSize());
        JLabel strokeLabel = new JLabel("Stroke");
        strokeLabel.setLabelFor(strokeSize);
        optionTools.add(strokeLabel);
        optionTools.add(strokeSize);
        
        JButton deactivate = buttonGenerator("nofill.png"); 
        deactivate.setToolTipText("deactivate the fill tool");
        deactivate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                observer.setActiveFill(true);       
            }
            
        });
        
        JButton activate = buttonGenerator("fill.png");
        activate.setToolTipText("activate the fill tool");
        activate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                observer.setActiveFill(false);       
            }
            
        });
        
        JButton goBack = buttonGenerator("back.png");
        goBack.setToolTipText("Go back to the original image");
        goBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                imageView.setImage(imageView.getOriginalImage(), false);         
            }
            
        });
        optionTools.add(activate);
        optionTools.add(deactivate);
        optionTools.add(goBack);
        
        JButton gray = buttonGenerator("grayfilter.png");
        gray.setText("Grayscale");
        gray.setToolTipText("Grayscale filter");
        gray.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                  imageView.setImage(FilterUtilities.gray(imageView.getCanvasImage()), false);
            }
             });
    
        
        optionTools.add(gray);
  
        
        final JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0 , 0));
        buttonPanel.setPreferredSize(new Dimension(200, this.getHeight()));
        buttonPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

 
        
        final JButton brushButton = buttonGenerator("brush.png");
        final JButton pencilButton = buttonGenerator("pencil.png");
        final JButton textButton = buttonGenerator("text.png");
        final JButton rectangleButton = buttonGenerator("rectangle.png");
        final JButton roundRectangleButton = buttonGenerator("roundrectangle.png");
        final JButton lineButton = buttonGenerator("line.png");
        final JButton ellipseButton = buttonGenerator("ellipse.png");
        JButton bucketButton = buttonGenerator("bucket.png");
        
        buttonPanel.add(pencilButton);  
        buttonPanel.add(brushButton);
        buttonPanel.add(textButton);
        buttonPanel.add(bucketButton);
        buttonPanel.add(rectangleButton);
        buttonPanel.add(roundRectangleButton);
        buttonPanel.add(lineButton);
        buttonPanel.add(ellipseButton);
        

        ActionListener toolGroupListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JButton btn = (JButton) ae.getSource();
                
                if (btn == pencilButton) {
                    observer.setGraphicTool(DrawTool.DRAW_TOOL.ordinal());
                    observer.setToolChooser(true);
                } else if (btn == textButton) {
                    observer.setGraphicTool(DrawTool.TEXT_TOOL.ordinal());
                    observer.setToolChooser(true);
                  } else if(btn == rectangleButton) {
                      observer.setGraphicTool(ShapeTool.RECTANGLE.ordinal()); 
                      observer.setToolChooser(false);
                  } else if(btn == lineButton) {
                      observer.setGraphicTool(ShapeTool.LINE.ordinal());
                      observer.setToolChooser(false);
                  }
                   else if(btn == ellipseButton) {
                      observer.setGraphicTool(ShapeTool.ELLIPSE.ordinal());
                      observer.setToolChooser(false);
                  }  else if(btn == bucketButton) {                         
                      observer.setGraphicTool(DrawTool.BUCKET_TOOL.ordinal());
                      observer.setToolChooser(true);
                  } else if(btn == roundRectangleButton) {
                      observer.setGraphicTool(ShapeTool.ROUND_RECTANGLE.ordinal()); 
                      observer.setToolChooser(false);       
                  } else if (btn == brushButton) {
                      observer.setGraphicTool(DrawTool.BRUSH.ordinal());
                      observer.setToolChooser(true);
                  }
                else {
                    System.out.println("hi");
                }
                  }
            
        };

        pencilButton.addActionListener(toolGroupListener);
        brushButton.addActionListener(toolGroupListener);
        textButton.addActionListener(toolGroupListener);
        rectangleButton.addActionListener(toolGroupListener);
        lineButton.addActionListener(toolGroupListener);
        ellipseButton.addActionListener(toolGroupListener);
        bucketButton.addActionListener(toolGroupListener);
        roundRectangleButton.addActionListener(toolGroupListener);
        
        final ColorPanel colorPanel = new ColorPanel();
        colorPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        Component[] comps = colorPanel.getComponents();
        for(Component comp : comps) {
            if (comp instanceof JButton) {
                JButton button = (JButton)comp;
               button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                 Color color = button.getBackground();
                 observer.setColor(color);
                 clear(colorImage, true);
                }
                   
               });
            }
        }
            
                
        
        transLabel = new JLabel("Transparent: 1");               
        transSlider = new JSlider(1, 100, 100);
        transSlider.setPreferredSize(new Dimension(100, 25));
        
        ChangeListener lForSlider = new ChangeListener() {
            
            public void stateChanged(ChangeEvent e) {
            
                    if(e.getSource() == transSlider){
            
                            transLabel.setText("Transparent: " + dec.format(transSlider.getValue() * .01) );
                            observer.setComposite(transSlider.getValue() * .01); 
                            
                    }            
            }
            
        };
        
       
        transSlider.addChangeListener(lForSlider);
        optionTools.add(transLabel);
        optionTools.add(transSlider);
        this.add(optionTools, BorderLayout.PAGE_START);
        this.add(colorPanel, BorderLayout.PAGE_END );
        this.add(buttonPanel, BorderLayout.LINE_START);

        clear(colorImage, true);
        clear(fillImage, false);
        clear(imageView.getCanvasImage(), true);
        clear(imageView.getOriginalImage(), true);
    }
    
    private void clear(BufferedImage bi, boolean bool) {
        imageView.clear(bi,bool);
     }
    
    private JButton buttonGenerator(String iconFile) {
        JButton theBut = new JButton();
        theBut.setIcon(new ImageIcon(ImageLoader.setImage(iconFile)));
        return theBut;
    }
    
   
    
    private JButton colorButtonGenerator(final boolean stroke){
        JButton theBut = new JButton();
        theBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               if(stroke) {
                   Color c = JColorChooser.showDialog(imageView, "Choose a color", Color.WHITE);
                   if (c!=null) {
                       observer.setColor(c);
                       clear(colorImage, true);
                   }                                         
               } else {
                   Color c = JColorChooser.showDialog(imageView, "Choose a color", Color.WHITE);
                   if(c != null) {
                       observer.setFillColor(c);
                       clear(fillImage, false);
                   }
               }
                
            }
            
        });
        return theBut;
        
    }
  
 
  @Override
public void setObserver(SelectToolObserverView observer){
      this.observer = observer;
  }

}
