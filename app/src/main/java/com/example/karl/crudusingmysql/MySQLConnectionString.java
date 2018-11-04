package com.example.karl.crudusingmysql;

public class MySQLConnectionString {
    public static String driver = "com.mysql.jdbc.Driver";

    private static String ip = "192.168.43.39";
    private static String port = "3306";
    private static String databaseName = "sampleandroiddatabase";
    private static String username = "test";
    private static String password = "123123";

    public static String DatabaseConnection = String.format("jdbc:mysql://%1$s:%2$s/%3$s?user=%4$s&password=%5$s", ip, port, databaseName, username, password);
}
