package org.practice.challenge.script;

import org.practice.challenge.script.base.AbstractProcessScript;
import org.practice.challenge.script.base.ConcurrencyEnum;
import org.practice.challenge.script.base.ScriptConfig;
import org.practice.challenge.script.base.ScriptItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 增量存储批量迁移（kp:2DSJGJ-5080）
 *
 * @author feikong
 * @version 2022/7/25
 */
@Component
@Scope("prototype")
public class DesignIncrProcessScript extends AbstractProcessScript<GdxDesign> {

    public DesignIncrProcessScript(@Qualifier("scriptDataExecutor") ThreadPoolTaskExecutor scriptDataExecutor) {
        this.scriptDataExecutor = scriptDataExecutor;
        this.config = new ScriptConfig(ConcurrencyEnum.LEVEL_3);
        this.processedCnt = 0;
        this.offset = null;
    }

    @Override
    protected List<ScriptItem<GdxDesign>> query() {
        // 已经在具体实现类里面了，就可以把offset确定为long类型
        if (offset == null) {
            offset = "0";
        }
        List<GdxDesign> list = new ArrayList<>();
        return list.stream().map(i -> new ScriptItem<>(i.getId(), i)).collect(Collectors.toList());
    }

    @Override
    protected String refreshOffset(ScriptItem<GdxDesign> lastItem) {
        // 取最后元素的ID值作为offset
        return String.valueOf(lastItem.getItem().getId());
    }

    @Override
    protected void doProcess(ScriptItem<GdxDesign> item) {
    }
}
