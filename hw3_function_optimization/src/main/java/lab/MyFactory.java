package lab;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MyFactory extends AbstractCandidateFactory<double[]> {

    private int dimension;

    public MyFactory(int dimension) {
        this.dimension = dimension;
    }

    public double[] generateRandomCandidate(Random random) {
        double[] solution = new double[dimension];
        // x from -5.0 to 5.0

        // your implementation:
        for (int i = 0; i < dimension; i++) {
            solution[i] = -5 + random.nextDouble() * 10.0;  // [-5.0, 5.0]
//            solution[i] = -5 + random.nextGaussian() * 10.0;  // [-5.0, 5.0]
        }

            return solution;
    }
}

