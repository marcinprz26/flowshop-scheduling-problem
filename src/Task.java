import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Task {

    private int number;
    private ArrayList<Integer> operations = new ArrayList<>();

    public Task(int number, ArrayList<Integer> operations) {
        this.number = number;
        this.operations = operations;
    }

    public Task(int number, int machines, int range) {
        this.number = number;
        this.operations = this.generateOperations(machines, range);
    }

    private ArrayList<Integer> generateOperations(int machines, int range) {
        Random random = new Random();
        ArrayList<Integer> op = new ArrayList<>();
        while(machines != 0) {
            op.add(random.nextInt(range) + 1);
            machines--;
        }

        return op;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Integer> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Integer> operations) {
        this.operations = operations;
    }

    @Override
    public boolean equals(Object object) {
        Task task = (Task) object;
        if (this.number != ((Task) object).getNumber())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Task{" +
                "number=" + number +
                ", operations=" + Arrays.toString(operations.toArray()) +
                '}';
    }
}
