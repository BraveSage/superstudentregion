package com.superstudentregion.stuenum;

public enum RoleEnum {
    USER("用户", 1),
    ADMIN("管理员", 2);

    private String name;
    private Integer value;

    private RoleEnum(String name, Integer value) {
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
