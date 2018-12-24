import java.util.ArrayList;
import java.util.Random;

public class Population {

    private int populationNumber;
    private ArrayList<Permutation> population;
    private double averageMakespan;
    private ArrayList<Population> top3;

    public ArrayList<Permutation> generateRandomPopulation(int tasks, int machines) {
        ArrayList<Permutation> population = new ArrayList<>();

        for (int i=0; i<populationNumber; i++) {
            population.add(new Permutation(tasks, machines, 10));
        }

        return population;
    }

    public Population(int populationNumber, ArrayList<Permutation> population) {
        this.populationNumber = populationNumber;
        this.population = population;
    }

    public Population(int populationNumber) {
        this.populationNumber = populationNumber;
    }

    public Population(int populationNumber, int tasks, int machines) {
        this.populationNumber = populationNumber;
        this.population = generateRandomPopulation(tasks, machines);
    }

    public void roulette() {
        Random random = new Random();
        ArrayList<Permutation> newPopulation = new ArrayList<>();
        long rateSum = 0;

        for (Permutation p : population) {
            rateSum += this.minmaxRate(p);
        }

        for (Permutation p : population) {
            long rate = (this.minmaxRate(p) / rateSum) * 100;
            p.setRouletteRate(rate);
        }

        int[] disc = new int[100];
        long stopPoint = 0;
        for (int i=0, j=0; i < population.size(); i++) {
            stopPoint += population.get(i).getRouletteRate();
            while(j < stopPoint) {
                disc[j] = i;
                j++;
            }
        }

        for (int i=0; i<population.size(); i++) {
            int result = random.nextInt(100);
            newPopulation.add(population.get(disc[result]));
        }

        this.population = newPopulation;
    }

    private long minmaxRate(Permutation p) {
        long minmax[] = getMinMaxMakespan();
        long rate = Math.abs((p.getMakespan() - minmax[0]) / (minmax[1] - minmax[0]));

        return rate;
    }

    private long[] getMinMaxMakespan() {
        long min = 10000, max = 0, makespan = 0;
        for (Permutation p : population) {
            makespan = p.calculateMakespan();
            if(makespan > max)
                max = p.getMakespan();
            if(makespan < min)
                min = makespan;
        }

        return new long[]{min, max};
    }

    public Permutation getBestPermutation() {
        Permutation best = population.get(0);
        long bestMakespan = 1000;
        for (Permutation p : population) {
            if(bestMakespan > p.getMakespan())
                best = p;
        }

        return best;
    }

    public int getPopulationNumber() {
        return populationNumber;
    }

    public void setPopulationNumber(int populationNumber) {
        this.populationNumber = populationNumber;
    }

    public ArrayList<Permutation> getPopulation() {
        return population;
    }

    public void setPopulation(ArrayList<Permutation> population) {
        this.population = population;
    }
}
