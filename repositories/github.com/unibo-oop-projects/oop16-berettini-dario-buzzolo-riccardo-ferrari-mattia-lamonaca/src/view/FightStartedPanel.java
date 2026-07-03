package view;

import controller.FightController;

public interface FightStartedPanel {

	public void endFightMessage(String message);

	public void addObserver(FightController controller);
}
