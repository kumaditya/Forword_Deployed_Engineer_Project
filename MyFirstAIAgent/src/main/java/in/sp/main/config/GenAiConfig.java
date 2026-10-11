package in.sp.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.genai.Client;

@Configuration
public class GenAiConfig {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Bean
    public Client.Builder clientBuilder() {
        return Client.builder().apiKey(apiKey);
    }
}