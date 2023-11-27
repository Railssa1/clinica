package DAO;

import model.Catalogo;
import model.Exame;
import model.Lembrete;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LembreteDAO {
    private Conexao conexao = new Conexao();
    public int insert(Lembrete lembrete) {
        conexao.conectar();

        String sql = "INSERT INTO lembrete(instrucoes, documentos) VALUES (?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, lembrete.getIntrucoes());
            stmt.setString(2, lembrete.getDocumentos());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }

    public int update(Lembrete lembrete) {
        conexao.conectar();

        String sql = "UPDATE lembrete SET instrucoes=?, documentos=? WHERE codigo=?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, lembrete.getIntrucoes());
            stmt.setString(2, lembrete.getDocumentos());
            stmt.setInt(3, lembrete.getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }
    public Lembrete select(int codigo) {
        conexao.conectar();
        String Sql = "SELECT codigo, instrucoes, documentos FROM lembrete WHERE codigo=?";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try {
            stmt.setInt(1, codigo);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                Lembrete lembrete = new Lembrete();
                lembrete.setCodigo(retorno.getInt("codigo"));
                lembrete.setIntrucoes(retorno.getString("instrucoes"));
                lembrete.setDocumentos(retorno.getString("documentos"));
                return new Lembrete();
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

        String Sql = "DELETE FROM lembrete WHERE codigo=?";

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
    public List<Lembrete> selectAll(){
        List<Lembrete> lista = new ArrayList<>();
        conexao.conectar();
        String Sql = "SELECT codigo, instrucoes, documentos FROM lembrete";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try{
            ResultSet retorno = stmt.executeQuery();

            while(retorno.next()){
                Lembrete lembrete = new Lembrete();
                lembrete.setCodigo(retorno.getInt("codigo"));
                lembrete.setIntrucoes(retorno.getString("instrucoes"));
                lembrete.setDocumentos(retorno.getString("documentos"));

                lista.add(lembrete);
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
