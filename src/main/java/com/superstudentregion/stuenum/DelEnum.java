package com.superstudentregion.stuenum;

public enum DelEnum {
    DEL("删除", 1),
    NOT_DEL("未删除", 0);

    private String name;
    private Integer value;

    private DelEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
