import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

public class Population {

    private int populationNumber;
    private ArrayList<Permutation> population;
    private double averageMakespan;
    private ArrayList<Population> top3;
    private long minMakespan = 0,
            maxMakespan = 0;
    BigDecimal bd;

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

    public ArrayList<Permutation> generateRandomPopulation(int tasks, int machines) {
        ArrayList<Permutation> population = new ArrayList<>();

        for (int i=0; i<populationNumber; i++) {
            population.add(new Permutation(tasks, machines, 5));
        }

        return population;
    }

    public ArrayList<Permutation> roulette() {

        Random random = new Random();
        ArrayList<Permutation> newPopulation = new ArrayList<>();
        double rateSum = 0;

        for (Permutation p : population) {
            rateSum += this.fittingFunction(p);
        }

        for (Permutation p : population) {
            long rate = (long)((p.getRouletteRate() / rateSum) * 100);
            p.setRouletteRate((double)rate);
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

        return population;
    }

    private double fittingFunction(Permutation p) {
        this.minMakespan = p.getTasks() + p.getMachines() - 1;
        this.maxMakespan = p.calculateMaxTime();

        double rate = ((double)(p.calculateMakespan() - minMakespan) / (double)(maxMakespan - minMakespan)) - 1;
        rate = Math.abs(rate);

        return rate;
    }

    private double minmaxRate(Permutation p) {
        if(minMakespan == 0 || maxMakespan == 0) {
            long minmax[] = getMinMaxMakespan();
            this.minMakespan = minmax[0];
            this.maxMakespan = minmax[1];
        }

        double rate = ((double)(p.calculateMakespan() - minMakespan) / (double)(maxMakespan - minMakespan)) - 1;
        rate = Math.abs(rate);

        bd = new BigDecimal(rate);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        p.setRouletteRate(bd.doubleValue());

        return bd.doubleValue();
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
