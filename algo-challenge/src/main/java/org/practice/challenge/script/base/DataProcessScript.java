package org.practice.challenge.script.base;

/**
 * 数据处理脚本接口
 *
 * @author feikong
 * @version 2022 /7/25
 */
public interface DataProcessScript {

    /**
     * 执行脚本
     */
    void execute();

    /**
     * 停止脚本
     */
    void stop();

    /**
     * 执行加速（即增大线程池，适用于异步执行）
     */
    void speedUp();

    /**
     * 执行减速（即减小线程池，适用于异步执行）
     */
    void slowDown();
}
