package de.fairstudy.backend.Utils;

import java.util.UUID;

public class UUIDGenerator {

    public UUIDGenerator() {

    }

    // Methode zur Generierung einer eindeutigen UUID
    public UUID generateUUID() {
        return UUID.randomUUID();
    }

    // Methode zur Umwandlung einer UUID in einen String
    public String convertUUIDToString(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID darf nicht null sein.");
        }
        return uuid.toString();
    }

    // Methode zur Umwandlung eines Strings in eine UUID
    public UUID convertStringToUUID(String uuidString) {
        if (uuidString == null || uuidString.isEmpty()) {
            throw new IllegalArgumentException("UUID-String darf nicht null oder leer sein.");
        }
        return UUID.fromString(uuidString);
    }

}
