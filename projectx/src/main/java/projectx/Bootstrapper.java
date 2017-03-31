package projectx;

import projectx.agent.ClassAgent;
import projectx.agent.LibraryAccessAgent;
import projectx.agent.TestClassAgent;
import projectx.layer.ClassInputLayer;
import projectx.layer.DefaultLayer;
import projectx.layer.OutputLayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Bootstrapper {

    public static void main(String[] args) throws IOException {

        String baseDirectory = args[0];
        ClassInputLayer inputLayer = new ClassInputLayer();

        DefaultLayer<ClassAgent, LibraryAccessAgent> layer1a = new DefaultLayer<>();
        layer1a.setAgents(new LibraryAccessAgent("org.apache.lucene"),
                new LibraryAccessAgent("com.fasterxml"));
        layer1a.setNextLayer(new OutputLayer<>());

        DefaultLayer<ClassAgent, TestClassAgent> layer1b = new DefaultLayer<>();
        layer1b.setAgents(new TestClassAgent());
        layer1b.setNextLayer(new OutputLayer<>());

        inputLayer.setNextLayers(Arrays.asList(layer1a, layer1b));

        SourceFileVisitor visitor = new SourceFileVisitor(baseDirectory, inputLayer);
        Files.walkFileTree(Paths.get(baseDirectory), visitor);
        visitor.onFinish();
    }
}
