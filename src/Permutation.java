import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Permutation {

    private int tasks;
    private int machines;
    private long makespan;
    private long rouletteRate;
    private ArrayList<Task> permutation;

    public Permutation(int tasks, int machines, ArrayList<Task> permutation) {
        this.tasks = tasks;
        this.machines = machines;
        this.permutation = permutation;
    }

    public Permutation(int tasks, int machines, int range) {
        this.tasks = tasks;
        this.machines = machines;
        this.permutation = this.generateRandomPermutation(tasks, machines, range);
    }

    public int getTasks() {
        return tasks;
    }

    public void setTasks(int tasks) {
        this.tasks = tasks;
    }

    public int getMachines() {
        return machines;
    }

    public void setMachines(int machines) {
        this.machines = machines;
    }

    public long getMakespan() {
        return makespan;
    }

    public void setMakespan(long makespan) {
        this.makespan = makespan;
    }

    public long getRouletteRate() {
        return rouletteRate;
    }

    public void setRouletteRate(long rouletteRate) {
        this.rouletteRate = rouletteRate;
    }

    public ArrayList<Task> getPermutation() {
        return permutation;
    }

    public void setPermutation(ArrayList<Task> permutation) {
        this.permutation = permutation;
    }

    public long calculateMakespan() {

        long[][] makespan = new long[this.tasks][this.machines];
        for (int j = 0; j < this.tasks; j++) {
            for (int m = 0; m < this.machines; m++) {
                if (j == 0 && m == 0)
                    makespan[j][m] = permutation.get(j).getOperations().get(m);
                else if (j == 0)
                    makespan[j][m] = permutation.get(j).getOperations().get(m-1) + permutation.get(j).getOperations().get(m);
                else if (m == 0)
                    makespan[j][m] = permutation.get(j-1).getOperations().get(m) + permutation.get(j).getOperations().get(m);
                else
                    makespan[j][m] = Math.max(makespan[j-1][m], makespan[j][m-1]) + permutation.get(j).getOperations().get(m);
            }
        }

        this.makespan = makespan[this.tasks-1][this.machines-1];

        return makespan[this.tasks-1][this.machines-1];
    }

    private ArrayList<Task> generateRandomPermutation(int tasks, int machines, int range) {
        ArrayList<Task> permutation = new ArrayList<>();

        for(int i=0; i<tasks; i++) {
            permutation.add(new Task(i, machines, range));
        }
        Collections.shuffle(permutation);

        return permutation;
    }

    @Override
    public String toString() {
        return "Permutation{" +
                "permutation=" + Arrays.toString(permutation.toArray()) +
                '}';
    }
}
