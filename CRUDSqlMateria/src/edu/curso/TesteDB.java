package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteDB {

    private final static String DB_URL = "jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=agenda;namedPipes=true";
    private final static String DB_USER = "anderson";
    private final static String DB_PASS = "123456";

    public static void main(String[] args) {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            System.out.println("Driver carreado com sucesso!");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Conexão feita com o banco de dados");

            String sql = "INSERT INTO contato (nome, telefone, email, nascimento)" +
                    "VALUES ('Ander', '11', 'ander@gmail.com', '2004-07-19')";

            PreparedStatement pst = con.prepareStatement( sql );
            pst.executeUpdate();
            System.out.println( sql );

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*
    private Connection c;
    public Connection getConnection() throws Exception{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=agenda;namedPipes=true",
                "anderson","123456");
        System.out.println("Conectado com sucesso!");
        return c;
    }
    */
}
