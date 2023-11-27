package model;

import DAO.ClinicaDAO;

import java.util.List;

public class Clinica {
    private int codigo;
    private String nome;
    private String cnpj;
    private String senha;

    private Catalogo catalogo;
    private Profissional profissional;

    public Clinica(){}

    public Clinica(int codigo, String nome, String cnpj, String senha, Catalogo catalogo, Profissional profissional) {
        this.codigo = codigo;
        this.nome = nome;
        this.cnpj = cnpj;
        this.senha = senha;
        this.catalogo = catalogo;
        this.profissional = profissional;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public void insert(){
        ClinicaDAO dao = new ClinicaDAO();
        if(dao.insert(this) >= 1){
            System.out.println("Clinica inserida com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao inserir clinica!");
        }
    }

    public void update(){
        ClinicaDAO dao = new ClinicaDAO();
        if(dao.update(this) >= 1){
            System.out.println("Clinica alterada com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao alterar clinica!");
        }
    }

    public void delete(){
        ClinicaDAO dao = new ClinicaDAO();
        if(dao.delete(getCodigo()) >= 1){
            System.out.println("Clinica removido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao remover clinica!");
        }
    }

    public void select(int codigo){
        ClinicaDAO dao = new ClinicaDAO();
        Clinica clinica = dao.select(codigo);
        if(clinica == null){
            System.out.println("Clinica não encontrada!");
        } else {
            System.out.println("############# CLINICA ###############");
            System.out.println("Nome: " + clinica.getNome());
            System.out.println("CNPJ: " + clinica.getCnpj());
            System.out.println("Senha: " + clinica.getSenha());
            System.out.println("Catalogo: " + clinica.getCatalogo().getCodigo());
            System.out.println("Profissional: " + clinica.getProfissional().getCodigo());
        }
    }
    public void selectAll(){
        ClinicaDAO dao = new ClinicaDAO();
        List<Clinica> clinicas = dao.selectAll();
        if(clinicas == null){
            System.out.println("Clinicas não encontrado!");
        } else {
            for(Clinica clinica : clinicas){
                System.out.printf("CLINICA -- %d\n", clinica.getCodigo());
                System.out.println("Nome: " + clinica.getNome());
                System.out.println("CNPJ: " + clinica.getCnpj());
                System.out.println("Senha: " + clinica.getSenha());
                System.out.println("Catalogo: " + clinica.getCatalogo().getCodigo());
                System.out.println("Profissional: " + clinica.getProfissional().getCodigo());
            }
        }
    }
}
