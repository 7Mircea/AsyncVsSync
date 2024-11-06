package syncasync.r2dbc_webflux.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class StaticSourceConfiguration {
    @Bean
    public List<String> getSource() {
        List<String> source = new LinkedList<>();
        source.add("Ana");
        source.add("Ana");
        source.add("Ana");
        source.add("Ana");
        source.add("Ana");
        source.add("Ana");
        source.add("Ana");
        source.add("Ana");
        return source;
    }
}
