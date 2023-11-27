package model;

import DAO.ExameDAO;

import java.sql.Date;
import java.util.List;

public class Exame {
    private int codigo;
    private Date dataExame;
    private String status;
    private String resultado;

    public Exame(){}

    public Exame(int codigo, Date dataExame, String status, String resultado) {
        this.codigo = codigo;
        this.dataExame = dataExame;
        this.status = status;
        this.resultado = resultado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataExame() {
        return dataExame;
    }

    public void setDataExame(Date dataExame) {
        this.dataExame = dataExame;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void disparaLembrete(){}

    public void insert(){
        ExameDAO dao = new ExameDAO();
        if(dao.insert(this) >= 1){
            System.out.println("Exame inserido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao inserir exame!");
        }
    }

    public void update(){
        ExameDAO dao = new ExameDAO();
        if(dao.update(this) >= 1){
            System.out.println("Exame alterado com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao alterar exame!");
        }
    }

    public void delete(){
        ExameDAO dao = new ExameDAO();
        if(dao.delete(getCodigo()) >= 1){
            System.out.println("Exame removido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao remover exame!");
        }
    }

    public void select(int codigo){
        ExameDAO dao = new ExameDAO();
        Exame exame = dao.select(codigo);
        if(exame == null){
            System.out.println("Exame não encontrada!");
        } else {
            System.out.println("############# EXAME ###############");
            System.out.println("Codigo: " + exame.getCodigo());
            System.out.println("Data: " + exame.getDataExame());
            System.out.println("Status exame: " + exame.getStatus());
            System.out.println("Resultado: " + exame.getResultado());
        }
    }
    public void selectAll(){
        ExameDAO dao = new ExameDAO();
        List<Exame> exames = dao.selectAll();
        if(exames == null){
            System.out.println("Exames não encontrado!");
        } else {
            for(Exame exame : exames){
                System.out.println("############# EXAME ###############");
                System.out.println("Codigo: " + exame.getCodigo());
                System.out.println("Data: " + exame.getDataExame());
                System.out.println("Status exame: " + exame.getStatus());
                System.out.println("Resultado: " + exame.getResultado());
            }
        }
    }
}
