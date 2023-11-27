package model;

import DAO.RegistroDAO;

import java.sql.Date;
import java.util.List;

public class Registro {
    private int codigo;
    private Date dataRegistro;
    private String detalhe;

    public Registro(){}

    public Registro(int codigo, Date dataRegistro, String detalhe) {
        this.codigo = codigo;
        this.dataRegistro = dataRegistro;
        this.detalhe = detalhe;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public void insert(){
       RegistroDAO dao = new RegistroDAO();
        if(dao.insert(this) >= 1){
            System.out.println("Registro inserido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao inserir lembrete!");
        }
    }

    public void update(){
       RegistroDAO dao = new RegistroDAO();
        if(dao.update(this) >= 1){
            System.out.println("rEGISTRO alterado com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao alterar registro!");
        }
    }

    public void delete(){
       RegistroDAO dao = new RegistroDAO();
        if(dao.delete(getCodigo()) >= 1){
            System.out.println("Registro removido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao remover registro!");
        }
    }

    public void select(int codigo){
        RegistroDAO dao = new RegistroDAO();
        Registro registro = dao.select(codigo);
        if(registro == null){
            System.out.println("Registro não encontrada!");
        } else {
            System.out.println("############# REGISTRO ###############");
            System.out.println("Codigo: " + registro.getCodigo());
            System.out.println("Data: " + registro.getDataRegistro());
            System.out.println("Detalhe: " + registro.getDetalhe());
        }
    }
    public void selectAll(){
        RegistroDAO dao = new RegistroDAO();
        List<Registro> registros = dao.selectAll();
        if(registros == null){
            System.out.println("Registros não encontrado!");
        } else {
            for(Registro registro : registros){
                System.out.println("############# REGISTRO ###############");
                System.out.println("Codigo: " + registro.getCodigo());
                System.out.println("Data: " + registro.getDataRegistro());
                System.out.println("Detalhe: " + registro.getDetalhe());
            }
        }
    }
}
