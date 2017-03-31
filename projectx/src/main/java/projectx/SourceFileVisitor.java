package projectx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectx.layer.InputLayer;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class SourceFileVisitor extends SimpleFileVisitor<Path> {
    private static final Logger logger = LoggerFactory.getLogger(SourceFileVisitor.class);


    private final String baseDirectory;
    private final InputLayer<FileMetadata> inputLayer;

    public SourceFileVisitor(String baseDirectory, InputLayer<FileMetadata> inputLayer) {
        this.baseDirectory = baseDirectory;
        this.inputLayer = inputLayer;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toAbsolutePath().toString().endsWith(".java") &&
                !file.toAbsolutePath().toString().endsWith("package-info.java")) {
            logger.debug("Feeding {}", file.getFileName());
            inputLayer.accept(new FileMetadata(file, baseDirectory));
        }
        return FileVisitResult.CONTINUE;
    }

    public void onFinish() {
        inputLayer.onFinish();
    }
}
