package com.superstudentregion.stuenum;

public enum StateEnum {
    NORMAL("正常", 0),
    ACTIVE("待激活", 1),
    FROZEN("冻结", 2);

    private String name;
    private Integer value;

    private StateEnum(String name, Integer value) {
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
