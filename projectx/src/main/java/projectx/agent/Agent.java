package projectx.agent;

import java.util.function.Consumer;

/**
 * Unit capable of processing input data to obtain some higher level information.
 *
 * @param <T> the type of input data.
 */
public interface Agent<T> extends Consumer<T> {

    String identifier();
}
