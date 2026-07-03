package view;

import controller.ExamController;

public interface ExamStarted {

	public void examCompletedMessage(String message);
	
	public void addObserver(ExamController controller);
}
