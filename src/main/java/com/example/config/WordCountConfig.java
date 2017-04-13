package com.example.config;

import com.example.service.WordCounter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("wordCount")
public class WordCountConfig {

    private boolean caseSensitive;
    private Words words;

    @Bean
    public WordCounter wordCounter(WordCountConfig wordCountConfig) {
        return new WordCounter(wordCountConfig);
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    @ConfigurationProperties(prefix = "wordCount.words")
    public static class Words {

        private List<String> skip;

        public List<String> getSkip() {
            return skip;
        }

        public void setSkip(List<String> skip) {
            this.skip = skip;
        }
    }

}
