package view;

import controller.ExamController;

public interface ExamPanel {
	
	public void setMainPanel();
	
	public void addObserver(ExamController controller);	
	public void updateAthlete();
	public void updateMaster();
}
