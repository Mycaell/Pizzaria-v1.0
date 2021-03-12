
CREATE TABLE cliente
(	
    nome character varying  NOT NULL,
    sobrenome character varying  NOT NULL,
    cpf character varying,
    telefone character varying  NOT NULL,
    bairro character varying NOT NULL,
    rua character varying NOT NULL,
    casa integer NOT NULL,
    endereco character varying NOT NULL,
    iddofuncionarioquerealizouocadastro integer NOT NULL,
    PRIMARY KEY (cpf)
);

CREATE TABLE contabilidade
(
    vendas integer NOT NULL,
    lucro double precision NOT NULL
);

INSERT INTO contabilidade (vendas, lucro) VALUES (0,0);


CREATE TABLE funcionario
(
    id serial,
    nome character varying NULL,
    sobrenome character varying NOT NULL,
    sexo "char" NOT NULL,
    nascimento date NOT NULL,
    "endereço" character varying NOT NULL,
    telefone character varying NOT NULL,
    cpf character varying NOT NULL,
    email character varying NOT NULL,
    senha character varying NOT NULL,
    cargo character varying NOT NULL,
    iddofuncionarioquerealizouocadastro integer NOT NULL,
    PRIMARY KEY (id)
);




CREATE TABLE ingrediente
(
    id serial,
    nome character varying NOT NULL,
    preco real NOT NULL,
    PRIMARY KEY (id)
);



CREATE TABLE pedido
(
    numero serial,
    cliente character varying NOT NULL,
    fatias integer NOT NULL,
    preco real NOT NULL,
    endereco character varying NOT NULL,
    aberto boolean NOT NULL,
    pronto boolean NOT NULL,
    entregue boolean,
    criacao timestamp without time zone NOT NULL,
    preparo timestamp without time zone,
    entrega timestamp without time zone,
    idatendente integer NOT NULL,
    idpizzaiolo integer,
    idmotoboy integer,
    sabores character varying NOT NULL,
    PRIMARY KEY (numero)
);





CREATE TABLE pizza
(
    id serial,
    sabor character varying NOT NULL,
    preco_fatia real NOT NULL,
    quantidade_vendida integer NOT NULL,
    id_do_gerente integer NOT NULL,
    custo_preparo real,
    PRIMARY KEY (id)
);







CREATE TABLE pizza_ingrediente
(
    id_pizza integer NOT NULL,
    id_ingrediente integer NOT NULL
);






