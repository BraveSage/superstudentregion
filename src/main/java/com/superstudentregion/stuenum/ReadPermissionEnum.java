package com.superstudentregion.stuenum;

public enum ReadPermissionEnum {
    ONLY_ONSELF("仅自己", 0),
    PUBLIC("公开", 1);

    private String name;
    private Integer value;

    private ReadPermissionEnum(String name, Integer value) {
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
