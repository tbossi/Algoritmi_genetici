package GA.GeneticAlgorithm;

import java.util.Random;

public abstract class Selection implements ISelection {
    protected final Random random;
    public Selection(Random random) {
        if (random == null) throw new IllegalArgumentException();
        this.random = random;
    }
}
