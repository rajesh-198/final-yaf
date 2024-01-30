package com.asset.foundation.utility.abstractclass;

public enum OperationType {

    ADD("add"), EDIT("edit"), SHOW("show");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
