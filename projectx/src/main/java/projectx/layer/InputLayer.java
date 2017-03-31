package projectx.layer;

import java.util.function.Consumer;

/**
 * First processing layer in the stack. It doesn't have a previous layer.
 *
 * @param <Input> input type which is accepted by this layer to be fed to its own agents.
 */
public interface InputLayer<Input> extends Consumer<Input> {

    void onFinish();

}
