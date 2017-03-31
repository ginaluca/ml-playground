package projectx.layer;

import projectx.AgentRegister;
import projectx.agent.Agent;

/**
 * Layer whose only function is to print out whatever it gets passed from the previous layer. It's the last layer in the stack.
 *
 * @param <A> Type of the agents which are pushed by the previous layer.
 */
public class OutputLayer<A extends Agent> implements Layer<A> {

    @Override
    public void accept(A a) {
        System.out.println(a);
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void setPreviousLayer(AgentRegister<A> agentRegister) {

    }
}
