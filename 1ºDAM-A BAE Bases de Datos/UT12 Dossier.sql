--1. Crear la Base de datos en la carpeta C:\SQLDatos con tamaño inicial 5M, tamaño mayor
--20 M e incrementos 4M. – (Si da problemas de escritura al estar en un equipo del aula,
--hacer únicamente un create database nombrebd y pasar al apartado 2)
create database UT12 on (
Name=UT12_dat,
Filename='C:\SQLDatos\UT12_dat.mdf',
Size=5MB,
MAXSIZE=20MB,
FILEGROWTH=4MB);

Create database Ut12
--2. Crear una tabla con la estructura siguiente:
drop table empleados
create table empleados
( DNI varchar(8) not null,
nombre varchar(30),
apellidos varchar(30),
fechanacimiento datetime,
cantidadhijos tinyint,
seccion varchar(20),
sueldo decimal(6,2) 
)

--3. Crear las sentencias para que valide lo siguiente:
--a. clave primaria DNI
alter table empleados add constraint PK_empleados primary key (DNI) ;
--b. no nulo apellidos
alter table empleados add constraint apellidos check (apellidos is not null)
--c. no nulo nombre
alter table empleados add constraint chk_nombre check (nombre is not null)
--d. valor único apellidos y nombre
alter table empleados add constraint unique_apellidosynombre unique(nombre,apellidos)
--e. validar que fechanacimiento sea menor que la fecha actual
ALTER TABLE empleados
DROP CONSTRAINT fechanacimiento;

-- Drop the existing column (if it exists)
ALTER TABLE empleados
DROP COLUMN fechanacimiento

alter table empleados add constraint FechaNacimientoMenorActual check (fechanacimiento<getdate())
--f. validar que cantidad de hijos no sea negativa ni mayor que 20
alter table empleados add constraint CHK_Hijos check ((cantidadhijos<20) and (cantidadhijos>0))
--g. validar que sección no esté vacío
alter table empleados add constraint CHK_Seccion check (seccion is not null)
--4. Ver los índices que tiene.
exec sp_helpindex empleados
--5. Añadir índice por fecha de nacimiento
create index ix_FechaNacimiento on empleados(fechanacimiento)
--6. Añadir índice por sueldo
create index ix_Sueldo on empleados(sueldo)
--7. Modificar lo siguiente en la tabla
--a. Añadir campo dirección varchar(100)
alter table empleados add direccion varchar(100)
select * from empleados
--b. Cambiar a no nulo seccion
alter table empleados alter column seccion varchar(20) not null
--c. Validar que sueldo sean >0 y <10000
--alter table empleados drop constraint chk_sueldo_range;
alter table empleados with check add constraint chk_sueldo_range check (sueldo>0 and sueldo <10000)
exec sp_helpindex empleados

-- Inserción que viola la restricción de no nulo en 'apellidos'
insert into empleados (DNI, nombre, fechanacimiento, cantidadhijos, seccion, sueldo, direccion)
values ('12345678', 'Juan','1990-15-05', 2, 'Ventas', 2500.00, 'Calle Principal 123');




-- Inserción que viola la restricción de no nulo en 'nombre'
 insert into empleados (DNI, apellidos, fechanacimiento, cantidadhijos, seccion, sueldo, direccion)
  values ('23456789', 'Perez', '1995-20-10', 1, 'Recursos Humanos', 3000.00, 'Avenida Central 456');

---- Inserción que viola la restricción de clave primaria en 'DNI'
insert into empleados (DNI, nombre, apellidos, fechanacimiento, cantidadhijos, seccion, sueldo, direccion)
values ('12345678', 'Pedro', 'Gomez', '1988-03-10', 2, 'Administración', 2800.00, 'Calle Secundaria 789');

---- Inserción que viola la restricción de valor único en 'apellidos' y 'nombre'
insert into empleados (DNI, nombre, apellidos, fechanacimiento, cantidadhijos, seccion, sueldo, direccion)
values ('34567890', 'Juan', 'Perez', '1992-20-07', 3, 'Logística', 3200.00, 'Calle Principal 234');

-- Inserción que viola la restricción de fecha de nacimiento menor que la fecha actual
insert into empleados (DNI, nombre, apellidos, fechanacimiento, cantidadhijos, seccion, sueldo, direccion)
values ('45678901', 'Luis', 'Martinez', '2030-01-01', 2, 'Ventas', 2700.00, 'Avenida Norte 567');

---- Inserción que viola la restricción de cantidad de hijos no negativa
insert into empleados (DNI, nombre, apellidos, fechanacimiento, cantidadhijos, seccion, sueldo, direccion)
values ('56789012', 'Ana', 'Garcia', '1985-25-12', 0, 'Recursos Humanos', 3100.00, 'Avenida Sur 890');

---- Inserción que viola la restricción de cantidad de hijos no mayor que 20
--insert into empleados (DNI, nombre, apellidos, fechanacimiento, cantidadhijos, seccion, sueldo, direccion)
--values ('67890123', 'David', 'Lopez', '1977-09-10', 25, 'Logística', 2900.00, 'Calle Este 123');

-- Inserción que viola la restricción de no nulo en 'seccion'
insert into empleados (DNI, nombre, apellidos, fechanacimiento, cantidadhijos,seccion,sueldo, direccion)
values ('78901234', 'Elena', 'Rodriguez', '1982-06-12', 1, 4000.00, 'Calle Oeste 456');

--Inserción que viola la restricción de sueldo >0 y <10000
insert into empleados (DNI, nombre, apellidos, fechanacimiento, cantidadhijos, seccion, sueldo, direccion)
values ('89012345', 'Carlos', 'Sanchez', '1998-03-05', 1, 'Administración', 10000.00, 'Avenida Oeste 789');


-- inserción que viola la restricción de no nulo en 'seccion
