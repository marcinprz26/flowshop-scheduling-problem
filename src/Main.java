public class Main {

    public static void main(String[] args) {
        FlowShopScheduling fs = new FlowShopScheduling(10, 5, 6, 1000,
                200, 1.0, 0.3, Crossover.PMXCROSSOVER);
        fs.schedule();
    }
}
