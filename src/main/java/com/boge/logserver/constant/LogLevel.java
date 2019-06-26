package com.boge.logserver.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志等级枚举
 */
public enum LogLevel implements IntEnum<LogLevel> {
    //1：info   2：debugger 3:fatal 4:error
    INFO(1,"INFO"),WARN(2,"WARN"),
    FATAL(3,"FATAL"),ERROR(4,"ERROR");

    private int value;
    private String name;


    static Map<Integer, LogLevel> enumMap = new HashMap<>();

    static {
        for (LogLevel type : LogLevel.values()) {
            enumMap.put(type.getValue(), type);
        }
    }

    LogLevel(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static LogLevel getEnum(int value) {
        return enumMap.get(value);
    }
}
