package form.profissional;

import DAO.ProfissionalDAO;
import model.Profissional;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfissionalInterface extends JFrame {
    private JPanel Panel1 = new JPanel();
    private JTextField txtCodigo = new JTextField(20);
    private JTextField txtNome = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtSenha = new JTextField(20);
    private JTextField txtCargo = new JTextField(20);
    private JButton inserirButton = new JButton("Inserir");
    private JButton removerButton = new JButton("Remover");
    private JButton alterarButton = new JButton("Alterar");
    private JButton pesquisarButton = new JButton("Pesquisar");
    private JButton cancelButton = new JButton("Cancelar");

    private void limparPanel1() {
        Panel1.removeAll();
        Panel1.revalidate();
        Panel1.repaint();
    }
    public void create() {
        limparPanel1();
        this.add(Panel1);

        Panel1.add(new JLabel("Nome:"));
        Panel1.add(txtNome);
        Panel1.add(new JLabel("Email:"));
        Panel1.add(txtEmail);
        Panel1.add(new JLabel("Senha:"));
        Panel1.add(txtSenha);
        Panel1.add(new JLabel("Cargo:"));
        Panel1.add(txtCargo);
        Panel1.add(inserirButton);

        this.setVisible(true);
        this.setSize(300, 500);
        this.setTitle("Profissional - CRUD");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Profissional profissional = new Profissional();
                    profissional.setNome(txtNome.getText());
                    profissional.setEmail(txtEmail.getText());
                    profissional.setSenha(txtSenha.getText());
                    profissional.setCargo(txtCargo.getText());
                    profissional.insert();
                    JOptionPane.showMessageDialog(null, "Profissional inserido com sucesso!");
                } catch (Exception err){
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir profissional!");
                }

                dispose();
            }
        });
    }
    public void read() {
        limparPanel1();
        this.add(Panel1);

        txtCodigo = new JTextField(20);

        Panel1.add(new JLabel("Código do funcionario:"));
        Panel1.add(txtCodigo);
        Panel1.add(pesquisarButton);

        this.setVisible(true);
        this.setSize(300, 500);
        this.setTitle("Profissional - CRUD");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProfissionalDAO dao = new ProfissionalDAO();
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    Profissional profissional = dao.select(codigo);

                    if (profissional != null) {
                        String mensagem = "Código: " + profissional.getCodigo() + "\nNome: " + profissional.getNome() +
                                "\nEmail: " + profissional.getEmail() + "\nSenha: " + profissional.getSenha() + "\nCargo: "
                                + profissional.getCargo();
                        JOptionPane.showMessageDialog(null, mensagem);

                        Object[] opcoes = {"FECHAR", "ATUALIZAR", "APAGAR", "CADASTRAR NOVO PROFISSIONAL"};

                        int resp = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Opções do Profissional",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                null, opcoes, opcoes[0]);

                        switch (resp) {
                            case 1:
                                update(profissional);
                                break;
                            case 2:
                                delete(profissional);
                                break;
                            case 3:
                                create();
                                break;
                            default:
                                dispose();
                                break;
                        }
                    } else {
                        int resposta = JOptionPane.showConfirmDialog(null,
                                "Profissional não encontrado. Deseja criar um novo?",
                                "Profissional não encontrado",
                                JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            create();
                        }
                    }
                }  catch (Exception err){
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao pesquisar o profissional!");
                }
            }
        });
    }

    public void delete(Profissional profissional) {
        limparPanel1();
        this.add(Panel1);

        Panel1.add(new JLabel("Confirma a exlusao do funcionario:"));
        Panel1.add(txtNome);
        txtNome.setText(profissional.getNome());
        txtNome.setEditable(false);
        Panel1.add(removerButton);
        Panel1.add(cancelButton);

        this.setVisible(true);
        this.setSize(300, 500);
        this.setTitle("Profissional - CRUD");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profissional profissional = new Profissional();
                try {
                    profissional.setCodigo(Integer.parseInt(txtCodigo.getText()));
                    profissional.delete();
                    JOptionPane.showMessageDialog(null, "Profissional excluído com sucesso!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir profissional!");
                    dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void update(Profissional profissional){
        limparPanel1();

        Panel1.add(new JLabel("Nome:"));
        Panel1.add(txtNome);
        Panel1.add(new JLabel("Email:"));
        Panel1.add(txtEmail);
        Panel1.add(new JLabel("Senha:"));
        Panel1.add(txtSenha);
        Panel1.add(new JLabel("Cargo:"));
        Panel1.add(txtCargo);
        Panel1.add(alterarButton);

        txtNome.setText(profissional.getNome());
        txtEmail.setText(profissional.getEmail());
        txtSenha.setText(profissional.getSenha());
        txtCargo.setText(profissional.getCargo());


        alterarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    profissional.setNome(txtNome.getText());
                    profissional.setEmail(txtEmail.getText());
                    profissional.setSenha(txtSenha.getText());
                    profissional.setCargo(txtCargo.getText());

                    profissional.update();
                    JOptionPane.showMessageDialog(null, "Profissional atualizado com sucesso!");
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o profissional!");
                }

                dispose();
            }
        });
    }
}