package DAO;

import model.Registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {
    private Conexao conexao = new Conexao();
    public int insert(Registro registro) {
        conexao.conectar();

        String sql = "INSERT INTO registro(dataRegistro, detalhe) VALUES (?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setDate(1, registro.getDataRegistro());
            stmt.setString(2, registro.getDetalhe());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }

    public int update(Registro registro) {
        conexao.conectar();

        String sql = "UPDATE registro SET dataRegistro=?, detalhe=? WHERE codigo=?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setDate(1, registro.getDataRegistro());
            stmt.setString(2, registro.getDetalhe());
            stmt.setInt(3, registro.getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }
    public Registro select(int codigo) {
        conexao.conectar();
        String Sql = "SELECT codigo, dataRegistro, detalhe FROM registro WHERE codigo=?";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try {
            stmt.setInt(1, codigo);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                Registro registro = new Registro();
                registro.setCodigo(retorno.getInt("codigo"));
                registro.setDataRegistro(retorno.getDate("dataRegistro"));
                registro.setDetalhe(retorno.getString(("detalhe")));
                return new Registro();
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

        String Sql = "DELETE FROM registro WHERE codigo=?";

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
    public List<Registro> selectAll(){
        List<Registro> lista = new ArrayList<>();
        conexao.conectar();
        String Sql = "SELECT codigo, dataRegistro, detalhe FROM registro ORDER BY dataRegistro";
        PreparedStatement stmt = conexao.prepareStatement(Sql);
        try{
            ResultSet retorno = stmt.executeQuery();

            while(retorno.next()){
                Registro registro = new Registro();
                registro.setCodigo(retorno.getInt("codigo"));
                registro.setDataRegistro(retorno.getDate("dataRegistro"));
                registro.setDetalhe(retorno.getString(("detalhe")));

                lista.add(registro);
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
