package todo.model.program;

import java.util.Optional;

import todo.controller.clipboard.ClipboardProvider;

public class DummyClipboard implements ClipboardProvider {
    private Optional<String> clipboardContent;

    public DummyClipboard() {
        this.clipboardContent = Optional.empty();
    }

    @Override
    public Optional<String> get() {
        return this.clipboardContent;
    }

    @Override
    public void set(final String string) {
        this.clipboardContent = Optional.of(string);
    }

}
