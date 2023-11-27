package DAO;

import model.Paciente;
import model.Profissional;
import model.Registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    private Conexao conexao = new Conexao();
    public int insert(Paciente paciente) {
        conexao.conectar();

        String sql = "INSERT INTO paciente(nome, email, senha, documento, registro) VALUES (?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEmail());
            stmt.setString(3, paciente.getSenha());
            stmt.setString(4, paciente.getDocumento());
            stmt.setInt(5, paciente.getRegistro().getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }

    public int update(Paciente paciente) {
        conexao.conectar();

        String sql = "UPDATE paciente SET nome=?, email=?, senha=?, documento=?, registro=? WHERE codigo=?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEmail());
            stmt.setString(3, paciente.getSenha());
            stmt.setString(4, paciente.getDocumento());
            stmt.setInt(5, paciente.getRegistro().getCodigo());
            stmt.setInt(6, paciente.getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }
    public Paciente select(int codigo) {
        conexao.conectar();
        String Sql = "SELECT codigo, nome, email, senha, documento, registro FROM paciente WHERE codigo=?";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try {
            stmt.setInt(1, codigo);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                Paciente paciente = new Paciente();
                paciente.setCodigo(retorno.getInt("codigo"));
                paciente.setNome(retorno.getString("nome"));
                paciente.setEmail(retorno.getString("email"));
                paciente.setSenha(retorno.getString("senha"));
                paciente.setDocumento(retorno.getString("documento"));

                Registro registro = new Registro();
                registro.setCodigo(retorno.getInt("registro"));

                paciente.setRegistro(registro);

                return paciente;
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

        String Sql = "DELETE FROM paciente WHERE codigo=?";

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
    public List<Paciente> selectAll(){
        List<Paciente> lista = new ArrayList<>();
        conexao.conectar();
        String Sql = "SELECT  codigo, nome, email, senha, documento, registro FROM paciente ORDER BY nome";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try{
            ResultSet retorno = stmt.executeQuery();

            while(retorno.next()){
                Paciente paciente = new Paciente();
                paciente.setCodigo(retorno.getInt("codigo"));
                paciente.setNome(retorno.getString("nome"));
                paciente.setEmail(retorno.getString("email"));
                paciente.setSenha(retorno.getString("senha"));
                paciente.setDocumento(retorno.getString("documento"));

                Registro registro = new Registro();
                registro.setCodigo(retorno.getInt("registro"));

                paciente.setRegistro(registro);

                lista.add(paciente);
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
