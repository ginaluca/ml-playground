package projectx.agent;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Agent which builds a list of the test classes in the project.
 */
public class TestClassAgent implements Agent<ClassAgent> {
    private Set<ClassAgent> testClassAgents = new HashSet<>();

    @Override
    public String identifier() {
        return "test";
    }

    @Override
    public String toString() {
        return "Test : " + testClassAgents.size() + " test classes. First 5: " +
                testClassAgents.stream().map(Agent::identifier).limit(5).collect(Collectors.toList());
    }

    @Override
    public void accept(ClassAgent classAgent) {
        if (classAgent.identifier().endsWith("Test") || classAgent.identifier().endsWith("Tests")) {
            this.testClassAgents.add(classAgent);
        }
    }
}
