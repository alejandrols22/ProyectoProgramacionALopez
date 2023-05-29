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
    caloriasProteinas FLOAT,
    caloriasCarbohidratos FLOAT,
    caloriasGrasas FLOAT,
    alimentos JSON,
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




-- INSERCIONES

USE programaciondb4;
SELECT Usuario.id, Usuario.edad, Usuario.peso, Usuario.altura, Usuario.sexo, Usuario.nivel_actividad, Usuario.objetivo, Usuario.objetivo_diario_calorias, Usuario.email, Usuario.contraseña, Usuario.telefono, Entidad.nombre AS entidad_nombre
FROM Usuario
JOIN Entidad ON Usuario.entidadId = Entidad.id;




SELECT re.*, ent.nombre, ej.categoria 
FROM Rutina_Ejercicio re
JOIN Ejercicio ej ON re.ejercicioId = ej.id
JOIN Entidad ent ON ej.entidadId = ent.id
LIMIT 0, 1000;


ALTER TABLE Ejercicio
ADD COLUMN gif_path VARCHAR(255);
SELECT re.*, ent.nombre, ej.categoria 
FROM Rutina_Ejercicio re
JOIN Ejercicio ej ON re.ejercicioId = ej.id
JOIN Entidad ent ON ej.entidadId = ent.id
LIMIT 0, 1000;
SELECT a.id, e.nombre AS entidad, a.cantidad, a.calorias, a.tipo 
FROM Alimento a 
JOIN Entidad e ON a.entidadId = e.id;
UPDATE Alimento
SET cantidad = 100,
    calorias = ROUND(calorias * (100 / cantidad), 2);



INSERT INTO Entidad (nombre) 
VALUES 
    ('Estiramiento de gato'),
    ('Estiramiento de cobra'),
    ('Postura del niño'),
    ('Postura del perro mirando hacia abajo'),
    ('Postura de la mariposa'),
    ('Estiramiento de piernas'),
    ('Estiramiento de hombros'),
    ('Estiramiento de isquiotibiales'),
    ('Estiramiento de cuádriceps'),
    ('Postura del árbol');

INSERT INTO Ejercicio (entidadId, calorias_quemadas_por_minuto, duracion, categoria) 
VALUES 
    ((SELECT id FROM Entidad WHERE nombre = 'Estiramiento de gato'), 3, 10, 'FLEXIBILIDAD'),
    ((SELECT id FROM Entidad WHERE nombre = 'Estiramiento de cobra'), 4, 10, 'FLEXIBILIDAD'),
    ((SELECT id FROM Entidad WHERE nombre = 'Postura del niño'), 2, 15, 'FLEXIBILIDAD'),
    ((SELECT id FROM Entidad WHERE nombre = 'Postura del perro mirando hacia abajo'), 4, 15, 'FLEXIBILIDAD'),
    ((SELECT id FROM Entidad WHERE nombre = 'Postura de la mariposa'), 3, 10, 'FLEXIBILIDAD'),
    ((SELECT id FROM Entidad WHERE nombre = 'Estiramiento de piernas'), 3, 15, 'FLEXIBILIDAD'),
    ((SELECT id FROM Entidad WHERE nombre = 'Estiramiento de hombros'), 2, 10, 'FLEXIBILIDAD'),
    ((SELECT id FROM Entidad WHERE nombre = 'Estiramiento de isquiotibiales'), 3, 10, 'FLEXIBILIDAD'),
    ((SELECT id FROM Entidad WHERE nombre = 'Estiramiento de cuádriceps'), 3, 10, 'FLEXIBILIDAD'),
    ((SELECT id FROM Entidad WHERE nombre = 'Postura del árbol'), 2, 15, 'FLEXIBILIDAD');
INSERT INTO Entidad (nombre) 
VALUES 
    ('Burpees'),
    ('Saltos de tijera'),
    ('Mountain Climbers'),
    ('Plancha estatica'),
    ('Flexiones de brazos'),
    ('Elevaciones de rodillas'),
    ('Zancadas'),
    ('Saltos en cuclillas'),
    ('Plancha lateral'),
    ('Crunch abdominal');

INSERT INTO Ejercicio (entidadId, calorias_quemadas_por_minuto, duracion, categoria) 
VALUES 
    ((SELECT id FROM Entidad WHERE nombre = 'Burpees'), 10, 20, 'RESISTENCIA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Saltos de tijera'), 8, 15, 'RESISTENCIA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Mountain Climbers'), 9, 20, 'RESISTENCIA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Plancha estatica'), 5, 30, 'RESISTENCIA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Flexiones de brazos'), 7, 20, 'RESISTENCIA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Elevaciones de rodillas'), 6, 15, 'RESISTENCIA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Zancadas'), 8, 25, 'RESISTENCIA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Saltos en cuclillas'), 9, 15, 'RESISTENCIA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Plancha lateral'), 5, 30, 'RESISTENCIA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Crunch abdominal'), 7, 20, 'RESISTENCIA');
