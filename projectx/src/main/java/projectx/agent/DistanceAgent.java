package projectx.agent;

import projectx.AgentRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Agent associated to a class, which build information about the "distance" of this class from the other classes.
 * By distance we mean transitive usage (e.g. a class A uses B if one of its methods have return type B).
 */
public class DistanceAgent implements Agent<ClassAgent> {

    private Map<String, Double> distanceByAgent = new HashMap<>();
    private AgentRegister<ClassAgent> agentRegister;
    private ClassAgent classAgent;

    @Override
    public String identifier() {
        return "distance-" + classAgent.identifier();
    }

    @Override
    public String toString() {
        return classAgent.identifier() + " " + distanceByAgent.toString();
    }

    @Override
    public void accept(ClassAgent classAgent) {
        this.classAgent = classAgent;
        visit(classAgent.getUsedTypes(), 1.0);
        for (String usedType : classAgent.getUsedTypes()) {
            distanceByAgent.put(usedType, 1.0);
        }
    }

    private void visit(Set<String> usedTypes, double distance) {
        if (distance < 0.1) {
            return;
        }
        for (String usedType : usedTypes) {
            if (!distanceByAgent.keySet().contains(usedType) || distanceByAgent.get(usedType) > distance) {
                distanceByAgent.put(usedType, distance);
            }
            if (agentRegister.getByIdentifier(usedType) != null) {
                visit(agentRegister.getByIdentifier(usedType).getUsedTypes(), distance / 2);
            }
        }
    }

    public void setAgentRegister(AgentRegister<ClassAgent> agentRegister) {
        this.agentRegister = agentRegister;
    }
}
