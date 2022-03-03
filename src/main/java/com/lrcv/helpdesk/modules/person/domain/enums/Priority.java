package com.lrcv.helpdesk.modules.person.domain.enums;

public enum Priority {
    LOW(0, "LOW"), MEDIUM(1, "MEDIUM"), HIGH(2, "HIGH");

    private Integer code;
    private String description;

    private Priority(Integer code, String description) {
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

    public static Priority toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Priority priority : Priority.values()) {
            if (code.equals(priority.getCode())) {
                return priority;
            }
        }

        throw new IllegalArgumentException("Invalid priority");
    }
}