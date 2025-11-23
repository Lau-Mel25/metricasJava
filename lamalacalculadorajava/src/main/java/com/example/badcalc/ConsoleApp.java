package com.example.badcalc;

import java.util.Scanner;

public class ConsoleApp {

    private final Calculator calc;
    private final HistoryService history;
    private final PromptService prompt;

    public ConsoleApp(Calculator c, HistoryService h, PromptService p) {
        this.calc = c;
        this.history = h;
        this.prompt = p;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("GOOD CALC (Refactor Edition)");
            System.out.println("1:+ 2:- 3:* 4:/ 5:^ 6:% 7:LLM 8:hist 0:exit");
            System.out.print("opt: ");

            String opt = sc.nextLine().trim();
            if ("0".equals(opt)) break;

            switch (opt) {
                case "7" -> handleLLM(sc);
                case "8" -> showHistory();
                default -> handleMath(sc, opt);
            }
        }

        history.saveLeftovers();
    }

    private void handleLLM(Scanner sc) {
        System.out.println("User template:");
        String tpl = sc.nextLine();

        System.out.println("User input:");
        String input = sc.nextLine();

        String structured = prompt.buildStructuredPrompt(
                "System: You are an assistant.",
                tpl,
                input
        );

        String resp = prompt.sendToLLM(structured);
        System.out.println("LLM => " + resp);
    }

    private void showHistory() {
        for (String h : history.all()) {
            System.out.println(h);
        }
    }

    private void handleMath(Scanner sc, String opt) {
        try {
            System.out.print("a: ");
            double a = calc.parse(sc.nextLine());

            System.out.print("b: ");
            double b = calc.parse(sc.nextLine());

            String op = switch (opt) {
                case "1" -> "+";
                case "2" -> "-";
                case "3" -> "*";
                case "4" -> "/"; 
                case "5" -> "^";
                case "6" -> "%";
                default -> throw new IllegalArgumentException("Invalid option");
            };

            double result = calc.compute(a, b, op);
            String entry = a + " " +  op + " " + b + " = " + result;

            history.add(entry);
            System.out.println("= " + result);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
