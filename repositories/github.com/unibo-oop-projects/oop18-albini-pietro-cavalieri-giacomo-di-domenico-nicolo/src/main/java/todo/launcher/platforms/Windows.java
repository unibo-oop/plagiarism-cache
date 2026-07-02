package todo.launcher.platforms;

import java.io.File;
import java.util.Collections;
import java.util.List;

class Windows implements Platform {
    @Override
    public String getJvmExecutable() {
        return System.getProperty("java.home") + File.separator + "bin" + File.separator + "java.exe";
    }

    @Override
    public boolean isCurrent() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    @Override
    public List<String> getCommandLineFlags() {
        return Collections.emptyList();
    }
}
