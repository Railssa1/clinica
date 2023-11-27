package DAO;

import model.Catalogo;
import model.Clinica;
import model.Profissional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicaDAO {
    private Conexao conexao = new Conexao();
    public int insert(Clinica clinica) {
        conexao.conectar();

        String sql = "INSERT INTO clinica(nome, cnpj, senha, profissional, catalogo) VALUES (?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, clinica.getNome());
            stmt.setString(2, clinica.getCnpj());
            stmt.setString(3, clinica.getSenha());
            stmt.setInt(4, clinica.getProfissional().getCodigo());
            stmt.setInt(5, clinica.getCatalogo().getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }

    public int update(Clinica clinica) {
        conexao.conectar();

        String sql = "UPDATE clinica SET nome=?, cnpj=?, senha=?, profissional=?, catalogo=? WHERE codigo=?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, clinica.getNome());
            stmt.setString(2, clinica.getCnpj());
            stmt.setString(3, clinica.getSenha());
            stmt.setInt(4, clinica.getProfissional().getCodigo());
            stmt.setInt(5, clinica.getCatalogo().getCodigo());
            stmt.setInt(6, clinica.getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }
    public Clinica select(int codigo) {
        conexao.conectar();
        String Sql = "SELECT codigo, nome, cnpj, senha, profissional, catalogo FROM clinica WHERE codigo=?";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try {
            stmt.setInt(1, codigo);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                Clinica clinica = new Clinica();
                clinica.setCodigo(retorno.getInt("codigo"));
                clinica.setNome(retorno.getString("nome"));
                clinica.setCnpj(retorno.getString("cnpj"));
                clinica.setSenha(retorno.getString("senha"));

                Profissional profissional = new Profissional();
                profissional.setCodigo(retorno.getInt("profissional"));

                Catalogo catalogo = new Catalogo();
                catalogo.setCodigo(retorno.getInt("catalogo"));

                clinica.setProfissional(profissional);
                clinica.setCatalogo(catalogo);

                return clinica;
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

        String Sql = "DELETE FROM clinica WHERE codigo=?";

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
    public List<Clinica> selectAll(){
        List<Clinica> lista = new ArrayList<>();
        conexao.conectar();
        String Sql = "SELECT codigo, nome, cnpj, senha, profissional, catalogo FROM clinica ORDER BY nome";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try{
            ResultSet retorno = stmt.executeQuery();

            while(retorno.next()){
                Clinica clinica = new Clinica();
                clinica.setCodigo(retorno.getInt("codigo"));
                clinica.setNome(retorno.getString("nome"));
                clinica.setCnpj(retorno.getString("cnpj"));
                clinica.setSenha(retorno.getString("senha"));

                Profissional profissional = new Profissional();
                profissional.setCodigo(retorno.getInt("profissional"));

                Catalogo catalogo = new Catalogo();
                catalogo.setCodigo(retorno.getInt("catalogo"));

                clinica.setProfissional(profissional);
                clinica.setCatalogo(catalogo);

                lista.add(clinica);
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
