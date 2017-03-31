package projectx.layer;

import projectx.AgentFactory;
import projectx.AgentRegister;
import projectx.agent.Agent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DefaultLayer<SourceAgent extends Agent, ConsumerAgent extends Agent<SourceAgent>> implements Layer<SourceAgent> {
    private Map<String, ConsumerAgent> agentsByIdentifier = new HashMap<>();
    private Layer<ConsumerAgent> nextLayer;
    private AgentFactory<SourceAgent, ConsumerAgent> agentFactory;

    @Override
    public void onFinish() {
        for (ConsumerAgent localAgent : agentsByIdentifier.values()) {
            nextLayer.accept(localAgent);
        }
        nextLayer.onFinish();
    }

    @Override
    public void setPreviousLayer(AgentRegister<SourceAgent> agentRegister) {

    }

    @Override
    public void accept(SourceAgent inputAgent) {
        if (agentFactory != null) {
            for (ConsumerAgent consumerAgent : agentFactory.create(inputAgent)) {
                agentsByIdentifier.put(consumerAgent.identifier(), consumerAgent);
            }
        } else {
            for (ConsumerAgent consumerAgent : agentsByIdentifier.values()) {
                consumerAgent.accept(inputAgent);
            }
        }
    }

    public void setNextLayer(Layer<ConsumerAgent> nextLayer) {
        this.nextLayer = nextLayer;
    }

    public void setAgentFactory(AgentFactory<SourceAgent, ConsumerAgent> agentFactory) {
        if (!agentsByIdentifier.isEmpty()) {
            throw new IllegalStateException("Either configure the factory or the agents");
        }
        this.agentFactory = agentFactory;
    }

    public void setAgents(ConsumerAgent... consumerAgents) {
        if (agentFactory != null) {
            throw new IllegalStateException("Either configure the factory or the agents");
        }
        for (ConsumerAgent consumerAgent : consumerAgents) {
            agentsByIdentifier.put(consumerAgent.identifier(), consumerAgent);
        }
    }
}
