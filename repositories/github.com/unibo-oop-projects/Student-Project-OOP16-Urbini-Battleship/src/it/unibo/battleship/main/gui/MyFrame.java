/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.gui;

import it.unibo.battleship.main.common.FieldBound;
import it.unibo.battleship.main.common.Point2dImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Class not usable yet.
 * @author fabio.urbini
 */
public final class MyFrame extends JFrame {
  /*
  Use composition over inheritance
  Remove final
  Make a static factory method or public constructor
   */
  private static final long serialVersionUID = -4540296256743795166L;
  private static final int WIDTH = 600;
  private static final int HEIGHT = 600;
  private final List<FieldButton> fieldList;
  private final FieldBound fieldBound;

  // The constructor is private until everything works
  private MyFrame(final String title, final LayoutManager lm,
      final FieldBound fieldBound) {
    super(title);
    this.getContentPane().add(new JPanel(lm));
    this.fieldList = new ArrayList<>();
    this.fieldBound = fieldBound;
    this.initialize();
  }

  private void addButton(final FieldButton btn) {
    this.getContentPane().add(btn.getButton());
  }

  private void initialize() {
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(WIDTH, HEIGHT);
    this.setVisible(true);

    for (int i = 0; i < this.fieldBound.getColumnsCount(); i++) {
      final FieldButton fb = new FieldButton(new Point2dImpl(i,0));
      this.fieldList.add(fb);
      this.addButton(fb);
      fb.getButton().addActionListener(
          (ActionEvent e) -> System.out.println("x : " + fb.getPosition().getX()));
    }
  }

  private List<JButton> getGridOfButtons() {
    final List<JButton> jcomponents = new ArrayList<>();

    // Aggiunta di MxN bottoni.

    /*
     * DECORATOR di JButton? JButton ha anche il valore della posizione
     */
    return jcomponents;
  }

  public JPanel getMainPanel() {
    return (JPanel) this.getContentPane();
  }
}
