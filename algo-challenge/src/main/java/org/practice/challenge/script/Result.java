package org.practice.challenge.script;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author feikong
 * @version 2022/7/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private String c;
    private String m;
    private T d;

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>("-1", message, null);
    }

    public static <T> Result<T> ok(T d) {
        return new Result<>("0", "", d);
    }
}
