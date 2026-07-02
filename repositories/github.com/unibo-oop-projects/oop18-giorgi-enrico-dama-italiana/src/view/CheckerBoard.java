package view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.Controller;
import model.CheckerBoardShadow;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import model.CheckerBoardShadow.piecesType;

public class CheckerBoard extends JFrame {
	private static final long serialVersionUID = -6218820567019985015L;
	private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
	private final Map<Pair<Integer,Integer>,DamaBox> pos = new HashMap<>();



	//Draw checkerboard background Black&White
	/**
	 * 
	 * @param rows of the checkerBoard
	 * @param cols - column of the checkerBoard
	 * @param controller - istance of the controller made for communicate the piece selected on the view
	 */
	public CheckerBoard(int rows, int cols, Controller controller) {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		//String name = null;
		//this.piece= new Piece(name);
		JPanel panel = new JPanel(new GridLayout(rows,cols));
		this.getContentPane().add(BorderLayout.CENTER,panel);

		ActionListener al = (e)->{
			final JButton bt = (JButton)e.getSource();
			final Pair<Integer,Integer> p = buttons.get(bt);
			controller.click(p);

		};

		for (int r=0; r<rows; r++){
			for (int c=0; c<cols; c++){
				final DamaBox jb = new DamaBox();

				jb.setBackground(Color.white);


				if((r%2==0 && c%2==0)||(r%2==1 && c%2==1)) {


					jb.setBackground(Color.gray);

				}
				jb.addActionListener(al);
				this.buttons.put(jb,new Pair<>(r,c));
				this.pos.put(new Pair<>(r,c),jb);
				panel.add(jb);

			}
		}
		this.setVisible(true);
		setResizable(false);
	}

	/**
	 * 
	 * @param x - row of the checkerBoard
	 * @param y - column of the checkerBoard
	 * @param pt - enum, current piece Type to set the piece on the checkerBoard through the Map "pos" (positions)
	 */
	public void SetPieces(int x, int y, piecesType pt) {
		this.pos.get(new Pair<>(x,y)).SetPiece(pt);
	}
	/**
	 * Reset all the ReD border on the checkerBoard
	 */
	public void resetRedBorder() {
		for (DamaBox d : this.pos.values())
		{
			d.setBorder(null);
		}
	}
	/**
	 * Set the red Border on position x,y
	 * @param x - row
	 * @param y - column
	 */
	public void setBorder(int x,int y)
	{ //color,thickness
		this.pos.get(new Pair<>(x,y)).setBorder(new LineBorder(Color.RED,5));
	}






}