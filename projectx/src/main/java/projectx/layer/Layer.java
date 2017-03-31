package projectx.layer;

import projectx.AgentRegister;
import projectx.agent.Agent;

import java.util.function.Consumer;

/**
 * Interface of a layer exposed to the previous layer in the stack.
 *
 * @param <SourceAgent> input type which is accepted by this layer to be fed to its own agents.
 */
public interface Layer<SourceAgent extends Agent> extends Consumer<SourceAgent> {

    void onFinish();

    void setPreviousLayer(AgentRegister<SourceAgent> agentRegister);
}
