package moves.damagingmove.physical.onehitko;

import types.Normal;

public class HornDrill extends OneHitKOPhysicalDamagingMove{

    public HornDrill() {
        super(  "Horn Drill",                                                                                                   //name
                "The user stabs the target with a horn that rotates like a drill.\n"+                                           //description
                "The target faints instantly if this attack hits. ",                 
                new Normal(),                                                                                                   //type
                0.30,                                                                                                           //accuracy
                5,                                                                                                              //PP
                0);                                                                                                             //Priority
    }

}
