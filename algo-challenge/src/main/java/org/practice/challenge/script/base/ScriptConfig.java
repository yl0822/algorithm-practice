package org.practice.challenge.script.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author feikong
 * @version 2022/7/25
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScriptConfig {
    /**
     * 脚本执行状态
     */
    private boolean started = false;

    /**
     * 单个数据单元处理失败后，是否终止执行，由于数据之间没有关联一般是可以继续执行后续数据的，存在一些场景要求支持快速失败。
     */
    private boolean fastFail = false;

    /**
     * 单批处理数量，主要用于限制从mysql单次拉取数据量
     */
    private int roundSize = 100;

    /**
     * 总处理量限制，存在一些场景是已经确定总数据量的，设定该值可以防止查询范围溢出
     */
    private int totalSize = Integer.MAX_VALUE;

    /**
     * 并发等级，默认=1表示串行执行
     */
    private ConcurrencyEnum concurrency = ConcurrencyEnum.LEVEL_1;

    public ScriptConfig(ConcurrencyEnum concurrency) {
        this.concurrency = concurrency;
    }
}
