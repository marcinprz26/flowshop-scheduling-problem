public class Main {

    public static void main(String[] args) {
        FlowShopScheduling fs = new FlowShopScheduling(5, 2, 4, 100,
                20, 1.0, 0.3, Crossover.PMXCROSSOVER);
        fs.schedule();
    }
}
