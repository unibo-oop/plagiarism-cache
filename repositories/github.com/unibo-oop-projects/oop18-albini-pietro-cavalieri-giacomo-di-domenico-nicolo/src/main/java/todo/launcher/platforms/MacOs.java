package todo.launcher.platforms;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class MacOs implements Platform {

    @Override
    public String getJvmExecutable() {
        return System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
    }

    @Override
    public boolean isCurrent() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    @Override
    public List<String> getCommandLineFlags() {
        // macOS requires a few extra flags to start the game properly.
        final List<String> flags = new ArrayList<>();
        // https://docs.oracle.com/javase/9/tools/java.htm#GUID-3B1CE181-CD30-4178-9602-230B800D4FAE__GUID-3305E367-1496-4863-BD96-8E2F462F14CF
        flags.add("-XstartOnFirstThread");
        // https://github.com/libgdx/libgdx/issues/5205#issuecomment-417277584
        flags.add("-Dorg.lwjgl.system.allocator=system");
        return flags;
    }
}
