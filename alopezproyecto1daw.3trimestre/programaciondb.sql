CREATE DATABASE IF NOT EXISTS programacionDB4;
USE programacionDB4;

CREATE TABLE IF NOT EXISTS Entidad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Alimento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entidadId INT,
    cantidad FLOAT NOT NULL,
    calorias FLOAT NOT NULL,
    tipo ENUM('Carbohidrato', 'Grasa', 'Proteina') NOT NULL,
    FOREIGN KEY (entidadId) REFERENCES Entidad(id)
);

CREATE TABLE IF NOT EXISTS Receta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entidadId INT,
    FOREIGN KEY (entidadId) REFERENCES Entidad(id)
);

CREATE TABLE IF NOT EXISTS Receta_Alimento (
    recetaId INT,
    alimentoId INT,
    PRIMARY KEY (recetaId, alimentoId),
    FOREIGN KEY (recetaId) REFERENCES Receta(id),
    FOREIGN KEY (alimentoId) REFERENCES Alimento(id)
);

CREATE TABLE IF NOT EXISTS Ejercicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entidadId INT,
    calorias_quemadas_por_minuto FLOAT NOT NULL,
    duracion SMALLINT NOT NULL,
    categoria ENUM('RESISTENCIA', 'FUERZA', 'FLEXIBILIDAD') NOT NULL,
    FOREIGN KEY (entidadId) REFERENCES Entidad(id)
);

CREATE TABLE IF NOT EXISTS Rutina (
    id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Rutina_Ejercicio (
    rutinaId INT,
    ejercicioId INT,
    duracion FLOAT NOT NULL,
    PRIMARY KEY (rutinaId, ejercicioId),
    FOREIGN KEY (rutinaId) REFERENCES Rutina(id),
    FOREIGN KEY (ejercicioId) REFERENCES Ejercicio(id)
);

CREATE TABLE IF NOT EXISTS Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entidadId INT,
    edad TINYINT NOT NULL,
    peso FLOAT NOT NULL,
    altura FLOAT NOT NULL,
    sexo CHAR(1) CHECK (sexo IN ('h', 'm')) NOT NULL,
    nivel_actividad ENUM('LIGERO', 'MODERADO', 'INTENSO') NOT NULL,
    objetivo ENUM('BAJAR_PESO', 'SUBIR_PESO', 'MANTENER_PESO') NOT NULL,
    objetivo_diario_calorias SMALLINT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    FOREIGN KEY (entidadId) REFERENCES Entidad(id)
);

CREATE TABLE IF NOT EXISTS Usuario_Receta (
    usuarioId INT,
    recetaId INT,
    PRIMARY KEY (usuarioId, recetaId),
    FOREIGN KEY (usuarioId) REFERENCES Usuario(id),
    FOREIGN KEY (recetaId) REFERENCES Receta(id)
);

CREATE TABLE IF NOT EXISTS Usuario_Rutina (
    usuarioId INT,
    rutinaId INT,
    PRIMARY KEY (usuarioId, rutinaId),
    FOREIGN KEY (usuarioId) REFERENCES Usuario(id),
    FOREIGN KEY (rutinaId) REFERENCES Rutina(id)
);