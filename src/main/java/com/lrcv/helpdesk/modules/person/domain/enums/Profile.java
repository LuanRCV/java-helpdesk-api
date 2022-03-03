package com.lrcv.helpdesk.modules.person.domain.enums;

/**
 * Profiles
 */
public enum Profile {
    ADMIN(0, "ROLE_ADMIN"), CUSTOMER(1, "CUSTOMER"), TECHNICAL(2, "TECHNICAL");

    private Integer code;
    private String description;

    private Profile(Integer code, String description) {
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

    public static Profile toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Profile profile : Profile.values()) {
            if (code.equals(profile.getCode())) {
                return profile;
            }
        }

        throw new IllegalArgumentException("Invalid profile");
    }
}