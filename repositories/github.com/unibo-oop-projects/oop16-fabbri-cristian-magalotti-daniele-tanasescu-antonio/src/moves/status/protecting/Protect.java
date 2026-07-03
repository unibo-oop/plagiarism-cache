package moves.status.protecting;


import types.Normal;

public class Protect extends ProtectingStatusMove{

    public Protect() {
        super(  "Protect",                                                                                                       //name
                "Enables the user to evade all attacks. Its chance of failing rises if it is used in succession.",               //description
                new Normal(),                                                                                                    //type
                10,                                                                                                              //PP                                                                                                                     
                +4);                                                                                                             //priority  
    }

}
