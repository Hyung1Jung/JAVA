package thread;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyRecursiveTask extends RecursiveTask<Long> {

    private long workLoad = 0;

    public MyRecursiveTask(long workLoad) {
        this.workLoad = workLoad;
    }

    protected Long compute() {
        String threadName = Thread.currentThread().getName();

        //if work is above threshold, break tasks up into smaller tasks
        if (this.workLoad > 16) {
            System.out.println("[" + LocalTime.now() + "][" + threadName + "]"
                    + " Splitting workLoad : " + this.workLoad);
            sleep(1000);
            List<MyRecursiveTask> subtasks =
                    new ArrayList<MyRecursiveTask>();
            subtasks.addAll(createSubtasks());

            for (MyRecursiveTask subtask : subtasks) {
                subtask.fork();
            }

            long result = 0;
            for (MyRecursiveTask subtask : subtasks) {
                result += subtask.join();
                System.out.println("[" + LocalTime.now() + "][" + threadName + "]"
                        + "Received result from subtask");
            }
            return result;

        } else {
            sleep(1000);
            System.out.println("[" + LocalTime.now() + "]["
                    + " Doing workLoad myself: " + this.workLoad);
            return workLoad * 3;
        }
    }

    private List<MyRecursiveTask> createSubtasks() {
        List<MyRecursiveTask> subtasks =
                new ArrayList<MyRecursiveTask>();

        MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
        MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
                ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);
        long mergedResult = forkJoinPool.invoke(myRecursiveTask);
        System.out.println("mergedResult = " + mergedResult);

//      Just wait until all tasks done
        try {
            forkJoinPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}