package model.analysis;

import controller.EnvironmentControllerImpl;

public class ProvaAnalisi {

    private final EnvironmentControllerImpl envController = new EnvironmentControllerImpl();

    public ProvaAnalisi() {
        this.envController.showAnalysis();
    }

//    public static void main(final String[] args) {
//      new ProvaAnalisi();
//    }

}
