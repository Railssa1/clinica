package DAO;

import model.Catalogo;
import model.Exame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExameDAO {
    private Conexao conexao = new Conexao();
    public int insert(Exame exame) {
        conexao.conectar();

        String sql = "INSERT INTO exame(dataExame, statusExame, resultado) VALUES (?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setDate(1, exame.getDataExame());
            stmt.setString(2, exame.getStatus());
            stmt.setString(3, exame.getResultado());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }

    public int update(Exame exame) {
        conexao.conectar();

        String sql = "UPDATE exame SET dataExame=?, statusExame=?, resultado=? WHERE codigo=?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setDate(1, exame.getDataExame());
            stmt.setString(2, exame.getStatus());
            stmt.setString(3, exame.getResultado());
            stmt.setInt(4, exame.getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }
    public Exame select(int codigo) {
        conexao.conectar();
        String Sql = "SELECT codigo, dataExame, statusExame, resultado FROM exame WHERE codigo=?";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try {
            stmt.setInt(1, codigo);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                Exame exame = new Exame();
                exame.setCodigo(retorno.getInt("codigo"));
                exame.setDataExame(retorno.getDate("dataExame"));
                exame.setStatus(retorno.getString("statusExame"));
                exame.setResultado(retorno.getString("resultado"));
                return exame;
            } else {
                return null;
            }
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }

    public int delete(int codigo) {
        conexao.conectar();

        String Sql = "DELETE FROM exame WHERE codigo=?";

        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try {
            stmt.setInt(1, codigo);
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }
    public List<Exame> selectAll(){
        List<Exame> lista = new ArrayList<>();
        conexao.conectar();
        String Sql = "SELECT codigo, dataExame, statusExame, resultado FROM exame ORDER BY dataExame";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try{
            ResultSet retorno = stmt.executeQuery();

            while(retorno.next()){
                Exame exame = new Exame();
                exame.setCodigo(retorno.getInt("codigo"));
                exame.setDataExame(retorno.getDate("dataExame"));
                exame.setStatus(retorno.getString("statusExame"));
                exame.setResultado(retorno.getString("resultado"));

                lista.add(exame);
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
            return null;
        } finally {
            conexao.desconectar();
            return lista;
        }
    }
}
