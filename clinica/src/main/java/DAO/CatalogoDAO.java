package DAO;

import model.Catalogo;
import model.Exame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogoDAO {
    private Conexao conexao = new Conexao();
    public int insert(Catalogo catalogo) {
        conexao.conectar();

        String sql = "INSERT INTO catalogo(nome, preparacao, orientacao, valor, exame) VALUES (?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, catalogo.getNome());
            stmt.setString(2, catalogo.getPreparacao());
            stmt.setString(3, catalogo.getOrientacao());
            stmt.setString(4, catalogo.getValor());
            for (Exame exame : catalogo.getExame()) {
                stmt.setInt(5, exame.getCodigo());
            }
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }

    public int update(Catalogo catalogo) {
        conexao.conectar();

        String sql = "UPDATE catalogo SET nome=?, preparacao=?, orientacao=?, valor=?, exame=? WHERE codigo=?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, catalogo.getNome());
            stmt.setString(2, catalogo.getPreparacao());
            stmt.setString(3, catalogo.getOrientacao());
            stmt.setString(4, catalogo.getValor());
            for (Exame exame : catalogo.getExame()) {
                stmt.setInt(5, exame.getCodigo());
            }
            stmt.setInt(6, catalogo.getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }
    public Catalogo select(int codigo) {
        conexao.conectar();
        String Sql = "SELECT codigo, nome, preparacao, orientacao, valor, exame FROM catalogo WHERE codigo=?";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try {
            stmt.setInt(1, codigo);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                Catalogo catalogo = new Catalogo();
                catalogo.setCodigo(retorno.getInt("codigo"));
                catalogo.setNome(retorno.getString("nome"));
                catalogo.setPreparacao(retorno.getString("preparacao"));
                catalogo.setOrientacao(retorno.getString("orientacao"));
                catalogo.setValor(retorno.getString("valor"));

                List<Exame> exames = new ArrayList<>();
                for(Exame exame : catalogo.getExame()){
                    exame.setCodigo(retorno.getInt("exame"));
                    exames.add(exame);
                }
                catalogo.setExame(exames);

                return catalogo;
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

        String Sql = "DELETE FROM catalogo WHERE codigo=?";

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
    public List<Catalogo> selectAll(){
        List<Catalogo> lista = new ArrayList<>();
        conexao.conectar();
        String Sql = "SELECT codigo, nome, preparacao, orientacao, valor, exame FROM catalogo ORDER BY nome";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try{
            ResultSet retorno = stmt.executeQuery();

            while(retorno.next()){
                Catalogo catalogo = new Catalogo();
                catalogo.setCodigo(retorno.getInt("codigo"));
                catalogo.setNome(retorno.getString("nome"));
                catalogo.setPreparacao(retorno.getString("preparacao"));
                catalogo.setOrientacao(retorno.getString("orientacao"));
                catalogo.setValor(retorno.getString("valor"));

                List<Exame> exames = new ArrayList<>();
                for(Exame exame : catalogo.getExame()){
                    exame.setCodigo(retorno.getInt("exame"));
                    exames.add(exame);
                }
                catalogo.setExame(exames);

                lista.add(catalogo);
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
