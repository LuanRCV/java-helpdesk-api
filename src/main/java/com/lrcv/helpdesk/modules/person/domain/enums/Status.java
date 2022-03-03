package com.lrcv.helpdesk.modules.person.domain.enums;

public enum Status {
    OPENED(0, "OPENED"), INPROGRESS(1, "INPROGRESS"), CLOSED(2, "CLOSED");

    private Integer code;
    private String description;

    private Status(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Status toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Status status : Status.values()) {
            if (code.equals(status.getCode())) {
                return status;
            }
        }

        throw new IllegalArgumentException("Invalid status");
    }
}
