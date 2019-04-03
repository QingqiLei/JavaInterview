package concurrent.executors;


import concurrent.basic.MyThread;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class poolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyRecursiveTask();
    }

    private static void fixed (){
        ExecutorService pool = Executors.newSingleThreadExecutor();
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        MyThread t5 = new MyThread();

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.shutdown();
    }

    private static void schrduled() throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        MyThread t5 = new MyThread();
        pool.execute(t1); //要求一个runnable 对象, 然后异步执行
        Future future = pool.submit(t2);
        System.out.println(future.get()); // run方法没有返回值, 所以为null
        // call() 和run() 不同在于他有返回值
        Future future1 = pool.submit(new Callable(){
            public Object call() throws Exception{
                System.out.println("Asynchronous Callable");
                return "Callable result";
            }
        });
        System.out.println(future1.get());
        pool.schedule(t4, 2, TimeUnit.SECONDS);
        pool.schedule(t5,4, TimeUnit.SECONDS);

        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduleAtFixedRate");
            }
        }, 2, 1, TimeUnit.SECONDS);

        pool.shutdown();
    }

    private static void invokeAll() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> callables = new HashSet<Callable<String>>();
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {

            public String call() throws Exception {
//                throw new NullPointerException();
                return "Task 2";

            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        List<Future<String>> futures = executorService.invokeAll(callables);
        for(Future<String> future : futures){
            System.out.println("future.get = " + future.get());
        }

        executorService.shutdown();

    }

    public static void ThreadPoolExecutorTest(){
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 5000;
        ExecutorService threadpOOLExecutor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

    }

    public static void MyRecursiveAction(){
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
        forkJoinPool.invoke(myRecursiveAction);


    }

    public static void MyRecursiveTask(){
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);
        long mergedResult = forkJoinPool.invoke(myRecursiveTask);
        System.out.println("mergedResult = "+mergedResult);
    }




    static class MyThread implements Runnable{
        public void run(){
            System.out.println(Thread.currentThread().getName()+" running ....");
        }
    }
}

class MyRecursiveAction extends RecursiveAction{
    private long workLoad = 0;

    public MyRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }

    protected void compute(){
        if(workLoad > 16){
            System.out.println(" splitting workload");
            List<MyRecursiveAction> subtasks = new ArrayList<>();
            subtasks.addAll(createSubtasks());

            for(RecursiveAction subtask: subtasks)
                subtask.fork();
        }else{
            System.out.println("doing the workload " + this.workLoad);
        }
    }

    private List<MyRecursiveAction> createSubtasks(){
        List<MyRecursiveAction> subtasks = new ArrayList<>();
        MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad / 2);
        MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad / 2);
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }


}

class MyRecursiveTask extends RecursiveTask<Long>{
    private long workLoad = 0;
    public MyRecursiveTask(long workLoad) {
        this.workLoad = workLoad;
    }
    @Override
    protected Long compute() {

        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);
            List<MyRecursiveTask> subtasks =
                    new ArrayList<MyRecursiveTask>();
            subtasks.addAll(createSubtasks());
            for(MyRecursiveTask subtask : subtasks){
                subtask.fork();
            }
            long result = 0;
            for(MyRecursiveTask subtask : subtasks) {
                result += subtask.join();
            }
            return result;
        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
            return workLoad ;
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
}