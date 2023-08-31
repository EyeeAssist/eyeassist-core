-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema eyeassist
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rol` (
  `id` BINARY(16) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` BINARY(16) NOT NULL,
  `nombres` VARCHAR(100) NOT NULL,
  `apellidos` VARCHAR(100) NOT NULL,
  `contrasenia` VARCHAR(68) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `id_rol` BINARY(16) NOT NULL,
  `fecha_hora_creacion` DATETIME NOT NULL,
  `fecha_hora_actualizacion` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk-id_rol-usuario-rol_idx` (`id_rol` ASC) VISIBLE,
  CONSTRAINT `fk-id_rol-usuario-rol`
    FOREIGN KEY (`id_rol`)
    REFERENCES `rol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `imagen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `imagen` (
  `id` BINARY(16) NOT NULL,
  `id_usuario` BINARY(16) NOT NULL,
  `hash` CHAR(64) NULL,
  `descripcion` VARCHAR(200) NULL,
  `fecha_hora_creacion` DATETIME NOT NULL,
  `creado_por` BINARY(16) NOT NULL,
  `fecha_hora_actualizacion` DATETIME NOT NULL,
  `actualizado_por` BINARY(16) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk-id_usuario-imagen-usuario_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk-id_usuario-imagen-usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `video` (
  `id` BINARY(16) NOT NULL,
  `id_usuario` BINARY(16) NOT NULL,
  `codigo` VARCHAR(11) NOT NULL,
  `descripcion` JSON NULL,
  `fecha_hora_creacion` DATETIME NOT NULL,
  `creado_por` BINARY(16) NOT NULL,
  `fecha_hora_actualizacion` DATETIME NOT NULL,
  `actualizado_por` BINARY(16) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk-id_usuario-video-usuario_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk-id_usuario-video-usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
