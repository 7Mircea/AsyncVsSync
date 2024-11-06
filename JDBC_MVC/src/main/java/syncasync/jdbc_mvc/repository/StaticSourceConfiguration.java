package syncasync.jdbc_mvc.repository;

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
        source.add("Marcel");
        source.add("Ion");
        source.add("Vlad");
        source.add("Cristian");
        source.add("Ionescu");
        source.add("Popescu");
        source.add("Gianina");
        return source;
    }
}
