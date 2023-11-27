CREATE DATABASE clinica;

USE clinica;

-- CRIANDO TABLES

CREATE TABLE clinica (
	codigo INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    senha VARCHAR(12),
    profissional INT,
    catalogo INT
);

CREATE TABLE profissional (
	codigo INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha CHAR(12),
    cargo VARCHAR(100) NOT NULL
);

CREATE TABLE catalogo (
	codigo INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    preparacao VARCHAR(100),
    orientacao VARCHAR(100),
    valor DECIMAL(10),
    exame INT
);

CREATE TABLE exame (
	codigo INT PRIMARY KEY AUTO_INCREMENT,
    dataExame DATE,
    statusExame CHAR(30),
    resultado VARCHAR(100)
);

CREATE TABLE lembrete (
	codigo INT PRIMARY KEY AUTO_INCREMENT,
    instrucoes VARCHAR(100),
    documentos VARCHAR(100)
);

CREATE TABLE paciente (
	codigo INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha CHAR(12),
    documento CHAR(12),
    registro INT
);

CREATE TABLE registro (
	codigo INT PRIMARY KEY AUTO_INCREMENT,
    dataRegistro DATE,
    detalhe VARCHAR(100)
);

-- ADICIONANDO CHAVE ESTRANGEIRA AS TABLES

ALTER TABLE clinica ADD FOREIGN KEY (profissional) REFERENCES 
profissional(codigo);

ALTER TABLE clinica ADD FOREIGN KEY (catalogo) REFERENCES 
catalogo(codigo);

ALTER TABLE catalogo ADD FOREIGN KEY (exame) REFERENCES
exame(codigo);

ALTER TABLE paciente ADD FOREIGN KEY (registro) REFERENCES
registro(codigo);

-- POPULANDO TABLES 
INSERT INTO registro (dataRegistro, detalhe) VALUES 
(NOW(), 'Primerio exame do paciente'),
(NOW(), 'Retorno do paciente'),
(NOW(), 'Consulta de rotina');

INSERT INTO paciente (nome, email, senha, documento, registro) VALUES 
('Juliana Oliveira', 'juliana.oliveira@fatec.sp.gov.br', '123456', '55447612789', 1),
('Fernando Alves', 'fernando.alves@fatec.sp.gov.br', '479612', '46574721465', 2),
('Lucas Vitor', 'lucas.vitor@fatec.sp.gov.br', '654321', '55578947132', 3);

INSERT INTO lembrete (instrucoes, documentos) VALUES 
('Jejum de 8 horas', 'RG E CPF'),
('Jejum de 12 horas', 'RG E CPF'),
('Jejum de 6 horas', 'RG E CPF');

INSERT INTO exame (dataExame, statusExame, resultado) VALUES 
(NOW(), 'Realizado', 'Sem alteracao'),
(NOW(), 'Pendente', 'Sem alteracao'),
(NOW(), 'Remarcado', 'Sem alteracao');

INSERT INTO catalogo (nome, preparacao, orientacao, valor, exame) VALUES 
('Exame de sangue', 'Jejum de 8 horas', 'Realizar jejum e trazer documentos com foto', 147.50, 1),
('Exame de urina', 'Jejum de 8 horas', 'Realizar jejum e trazer documentos com foto', 120.00, 2),
('Exame de urina e sangue', 'Jejum de 8 horas','Realizar jejum e trazer documentos com foto', 200.00, 3);

INSERT INTO profissional (nome, email, senha, cargo) VALUES 
('Luana Pimenta', 'luana.pimenta@fatec.sp.gov.br', '123456', 'Medico'),
('Maria Silva', 'maria.silva@fatec.sp.gov.br', '123456', 'Enfermeira'),
('Jose Alves', 'jose.alves@fatec.sp.gov.br', '123456', 'Recepcionista');

INSERT INTO clinica (nome, cnpj, senha, profissional, catalogo) VALUES 
('Hospital Santa Fe', '4123335544001', '147856', 1, 2),
('Hospital Albert Einsten', '6353354444001', '001856', 2, 3),
('Hospital Menino Jesus', '7453354444001', '854631', 3, 4);

-- CRIANDO INDICES
CREATE INDEX indice_clinica ON 
clinica(codigo, nome, cnpj, senha);

CREATE INDEX indice_profissional ON 
profissional(codigo, nome, email, senha);

CREATE INDEX indice_catalogo ON 
catalogo(codigo, nome, preparacao, orientacao, valor, exame);

CREATE INDEX indice_exame ON 
exame (codigo, dataExame, statusExame, resultado);

CREATE INDEX indice_lembrete ON 
lembrete (codigo, instrucoes, documentos);

CREATE INDEX indice_paciente on
paciente (codigo, nome, email, senha, documento, registro);

CREATE INDEX indice_registro on
registro (dataRegistro, detalhe);

-- UTILIZANDO O BETWEEN 
SELECT * FROM registro WHERE dataRegistro BETWEEN '2023-11-16' AND '2023-11-18';

-- UTILIZANDO O LIKE
SELECT nome FROM paciente
WHERE nome LIKE '%Silva';

-- CRIANDO UMA VIEW 
CREATE VIEW paciente_exame AS
SELECT 
	codigo, nome, documento
FROM 
	paciente
UNION
SELECT 
codigo, dataExame, statusExame
FROM 
	exame;
SELECT * FROM paciente_exame;