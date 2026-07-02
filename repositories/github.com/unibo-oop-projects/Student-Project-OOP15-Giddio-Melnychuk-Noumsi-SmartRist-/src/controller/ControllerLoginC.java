package controller;

import java.util.List;

import pro_ristorante_oop.*;

public class ControllerLoginC implements ControllerLogin {
	List<Cuoco> c;
	List<Cameriere> cam;
	Proprietario p;
	@Override
	public void checkLog() {
		
		String stringaView = null;
		// TODO Auto-generated method stub
		List<Persona> pe = SaveAndGetDatas.loadPersone(Persona.class);
		int i=0;
		do{
			if(pe.get(i).getNome() == stringaView && pe.get(i).getPassword() == stringaView){
				i=0;
				do{
					if(pe.get(i).getClass()==Cuoco.class){
						c.add((Cuoco) pe.get(i));
					}
					if(pe.get(i).getClass()==Cameriere.class){
						cam.add((Cameriere) pe.get(i));
					}
					if(pe.get(i).getClass()==Proprietario.class){
						p=(Proprietario) pe.get(i);
					}
					}while(!pe.equals(null));
			}
		}while(!pe.equals(null));
		for(i=0;i<c.size();i++){
			c.get(i).kichenIsOpen();
		}
		for(i=0;i<cam.size();i++){
			cam.get(i).cameriereInAtt();
		}
		/*qui faccio partire le altre View*/
	}

}
