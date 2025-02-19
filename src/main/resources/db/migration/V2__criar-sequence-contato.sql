CREATE SEQUENCE TB_CONTATO_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE TB_CONTATO(
    ID INTEGER DEFAULT TB_CONTATO_SEQ.NEXTVAL NOT NULL,
    NOME VARCHAR2(100) NOT NULL,
    EMAIL VARCHAR2(100) NOT NULL,
    SENHA VARCHAR2(100) NOT NULL,
    DATA_NASCIMENTO DATE NOT NULL
);