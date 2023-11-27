import form.profissional.ProfissionalInterface;
import model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Profissional profissional = new Profissional(1, "Marcio Andre", "marcio.andre@fatec.sp.gov.br",
                "123456", "Medico");

        Exame exame = new Exame(1, new Date(2023 - 1900, 11 - 1, 19), "18:40", "Raio-X");
        Exame exame2 = new Exame(2, new Date(2023 - 1900, 11 - 1, 19), "12:40", "Exame de sangue");

        List<Exame> exames = new ArrayList<>(Arrays.asList(exame, exame2));

        Catalogo catalogo = new Catalogo(2, "Exames disponiveis", "Jejum antes da consulta",
                "Trazer documento com foto", "150.00", exames);

        Clinica clinica = new Clinica(1, "Hospital Santa Fé", "14236589972320", "123456",
                catalogo, profissional);

        Lembrete lembrete = new Lembrete(1, "Realizar jejum de 8 horas", "RG OU CPF");

        Registro registro = new Registro(1, new Date(2023 - 1900, 11 - 1, 19),
                "Registro basico");

        Paciente paciente = new Paciente(1, "Luisa Viana", "luisa.viana@fatec.sp.gov.br",
                "5479631","45674197523",registro);

//        profissional.insert();
//        profissional.setSenha("0785");
//        profissional.update();
//        profissional.select(1);
//        profissional.selectAll();
//        profissional.delete();

//        exame.insert();
//        exame.setResultado("Resultado enviado por email");
//        exame.update();
//        exame.select(1);
//        exame.selectAll();
//        exame.delete();

//        catalogo.insert();
//        catalogo.setValor("74.90");
//        catalogo.update();
//        catalogo.select(1);
//        catalogo.selectAll();
//        catalogo.delete();

//        clinica.insert();
//        clinica.setSenha("147963");
//        clinica.update();
//        clinica.select(1);
//        clinica.selectAll();

//        lembrete.insert();
//        lembrete.setDocumentos("RG E CPF");
//        lembrete.update();
//        lembrete.select(1);
//        lembrete.selectAll();

//        paciente.insert();
//        paciente.setSenha("123456");
//        paciente.update();
//        paciente.select(1);
//        paciente.selectAll();

        ProfissionalInterface profissionalInterface = new ProfissionalInterface();
        profissionalInterface.read();
    }
}
