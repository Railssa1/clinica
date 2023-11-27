package model;

import DAO.ClinicaDAO;
import DAO.ProfissionalDAO;

import java.util.List;

public class Profissional extends Pessoa {
    private String cargo;
    public Profissional(){}

    public Profissional(int codigo, String nome, String email, String senha, String cargo) {
        super(codigo, nome, email, senha);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public void responsavelPorExame(){}
    @Override
    public void mostrar(){
        System.out.println("DADOS DO PROFISSIONAL: ");
        super.mostrar();
    }

    public void insert(){
        ProfissionalDAO dao = new ProfissionalDAO();
        if(dao.insert(this) >= 1){
            System.out.println("Profissional inserido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao inserir profissional!");
        }
    }

    public void update(){
        ProfissionalDAO dao = new ProfissionalDAO();
        if(dao.update(this) >= 1){
            System.out.println("Profissional alterado com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao alterar profissional!");
        }
    }

    public void delete(){
        ProfissionalDAO dao = new ProfissionalDAO();
        if(dao.delete(getCodigo()) >= 1){
            System.out.println("Profissional removido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao remover profissional!");
        }
    }

    public void select(int codigo){
        ProfissionalDAO dao = new ProfissionalDAO();
        Profissional profissional = dao.select(codigo);
        if(profissional == null){
            System.out.println("Profissional não encontrada!");
        } else {
            System.out.println("############# PROFISSIONAL ###############");
            System.out.println("Codigo: " + profissional.getCodigo());
            System.out.println("Nome: " + profissional.getNome());
            System.out.println("Email: " + profissional.getEmail());
            System.out.println("Senha: " + profissional.getSenha());

        }
    }

    public void selectAll(){
        ProfissionalDAO dao = new ProfissionalDAO();
        List<Profissional> profissionais = dao.selectAll();
        if(profissionais == null){
            System.out.println("Clinicas não encontrado!");
        } else {
            for(Profissional profissional : profissionais){
                System.out.printf("PROFISSIONAL -- %d\n", profissional.getCodigo());
                System.out.println("Codigo: " + profissional.getCodigo());
                System.out.println("Nome: " + profissional.getNome());
                System.out.println("Email: " + profissional.getEmail());
                System.out.println("Senha: " + profissional.getSenha());
            }
        }
    }
}
