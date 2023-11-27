package model;

import DAO.ExameDAO;
import DAO.LembreteDAO;

import java.util.List;

public class Lembrete {
    private int codigo;
    private String intrucoes;
    private String documentos;

    public Lembrete(){}

    public Lembrete(int codigo, String intrucoes, String documentos) {
        this.codigo = codigo;
        this.intrucoes = intrucoes;
        this.documentos = documentos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getIntrucoes() {
        return intrucoes;
    }

    public void setIntrucoes(String intrucoes) {
        this.intrucoes = intrucoes;
    }

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public void insert(){
       LembreteDAO dao = new LembreteDAO();
        if(dao.insert(this) >= 1){
            System.out.println("Lembrete inserido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao inserir lembrete!");
        }
    }

    public void update(){
       LembreteDAO dao = new LembreteDAO();
        if(dao.update(this) >= 1){
            System.out.println("Lembrete alterado com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao alterar lembrete!");
        }
    }

    public void delete(){
       LembreteDAO dao = new LembreteDAO();
        if(dao.delete(getCodigo()) >= 1){
            System.out.println("Lembrete removido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao remover lembrete!");
        }
    }

    public void select(int codigo){
       LembreteDAO dao = new LembreteDAO();
        Lembrete lembrete = dao.select(codigo);
        if(lembrete == null){
            System.out.println("Lembrete não encontrada!");
        } else {
            System.out.println("############# LEMBRETE ###############");
            System.out.println("Codigo: " + lembrete.getCodigo());
            System.out.println("Instrucoes: " + lembrete.getIntrucoes());
            System.out.println("Documento: " + lembrete.getDocumentos());
        }
    }

    public void selectAll(){
        LembreteDAO dao = new LembreteDAO();
        List<Lembrete> lembretes = dao.selectAll();
        if(lembretes == null){
            System.out.println("Exames não encontrado!");
        } else {
            for(Lembrete lembrete : lembretes){
                System.out.println("############# LEMBRETE ###############");
                System.out.println("Codigo: " + lembrete.getCodigo());
                System.out.println("Instrucoes: " + lembrete.getIntrucoes());
                System.out.println("Documento: " + lembrete.getDocumentos());
            }
        }
    }
}
