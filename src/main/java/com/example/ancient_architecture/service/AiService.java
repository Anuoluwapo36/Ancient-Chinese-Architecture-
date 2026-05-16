package com.example.ancient_architecture.service;

import com.example.ancient_architecture.model.Architecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    public static final String QWEN_API_KEY = "sk-229d1f81b4bf42f0aafa936026466d79";
    public static final String API_URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

    @Autowired
    private RestTemplate restTemplate;

    
    public String generateHistoricalInsight(Architecture arch) {
        if (arch == null) return "No information available.";

        
   String prompt = """
            Write a complete, detailed historical essay about the ancient Chinese building: %s.
            You must write each of the following elements as its OWN SEPARATE PARAGRAPH — no merging, no joining together:
            - Who built it (1 full paragraph)
            - Why it was built (1 full paragraph)
            - Full historical background (1 full paragraph)
            - Architectural features (1 full paragraph)
            - Cultural and historical significance (1 full paragraph)
            - Its legacy (1 full paragraph)
            
            Write naturally like a professional historian. Do not use bullet points.
            Every paragraph must be distinct, stand alone, and not merge with any other paragraph.
            Do NOT repeat facts — use your own knowledge.
            """.formatted(arch.getEnglishName());

        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + QWEN_API_KEY);

        
        Map<String, Object> input = new HashMap<>();
        input.put("prompt", prompt);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("temperature", 0.8);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "qwen-turbo");
        requestBody.put("input", input);
        requestBody.put("parameters", parameters);

        try {
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, request, Map.class);

            
            Map<String, Object> output = (Map<String, Object>) response.getBody().get("output");
            return output.get("text").toString().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI is generating complete historical information...";
        }
    } 
    
}