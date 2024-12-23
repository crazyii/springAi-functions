package dev.wangzz.functions.controller;

import dev.wangzz.functions.function.WeatherFunction;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Map;

/**
 * @author wangzz
 * @title: WeatherController
 * @description: TODO
 * @date 2024/12/23 14:26
 */
@RestController
public class WeatherController {
    
    @Value("classpath:/prompt/weather.st")
    private Resource promptResource;
    private final ChatClient chatClient;
    public WeatherController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    
    @RequestMapping
    public String weather(@RequestParam String city) {
        PromptTemplate promptTemplate = new PromptTemplate(promptResource, Map.of("city", city));
        return chatClient
                .prompt(promptTemplate.create())
                .functions("weather")
                .call()
                .content();
    }
}
