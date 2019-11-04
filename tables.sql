CREATE TABLE IF NOT EXISTS 'tipo_recurso'(
    'id' INTEGER NOT NULL,
    'tipo' VARCHAR(50) NOT NUll
);

CREATE TABLE IF NOT EXISTS 'recurso'(
    'id_interno' INTEGER NOT NULL,
    'id_tipo' INTEGER NOT NULL,
    'nombre' VARCHAR(50) NOT NULL,
    'ubicacion' VARCHAR(50) NOT NULL,
    'capacidad' INTEGER
);

CREATE TABLE IF NOT EXISTS 'recurso_rentado'(
    'id' INTEGER NOT NULL,
    'fecha_in' DATE NOT NULL,
    'fecha_fin' DATE NOT NULL,
    'id_recurso' INTEGER
);

  