package com.hbx.study.se.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 使用技巧
 * runXxx - 没有返回值
 * supplyXxx - 返回值
 * AcceptXxx - 接收前一个任务的返回结果，但是没有返回值
 * ApplyXxx - 接收前⼀个任务的返回结果，有返回值
 */
public class CompletableFutureL {

//    =========== 执行单个任务 ==============

    /**
     * 任务没有返回值
     */
    public static void runAsync() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync:execute task");
        });
    }

    /**
     * 任务有返回值
     */
    public static Object supplyAsync() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "supplyAsync:execute task";
        });
        return completableFuture.join();
    }


//    ============= 异常处理 ===========

    /**
     * whenComplete 可以捕获异常，但是不能更改返回，因为入参是一个BiConsumer
     */
    public static void whenComplete() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("whenComplete:execute task");
            int i = 1/0;
            return i;
        }).whenComplete((res, err) -> {
            System.out.println("execute err:"+err);
        });
    }

    /**
     * exceptionally
     * 但是可以在出现异常的时候，修改返回值,但是不知道任务执行的结果
     */
    public static Object exceptionally() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("exceptionally:execute task");
            int i = 1 / 0;
            return "exceptionally:success";
        }).exceptionally((err) -> {
            System.out.println("exceptionally:execute err:" + err);
            return "exceptionally:err";
        });
        return future.join();
    }

    /**
     * 结合whenComplete和exceptionally使用
     */
    public static Object whenCompleteAndExceptionally() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("whenCompleteAndExceptionally:execute task");
            int i = 1 / 0;
            return "whenCompleteAndExceptionally:success";
        }).whenComplete((res, err) -> {
            System.out.println("whenCompleteAndExceptionally:execute err:" + err);
        }).exceptionally((err) -> {
            return "whenCompleteAndExceptionally:err";
        });
        return future.join();
    }

    /**
     * 推荐使用！！！
     * handle 可以捕获异常，并且可以修改返回值
     * 就是上面两个的结合体
     */
    public static Object handle() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("handle:execute task");
            int i = 1 / 0;
            return "handle:success";
        }).handle((res, err) -> {
            if (err != null){
                System.out.println("handle:execute err:" + err);
                return "handle:err";
            }
            return res;
        });
        return future.join();
    }



//    =========== 三个个任务以下编排  ==========

    /**
     * thenRunAsync (没有返回值)
     * 任务串行执行
     */
    public static void thenRunAsync() {
        CompletableFuture.runAsync(() -> {
            System.out.println("thenRunAsync:execute task-1");
        }).thenRunAsync(() -> {
            System.out.println("thenRunAsync:execute task-2");
        });
    }

    /**
     * thenAcceptAsync (接收上一个任务的返回值,但是自己没有返回值)
     * thenApplyAsync (接收上一个任务的返回值,并且有返回值)
     * 串行执行
     */
    public static void thenAcceptAsync() {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("thenAcceptAsync:execute task-1,cur i is 1");
            return 1;
        }).thenApplyAsync(res -> {
            System.out.println("thenApplyAsync:execute task-2 cur i is:" + (res + 1));
            return res + 1;
        }).thenAcceptAsync(res -> {
            System.out.println("thenAcceptAsync:execute task-3 cur i is:" + (res + 1));
        });
        future.join();
    }

    /**
     * runAfterBothAsync 在自己和另外一个任务都执行后，在执行下一个任务
     */
    public static void runAfterBothAsync(){
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            System.out.println("runAfterBothAsync:execute task-1");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            System.out.println("runAfterBothAsync:execute task-2");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
//      当task1和task2都执行完后，执行task3
        task1.runAfterBothAsync(task2, () -> {
            System.out.println("runAfterBothAsync:execute task-3");
        }).join();

    }

    /**
     * 获取前两个任务执行的结果，执行本次任务
     */
    public static void thenCombineAsync() {
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("thenCombineAsync:execute task-1");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                return 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("thenCombineAsync:execute task-2");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                return 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Integer> combineAsync = task1.thenCombineAsync(task2, (res1, res2) -> {
            System.out.println("thenCombineAsync:execute task-3");
            return res1 + res2 + 1;
        });
        System.out.println(combineAsync.join());
    }

//    ======= 多任务编排 ========

    /**
     * 等待所有任务都执行完
     */
    public static void allOf() throws ExecutionException, InterruptedException {
        /**
         * 多任务组合
         */
        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品图片信息");
            return "hello.jpg";
        });

        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品属性信息");
            return "黑色+256G";
        });

        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("查询商品介绍信息");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "华为...";
        });
        CompletableFuture<Void> future = CompletableFuture.allOf(futureAttr, futureDesc, futureImg);
        future.join();
        System.out.println("allOf:商品详情信息：" + futureImg.get() + "|" + futureAttr.get() + "|" + futureDesc.get());

    }

    /**
     * 等待任意一个任务执行完成
     */
    public static Object anyOf() {
        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品图片信息");
            return "hello.jpg";
        });

        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品属性信息");
            return "黑色+256G";
        });

        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("查询商品介绍信息");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "华为...";
        });
        CompletableFuture<Object> future = CompletableFuture.anyOf(futureAttr, futureDesc, futureImg);
        return future.join();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("=====执行单个任务=====");
        runAsync();
        Object o = supplyAsync();
        System.out.println(o);

        System.out.println("=====异常处理=====");
        whenComplete();
        Object exceptionally = exceptionally();
        System.out.println(exceptionally);
        Object whenCompleteAndExceptionally = whenCompleteAndExceptionally();
        System.out.println(whenCompleteAndExceptionally);
        Object handle = handle();
        System.out.println(handle);
        System.out.println("=====三个任务以下编排=====");
        thenRunAsync();
        thenAcceptAsync();
        runAfterBothAsync();
        thenCombineAsync();
        System.out.println("多任务编排");
        allOf();
        Object o1 = anyOf();
        System.out.println("anyOf:"+o1);
    }


}
