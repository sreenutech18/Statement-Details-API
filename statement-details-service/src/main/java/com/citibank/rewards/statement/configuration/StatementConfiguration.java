package com.citibank.rewards.statement.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc // load all required spring mvc annotations
@ComponentScan(basePackages = "com.citibank.rewards.statement.controller")
public class StatementConfiguration {

}
