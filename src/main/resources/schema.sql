-- =============================================
-- Script de creación de la base de datos
-- Gestión de Usuarios - Arquitectura Hexagonal
-- =============================================

CREATE DATABASE IF NOT EXISTS crud_usuarios
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE crud_usuarios;

CREATE TABLE IF NOT EXISTS users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        ENUM('ADMIN', 'MEMBER', 'REVIEWER') NOT NULL,
    status      ENUM('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED') NOT NULL DEFAULT 'PENDING',
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Usuario administrador inicial (password: Admin1234!)
INSERT INTO users (id, name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@example.com',
    '$2a$12$sfwkajsls.fktQb0Ngw5cOiSiV3z4LIwbucSAy4sw2wyNlxbUBt4q',
    'ADMIN',
    'ACTIVE'
);


CREATE TABLE IF NOT EXISTS contratos (
                                         id              INT AUTO_INCREMENT PRIMARY KEY,
                                         id_usuario      VARCHAR(36)    NOT NULL,
                                         empresa         VARCHAR(150)   NOT NULL,
                                         empleado        VARCHAR(150)   NOT NULL,
                                         funciones       TEXT           NOT NULL,
                                         monto           DECIMAL(15, 2) NOT NULL,
                                         frecuencia_pago ENUM('SEMANAL', 'QUINCENAL', 'MENSUAL', 'SEMESTRAL', 'ANUAL') NOT NULL,
                                         fecha_firma     DATE           NOT NULL,
                                         fecha_inicio    DATE           NOT NULL,
                                         fecha_fin       DATE           NOT NULL,
                                         estado          ENUM('VIGENTE', 'VENCIDO') NOT NULL DEFAULT 'VIGENTE',
                                         created_at      DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         updated_at      DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                         CONSTRAINT fk_contratos_usuario FOREIGN KEY (id_usuario) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

