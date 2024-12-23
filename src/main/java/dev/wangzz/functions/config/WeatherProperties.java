package dev.wangzz.functions.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangzz
 * @title: WeatherProperties
 * @description: TODO
 * @date 2024/12/23 15:24
 */
@Data
@ConfigurationProperties(prefix = "custom.weather", ignoreUnknownFields = false)
public class WeatherProperties {
    private String apiKey;
    private String apiUrl;
    
    public String getApiKey() {
        return apiKey;
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String getApiUrl() {
        return apiUrl;
    }
    
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
