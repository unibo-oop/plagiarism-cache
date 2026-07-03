package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.EmptyBorder;
import controller.ExamController;
import model.Athlete;
import model.Master;

public class VotiEsameImpl extends JFrame implements VotiEsame{

	private static final long serialVersionUID = -3350874684438626331L;
	
	ExamController examController;
	private JPanel mainPanel = new JPanel();
	private JLabel nomeAtleta;
	private JLabel[] nomiMaestri;
	Font f = new Font("TimesRoman", Font.PLAIN, 24);
	private List<JSpinner> listaText = new ArrayList<>();
	private JButton submitCalci = new JButton("Calci");
	private JButton submitForma = new JButton("Forma");
	private JButton submitCombattimento = new JButton("Combattimento");
	private JButton submit = new JButton("Assegna");
	private JButton goback = new JButton("Indietro");
	private JFrame guida;
	public  VotiEsameImpl(List<Master> listaMaestri,Athlete atleta) {
		
		this.nomiMaestri = new JLabel[listaMaestri.size()];
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(100, 100, 500, 500);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		
			nomeAtleta = new JLabel(atleta.getName()+" "+atleta.getSurname());
			mainPanel.add(nomeAtleta);
			
			submitCalci.setForeground(new Color(0, 0, 0));
			submitCalci.setBounds(80, 20, 100, 40);
			submitCalci.setFont(new Font("Arial", Font.BOLD, 14));
			// name.setBackground(new Color(255, 255, 255));
			mainPanel.add(submitCalci);
			
			submitForma.setForeground(new Color(0, 0, 0));
			submitForma.setBounds(200, 20, 100, 40);
			submitForma.setFont(new Font("Arial", Font.BOLD, 14));
			// name.setBackground(new Color(255, 255, 255));
			mainPanel.add(submitForma);
			
			submitCombattimento.setForeground(new Color(0, 0, 0));
			submitCombattimento.setBounds(320, 20, 150, 40);
			submitCombattimento.setFont(new Font("Arial", Font.BOLD, 14));
			// name.setBackground(new Color(255, 255, 255));
			mainPanel.add(submitCombattimento);	
			
			goback.setForeground(new Color(0, 0, 0));
			goback.setBounds(140, 410, 100, 40);
			goback.setFont(new Font("Arial", Font.BOLD, 14));
			// name.setBackground(new Color(255, 255, 255));
			mainPanel.add(goback);
	
			if(atleta.getVoto(0)!=0){
				this.submitCalci.setEnabled(false);
			}
			if(atleta.getVoto(1)!=0){
				this.submitForma.setEnabled(false);
			}
			if(atleta.getVoto(2)!=0){
				this.submitCombattimento.setEnabled(false);
			}
			
			submitCalci.addActionListener(e->{
				
				submitCalci.setEnabled(false);
				submitCombattimento.setEnabled(false);
				submitForma.setEnabled(false);
				
				guida = new JFrame();
				JPanel mainGuida = new JPanel();
				guida.add(mainGuida);
				mainGuida.add(new JLabel(new ImageIcon(examController.openGuidaTecnica("calcio",atleta.getBelt().getValue()))));
				guida.setVisible(true);
				guida.pack();
				
				final JLabel calci = new JLabel("Calci");
				calci.setForeground(new Color(0, 0, 0));
				calci.setBounds(230, 70, 100, 30);
				calci.setFont(new Font("Arial", Font.BOLD, 15));
				// name.setBackground(new Color(255, 255, 255));
				mainPanel.add(calci);
				
				for(int i=0; i<listaMaestri.size();i++){

					nomiMaestri[i] = new JLabel(listaMaestri.get(i).getSurname());
					SpinnerModel modelJS = new SpinnerNumberModel(5,1,10,1);
					JSpinner spinner = new JSpinner(modelJS);
					((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
					listaText.add(spinner);
				}
				
				int h = 110;
				for(JLabel l : nomiMaestri){	
					l.setForeground(new Color(0, 0, 0));
					l.setBounds(150, h, 100, 40);
					l.setFont(new Font("Arial", Font.BOLD, 15));
					// name.setBackground(new Color(255, 255, 255));
					mainPanel.add(l);
					h+= 50;
				}
				h = 110;
				for(JSpinner s : listaText){
					s.setForeground(new Color(0, 0, 0));
					s.setBounds(270, h, 70, 40);
					s.setFont(new Font("Arial", Font.BOLD, 15));
					// name.setBackground(new Color(255, 255, 255));
					mainPanel.add(s);
					h += 50;
				}
				h = 110;
				
				submit.setForeground(new Color(0, 0, 0));
				submit.setBounds(260, 410, 100, 40);
				submit.setFont(new Font("Arial", Font.BOLD, 15));
				// name.setBackground(new Color(255, 255, 255));
				mainPanel.add(submit);
				
				submit.addActionListener(a->{
					guida.dispose();
					Integer sum = 0;
					for(JSpinner b: listaText){
						
						sum =sum + Integer.parseInt(b.getValue().toString());					
					}
					sum = sum / listaMaestri.size();
					atleta.setVoto(0, sum);
					this.examController.startExamView();
					dispose();
				});
				SwingUtilities.updateComponentTreeUI(this);	
				this.repaint();
			});
			submitForma.addActionListener(e->{
				
				submitCalci.setEnabled(false);
				submitCombattimento.setEnabled(false);
				submitForma.setEnabled(false);
				
				guida = new JFrame();
				JPanel mainGuida = new JPanel();
				guida.add(mainGuida);
				mainGuida.add(new JLabel(new ImageIcon(examController.openGuidaTecnica("forma",atleta.getBelt().getValue()))));
				guida.setVisible(true);
				guida.pack();
				
				final JLabel forma = new JLabel("Forma");
				forma.setForeground(new Color(0, 0, 0));
				forma.setBounds(230, 70, 100, 30);
				forma.setFont(new Font("Arial", Font.BOLD, 15));
				// name.setBackground(new Color(255, 255, 255));
				mainPanel.add(forma);
				
				for(int i=0; i<listaMaestri.size();i++){

					nomiMaestri[i] = new JLabel(listaMaestri.get(i).getSurname());
					SpinnerModel modelJS = new SpinnerNumberModel(5,1,10,1);
					JSpinner spinner = new JSpinner(modelJS);
					((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
					listaText.add(spinner);
				}
				int h = 110;
				for(JLabel l : nomiMaestri){	
					l.setForeground(new Color(0, 0, 0));
					l.setBounds(150, h, 100, 40);
					l.setFont(new Font("Arial", Font.BOLD, 15));
					// name.setBackground(new Color(255, 255, 255));
					mainPanel.add(l);
					h+= 50;
				}
				h = 110;
				for(JSpinner s : listaText){
					s.setForeground(new Color(0, 0, 0));
					s.setBounds(270, h, 70, 40);
					s.setFont(new Font("Arial", Font.BOLD, 15));
					// name.setBackground(new Color(255, 255, 255));
					mainPanel.add(s);
					h += 50;
				}
				h = 110;
				
				submit.setForeground(new Color(0, 0, 0));
				submit.setBounds(260, 410, 100, 40);
				submit.setFont(new Font("Arial", Font.BOLD, 15));
				// name.setBackground(new Color(255, 255, 255));
				mainPanel.add(submit);
				
				submit.addActionListener(a->{
					guida.dispose();
					Integer sum = 0;
					for(JSpinner b: listaText){
						
						sum +=  Integer.parseInt(b.getValue().toString());			
					}
					
					sum = sum / listaMaestri.size();
					atleta.setVoto(1, sum);
					this.examController.startExamView();
					dispose();
				});
				SwingUtilities.updateComponentTreeUI(this);	
				
			});
			submitCombattimento.addActionListener(e->{
				
				submitCalci.setEnabled(false);
				submitCombattimento.setEnabled(false);
				submitForma.setEnabled(false);
				
				guida = new JFrame();
				JPanel mainGuida = new JPanel();
				guida.add(mainGuida);
				mainGuida.add(new JLabel(new ImageIcon(examController.openGuidaTecnica("combattimento",atleta.getBelt().getValue()))));
				guida.setVisible(true);
				guida.pack();
				
				final JLabel combat = new JLabel("Combattimento");
				combat.setForeground(new Color(0, 0, 0));
				combat.setBounds(200, 70, 200, 30);
				combat.setFont(new Font("Arial", Font.BOLD, 15));
				// name.setBackground(new Color(255, 255, 255));
				mainPanel.add(combat);
				
				JPanel focusPanel1 = new JPanel();
				mainPanel.add(focusPanel1);
				focusPanel1.setLayout(new GridLayout(listaMaestri.size(),2));
				for(int i=0; i<listaMaestri.size();i++){

					nomiMaestri[i] = new JLabel(listaMaestri.get(i).getSurname());
					SpinnerModel modelJS = new SpinnerNumberModel(5,1,10,1);
					JSpinner spinner = new JSpinner(modelJS);
					((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
					listaText.add(spinner);
				}
				int h = 110;
				for(JLabel l : nomiMaestri){	
					l.setForeground(new Color(0, 0, 0));
					l.setBounds(150, h, 100, 40);
					l.setFont(new Font("Arial", Font.BOLD, 15));
					// name.setBackground(new Color(255, 255, 255));
					mainPanel.add(l);
					h+= 50;
				}
				h = 110;
				for(JSpinner s : listaText){
					s.setForeground(new Color(0, 0, 0));
					s.setBounds(270, h, 70, 40);
					s.setFont(new Font("Arial", Font.BOLD, 15));
					// name.setBackground(new Color(255, 255, 255));
					mainPanel.add(s);
					h += 50;
				}
				h = 110;
				
				submit.setForeground(new Color(0, 0, 0));
				submit.setBounds(260, 410, 100, 40);
				submit.setFont(new Font("Arial", Font.BOLD, 15));
				// name.setBackground(new Color(255, 255, 255));
				mainPanel.add(submit);
				
				submit.addActionListener(a->{
					guida.dispose();
					Integer sum = 0;
					for(JSpinner b: listaText){
						
						sum +=  Integer.parseInt(b.getValue().toString());					
					}
					sum = sum / listaMaestri.size();
					atleta.setVoto(2, sum);
					this.examController.startExamView();
					dispose();
				});
				SwingUtilities.updateComponentTreeUI(this);	
			});
		
		goback.addActionListener(e->{
			
			this.examController.startExamView();
			guida.dispose();
			dispose();
			
		});
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void addObserver(ExamController controller) {

		this.examController = controller;
	}

}
