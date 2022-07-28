package org.practice.challenge.script.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 并发分级
 *
 * @author feikong
 * @version 2022/7/26
 */
@Getter
@AllArgsConstructor
public enum ConcurrencyEnum {
    LEVEL_1(1),
    LEVEL_3(3),
    LEVEL_5(5),
    LEVEL_10(10);
    private final int concurrency;

    public ConcurrencyEnum nextLevel() {
        for (int i = 0; i < values().length; i++) {
            if (this != values()[i]) {
                continue;
            }
            if (i == values().length - 1) {
                return this;
            } else {
                return values()[i + 1];
            }
        }
        return this;
    }

    public ConcurrencyEnum preLevel() {
        for (int i = 0; i < values().length; i++) {
            if (this != values()[i]) {
                continue;
            }
            if (i == 0) {
                return this;
            } else {
                return values()[i - 1];
            }
        }
        return this;
    }
}
