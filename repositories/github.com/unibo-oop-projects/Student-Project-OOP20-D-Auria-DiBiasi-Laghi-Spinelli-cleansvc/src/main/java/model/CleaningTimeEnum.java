package model;

import java.time.Duration;

public enum CleaningTimeEnum {

    LAVAGGIO(Duration.ofSeconds(60), "Lavaggio"),               //I secondi sono per metroquadro.
    DISINFEZIONE(Duration.ofSeconds(20), "Disinfezione"),
    RISCIACQUO(Duration.ofSeconds(30), "Riasciacquo");
 
    private final Duration seconds;
    private final String stageName;

    CleaningTimeEnum(final Duration seconds, final String stageName){
        this.seconds = seconds;
        this.stageName = stageName;

    }

    public Duration getSeconds() {
        return seconds;
    }

    public String getStageName() {
        return stageName;
    }
    
}
