package DAO;

import model.Profissional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO {
    private Conexao conexao = new Conexao();
    public int insert(Profissional profissional) {
        conexao.conectar();

        String sql = "INSERT INTO profissional(nome, email, senha, cargo) VALUES (?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getEmail());
            stmt.setString(3, profissional.getSenha());
            stmt.setString(4, profissional.getCargo());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }

    public int update(Profissional profissional) {
        conexao.conectar();

        String sql = "UPDATE profissional SET nome=?, email=?, senha=?, cargo=? WHERE codigo=?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getEmail());
            stmt.setString(3, profissional.getSenha());
            stmt.setString(4, profissional.getCargo());
            stmt.setInt(5, profissional.getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }
    public Profissional select(int codigo) {
        conexao.conectar();
        String Sql = "SELECT codigo, nome, email, senha, cargo FROM profissional WHERE codigo=?";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try {
            stmt.setInt(1, codigo);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                    Profissional profissional = new Profissional();
                    profissional.setCodigo(retorno.getInt("codigo"));
                    profissional.setNome(retorno.getString("nome"));
                    profissional.setEmail(retorno.getString("email"));
                    profissional.setSenha(retorno.getString("senha"));
                    profissional.setCargo(retorno.getString("cargo"));
                return profissional;
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

        String Sql = "DELETE FROM profissional WHERE codigo=?";

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
    public List<Profissional> selectAll(){
        List<Profissional> lista = new ArrayList<>();
        conexao.conectar();
        String Sql = "SELECT codigo, nome, email, senha, cargo FROM profissional ORDER BY nome";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try{
            ResultSet retorno = stmt.executeQuery();

            while(retorno.next()){
                Profissional profissional = new Profissional();
                profissional.setCodigo(retorno.getInt("codigo"));
                profissional.setNome(retorno.getString("nome"));
                profissional.setEmail(retorno.getString("email"));
                profissional.setSenha(retorno.getString("senha"));
                profissional.setCargo(retorno.getString("cargo"));

                lista.add(profissional);
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