INSERT INTO Entidad (nombre) 
VALUES 
    ('Sentadillas'),
    ('Press de banca'),
    ('Peso muerto'),
    ('Dominadas'),
    ('Press militar'),
    ('Fondos en paralelas'),
    ('Curl de bíceps'),
    ('Extensiones de tríceps'),
    ('Remo con barra'),
    ('Zancadas con pesas');
INSERT INTO Ejercicio (entidadId, calorias_quemadas_por_minuto, duracion, categoria) 
VALUES 
    ((SELECT id FROM Entidad WHERE nombre = 'Sentadillas'), 8, 30, 'FUERZA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Press de banca'), 9, 35, 'FUERZA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Peso muerto'), 11, 45, 'FUERZA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Dominadas'), 7, 20, 'FUERZA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Press militar'), 8, 30, 'FUERZA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Fondos en paralelas'), 9, 30, 'FUERZA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Curl de bíceps'), 5, 20, 'FUERZA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Extensiones de tríceps'), 5, 20, 'FUERZA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Remo con barra'), 9, 30, 'FUERZA'),
    ((SELECT id FROM Entidad WHERE nombre = 'Zancadas con pesas'), 8, 30, 'FUERZA');


INSERT INTO Ejercicio (entidadId, calorias_quemadas_por_minuto, duracion, categoria)
SELECT
    (SELECT id FROM Entidad WHERE nombre = 'Sentadillas'),
    5.0, -- Calorías quemadas por minuto (puedes ajustar este valor según corresponda)
    10, -- Duración en minutos (puedes ajustar este valor según corresponda)
    'FUERZA' -- Categoría del ejercicio
UNION ALL
SELECT
    (SELECT id FROM Entidad WHERE nombre = 'Flexiones de brazos'),
    4.0, -- Calorías quemadas por minuto (puedes ajustar este valor según corresponda)
    8, -- Duración en minutos (puedes ajustar este valor según corresponda)
    'FUERZA' -- Categoría del ejercicio
UNION ALL
SELECT
    (SELECT id FROM Entidad WHERE nombre = 'Planchas'),
    3.5, -- Calorías quemadas por minuto (puedes ajustar este valor según corresponda)
    5, -- Duración en minutos (puedes ajustar este valor según corresponda)
    'FUERZA' -- Categoría del ejercicio
-- Agrega las demás consultas UNION ALL para los ejercicios restantes
;
;






INSERT INTO Ejercicio (entidadId, calorias_quemadas_por_minuto, duracion, categoria)
SELECT
    (SELECT id FROM Entidad WHERE nombre = 'Sentadillas'),
    5.0, -- Calorías quemadas por minuto (puedes ajustar este valor según corresponda)
    10, -- Duración en minutos (puedes ajustar este valor según corresponda)
    'FUERZA' -- Categoría del ejercicio
UNION ALL
SELECT
    (SELECT id FROM Entidad WHERE nombre = 'Flexiones de brazos'),
    4.0, -- Calorías quemadas por minuto (puedes ajustar este valor según corresponda)
    8, -- Duración en minutos (puedes ajustar este valor según corresponda)
    'FUERZA' -- Categoría del ejercicio
UNION ALL
SELECT
    (SELECT id FROM Entidad WHERE nombre = 'Planchas'),
    3.5, -- Calorías quemadas por minuto (puedes ajustar este valor según corresponda)
    5, -- Duración en minutos (puedes ajustar este valor según corresponda)
    'FUERZA' -- Categoría del ejercicio
-- Agrega las demás consultas UNION ALL para los ejercicios restantes
;
-- Insertar los carbohidratos y las entidades
INSERT INTO Entidad (nombre)
VALUES
    ('Pasta'),
    ('Pan'),
    ('Patata'),
    ('Maíz'),
    ('Avena'),
    ('Cereal'),
    ('Yuca'),
    ('Quinoa'),
    ('Camote'),
    ('Cuscús'),
    ('Galletas'),
    ('Plátano'),
    ('Batata'),
    ('Harina'),
    ('Tortilla'),
    ('Frijoles'),
    ('Lentejas'),
    ('Garbanzos'),
    ('Espinacas');

-- Insertar los alimentos de tipo carbohidrato
SET @entidadId = (SELECT MAX(id) FROM Entidad) - 18;

INSERT INTO Alimento (entidadId, cantidad, calorias, tipo)
VALUES
    (@entidadId, 90, 180, 'Carbohidrato'),
    (@entidadId + 1, 50, 120, 'Carbohidrato'),
    (@entidadId + 2, 120, 160, 'Carbohidrato'),
    (@entidadId + 3, 80, 90, 'Carbohidrato'),
    (@entidadId + 4, 40, 150, 'Carbohidrato'),
    (@entidadId + 5, 60, 200, 'Carbohidrato'),
    (@entidadId + 6, 70, 110, 'Carbohidrato'),
    (@entidadId + 7, 85, 140, 'Carbohidrato'),
    (@entidadId + 8, 75, 170, 'Carbohidrato'),
    (@entidadId + 9, 55, 130, 'Carbohidrato'),
    (@entidadId + 10, 65, 160, 'Carbohidrato'),
    (@entidadId + 11, 45, 100, 'Carbohidrato'),
    (@entidadId + 12, 95, 190, 'Carbohidrato'),
    (@entidadId + 13, 110, 140, 'Carbohidrato'),
    (@entidadId + 14, 80, 120, 'Carbohidrato'),
    (@entidadId + 15, 70, 100, 'Carbohidrato'),
    (@entidadId + 16, 90, 150, 'Carbohidrato'),
    (@entidadId + 17, 100, 180, 'Carbohidrato'),
    (@entidadId + 18, 70, 90, 'Carbohidrato');
   
    -- Insertar las entidades para las proteínas
