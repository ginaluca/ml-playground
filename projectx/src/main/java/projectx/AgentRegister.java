package projectx;

import projectx.agent.Agent;

/**
 * One of the roles of a layer as it is seen by the next layer in the stack is that of a register which exposes the agents.
 *
 * @param <A> Type of the agents which are exposed.
 */
public interface AgentRegister<A extends Agent> {

    A getByIdentifier(String identifier);
}
