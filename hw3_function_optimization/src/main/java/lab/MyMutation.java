package lab;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class MyMutation implements EvolutionaryOperator<double[]> {
    public List<double[]> apply(List<double[]> population, Random random) {
        // initial population
        // need to change individuals, but not their number!

        // your implementation:
        double mutationThreshold = 0.5;
        double sigma = 0.5;
        int numIndividuals = population.size();
        for (int i = 0; i < numIndividuals; i++) {
            double[] individual = population.get(i);
            double[] mutatedIndividual = gaussianMutation(individual, random, mutationThreshold, sigma);
            population.set(i, mutatedIndividual);
        }

        //result population
        return population;
    }

    public static double[] gaussianMutation(double[] individual, Random random, double mutationThreshold, double sigma) {
        int numGenes = individual.length;
        double[] mutatedIndividual = new double[numGenes];
        int maxMutations = (int) (0.5 * numGenes);
        int cntMutations = 0;
        for (int i = 0; i < numGenes; i++) {
            if ((cntMutations < maxMutations) & (random.nextDouble() < mutationThreshold)) {
                // add noise
                mutatedIndividual[i] = individual[i] + sigma * random.nextDouble() * random.nextGaussian();
                // clamp to [-5, 5]
                mutatedIndividual[i] = Math.min(Math.max(mutatedIndividual[i], -5), 5);
                cntMutations++;
            } else {
                mutatedIndividual[i] = individual[i];
            }
        }
        return mutatedIndividual;
    }
}
