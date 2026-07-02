package todo.launcher.platforms;

import java.io.File;
import java.util.Collections;
import java.util.List;

class Linux implements Platform {
    @Override
    public String getJvmExecutable() {
        return System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
    }

    @Override
    public boolean isCurrent() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    @Override
    public List<String> getCommandLineFlags() {
        return Collections.emptyList();
    }
}