INSERT INTO Entidad (nombre)
VALUES
    
    ('Carne de res'),
    ('Pescado'),
    ('Huevo'),
    ('Lentejas'),
    ('Garbanzos'),
    ('Tofu'),
    ('Atún'),
    ('Salmón'),
    ('Camarones'),
    ('Yogur'),
    ('Quinoa'),
    ('Chía'),
    ('Carne de cerdo'),
    ('Pavo'),
    ('Soja'),
    ('Queso cottage'),
    ('Filete de soja'),
    ('Cangrejo'),
    ('Leche');

-- Insertar los alimentos de tipo proteína
SET @entidadId = (SELECT MAX(id) FROM Entidad) - 19;

INSERT INTO Alimento (entidadId, cantidad, calorias, tipo)
VALUES
    
    (@entidadId + 1, 120, 250, 'Proteina'),
    (@entidadId + 2, 80, 200, 'Proteina'),
    (@entidadId + 3, 50, 70, 'Proteina'),
    (@entidadId + 4, 90, 160, 'Proteina'),
    (@entidadId + 5, 70, 130, 'Proteina'),
    (@entidadId + 6, 60, 100, 'Proteina'),
    (@entidadId + 7, 110, 180, 'Proteina'),
    (@entidadId + 8, 95, 200, 'Proteina'),
    (@entidadId + 9, 75, 120, 'Proteina'),
    (@entidadId + 10, 100, 90, 'Proteina'),
    (@entidadId + 11, 65, 150, 'Proteina'),
    (@entidadId + 12, 45, 80, 'Proteina'),
    (@entidadId + 13, 85, 170, 'Proteina'),
    (@entidadId + 14, 80, 140, 'Proteina'),
    (@entidadId + 15, 55, 100, 'Proteina'),
    (@entidadId + 16, 70, 120, 'Proteina'),
    (@entidadId + 17, 90, 160, 'Proteina'),
    (@entidadId + 18, 105, 190, 'Proteina'),
    (@entidadId + 19, 115, 220, 'Proteina');
    
    
       INSERT INTO Entidad (nombre)
VALUES
    
    ('Aguacate'),
    ('Nueces'),
    ('Mantequilla'),
    ('Semillas de chía'),
    ('Salmon'),
    ('Arenques'),
    ('Aceitunas'),
    ('Linaza'),
    ('Aceite de oliva'),
    ('Aceite de girasol'),
    ('Almendras'),
    ('Maní'),
    ('Salmón enlatado'),
    ('Aceite de aguacate'),
    ('Aceite de canola'),
    ('Aceite de linaza'),
    ('Mantequilla de almendras'),
    ('Aceite de cacahuete'),
    ('Nueces de Brasil');

-- Insertar los alimentos de tipo grasa
SET @entidadId = (SELECT MAX(id) FROM Entidad) - 19;

INSERT INTO Alimento (entidadId, cantidad, calorias, tipo)
VALUES
    
    (@entidadId + 1, 100, 160, 'Grasa'),
    (@entidadId + 2, 30, 200, 'Grasa'),
    (@entidadId + 3, 10, 100, 'Grasa'),
    (@entidadId + 4, 25, 120, 'Grasa'),
    (@entidadId + 5, 90, 250, 'Grasa'),
    (@entidadId + 6, 75, 180, 'Grasa'),
    (@entidadId + 7, 20, 60, 'Grasa'),
    (@entidadId + 8, 40, 100, 'Grasa'),
    (@entidadId + 9, 35, 140, 'Grasa'),
    (@entidadId + 10, 50, 150, 'Grasa'),
    (@entidadId + 11, 60, 190, 'Grasa'),
    (@entidadId + 12, 45, 180, 'Grasa'),
    (@entidadId + 13, 55, 220, 'Grasa'),
    (@entidadId + 14, 80, 230, 'Grasa'),
    (@entidadId + 15, 30, 80, 'Grasa'),
    (@entidadId + 16, 65, 210, 'Grasa'),
    (@entidadId + 17, 85, 250, 'Grasa'),
    (@entidadId + 18, 70, 170, 'Grasa'),
    (@entidadId + 19, 95, 180, 'Grasa');
    
    
    
    SELECT Alimento.id, Entidad.nombre AS entidad_nombre, Alimento.cantidad, Alimento.calorias, Alimento.tipo
FROM Alimento
JOIN Entidad ON Alimento.entidadId = Entidad.id;

SELECT a.tipo, e.nombre, a.calorias, a.cantidad FROM Alimento a JOIN Entidad e ON a.entidadId = e.id
    
  