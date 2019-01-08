public class Main {

    public static void main(String[] args) {
        FlowShopScheduling fs = new FlowShopScheduling(4, 2, 4, 3,
                0, 1.0, 0.1, Crossover.PMXCROSSOVER);

//        fs.scheduleWithRandom();
        fs.scheduleWithData();
    }
}
