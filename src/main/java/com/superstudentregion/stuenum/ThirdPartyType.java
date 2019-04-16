package com.superstudentregion.stuenum;

public enum ThirdPartyType {
    EMAIL("用户", 0),
    QQ_TYPE("qq", 1),
    WECHAT_TYPE("wechat", 2);

    private String name;
    private Integer value;

    private ThirdPartyType(String name, Integer value) {
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
