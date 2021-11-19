package com.mtcg;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Optional;
public class Main {

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/swe1user", "swe1user", "swe1pw");
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO cards
                    (cardid, cardname)
                    VALUES (?,?);
                """)
        ){
            statement.setString(1, "randomIDKEKW");
            statement.setString(2, "asldkaskldjaklsd");
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // write your code here
        //return Optional.empty();
    }
}
