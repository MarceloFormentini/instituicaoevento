-- Criar a tabela de instituições caso não exista
DROP TABLE IF EXISTS instituicao CASCADE;
CREATE TABLE IF NOT EXISTS instituicao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX idx_instituicao_nome_tipo ON instituicao (nome, tipo);
CREATE INDEX idx_instituicao_tipo ON instituicao (tipo);

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

-- Índices para melhorar a performance nas consultas de Eventos
CREATE INDEX idx_evento_instituicao ON evento (instituicao_id);
CREATE INDEX idx_evento_data_inicio ON evento (data_inicio);
CREATE INDEX idx_evento_data_fim ON evento (data_fim);
CREATE INDEX idx_evento_ativo ON evento (ativo);
