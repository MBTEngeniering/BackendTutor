package de.fairstudy.backend.model.enums;

import java.util.Arrays;

public enum Role {
    ROLE_STUDENT(1),
    ROLE_TUTOR(2),
    ROLE_ENTERPRISE(3),
    ROLE_FINANCE(4),
    ROLE_ADMIN(5),
    ROLE_SUPPORT(6);

    public final int label;

    Role(int label) {
        this.label = label;
    }

    public static boolean contains(int s) {
        return Arrays.stream(values()).anyMatch(choice -> choice.label == s);
    }

    public static Role valueOfLabel(int label) {
        for (Role e : values()) {
            if (e.label == label) {
                return e;
            }
        }
        return null;
    }
}