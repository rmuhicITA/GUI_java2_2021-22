package com.app_with_db.connection;

public enum ConnectionProperties {

    URL("jdbc:mysql://localhost:3306/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false"),
    USERNAME("root"),
    PASSWORD("password");

    private String value;

    private ConnectionProperties(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
