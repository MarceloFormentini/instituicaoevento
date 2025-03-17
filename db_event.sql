-- Criar a tabela de instituições caso não exista
DROP TABLE IF EXISTS instituicao CASCADE;
CREATE TABLE IF NOT EXISTS instituicao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL
);

-- Criar a tabela de eventos caso não exista
DROP TABLE IF EXISTS evento CASCADE;
CREATE TABLE evento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL,
    ativo BOOLEAN DEFAULT FALSE,
    instituicao_id INT NOT NULL,
    CONSTRAINT fk_instituicao FOREIGN KEY (instituicao_id) REFERENCES instituicao(id)
);
