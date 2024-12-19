--EJERCICIO 1. -
--1.- Crear una función que devuelva cuántos libros hay de precio mayor que el que
--suministremos como parámetro.
Create database funciones2
Use funciones2

if object_id('libros') is not null
drop table libros;
create table libros(
codigo int identity,
titulo varchar(40),
autor varchar(30),
editorial varchar(20),
precio decimal(5,2)
);
insert into libros values('Alicia en el pais de las maravillas','Lewis
Carroll','Emece',20.00);
insert into libros values('Alicia en el pais de las maravillas','Lewis
Carroll','Plaza',35.00);
insert into libros values('Aprenda PHP','Mario Molina','Siglo XXI',40.00);
insert into libros values('El aleph','Borges','Emece',10.00);
insert into libros values('Ilusiones','Richard Bach','Planeta',15.00);
insert into libros values('Java en 10 minutos','Mario Molina','Siglo XXI',50.00);
insert into libros values('Martin Fierro','Jose Hernandez','Planeta',20.00);
insert into libros values('Martin Fierro','Jose Hernandez','Emece',30.00);
insert into libros values('Uno','Richard Bach','Planeta',10.00);


CREATE FUNCTION ContarLibrosPrecioMayor(@precio decimal(5,2))
RETURNS INT
AS
BEGIN
    DECLARE @CantidadLibros INT;
    SELECT @CantidadLibros = COUNT(*) 
    FROM libros
    WHERE precio > @precio;
    RETURN @CantidadLibros;
END;

select dbo.ContarLibrosPrecioMayor(15)

--2- Crear una función escalar que tenga como parámetros el DNI y la letra del NIF y
--nos valide si es correcta o no. Usar la función con los datos de una tabla que
--contenga nombre, apellidos, fechanacimiento, dni y la letra del nif.
create table personas
( nombre varchar(100),
apellidos varchar(100),
fechanacimiento datetime,
dni char(8),
letra char(1))
set dateformat dmy
insert into personas values
('Juan','Pérez','01/01/1970','56789443','M'),
('María','Hernández','05/06/1985','45678432','L'),
('Ana','Rodríguez','25/10/1991','42001982','A')
go

CREATE FUNCTION validar_nif(@dni char(8), @letra char(1))
RETURNS VARCHAR(50)
AS
BEGIN
    DECLARE @mensaje VARCHAR(50);
    DECLARE @letra_correcta char(1);

    IF LEN(@dni) <> 8 OR PATINDEX('%[0-9]%', @dni) = 0
    BEGIN
        SET @mensaje = 'DNI incorrecto';
    END
    ELSE
    BEGIN
        SET @letra_correcta = CHAR(64 + (CAST(@dni AS INT) % 23));
        
        IF @letra = @letra_correcta
        BEGIN
            SET @mensaje = 'NIF válido';
        END
        ELSE
        BEGIN
            SET @mensaje = 'NIF incorrecto';
        END
    END

    RETURN @mensaje;
END;
SELECT nombre, apellidos, dni, letra, dbo.validar_nif(dni, letra) AS estado_nif
FROM personas;


--3.- Crear una función que nos devuelva los años de diferencia respecto al actual a
--partir de la fecha pasada como parámetro. Usar la función con la tabla anterior.
CREATE FUNCTION calcular_anios_diferencia(@fecha datetime)
RETURNS INT
AS 
BEGIN
    DECLARE @anios_diferencia INT;
    SELECT @anios_diferencia = DATEDIFF(YEAR, @fecha, GETDATE());
    RETURN @anios_diferencia;
END;
SELECT nombre, apellidos, fechanacimiento, dbo.calcular_anios_diferencia(fechanacimiento) AS anios_diferencia
FROM personas;


--4.- Crear función que dada fecha como cadena de caracteres devuelva que no es
--correcta o en caso contrario el nombre del mes.
CREATE FUNCTION validar_fecha(@fecha varchar(10))
RETURNS VARCHAR(20)
AS
BEGIN
    DECLARE @nombre_mes VARCHAR(20);
    IF ISDATE(@fecha) = 0
    BEGIN
        SET @nombre_mes = 'Fecha no válida';
    END
    ELSE
    BEGIN
        SET @nombre_mes = DATENAME(MONTH, @fecha);
    END
    RETURN @nombre_mes;
END;
select dbo.validar_fecha('28 octubre de 2003') as Mes;
select dbo.validar_fecha(2003-10-28) as Mes;
--5.- Crear una función de tabla que devuelva los libros de precio mayor que el que
--suministremos como parámetro.
CREATE FUNCTION LibrosMasCaros(@precio decimal(5,2))
RETURNS TABLE
AS
return
(select * from libros where precio > @precio)

select * from dbo.LibrosMasCaros(15)

--6.- Función que devuelva el máximo precio de la tabla libros
CREATE FUNCTION LibroMasCaro()
RETURNS DECIMAL(5,2)
AS
BEGIN
Declare @Max_precio decimal(5,2)
select @Max_precio=MAX(precio) from libros;
return @Max_precio
end
select dbo.LibroMasCaro() as libroMasCaro;

--7.- Función que devuelva una tabla con el nombre y dni de las personas de dos
--tablas (personal y alumnado), pasando como parámetro: personal (saca sólo los de
--la tabla personal; alumnado (saca sólo los de la tabla alumnado; ambos (saca los de
--ambas tablas).
CREATE FUNCTION obtener_personas(@tabla varchar(10))
RETURNS TABLE
AS
RETURN (
    SELECT nombre, apellidos FROM personal WHERE @tabla = 'personal'
    UNION ALL
    SELECT nom, apell FROM alumnado WHERE @tabla = 'alumnado'
    UNION ALL
    SELECT nombre, apellidos FROM personal WHERE @tabla = 'ambos'
    UNION ALL
    SELECT nom, apell FROM alumnado WHERE @tabla = 'ambos'
);


if object_id ('personal') is not null
drop table personal;
go
if object_id ('alumnado') is not null
drop table alumnado;
go
create table personal
( nombre varchar(100),
apellidos varchar(100))
go
create table alumnado
( nom varchar(50),
apell varchar(50))
insert into personal values
('Juan','Pérez'),
('María','Hernández'),
('Ana','Rodríguez');
insert into alumnado values
('Juana','García'),
('María','Fernández'),
('Pedro','Rodríguez'),
('Marta','García');


--8.- Función que devuelva el nº de días del mes de una fecha pasada como
--parámetro.
CREATE FUNCTION obtener_dias_mes(@fecha date)
RETURNS INT
AS
BEGIN
    DECLARE @dias_mes INT;
    SET @dias_mes = DAY(EOMONTH(@fecha));
    RETURN @dias_mes;
END;
select dbo.obtener_dias_mes('2003-10-28') as DiasMes


--9.- Crear función que valide si una cadena de caracteres es un DNI correcto. Que
--contenga 8 dígitos y una letra y la letra se corresponda con la correcta. Probarlo con
--la tabla personas.
CREATE FUNCTION validar_dni(@dni varchar(9))
RETURNS VARCHAR(30)
AS
BEGIN
    DECLARE @mensaje VARCHAR(30);
    IF LEN(@dni) <> 9 OR PATINDEX('%[0-9]%', LEFT(@dni, 8)) = 0 OR PATINDEX('%[A-Z]%', RIGHT(@dni, 1)) = 0
    BEGIN
        SET @mensaje = 'DNI incorrecto';
    END
    ELSE
    BEGIN
        DECLARE @letra_correcta CHAR(1);
        SELECT @letra_correcta = CHAR(64 + (CAST(LEFT(@dni, 8) AS INT) % 23));
        IF @letra_correcta = RIGHT(@dni, 1)
        BEGIN
            SET @mensaje = 'DNI válido';
        END
        ELSE
        BEGIN
            SET @mensaje = 'DNI incorrecto';
        END
    END
    RETURN @mensaje;
END;


--EJERCICIO 2. -
--Crear un procedimiento almacenado que recorra una tabla con un cursor. Esta tabla
--contendrá en un campo el nombre de la tabla a crear y en otros dos las
--descripciones de los dos campos que compondrán dicha tabla crear. El
--procedimiento deberá crear todas las tablas que se indiquen en TablasCrear.
create table TablasCrear
(Tabla nvarchar(100),
DescripcionCampo1 nvarchar(100),
DescripcionCampo2 nvarchar(100))
go
insert into TablasCrear (Tabla, DescripcionCampo1,
DescripcionCampo2)
values (N'alum',N'DNI char(9)',N'Nombre varchar(50)')
insert into TablasCrear (Tabla, DescripcionCampo1,
DescripcionCampo2)
values (N'libro',N'Titulo varchar(50)',N'Autor varchar(50)')
go

CREATE PROCEDURE CrearTablas
AS
BEGIN
    DECLARE @Tabla nvarchar(100);
    DECLARE @DescripcionCampo1 nvarchar(100);
    DECLARE @DescripcionCampo2 nvarchar(100);
    DECLARE @SQL nvarchar(max);

    DECLARE tabla_cursor CURSOR FOR
    SELECT Tabla, DescripcionCampo1, DescripcionCampo2
    FROM TablasCrear;

    OPEN tabla_cursor;
    FETCH NEXT FROM tabla_cursor INTO @Tabla, @DescripcionCampo1, @DescripcionCampo2;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        SET @SQL = 'CREATE TABLE ' + QUOTENAME(@Tabla) + ' (' +
                   @DescripcionCampo1 + ',' +
                   @DescripcionCampo2 + ');';
        EXEC sp_executesql @SQL;

        FETCH NEXT FROM tabla_cursor INTO @Tabla, @DescripcionCampo1, @DescripcionCampo2;
    END;

    CLOSE tabla_cursor;
    DEALLOCATE tabla_cursor;
END;
exec CrearTablas;
select * from TablasCrear