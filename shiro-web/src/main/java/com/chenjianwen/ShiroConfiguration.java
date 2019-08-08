package com.chenjianwen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {

    @Bean
    public CredentialMatcher getCredentialMatcher(){
        return new CredentialMatcher();
    }
}
