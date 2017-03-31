package projectx;

import java.nio.file.Path;

public class FileMetadata {
    private final Path file;
    private final String baseDirectory;

    public FileMetadata(Path file, String baseDirectory) {
        this.file = file;
        this.baseDirectory = baseDirectory;
    }

    public Path getFile() {
        return file;
    }

    public String getBaseDirectory() {
        return baseDirectory;
    }
}
