package dbSample2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Person> getPersonFromKadaidb(String name) {
        List<Person> results = new ArrayList<Person>();

        try {

            this.getConnection();

            pstmt = con.prepareStatement("select * from person where Name = ?");

            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                Person person = new Person();
                person.setId(rs.getInt("Id"));
                person.setName(rs.getString("Name"));
                person.setAge(rs.getInt("Age"));

                results.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            this.close();
        }

        return results;
    }

    public void getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(
                "jdbc:mysql://localhost/kadaidb?useSSL=false&useUnicode=true&characterEncoding=utf8",
                "root",
                "smile0220"
            );
    }

    private void close() {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
