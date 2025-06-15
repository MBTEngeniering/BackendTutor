package de.fairstudy.backend.Utils;

import de.fairstudy.backend.model.enums.Subject;

public class SubjectUtils {

    public static Subject fromMultilingualString(String input) {
        if (input == null) {
            return null;
        }
        String normalized = input.trim().toLowerCase();

        switch (normalized) {
            case "italian":
            case "italienisch":
            case "italien":
                return Subject.ITALIAN;

            case "physics":
            case "physik":
            case "physique":
                return Subject.PHYSICS;

            case "math":
            case "mathematik":
            case "mathématiques":
                return Subject.MATH;

            case "math (os)":
            case "mathematik (os)":
            case "mathématiques (os)":
                return Subject.MATH_OS;

            case "math (us)":
            case "mathematik (us)":
            case "mathématiques (us)":
                return Subject.MATH_US;

            case "french":
            case "französisch":
            case "français":
                return Subject.FRENCH;

            case "english":
            case "englisch":
            case "anglais":
                return Subject.ENGLISH;

            case "chemistry":
            case "chemie":
            case "chimie":
                return Subject.CHEMISTRY;

            case "religion":
                return Subject.RELIGION;

            case "biology":
            case "biologie":
                return Subject.BIOLOGY;

            case "history":
            case "geschichte":
            case "histoire":
                return Subject.HISTORY;

            case "geography":
            case "geografie":
            case "géographie":
                return Subject.GEOGRAPHY;

            case "computer science":
            case "informatik":
            case "informatique":
                return Subject.COMPUTER_SCIENCE;

            case "economics":
            case "wirtschaft":
            case "économie":
                return Subject.ECONOMICS;

            case "politics":
            case "politik":
            case "politique":
                return Subject.POLITICS;

            case "philosophy":
            case "philosophie":
                return Subject.PHILOSOPHY;

            case "art":
                return Subject.ART;

            case "music":
            case "musik":
                return Subject.MUSIC;

            case "sports":
            case "sport":
                return Subject.SPORTS;

            case "latin":
                return Subject.LATIN;

            case "german":
            case "deutsch":
            case "allemand":
                return Subject.GERMAN;

            case "spanish":
            case "spanisch":
            case "espagnol":
                return Subject.SPANISH;

            default:
                return null;
        }
    }
}
