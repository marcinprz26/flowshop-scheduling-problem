import java.util.ArrayList;
import java.util.Random;

public class FlowShopScheduling {

    private int tasks;
    private int machines;
    private int populationNumber;
    private int maxGeneration;
    private int initStable;
    private double crossoverProb;
    private double mutationProb;
    private int crossoverType = Crossover.PMXCROSSOVER;
    private Random random = new Random();

    public FlowShopScheduling(int tasks, int machines, int populationNumber, int maxGeneration, int initStable,
                              double crossoverProb, double mutationProb) {
        this.tasks = tasks;
        this.machines = machines;
        this.populationNumber = populationNumber;
        this.maxGeneration = maxGeneration;
        this.initStable = initStable;
        this.crossoverProb = crossoverProb;
        this.mutationProb = mutationProb;
    }

    public FlowShopScheduling(int tasks, int machines, int populationNumber, int maxGeneration, int initStable,
                              double crossoverProb, double mutationProb, int crossoverType) {
        this.tasks = tasks;
        this.machines = machines;
        this.populationNumber = populationNumber;
        this.maxGeneration = maxGeneration;
        this.initStable = initStable;
        this.crossoverProb = crossoverProb;
        this.mutationProb = mutationProb;
        this.crossoverType = crossoverType;
    }

    public void schedule() {
        Population population = new Population(populationNumber, tasks, machines);

        ArrayList<Permutation> populationList = population.getPopulation();
        int generations = 0, stableResult = 0;
        long lastResult = Long.MAX_VALUE;

        System.out.println("Initial population:");
        Print.printPopulation(population);

        while(maxGeneration != generations || stableResult < initStable) {
            populationList = population.roulette();

            System.out.println("-=-=-=-=-=-=-=-");
            System.out.println("\tStep " + (generations+1));
            System.out.println("-=-=-=-=-=-=-=-\n");

            System.out.println("Selected population:");
            Print.printPopulation(population);

            if (random.nextDouble() < crossoverProb) {
                for (int i = 0; i < populationList.size(); i = i+2) {
                    System.out.println("Parents: ");
                    Print.printPermutation(populationList.get(i));
                    Print.printPermutation(populationList.get(i + 1));
                    System.out.println();

                    ArrayList<Permutation> children = Crossover.Crossover(populationList.get(i), populationList.get(i + 1), crossoverType);
                    populationList.set(i, children.get(0));
                    populationList.set(i + 1, children.get(1));

                    System.out.println("Children: ");
                    Print.printPermutation(children.get(0));
                    Print.printPermutation(children.get(1));
                    System.out.println();
                }
            }

            for (int i = 0; i < populationList.size(); i++) {
                if (random.nextDouble() < mutationProb) {
                    System.out.print("Permutation before mutation: ");
                    Print.printPermutation(populationList.get(i));

                    Permutation mutated = Mutation.mutation(populationList.get(i));
                    populationList.set(i, mutated);

                    System.out.print("Permutation after mutation:  ");
                    Print.printPermutation(mutated);
                    System.out.println();
                }
            }

            population.setPopulation(populationList);

            generations++;

            if (lastResult == population.getBestPermutation().getMakespan())
                stableResult++;
            else
                lastResult = population.getBestPermutation().getMakespan();

            Print.printPopulation(population);
        }
    }
}
