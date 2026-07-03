package com.jlearn.view.voice_recognition;

/**
 * Enum for {@link VoiceRecognition} commands.
 *
 */
public enum VoiceCommands {
    /**
     * Commands.
     */
    HOME("home"), EXERCISE("exercise"), THEORY("theory"), STATISTICS("statistics"), MENU("menu"), VOICE_OFF("voice off"), EXIT("exit application");
    private String voiceCommand;

    VoiceCommands(final String voice) {
        this.voiceCommand = voice;
    }

    /**
     * Get the {@link VoiceCommands} {@link String}.
     *
     * @return the {@link String} command
     */
    public String getVoiceCommand() {
        return this.voiceCommand;
    }
}
