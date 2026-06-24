function fn() {

    var config = {
        env: 'test'
    };

    var DbUtils = Java.type('it.StepDb');
    config.db = new DbUtils({
        username: 'sa',
        password: 'password',
        url: 'jdbc:h2:mem:testdb',
        driverClassName: 'org.h2.Driver'
    });

    return config;
}
