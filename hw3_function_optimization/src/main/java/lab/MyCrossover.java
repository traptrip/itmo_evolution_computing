package lab;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyCrossover extends AbstractCrossover<double[]> {
    protected MyCrossover() {
        super(1);
    }

    protected List<double[]> mate(double[] p1, double[] p2, int i, Random random) {
        ArrayList children = new ArrayList();

        // your implementation:
        double alpha = random.nextDouble();
        double[] child = simpleCrossover(p1, p2, alpha, random);

        children.add(child);
        children.add(p1);
        children.add(p2);

        return children;
    }
    public static double[] simpleCrossover(double[] parent1, double[] parent2, double alpha, Random random) {
        double[] child = new double[parent1.length];
        double threshold = 0.5;
        for (int i = 0; i < parent1.length; i++) {
            if (random.nextDouble() < threshold) {
                // cross parents
                child[i] = alpha * parent1[i] + (1 - alpha) * parent2[i];
            } else {
                // chose one of parents
                if (random.nextDouble() < 0.5) {
                    child[i] = parent1[i];
                }
                else {
                    child[i] = parent2[i];
                }
            }
        }
        return child;
    }
}


