package projectx;

import java.util.List;

/**
 * Factory of agents of type B.
 */
public interface AgentFactory<A, B> {

    List<B> create(A inputAgent);
}
