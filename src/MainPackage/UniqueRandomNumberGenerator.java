package MainPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import  java.sql.*;
import java.util.Random;
import DriverPackage.*;

public class UniqueRandomNumberGenerator {

    public static int generateUniqueRandomNumber(int numberOfDigits) {
        if (numberOfDigits < 1 || numberOfDigits > 10) {
            throw new IllegalArgumentException("Number of digits must be between 1 and 10");
        }

        Random random = new Random();
        int uniqueRandomNumber;

        try {
            GlobalVariable gv = new GlobalVariable();
            gv.conn = gv.c1.connect();
            gv.Stm = gv.conn.prepareStatement("INSERT INTO randomnumber (numbers) VALUES (?)");
            while (true) {
                uniqueRandomNumber = random.nextInt((int) Math.pow(10, numberOfDigits));
                if (!isNumberUsed(gv.conn, uniqueRandomNumber)) {
                    gv.Stm.setInt(1, uniqueRandomNumber);
                    gv.Stm.executeUpdate();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error generating unique random number");
        }

        return uniqueRandomNumber;
    }
    public static boolean isNumberUsed(Connection connection, int number) throws SQLException {
        try (PreparedStatement checkNumber = connection.prepareStatement("SELECT numbers FROM randomnumber WHERE numbers = ?")) {
            checkNumber.setInt(1, number);
            ResultSet resultSet = checkNumber.executeQuery();
            return resultSet.next();
        }
    }
}
