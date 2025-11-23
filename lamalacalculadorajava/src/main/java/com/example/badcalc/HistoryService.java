package com.example.badcalc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {

    private final List<String> history = new ArrayList<>();
    private final String filePath = "history.txt";

    public void add(String entry) {
        history.add(entry);
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(entry + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("No se pudo escribir historial: " + e.getMessage());
        }
    }

    public List<String> all() {
        return List.copyOf(history);
    }

    public void saveLeftovers() {
        try (FileWriter fw = new FileWriter("leftover.tmp")) {
            for (String h : history) fw.write(h + ",");
        } catch (IOException e) {
            System.err.println("Error guardando leftovers: " + e.getMessage());
        }
    }
}
