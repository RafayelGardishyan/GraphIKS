package graphiks.database;

import graphiks.Date;
import graphiks.Project;
import graphiks.ProjectData;

import java.sql.*;
import java.util.ArrayList;

public class db {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // Connect to database
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:projects.db");
        statmt = conn.createStatement();
        System.out.println("Database Connected");
    }


    // Add test data to database
    public static void WriteDB(String name, Date lastopened, String json_formulas) throws SQLException
    {
        String statement = "INSERT INTO 'projects' ('name', 'lastopened', 'json_formulas') VALUES (?, ?, ?); ";

        try (PreparedStatement pstmt = conn.prepareStatement(statement)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, lastopened.getDateInteger());
            pstmt.setString(3, json_formulas);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Test data is written");
    }

    // Print data from database
    public static ArrayList<ProjectData> ReadDB() throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM projects");
        ArrayList<ProjectData> projects = new ArrayList<>();

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("name");
            int  lastopened = resSet.getInt("lastopened");
            String  json_formulas = resSet.getString("json_formulas");
            projects.add(new ProjectData(id, name, lastopened, json_formulas));
        }

        System.out.println("Database data is retrieved");
        return projects;
    }

    // Close database
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Connection is closed");
    }


}
