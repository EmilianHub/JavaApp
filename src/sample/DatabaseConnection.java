package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public boolean Offline;
    public Connection getConnection()
    {
        String databaseName = "accounts";
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (Exception e)
        {
            Offline = true;
            e.getCause();
        }
        return databaseLink;
    }
}
