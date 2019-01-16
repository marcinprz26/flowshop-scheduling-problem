import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FlowShopScheduling fs = new FlowShopScheduling(4, 2, 4, 3, 0, 1.0, 0.1, Crossover.PMXCROSSOVER);
        //        fs.scheduleWithRandom();

        fs = new FlowShopScheduling(100, 100, 5, 1.0, 0.1, Crossover.OWNCROSSOVER);
        fs.scheduleWithFile("res/tai500_20.txt");

        long elapsedTimeMillis = System.currentTimeMillis()-start;
        float elapsedTimeSec = elapsedTimeMillis/1000F;

        System.out.println("\nCompleted in: " + elapsedTimeSec + " sec" );
    }
}
