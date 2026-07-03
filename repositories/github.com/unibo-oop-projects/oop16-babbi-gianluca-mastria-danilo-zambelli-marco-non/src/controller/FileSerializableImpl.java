package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import model.CellImpl;
import model.PlayerImpl;
import model.TokenImpl;
import view.AllListImpl;

public class FileSerializableImpl extends JPanel implements FileSerializable {
	
	private static final long serialVersionUID = 1L;
	
	private AllListImpl allList = new AllListImpl();
	private ApplicationImpl appC = new ApplicationImpl();
	private static File f;		//Instance of file where the game informations are saved
	
	public FileSerializableImpl() {}
	
	public int save(int nPlayers){
		JFileChooser fileChooser = new JFileChooser();
		int n = fileChooser.showSaveDialog(FileSerializableImpl.this);		//Open the dialog and user chooses where to save the file
		if (n == JFileChooser.APPROVE_OPTION) {
			f = fileChooser.getSelectedFile();
			try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f))){	//Create object
				out.writeInt(nPlayers);														//and write in the selected file
				out.writeInt(appC.getTurns());
				out.writeInt(appC.getlastDice());

				ApplicationImpl.getM().getPlayers().forEach(x -> {
					try {
						out.writeObject(x);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});

				ApplicationImpl.getM().getGameBoard().forEach((x,y)->{
					try {
						out.writeObject(x);
						out.writeObject(y);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}	
				});

				allList.getRouteButton().forEach(x -> {
					try {
						out.writeInt(x.getY());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});

				allList.getListBasePlayerBlue().forEach(x -> {
					try {
						out.writeBoolean(x.getY());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});

				allList.getListBasePlayerOrange().forEach(x -> {
					try {
						out.writeBoolean(x.getY());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});

				allList.getListBasePlayerPurple().forEach(x -> {
					try {
						out.writeBoolean(x.getY());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});

				allList.getListBasePlayerRed().forEach(x -> {
					try {
						out.writeBoolean(x.getY());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});

				allList.getListArrivedPlayerBlue().forEach(x -> {
					try {
						out.writeBoolean(x.getY());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});

				allList.getListArrivedPlayerOrange().forEach(x -> {
					try {
						out.writeBoolean(x.getY());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});

				allList.getListArrivedPlayerPurple().forEach(x -> {
					try {
						out.writeBoolean(x.getY());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});

				allList.getListArrivedPlayerRed().forEach(x -> {
					try {
						out.writeBoolean(x.getY());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return n;
	}

	public void load(){
		int nPlayers = 0;
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))){		//Create the object 
			nPlayers = in.readInt();													//and read in the file choose before for load
			appC.setTurns(in.readInt());
			appC.setLastDice(in.readInt());

			appC.startMatch(nPlayers, true);

			for(int i = 0; i < nPlayers; i++){
				try {
					ApplicationImpl.getM().getPlayers().add((PlayerImpl) in.readObject());
				} catch (ClassNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}

			for(int i = 0; i < 4*nPlayers; i++){
				try {
					ApplicationImpl.getM().getGameBoard().put((TokenImpl) in.readObject(), (CellImpl) in.readObject());
				} catch (ClassNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}

			allList.getRouteButton().forEach(x -> {
				try {
					x.setY(in.readInt()); 
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});

			allList.getListBasePlayerBlue().forEach(x -> {
				try {
					x.setY(in.readBoolean()); 
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});

			allList.getListBasePlayerOrange().forEach(x -> {
				try {
					x.setY(in.readBoolean()); 
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});

			allList.getListBasePlayerPurple().forEach(x -> {
				try {
					x.setY(in.readBoolean()); 
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});

			allList.getListBasePlayerRed().forEach(x -> {
				try {
					x.setY(in.readBoolean()); 
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});

			allList.getListArrivedPlayerBlue().forEach(x -> {
				try {
					x.setY(in.readBoolean()); 
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});

			allList.getListArrivedPlayerOrange().forEach(x -> {
				try {
					x.setY(in.readBoolean()); 
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});

			allList.getListArrivedPlayerPurple().forEach(x -> {
				try {
					x.setY(in.readBoolean()); 
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});

			allList.getListArrivedPlayerRed().forEach(x -> {
				try {
					x.setY(in.readBoolean()); 
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	public int loadNPlayers(){
		int nPlayers = 0;
		JFileChooser fileChooser = new JFileChooser();
		int n = fileChooser.showOpenDialog(FileSerializableImpl.this);		//Open the dialog and user chooses where to load the file
		if (n == JFileChooser.APPROVE_OPTION) {
			f = fileChooser.getSelectedFile();
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))){		//Create object
				nPlayers = in.readInt();													//and read in the selected file
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
		return nPlayers;
	}
}
