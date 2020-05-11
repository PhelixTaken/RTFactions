package me.phelix.rtfactions.utils;

public enum Role {

    LEADER(5, "ROLE_LEADER"),
    COLEADER(4, "ROLE_COLEADER"),
    MODERATOR(3, "ROLE_MODERATOR"),
    MEMBER(2, "ROLE_MEMBER"),
    RECRUIT(1, "ROLE_RECRUIT"),
    NONE(0, "ROLE_NONE");

    public String name;
    public int value;

    Role(int value, String role) {
        this.value = value;
        this.name = role;
    }

    public final Role getByValue(int value) {
        switch (value) {
            case 0:
                return NONE;
            case 1:
                return RECRUIT;
            case 2:
                return MEMBER;
            case 3:
                return MODERATOR;
            case 4:
                return COLEADER;
            case 5:
                return LEADER;
        }
        return null;
    }

    public final int getValue() {
        switch (this) {
            case NONE:
                return 0;
            case RECRUIT:
                return 1;
            case MEMBER:
                return 2;
            case MODERATOR:
                return 3;
            case COLEADER:
                return 4;
            case LEADER:
                return 5;
        }
        return 0;
    }

    public final Role getRole(String check) {
        switch (check.toLowerCase()) {
            case "leader":
            case "admin":
                return LEADER;
            case "coleader":
                return COLEADER;
            case "mod":
            case "moderator":
                return MODERATOR;
            case "normal":
            case "member":
                return MEMBER;
            case "recruit":
            case "rec":
                return RECRUIT;
            case "none":
                return NONE;
        }
        return null;
    }

    public final boolean isAtleast(Role role) {
        return this.value >= role.value;
    }

    public final boolean isAtMost(Role role) {
        return this.value <= role.value;
    }

    public final String getPrefix() {
        switch (this) {
            case LEADER:
                return "Leader";
            case COLEADER:
                return "CO-Leader";
            case MODERATOR:
                return "Moderator";
            case MEMBER:
                return "Member";
            case RECRUIT:
                return "Recruit";
            case NONE:
                return "None";
        }
        return "";
    }

}
