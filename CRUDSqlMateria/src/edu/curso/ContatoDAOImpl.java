package edu.curso;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class ContatoDAOImpl implements ContatoDAO{
    private final static String DB_URL = "jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=agenda;namedPipes=true";
    private static final String DB_USER = "anderson";
    private static final String DB_PASS = "123456";

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Connection con;

    public ContatoDAOImpl() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            System.out.println("Driver carregado com sucesso");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Conexão feita com o banco de dados");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void criar(Contato contato) {
        String sql = String.format("INSERT INTO contato (nome, telefone, email, nascimento)" +
                "VALUES (?, ?, ?, ?)");
        try {
            PreparedStatement pst = this.con.prepareStatement( sql );
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getTelefone());
            pst.setString(3, contato.getEmail());
            pst.setDate(4, Date.valueOf(contato.getNascimento()));

            pst.executeUpdate();
            System.out.println("Contato adicionado com sucesso");
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public Contato procurarPorId(long id) {
        String sql = "SELECT * FROM agenda WHERE id =" + id;
        System.out.println(sql);
        Contato c = null;
        try {
            PreparedStatement pst = this.con.prepareStatement( sql );
            ResultSet rs = pst.executeQuery();
            if ( rs.next() ) {
                c = new Contato();
                c.setId( rs.getLong("id") );
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setNascimento( rs.getDate("nascimento").toLocalDate() );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Contato> lerTodos() {
        String sql = "SELECT * FROM agenda";
        System.out.println(sql);
        List<Contato> lista = new ArrayList<>();

        try {
            PreparedStatement pst = this.con.prepareStatement( sql );
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Contato c = new Contato();
                c.setId( rs.getLong("id") );
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setNascimento( rs.getDate("nascimento").toLocalDate() );
                lista.add(c);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void atualizar(long id, Contato contato) {
        String sql = String.format("UPDATE SET nome='%s', telefone='%s', email='%s', " +
        "nascimento='%s' WHERE id=%d",
                contato.getNome(),
                contato.getTelefone(),
                contato.getEmail(),
                contato.getNascimento().format(dtf),
                id);
        System.out.println(sql);
        try {
            PreparedStatement pst = this.con.prepareStatement( sql );
            pst.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(long id) {
        String sql = String.format("DELETE FROM agenda WHERE id=%d", id);
        System.out.println(sql);
        try {
            PreparedStatement pst = this.con.prepareStatement( sql );
            pst.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }
}
