CREATE TABLE donos(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(125),
    email VARCHAR(70) NOT NULL,
    login VARCHAR(20) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    ultima_alteracao DATE
);

CREATE TABLE clientes(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(125) NOT NULL,
    email VARCHAR(70) NOT NULL,
    login VARCHAR(20) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    ultima_alteracao DATE,
    endereco VARCHAR(100) NOT NULL
);

INSERT INTO donos (nome, email, login, senha, ultima_alteracao)
VALUES ('Dono loja a', 'admin@lojaa.com', 'lojaA', 'lojaAA22', '2024-10-15');

INSERT INTO clientes (nome, email, login, senha, ultima_alteracao, endereco)
VALUES ('Cliente Teste', 'cliente@teste.com', 'cliente', 'cliente123456', '2024-10-15', 'Centro São Paulo - SP');

