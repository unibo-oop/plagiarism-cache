package moves.status.protecting;

import types.Fight;

public class Detect extends ProtectingStatusMove{

    public Detect() {
        super(  "Detect",                                                                                                        //name
                "Enables the user to evade all attacks. Its chance of failing rises if it is used in succession.",               //description
                new Fight(),                                                                                                     //type
                5,                                                                                                               //PP                                                                                                                     
                +4);                                                                                                             //priority  
    }

}
