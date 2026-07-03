package model.levels;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.DonkeyKongImpl;
import model.entities.FloorTile;
import model.entities.FloorTileImpl;
import model.entities.MarioImpl;
import model.entities.PrincessImpl;
import model.entities.Stair;
import model.entities.StairImpl;

public class BasicLevelBuilder extends BasicLevelImpl{
    
    List<FloorTile> floor = new ArrayList<>();
    List<Stair> stairs = new ArrayList<>();
    Scanner sc;
    String tmp;
    
    public BasicLevelBuilder(final File level) {
        
        try {
            sc = new Scanner(level);
            while(sc.hasNext()) {
                tmp = new String(sc.next());
                
                if(tmp.equals("name")) {
                super.setLevelName(tmp);
                } else if(tmp.equals("image_directory")) {
                super.setImageDirectory(tmp);
                } else if(tmp.equals("mario")) {
                    super.setMario(new MarioImpl(Double.parseDouble(sc.next()), Double.parseDouble(sc.next()), new Dimension(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()))));
                } else if (tmp.equals("donkey_kong")) {
                    super.setDonkeyKong(new DonkeyKongImpl(Double.parseDouble(sc.next()), Double.parseDouble(sc.next()), new Dimension(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()))));
                } else if (tmp.equals("princess")) {
                    super.setPrincess(new PrincessImpl(Double.parseDouble(sc.next()), Double.parseDouble(sc.next()), new Dimension(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()))));
                } else if (tmp.equals("floor")) {
                    floor.add(new FloorTileImpl(Double.parseDouble(sc.next()), Double.parseDouble(sc.next()), new Dimension(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()))));
                } else if (tmp.equals("stair")) {
                    stairs.add(new StairImpl(Double.parseDouble(sc.next()), Double.parseDouble(sc.next()), new Dimension(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()))));
                }
            }
            
            super.setStairs(stairs);
            super.setFloor(floor);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
