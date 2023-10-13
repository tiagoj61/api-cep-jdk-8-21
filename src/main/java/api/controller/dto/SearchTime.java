package api.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface SearchTime {
    void searchMoment(String time);

    default void actualMoment(LocalDateTime now) {
        this.searchMoment(formatDate(now));
    }

    static String formatDate(LocalDateTime now) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return now.format(formatter);
    }
}
