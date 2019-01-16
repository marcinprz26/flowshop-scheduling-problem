public class Main {

    public static void main(String[] args) {
        FlowShopScheduling fs = new FlowShopScheduling(6, 2, 4, 3,
                0, 1.0, 0.1, Crossover.OWNCROSSOVER);

//        fs.scheduleWithRandom();
        fs.scheduleWithData();
    }
}
