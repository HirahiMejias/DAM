--EJERCICIO 1.- Un club de barrio almacena los datos de sus socios en una tabla
--llamada "socios", los datos de las inscripciones en una tabla denominada "inscritos"
--y en una tabla "morosos" almacena el documento de los socios inscritos que deben
--matr�cula.
create database TriggerHirahi
Use TriggerHirahi
--1- Elimine las tablas si existen:
if object_id('inscritos') is not null
drop table inscritos;
if object_id('socios') is not null
drop table socios;
if object_id('morosos') is not null
drop table morosos;
--2- Cree las tablas, con las siguientes estructuras:
create table socios(
documento char(8) not null,
nombre varchar(30),
domicilio varchar(30),
constraint PK_socios primary key (documento)
);
create table inscritos(
documento char(8) not null,
deporte varchar(30) not null,
matricula char(1),
constraint CK_inscritos check (matricula in ('s','n')),
constraint PK_inscritos primary key (documento,deporte),
constraint FK_inscritos_documento foreign key(documento)
references socios (documento)
);
create table morosos(
documento char(8) not null
);
--3- Ingrese algunos registros en las tres tablas:
insert into socios values ('22222222','Ana Acosta','Avellaneda 800');
insert into socios values ('23333333','Bernardo Bustos','Bulnes 234');
insert into socios values ('24444444','Carlos Caseros','Colon 321');
insert into socios values ('25555555','Mariana Morales','Maipu 483');
insert into inscritos values ('22222222','tenis','s');
insert into inscritos values ('22222222','natacion','n');
insert into inscritos values ('23333333','tenis','n');
insert into inscritos values ('24444444','tenis','s');
insert into inscritos values ('24444444','futbol','s');
insert into morosos values ('22222222');
insert into morosos values ('23333333');
--4- Cree un disparador de inserci�n que no permita ingresar inscripciones si el socio
--es moroso, es decir, si est� en la tabla "morosos".
if object_id('morosos') is not null
drop trigger NoInscribirSocioMoroso;

create trigger NoInscribirSocioMoroso
on inscritos 
for insert
as
begin
    declare @documento char(8);

    select @documento = documento from inserted;

    if exists (select 1 from morosos where documento = @documento)
    begin
        print 'El socio es moroso y no puede ser inscrito.';
        rollback transaction;
    end
end; 

insert into inscritos values ('22222222', 'futbol', 's');
--5- Realice la inscripci�n de un socio que no deba matr�culas.
--El disparador se ejecut�.
insert into inscritos values ('24444444', 'golf', 's');
--6- Intente inscribir a un socio moroso.
--El trigger se dispar�, mostr� un mensaje y no permiti� la inserci�n.
insert into inscritos values ('23333333', 'ajedrez', 's');
--7- Cree otro "insert trigger" para "inscritos" que ingrese el socio en la tabla
--"morosos" si no paga la matr�cula (si se ingresa 'n' para el campo "matricula").
--Recuerde que podemos crear varios triggers para un mismo evento sobre una
--misma tabla.
if object_id('morosos') is not null
drop trigger MorosoSiNoPagaMatricula ;
create trigger MorosoSiNoPagaMatricula 
on inscritos
for insert 
as 
begin 
    declare @documento char(8);
    declare @matricula char(1);
    
    select @documento = documento, @matricula = matricula from inserted;
    
    if @matricula = 'n'
    begin
        insert into morosos values (@documento);
        print 'El socio no ha pagado matr�cula.';
    end
end; 


insert into inscritos values ('24444444', 'badmintonn', 'n');

--8- Realice la inscripci�n de un socio que no deba matr�culas con el valor 's' para
--"matricula".
insert into inscritos values ('25555555', 'badminton', 's');
--El disparador "dis_inscritos_insertar" se ejecuta y permite la transacci�n; el
--disparador
--"dis_inscritos_insertar2" se ejecuta y permite la transacci�n.

--9- Realice la inscripci�n de un socio que no deba matr�culas con el valor 'n' para
--"matricula".
--El disparador "dis_inscritos_insertar" se ejecuta y permite la transacci�n; el
--disparador
--"dis_inscritos_insertar2" se ejecuta y permite la transacci�n.
insert into inscritos values ('25555555', 'golf', 'n');

--10- Verifique que el disparador "dis_inscritos_insertar2" se ejecut� consultando la
--tabla
--"morosos".
select * from morosos
--11- Realice la inscripci�n de un socio que deba matr�culas con el valor 's' para
--"matricula".
--El disparador "dis_inscritos_insertar" se ejecuta y no permite la transacci�n; el
--disparador
--"dis_inscritos_insertar2" no llega a ejecutarse.
insert into inscritos values ('24444444', 'badmintonnnn', 's');
--12- Realice la inscripci�n de un socio que deba matr�culas con el valor 'n' para
--"matricula".
--El disparador "dis_inscritos_insertar" se ejecuta y no permite la transacci�n; el
--disparador
--"dis_inscritos_insertar2" no llega a ejecutarse.
INSERT INTO inscritos VALUES ('24444444', 'badmintonnnnn', 'n');

--13- Creamos un disparador sobre la tabla "socios" para que no permita ingresar
--nuevos socios. El mismo debe mostrar un mensaje al dispararse y deshacer la
--transacci�n.
-- Elimina el disparador si ya existe
IF OBJECT_ID('NoIngresarNuevoSocio', 'TR') IS NOT NULL
    DROP TRIGGER NoIngresarNuevoSocio;
GO

-- Crea el disparador
CREATE TRIGGER NoIngresarNuevoSocio
ON socios
FOR INSERT
AS
BEGIN
    -- Mostrar mensaje de error
    PRINT 'No se permite ingresar nuevos socios.';
    -- Deshacer la transacci�n para evitar la inserci�n
    ROLLBACK TRANSACTION;
END;


--14- Intente ingresar un nuevo socio.
--El trigger se dispara, muestra el mensaje y deshace la transacci�n.
INSERT INTO socios VALUES ('26666666', 'Elena Esposito', 'Calle Falsa 123');
--15- Actualizar el domicilio de un socio existente.
UPDATE socios SET domicilio = 'Avenida Real 456' WHERE documento = '25555555';
--El trigger no se dispara porque est� definido para el evento "insert".
