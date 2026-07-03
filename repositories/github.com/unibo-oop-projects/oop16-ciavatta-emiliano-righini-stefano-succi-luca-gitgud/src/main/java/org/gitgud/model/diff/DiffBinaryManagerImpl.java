package org.gitgud.model.diff;

import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.utils.Utils;

import javafx.scene.image.Image;

class DiffBinaryManagerImpl implements DiffBinaryManager {

    private final Repository repository;
    private final String fileObjectId;
    private final String fileName;
    private Optional<BinaryType> binaryType = Optional.empty();

    private InputStream inputStream;

    private boolean alreadyClosed;

    DiffBinaryManagerImpl(final Repository repository, final String fileObjectId, final String fileName) {
        Objects.requireNonNull(repository);
        Objects.requireNonNull(fileObjectId);
        Objects.requireNonNull(fileName);

        this.repository = repository;
        this.fileObjectId = fileObjectId;
        this.fileName = fileName;
    }

    @Override
    public void close() {
        if (alreadyClosed) {
            throw new IllegalStateException("DiffBinaryManager already closed");
        }

        try {
            inputStream.close();
            alreadyClosed = true;
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Can't close DiffBinaryManager");
        }
    }

    @Override
    public Image createImage() {
        if (!binaryType.isPresent() || binaryType.get() != BinaryType.IMAGE) {
            throw new IllegalStateException("File source is not an image");
        }

        return new Image(inputStream);
    }

    @Override
    public BinaryType getBinaryType() {
        if (binaryType.isPresent()) {
            return binaryType.get();
        }

        try {
            final ObjectId objectId = repository.resolve(fileObjectId);
            final ObjectLoader loader = repository.open(objectId);

            binaryType = Optional.of(findBinaryType());
            inputStream = loader.openStream();

            return binaryType.get();
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Can't resolve file image");
        }
    }

    private BinaryType findBinaryType() {
        final String[] parts = fileName.split("\\.");
        if (parts.length < 2) {
            return BinaryType.UNKNOWN;
        }

        switch (parts[parts.length - 1].toLowerCase(Utils.getLocale())) {
            case "png":
            case "git":
            case "jpg":
            case "jps":
            case "mpo":
            case "tiff":
                return BinaryType.IMAGE;
            default:
                return BinaryType.UNKNOWN;
        }
    }

}
