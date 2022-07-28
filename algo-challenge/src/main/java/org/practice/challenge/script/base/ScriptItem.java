package org.practice.challenge.script.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 包装对应的业务数据模型
 *
 * @author feikong
 * @version 2022/7/25
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScriptItem<T> {
    /**
     * item的唯一识别符
     * 一般来说item会是一个较大的模型，为了方便打印日志等操作，通过该值来标记单条处理结果。
     * */
    private String uid;

    /**
     * 对应的业务数据模型
     */
    private T item;
}
