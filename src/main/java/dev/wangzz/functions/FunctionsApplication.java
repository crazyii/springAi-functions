package dev.wangzz.functions;

import dev.wangzz.functions.config.WeatherProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@EnableConfigurationProperties(WeatherProperties.class)
@ComponentScan("dev.wangzz.functions")
@SpringBootApplication
public class FunctionsApplication {
    @Autowired
    private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(FunctionsApplication.class, args);
	}
    
    @PostConstruct
    public void checkEnv() {
        /**配置了系统全局的环境变量， 命令窗口都可以，
         * 但是ide里打印出来还是空，最后在IDEA启动项里加了个环境变量**/
        System.out.println("GAODE_WEATHER_API_KEY: " + System.getenv("GAODE_WEATHER_API_KEY"));
        System.out.println("GAODE_WEATHER_API_KEY: " + environment.getProperty("GAODE_WEATHER_API_KEY"));
    }
}
