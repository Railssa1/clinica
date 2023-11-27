package model;

import DAO.PacienteDAO;
import DAO.ProfissionalDAO;

import java.util.List;

public class Paciente extends Pessoa {
    private String documento;
    private Registro registro;

    public Paciente(){}

    public Paciente(int codigo, String nome, String email, String senha, String documento, Registro registro) {
        super(codigo, nome, email, senha);
        this.documento = documento;
        this.registro = registro;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    @Override
    public void mostrar(){
        System.out.println("DADOS DO PACIENTE: ");
        super.mostrar();
        System.out.println("Documento: " + this.getDocumento());
        System.out.println("Registro: " + this.getRegistro().getDetalhe());
    }

    public void agendaExame(){}

    public void insert(){
       PacienteDAO dao = new PacienteDAO();
        if(dao.insert(this) >= 1){
            System.out.println("Paciente inserido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao inserir paciente!");
        }
    }

    public void update(){
       PacienteDAO dao = new PacienteDAO();
        if(dao.update(this) >= 1){
            System.out.println("Paciente alterado com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao alterar paciente!");
        }
    }

    public void delete(){
       PacienteDAO dao = new PacienteDAO();
        if(dao.delete(getCodigo()) >= 1){
            System.out.println("Paciente removido com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao remover paciente!");
        }
    }

    public void select(int codigo){
        PacienteDAO dao = new PacienteDAO();
        Paciente paciente = dao.select(codigo);
        if(paciente == null){
            System.out.println("Paciente não encontrada!");
        } else {
            System.out.println("############# PACIENTE ###############");
            System.out.println("Codigo: " + paciente.getCodigo());
            System.out.println("Nome: " + paciente.getNome());
            System.out.println("Email: " + paciente.getEmail());
            System.out.println("Senha: " + paciente.getSenha());

        }
    }
    public void selectAll(){
       PacienteDAO dao = new PacienteDAO();
        List<Paciente> pacientes = dao.selectAll();
        if(pacientes == null){
            System.out.println("Pacientes não encontrado!");
        } else {
            for(Paciente paciente : pacientes){
                System.out.printf("PACIENTE -- %d\n", paciente.getCodigo());
                System.out.println("Codigo: " + paciente.getCodigo());
                System.out.println("Nome: " + paciente.getNome());
                System.out.println("Email: " + paciente.getEmail());
                System.out.println("Senha: " + paciente.getSenha());
            }
        }
    }
}
