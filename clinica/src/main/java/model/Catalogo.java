package model;

import DAO.CatalogoDAO;
import DAO.ProfissionalDAO;

import java.util.List;

public class Catalogo {
    private int codigo;
    private String nome;
    private String preparacao;
    private String orientacao;
    private String valor;
    private List<Exame> exame;

    public Catalogo(){}
    public Catalogo(int codigo, String nome, String preparacao, String orientacao, String valor, List<Exame> exame) {
        this.codigo = codigo;
        this.nome = nome;
        this.preparacao = preparacao;
        this.orientacao = orientacao;
        this.valor = valor;
        this.exame = exame;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreparacao() {
        return preparacao;
    }

    public void setPreparacao(String preparacao) {
        this.preparacao = preparacao;
    }

    public String getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(String orientacao) {
        this.orientacao = orientacao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<Exame> getExame() {
        return exame;
    }

    public void setExame(List<Exame> exame) {
        this.exame = exame;
    }

    public void insert(){
        CatalogoDAO dao = new CatalogoDAO();
        if(dao.insert(this) >= 1){
            System.out.println("Catalogo inserido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao inserir catalogo!");
        }
    }

    public void update(){
        CatalogoDAO dao = new CatalogoDAO();
        if(dao.update(this) >= 1){
            System.out.println("Catalogo alterado com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao alterar catalogo!");
        }
    }

    public void delete(){
        CatalogoDAO dao = new CatalogoDAO();
        if(dao.delete(getCodigo()) >= 1){
            System.out.println("Catalogo removido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao remover catalogo!");
        }
    }

    public void select(int codigo){
        CatalogoDAO dao = new CatalogoDAO();
        Catalogo catalogo = dao.select(codigo);
        if(catalogo == null){
            System.out.println("Catalogo não encontrada!");
        } else {
            System.out.println("############# CATALOGO ###############");
            System.out.println("Codigo: " + catalogo.getCodigo());
            System.out.println("Nome: " + catalogo.getNome());
            System.out.println("Preparacao: " + catalogo.getPreparacao());
            System.out.println("Orientacao: " + catalogo.getOrientacao());
            System.out.println("Valor: " + catalogo.getValor());
            for(Exame exame : catalogo.getExame()){
                System.out.println("Exame: " + exame.getCodigo());
            }
        }
    }
    public void selectAll(){
        CatalogoDAO dao = new CatalogoDAO();
        List<Catalogo> catalogos = dao.selectAll();
        if(catalogos == null){
            System.out.println("Catalogos não encontrado!");
        } else {
            for(Catalogo catalogo : catalogos){
                System.out.println("############# CATALOGO ###############");
                System.out.println("Codigo: " + catalogo.getCodigo());
                System.out.println("Nome: " + catalogo.getNome());
                System.out.println("Preparacao: " + catalogo.getPreparacao());
                System.out.println("Orientacao: " + catalogo.getOrientacao());
                System.out.println("Valor: " + catalogo.getValor());
                for(Exame exame : catalogo.getExame()){
                    System.out.println("Exame: " + exame.getCodigo());
                }
            }
        }
    }
}
