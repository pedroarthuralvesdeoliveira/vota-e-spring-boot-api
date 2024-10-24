CREATE SEQUENCE SEQ_PROJETOS
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE TBL_PROJETOS (
                              PROJETO_ID INTEGER DEFAULT SEQ_PROJETOS.nextval NOT NULL primary key,
                              DESCRICAO CLOB NOT NULL,
                              TITULO NVARCHAR2(200) NOT NULL,
                              STATUS NVARCHAR2(20) CHECK ( STATUS IN ('EM_ELABORACAO', 'ENVIADO', 'APROVADO', 'REJEITADO', 'CANCELADO') ) NOT NULL,
                              DATA_CRIACAO DATE DEFAULT SYSDATE NOT NULL,
                              DATA_ENVIO DATE,
                              DATA_APROVACAO DATE,
                              SUGESTAO_ID INTEGER NOT NULL,
                              CONSTRAINT FK_SUGESTAO
                                  FOREIGN KEY (SUGESTAO_ID)
                                      references TBL_SUGESTOES(SUGESTAO_ID)
);