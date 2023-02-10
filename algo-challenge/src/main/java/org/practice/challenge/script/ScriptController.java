package org.practice.challenge.script;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.practice.challenge.script.base.DataProcessScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author feikong
 * @version 2022/7/26
 */
@Api(tags = "脚本控制")
@RestController
public class ScriptController {
    @Autowired
    private Map<String, DataProcessScript> scriptMap;

    @ApiOperation("执行脚本")
    @GetMapping("/execute")
    public Result<String> executeScript(@RequestParam String pwd, @RequestParam String name) {
        if (!checkAuth(pwd)) {
            return Result.error("无触发权限");
        }
        scriptMap.get(name).execute();
        return Result.ok();
    }

    @ApiOperation("停止脚本")
    @GetMapping("/stop")
    public Result<String> stopScript(@RequestParam String pwd, @RequestParam String name) {
        if (!checkAuth(pwd)) {
            return Result.error("无触发权限");
        }
        scriptMap.get(name).stop();
        return Result.ok();
    }

    @ApiOperation("执行加速")
    @GetMapping("/speedUp")
    public Result<String> speedUpScript(@RequestParam String pwd, @RequestParam String name) {
        if (!checkAuth(pwd)) {
            return Result.error("无触发权限");
        }
        scriptMap.get(name).speedUp();
        return Result.ok();
    }

    @ApiOperation("执行减速")
    @GetMapping("/slowDown")
    public Result<String> slowDownScript(@RequestParam String pwd, @RequestParam String name) {
        if (!checkAuth(pwd)) {
            return Result.error("无触发权限");
        }
        scriptMap.get(name).slowDown();
        return Result.ok();
    }

    private static boolean checkAuth(String pwd) {
        return "xxx".equals(pwd);
    }
}
