package org.apereo.cas.config;

import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.configuration.CasConfigurationPropertiesEnvironmentManager;
import org.apereo.cas.support.events.listener.CasCloudBusConfigurationEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is {@link CasCloudBusEventsConfigEnvironmentConfiguration}.
 *
 * @author Misagh Moayyed
 * @since 5.1.0
 */
@Configuration("casCloudBusEventsConfigEnvironmentConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
@Slf4j
public class CasCloudBusEventsConfigEnvironmentConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @ConditionalOnMissingBean(name = "casCloudBusConfigurationEventListener")
    @Bean
    @Autowired
    public CasCloudBusConfigurationEventListener casCloudBusConfigurationEventListener(
            @Qualifier("configurationPropertiesEnvironmentManager") final CasConfigurationPropertiesEnvironmentManager manager) {
        return new CasCloudBusConfigurationEventListener(manager, this.applicationContext);
    }

}
