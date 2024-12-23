package dev.wangzz.functions.function;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import dev.wangzz.functions.config.WeatherProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.function.Function;

/**
 * @author wangzz
 * @title: WeatherService
 * @description: TODO
 * @date 2024/12/23 15:01
 */
@Component
public class WeatherFunction implements Function<WeatherFunction.Request, WeatherFunction.Response> {
    
    
    private WeatherProperties weatherProperties;
    private final RestClient restClient;
    
    public WeatherFunction(WeatherProperties properties) {
        this.weatherProperties = properties;
        System.out.println("API Key: " + properties.getApiKey());
        
        this.restClient = RestClient.create(properties.getApiUrl());
    }
    
    @Override
    public Response apply(Request request) {
        // 使用 restClient 发送请求，获取天气信息
        return restClient.get()
                .uri("/weatherInfo?key={key}&city={city}", this.weatherProperties.getApiKey(), request.cityCode())
                .retrieve()
                .body(Response.class);
    }
    
    
    public record Request(
            @JsonPropertyDescription("中国城市代码。例：北京朝阳110105") String cityCode) {
    }
    public record Response(
            @JsonPropertyDescription("总结果数量") String count,
            @JsonPropertyDescription("状态码，1是正常") String status,
            @JsonPropertyDescription("实时天气信息结果对象") List<Live> lives) {
    }
    
    public record Live(String city, String temperature, String humidity, String reporttime){}
}
