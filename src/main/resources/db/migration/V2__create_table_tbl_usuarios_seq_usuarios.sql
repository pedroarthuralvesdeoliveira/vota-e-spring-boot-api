CREATE SEQUENCE USUARIOS_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE TBL_USUARIOS (
                              USUARIO_ID INTEGER DEFAULT USUARIOS_SEQ.NEXTVAL NOT NULL,
                              NOME nvarchar2(100) NOT NULL,
                              EMAIL nvarchar2(100) NOT NULL UNIQUE,
                              SENHA nvarchar2(100) NOT NULL,
                              TELEFONE nvarchar2(20) NOT NULL UNIQUE,
                              ROLE NVARCHAR2(50) DEFAULT 'USER'
);