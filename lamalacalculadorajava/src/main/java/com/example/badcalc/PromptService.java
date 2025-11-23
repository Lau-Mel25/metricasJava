package com.example.badcalc;

import com.google.gson.Gson;
import java.util.Map;

public class PromptService {

    private final Gson gson = new Gson();

    public String buildStructuredPrompt(String system, String template, String userInput) {
       return gson.toJson(Map.of(
                "system", system,
                "template", template,
                "input", userInput
        ));
    }

    public String sendToLLM(String structuredPrompt) {
        System.out.println("=== STRUCTURED PROMPT ===");
        System.out.println(structuredPrompt);
        System.out.println("=========================");
        return "SIMULATED_LLM_RESPONSE";
    }
}
