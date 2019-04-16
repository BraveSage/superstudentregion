package com.superstudentregion.stuenum;

public enum SexEnum {
    NONE("未设置", 0),
    MALE("男", 1),
    FEMALE("女", 2);

    private String name;
    private Integer value;

    private SexEnum(String name, Integer value) {
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
