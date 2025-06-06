package com.hbx.study.se.juc;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * 线程池
 * 执行逻辑 先交给核心线程--再交给队列--再交给临时线程
 */
public class Executor {

    public static ExecutorService executor;
    static {
        executor = Executors.newFixedThreadPool(1);
    }


    public static void main(String[] args) throws InterruptedException {
//        没有临时线程，队列大小为2，如果执行3个任务将 像单线程一样
        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(
                1,
                1,
                1000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 3; i++){
            executor1.execute(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor1.shutdown();
        executor1.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        //        1个临时线程，队列大小为1，如果执行3个任务将 有两个并发执行
        executor1 = new ThreadPoolExecutor(
                1,
                2,
                1000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 3; i++){
            executor1.execute(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor1.shutdown();
        executor1.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

    }
}

/**
 * 工作窃取线程池
 * 每个线程都维护一个自己的任务队列，当任务队列为空时，会从其他线程的队列中窃取任务执行
 * 线程池的线程个数是动态改变的
 */
class ForkJoinPoolExecutor {

    static class QuickSort extends RecursiveAction {
        private int[] array;
        private int left;
        private int right;

        public QuickSort(int[] array, int start, int end) {
            this.array = array;
            this.left = start;
            this.right = end;
        }

        public int partition(int left, int right) {
            int pivot = array[right];
            int i = left - 1;
            for (int j = left; j < right; j++) {
                if (array[j] <= pivot) {
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            int temp = array[i + 1];
            array[i + 1] = array[right];
            array[right] = temp;
            return i + 1;

        }

        @Override
        protected void compute() {
            if (left<right){
                int pivot = partition(left, right);
                QuickSort leftTask = new QuickSort(array, left, pivot - 1);
                QuickSort rightTask = new QuickSort(array, pivot + 1, right);
                leftTask.fork();
                rightTask.fork();
                leftTask.join();
                rightTask.join();
            }

        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] arr = {12,35,87,23,65,34,56,78,90};
        forkJoinPool.invoke(new QuickSort(arr,0,arr.length-1));
        System.out.println(Arrays.toString(arr));
    }
}