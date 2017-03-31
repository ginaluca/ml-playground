package projectx;

import projectx.agent.ClassAgent;
import projectx.agent.DistanceAgent;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DistanceAgentFactory implements AgentFactory<ClassAgent, DistanceAgent> {

    private final AgentRegister<ClassAgent> agentRegister;

    public DistanceAgentFactory(AgentRegister<ClassAgent> agentRegister) {
        this.agentRegister = agentRegister;
    }

    @Override
    public List<DistanceAgent> create(ClassAgent classAgent) {
        DistanceAgent distanceAgent = new DistanceAgent();
        distanceAgent.setAgentRegister(agentRegister);
        distanceAgent.accept(classAgent);
        return Collections.singletonList(distanceAgent);
    }
}
