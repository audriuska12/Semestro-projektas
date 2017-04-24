package com.tvarkarastis.config;

import com.tvarkarastis.bean.ConnectionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by audri on 2017-04-10.
 */

@Configuration
public class RootConfig {

    @Bean
    public boolean connectionProvider() {
        ConnectionProvider.Initialize();
        return ConnectionProvider.isInitialized();
    }

}
