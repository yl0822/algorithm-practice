package org.practice.challenge.script.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;

/**
 * 数据处理脚本基础实现
 *
 * @author feikong
 * @version 2022 /7/25
 */
@Slf4j
public abstract class AbstractProcessScript<T> implements DataProcessScript {
    protected static final String LOG_PREFIX = "[SCRIPT] -- ";
    protected ThreadPoolTaskExecutor scriptDataExecutor;
    protected ScriptConfig config;
    protected int processedCnt;
    protected String offset;

    @Override
    public void execute() {
        if (!config.isStarted()) {
//            LogUtil.message(LOG, LOG_PREFIX + "script is shutdown").info();
        }
        StopWatch stopWatch = StopWatch.createStarted();
        List<ScriptItem<T>> list = query();
        while (config.isStarted() && processedCnt <= config.getTotalSize() && !list.isEmpty()) {
            for (ScriptItem<T> item : list) {
                Pair<Boolean, String> validRet = validate(item);
                if (!validRet.getLeft()) {
//                    LogUtil.message(LOG, LOG_PREFIX + "item validate not pass:")
//                            .with("designId", item.getUid())
//                            .info();
                    continue;
                }
                try {
                    if (config.getConcurrency() == ConcurrencyEnum.LEVEL_1) {
                        doProcess(item);
                    } else {
                        scriptDataExecutor.execute(() -> doProcess(item));
                    }
//                    LogUtil.message(LOG, LOG_PREFIX + "item process success:")
//                            .with("designId", item.getUid())
//                            .info();
                } catch (Exception e) {
//                    LogUtil.message(LOG, LOG_PREFIX + "item process failed:", e)
//                            .with("designId", item.getUid())
//                            .error();
                } finally {
                    processedCnt++;
                }
            }
            // 更新偏移量
            this.offset = refreshOffset(list.get(list.size() - 1));
            // 更新分批列表
            list = query();
        }
        // 打印统计信息
//        LogUtil.message(LOG, LOG_PREFIX + "script process done:")
//                .with("costTime", stopWatch.getTime())
//                .with("processedCnt", processedCnt)
//                .with("lastOffset", offset)
//                .info();
    }

    @Override
    public void stop() {
        this.config.setStarted(false);
    }

    @Override
    public void speedUp() {
        config.setConcurrency(config.getConcurrency().nextLevel());
        scriptDataExecutor.setCorePoolSize(config.getConcurrency().getConcurrency());
        scriptDataExecutor.setMaxPoolSize(config.getConcurrency().getConcurrency());
    }

    @Override
    public void slowDown() {
        config.setConcurrency(config.getConcurrency().preLevel());
        scriptDataExecutor.setCorePoolSize(config.getConcurrency().getConcurrency());
        scriptDataExecutor.setMaxPoolSize(config.getConcurrency().getConcurrency());
    }

    /**
     * 获取数据列表
     * 实现者自行根据offset实现分页获取逻辑，初始批次传null
     */
    protected abstract List<ScriptItem<T>> query();

    /**
     * 校验数据单元，默认实现不校验
     */
    public Pair<Boolean, String> validate(ScriptItem<T> item) {
        return Pair.of(true, StringUtils.EMPTY);
    }

    /**
     * 更新偏移量
     * 由于mysql有不同分页方式（limit_offset方式 或 idx>xxx方式）甚至可能非mysql存储等场景，所以offset的逻辑让实现方自行处理。
     * 所以offset具体存储内容由实现者自行设计和处理。
     *
     * @param lastItem 当前轮次最后一个元素
     */
    protected abstract String refreshOffset(ScriptItem<T> lastItem);

    /**
     * 实际执行过程，调度层面无出参，执行中的问题直接抛异常通知外部
     */
    protected abstract void doProcess(ScriptItem<T> item);
}
