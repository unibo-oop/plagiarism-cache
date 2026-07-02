package jwhale.model.engine.operations.creation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
/**
 * Configurator for image build operation.
 *
 */
public final class ImageBuild extends AbstractCreationalOpImpl<ImageBuild> {
    private static final String EMPTY = "";
    private static final String TAG = "t";

    public ImageBuild() {
        super(Method.POST, EndPoint.BUILD);
    }
    /**
     * Get archive path.
     * @param archivePath
     *          tar archive path.
     * @return
     *          itself
     * @throws IOException
     */
    public ImageBuild getArchive(final String archivePath) throws IOException {
        getOperation().setBodyRequest(Files.readString(Paths.get(archivePath)));
        setSetup();
        return this;
    }
    @Override
    public ImageBuild create(final String mandatoryParam) {
        getOperation().setLastParam(EMPTY);
        getOperation().setQueryParams(TAG, mandatoryParam);
        return this;
    }
}
