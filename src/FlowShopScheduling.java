import java.util.ArrayList;
import java.util.Collections;
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

    public void schedule(Population population) {
        ArrayList<Permutation> populationList;
        int generations = 0, stableResult = 0;
        long lastResult = Long.MAX_VALUE;

        System.out.println("Initial population:");
        Print.printPopulation(population);

        while (maxGeneration != generations || stableResult < initStable) {
            populationList = population.roulette();

            Print.printGeneration(generations);

            System.out.println("Selected population:");
            Print.printPopulation(population);

            if (random.nextDouble() < crossoverProb) {
                for (int i = 0; i < populationList.size(); i = i + 2) {
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

            Print.printAverageMakespan(population.calculateAverageMakespan());
        }

        System.out.println("Generated best permutation:");
        Print.printPermutationWithOperations(population.getBestPermutation());
    }
    public void scheduleWithRandom() {
        Population population = new Population(populationNumber, tasks, machines);

        schedule(population);
    }

    public void scheduleWithData() {
        ArrayList<Integer> operations = new ArrayList<>();

        operations.add(7);
        operations.add(3);

        operations.add(4);
        operations.add(6);

        operations.add(3);
        operations.add(1);

        operations.add(2);
        operations.add(4);

        operations.add(5);
        operations.add(2);

        operations.add(3);
        operations.add(7);

        operations.add(1);
        operations.add(4);

        ArrayList<Task> tempTasks = new ArrayList<>();
        for (int n = 0; n < tasks; n++) {
            ArrayList<Integer> tempOperations = new ArrayList<>();
            for (int m = 0; m < machines; m++) {
                tempOperations.add(operations.remove(0));
            }
            tempTasks.add(new Task(n, tempOperations));
        }
        ArrayList<Permutation> permutations = new ArrayList<>();
        for (int n = 0; n < populationNumber; n++) {
            ArrayList<Task> permutationsTasks = new ArrayList<>(tempTasks);
            Collections.shuffle(permutationsTasks);

            permutations.add(new Permutation(permutationsTasks));
        }

        Population population = new Population(populationNumber, permutations);

        schedule(population);
    }
}