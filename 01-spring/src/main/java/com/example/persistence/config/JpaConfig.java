package com.example.persistence.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.persistence.repository")
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.H2);
        vendorAdapter.setGenerateDdl(false);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("com.example.persistence.entity", "org.springframework.data.jpa.convert.threeten");
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.physical_naming_strategy", new MyPhysicalNamingStrategy());
        properties.put("hibernate.format_sql", true);
        factoryBean.setJpaPropertyMap(properties);
        return factoryBean;
    }

    private static class MyPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {
        @Override
        public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
            StringBuilder builder = new StringBuilder(name.getText());
            for (int i = 1; i < builder.length() - 1; i++) {
                if (Character.isLowerCase(builder.charAt(i - 1))
                        && Character.isUpperCase(builder.charAt(i))) {
                    builder.insert(i, '_');
                    i++;
                }
            }
            return Identifier.toIdentifier(builder.toString().toLowerCase());
        }
    }
}
