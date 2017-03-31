package projectx.agent;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import projectx.FileMetadata;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;

/**
 * Agent which extracts and exposes language level information from a java file (e.g. list of imports).
 */
public class ClassAgent implements Agent<FileMetadata> {

    private Set<String> usedTypes = new HashSet<>();
    private List<String> imports = new ArrayList<>();
    private String className;

    public ClassAgent() {
    }

    @Override
    public void accept(FileMetadata fileMetadata) {
        try (InputStream is = Files.newInputStream(fileMetadata.getFile())) {
            CompilationUnit compilationUnit = JavaParser.parse(is);
            className = compilationUnit.getType(0).getName().getIdentifier();
            compilationUnit.getNodesByType(MethodDeclaration.class).forEach(methodDeclaration -> {
                if (methodDeclaration.getType() instanceof ClassOrInterfaceType) {
                    usedTypes.add(((ClassOrInterfaceType) methodDeclaration.getType()).getName().getIdentifier());
                }
                for (Parameter parameter : methodDeclaration.getParameters()) {
                    if (parameter.getType() instanceof ClassOrInterfaceType) {
                        usedTypes.add(((ClassOrInterfaceType) parameter.getType()).getName().getIdentifier());
                    }
                }});
            compilationUnit.getImports().stream().forEach(importDeclaration -> {
                imports.add(importDeclaration.getName().toString());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String identifier() {
        return className;
    }

    public Set<String> getUsedTypes() {
        return usedTypes;
    }

    @Override
    public String toString() {
        return className + ": " + usedTypes;
    }

    public List<String> getImports() {
        return imports;
    }
}
