create sequence SUS_HORARIO_seq
  start with 1
  increment by 1
  maxvalue 99999999
  minvalue 1;

CREATE TABLE HORARIO
(id integer NOT NULL DEFAULT NEXTVAL('SUS_HORARIO_seq'),
id_recurso integer NOT NULL,
hora_inicio time NOT NULL,
hora_fin time NOT NULL,
PRIMARY KEY (id)
);

create sequence SUS_TIPO_RECURSO_seq
  start with 1
  increment by 1
  maxvalue 99999999
  minvalue 1;

CREATE TABLE TIPO_RECURSO
(id integer NOT NULL DEFAULT NEXTVAL('SUS_TIPO_RECURSO_seq'),
tipo VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
);

create sequence SUS_RECURSO_seq
  start with 1
  increment by 1
  maxvalue 99999999
  minvalue 1;

CREATE TABLE RECURSO
(id_interno integer NOT NULL DEFAULT NEXTVAL('SUS_RECURSO_seq'),
id_tipo integer NOT NULL,
nombre VARCHAR(50) NOT NULL,
ubicacion VARCHAR(50) NOT NULL,
capacidad integer,
estado VARCHAR(50) NOT NULL,
PRIMARY KEY (id_interno)
);

CREATE SEQUENCE SUS_RESERVA_seq
  start with 1
  increment by 1
  maxvalue 99999999
  minvalue 1;

CREATE TABLE RESERVA
(id integer NOT NULL DEFAULT NEXTVAL('SUS_RESERVA_seq'),
fecha_ini TIMESTAMP NOT NULL,
fecha_fin TIMESTAMP NOT NULL,
id_recurso integer NOT NULL,
id_usuario VARCHAR(50) NOT NULL,
estado VARCHAR(50) NOT NULL,
tipo VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE USUARIO(
id VARCHAR(50) NOT NULL,
passw VARCHAR(100) NOT NULL,
tipo VARCHAR(50) NOT NULL,
carnet INTEGER NOT NULL,
nombre VARCHAR(50) NOT NULL,
carrera VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);