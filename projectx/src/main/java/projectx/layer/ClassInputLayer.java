package projectx.layer;

import projectx.AgentRegister;
import projectx.FileMetadata;
import projectx.agent.ClassAgent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Input layer creating a population of {@link ClassAgent}s.
 */
public class ClassInputLayer implements InputLayer<FileMetadata>, AgentRegister<ClassAgent> {
    private Map<String, ClassAgent> agentsByIdentifier = new HashMap<>();
    private List<Layer<ClassAgent>> nextLayers;

    public void setNextLayers(List<Layer<ClassAgent>> nextLayers) {
        this.nextLayers = nextLayers;
        nextLayers.forEach(l -> l.setPreviousLayer(this));
    }

    @Override
    public void onFinish() {
        for (ClassAgent classAgent : agentsByIdentifier.values()) {
            nextLayers.forEach(l -> l.accept(classAgent));
        }
        nextLayers.forEach(Layer::onFinish);
    }

    @Override
    public void accept(FileMetadata fileMetadata) {
        ClassAgent classAgent = new ClassAgent();
        classAgent.accept(fileMetadata);
        agentsByIdentifier.put(classAgent.identifier(), classAgent);
    }

    @Override
    public ClassAgent getByIdentifier(String identifier) {
        return agentsByIdentifier.get(identifier);
    }
}
