package com.asset.foundation.utility.abstractclass;

public enum ResponseMessage {

    SUCCESS(100, "Success"),
    ERROR(101, "Error"),
    ADDED_SUCCESS(102, "Added Sucessfully."),
    EDIT_SUCCESS(103, "Edited Sucessfully."),
    DELETE_SUCCESS(104, "Deleted Sucessfully."),
    ACTIVATED_SUCCESS(104, "Re-Activated Sucessfully."),
    DELETE_FAIL(104, "Delete Fail."),


    PLAYER_NOT_ADDED(201, "Player not added!"),
    PLAYER_NOT_EDITED(202, "Player not edited!");


    private final int id;
    private final String value;

    ResponseMessage(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
