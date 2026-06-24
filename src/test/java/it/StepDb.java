package it;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Arrays;
import java.util.Map;

@SuppressWarnings("unused")
public class StepDb {


    private final JdbcTemplate jdbc;


    public StepDb(Map<String, Object> config) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName((String) config.get("driverClassName"));
        dataSource.setUrl((String) config.get("url"));
        dataSource.setUsername((String) config.get("username"));
        dataSource.setPassword((String) config.get("password"));
        jdbc = new JdbcTemplate(dataSource);
    }

    public void update(String sql) {
        String[] split = sql.split(";");
        Arrays.stream(split).forEach(jdbc::update);
    }
}