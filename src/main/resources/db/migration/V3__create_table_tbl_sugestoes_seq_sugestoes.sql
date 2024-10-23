CREATE SEQUENCE SEQ_SUGESTOES
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE TBL_SUGESTOES (
                               SUGESTAO_ID INTEGER DEFAULT SEQ_SUGESTOES.nextval NOT NULL,
                               DESCRICAO VARCHAR (100) NOT NULL,
                               OBSERVACAO VARCHAR (50) NULL,
                               LOCALIZACAO VARCHAR (125) NULL,
                               DATA_CRIACAO DATE DEFAULT SYSDATE NOT NULL,
                               USUARIO_ID INTEGER NOT NULL,
                               CONSTRAINT FK_USUARIO
                                   FOREIGN KEY (USUARIO_ID)
                                       references TBL_USUARIOS(ID)
);