package ru.grigorenkomo.loggingstarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import ru.grigorenkomo.loggingstarter.aspect.LogExecutionAspect;
import ru.grigorenkomo.loggingstarter.webfilter.WebLoggingFilter;
import ru.grigorenkomo.loggingstarter.webfilter.WebLoggingRequestBodyAdvice;

@AutoConfiguration
@ConditionalOnProperty(prefix = "logging", value = "enabled", havingValue = "true", matchIfMissing = true)
public class LoggingStarterAutoConfiguration {

    @ConditionalOnProperty(prefix = "logging", value = "log-exec-time", havingValue = "true", matchIfMissing = true)
    @Bean
    public LogExecutionAspect logExecutionAspect() {
        return new LogExecutionAspect();
    }
    @ConditionalOnProperty(prefix = "logging.web-logging", value = "enabled", havingValue = "true", matchIfMissing = true)
    @Bean
    public WebLoggingFilter webLoggingFilter() {
        return new WebLoggingFilter();
    }
    @ConditionalOnProperty(prefix = "logging.web-logging", value = {"enabled","log-body"}, havingValue = "true")
    @Bean
    public WebLoggingRequestBodyAdvice webLoggingRequestBodyAdvice() {
        return new WebLoggingRequestBodyAdvice();
    }
}
