package dev.wangzz.functions.config;

import dev.wangzz.functions.function.WeatherFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

/**
 * @author wangzz
 * @title: WeatherFunction
 * @description: TODO
 * @date 2024/12/23 15:14
 */
@Configuration
public class WeatherFunctionConfig {
    private final WeatherProperties properties;
    
    public WeatherFunctionConfig(WeatherProperties properties) {
        this.properties = properties;
    }
    
    @Bean
    @Description("获取天气信息")
    public Function<WeatherFunction.Request, WeatherFunction.Response> weather() {
        return new WeatherFunction(this.properties);
    }
}
