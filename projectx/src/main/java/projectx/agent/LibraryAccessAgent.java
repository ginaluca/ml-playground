package projectx.agent;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Agent which builds a list of classes which make use of a specific library.
 */
public class LibraryAccessAgent implements Agent<ClassAgent> {

    private final String libraryPackage;
    private Set<ClassAgent> clientClassesAgents = new HashSet<>();

    public LibraryAccessAgent(String libraryPackage) {
        this.libraryPackage = libraryPackage;
    }

    @Override
    public String identifier() {
        return libraryPackage;
    }

    @Override
    public String toString() {
        return libraryPackage + ": " + clientClassesAgents.size() + " classes using the library. First 5: " +
                clientClassesAgents.stream().map(Agent::identifier).limit(5).collect(Collectors.toList());
    }

    @Override
    public void accept(ClassAgent classAgent) {
        for (String importDeclaration : classAgent.getImports()) {
            if (importDeclaration.startsWith(libraryPackage)) {
                this.clientClassesAgents.add(classAgent);
            }
        }
    }
}
