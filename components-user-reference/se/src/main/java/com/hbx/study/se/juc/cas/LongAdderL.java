package com.hbx.study.se.juc.cas;

/**
 * 解决AtomicLong 性能问题，
 * 空间换时间
 * 原理 基于一个cell数组， 竞争激烈的情况下，每个线程都会经过hash到cell的一个槽，单独计算，最后在对cell数组进行汇总
 * 但是可能会出现计算不准的情况， 因为在汇总的时候可能有线程在并发更新，没有汇总进来
 */
public class LongAdderL {

    public static void main(String[] args) {

    }
}
