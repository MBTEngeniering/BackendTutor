package de.fairstudy.backend.model;

import de.fairstudy.backend.model.enums.Subject;

public class SubjectTranslator {

    public static String translateFrench(Subject subject) {
        return switch (subject) {
            case MATH -> "Mathématiques";
            case PHYSICS -> "Physique";
            case MATH_OS -> "Mathématiques lycée";
            case MATH_US -> "Mathématiques collège";
            case FRENCH -> "Français";
            case ENGLISH -> "Anglais";
            case CHEMISTRY -> "Chimie";
            case RELIGION -> "Religion";
            case BIOLOGY -> "Biologie";
            case HISTORY -> "Histoire";
            case GEOGRAPHY -> "Géographie";
            case COMPUTER_SCIENCE -> "Informatique";
            case ECONOMICS -> "Économie";
            case POLITICS -> "Politique";
            case PHILOSOPHY -> "Philosophie";
            case ART -> "Arts plastiques";
            case MUSIC -> "Musique";
            case SPORTS -> "Sport";
            case LATIN -> "Latin";
            case GERMAN -> "Allemand";
            case SPANISH -> "Espagnol";
            case ITALIAN -> "Italien";
        };

    }
    public static String translateGerman(Subject subject) {
        return switch (subject) {
            case MATH -> "Mathematik";
            case PHYSICS -> "Physik";
            case MATH_OS -> "Mathematik Oberstufe";
            case MATH_US -> "Mathematik Unterstufe";
            case FRENCH -> "Französisch";
            case ENGLISH -> "Englisch";
            case CHEMISTRY -> "Chemie";
            case RELIGION -> "Religion";
            case BIOLOGY -> "Biologie";
            case HISTORY -> "Geschichte";
            case GEOGRAPHY -> "Geographie";
            case COMPUTER_SCIENCE -> "Informatik";
            case ECONOMICS -> "Wirtschaft";
            case POLITICS -> "Politik";
            case PHILOSOPHY -> "Philosophie";
            case ART -> "Kunst";
            case MUSIC -> "Musik";
            case SPORTS -> "Sport";
            case LATIN -> "Latein";
            case GERMAN -> "Deutsch";
            case SPANISH -> "Spanisch";
            case ITALIAN -> "Italienisch";
        };
    }
}
