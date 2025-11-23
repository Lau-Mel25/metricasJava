package com.example.badcalc;

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        HistoryService history = new HistoryService();
        PromptService prompt = new PromptService();

        ConsoleApp app = new ConsoleApp(calc, history, prompt);
        app.run();
    }
}
