
	
	
	
	USE JardinBotanicoBasicas;
	if object_id('Familia') is not null
	drop table Familia;
	go
	CREATE TABLE Familia (
	CodFamilia int primary key,
	Familia varchar(50)not null);
	
	USE JardinBotanicoBasicas;
	if object_id('Planta') is not null
	drop table Planta;
	go
	CREATE TABLE Planta (
	CodPlanta int primary key,
	DescripcionPlanta varchar(50),
	CodFamilia int,
	Precio DECIMAL(6,2))

INSERT Familia (CodFamilia,Familia)
VALUES (1,'CYPERACEAE');
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (1,'Scyrpus Holoschoenus',1,23);
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (2,'Scyrpus lacustris',1,14);
INSERT Familia (CodFamilia,Familia)
VALUES (2,'CUCURBITACEAE');
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (3,'Cucumis melo',2,4);
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (4,'Cucurbita pepo',2,12);
INSERT Familia (CodFamilia,Familia)
VALUES (3,'PINACEAE-ABIETACEAE');
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (5,'Pinus pinea',3,45);
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (6,'Cedrus deodara',3,23.30);
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (7,'Cedrus libani',3,10.40);
INSERT Familia (CodFamilia,Familia)
VALUES (4,'PLATANACEAE');
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (8,'Platanus hispánica',4,12.50);
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (9,'Platanus orientalis ',4,14.30);
INSERT Familia (CodFamilia,Familia)
VALUES (5,'ROSACEAE');
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (10,'Fragaria X ananassa',5,1.05);
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (11,'Prunus pérsica',5,2.30);
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (12,'Prunus avium',5,6.20);
INSERT Familia (CodFamilia,Familia)
VALUES (6,'RUTACEAE');
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (13,'Agave americana',6,20.30);
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (14,'Dracaena indivisa',6,3.20);
INSERT Familia (CodFamilia,Familia)
VALUES (7,'RUBIACEAE');
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (15,'Citrus limón',7,10.20);
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (16,'Citrus sinensis',7,12.30);
INSERT Planta (CodPlanta,DescripcionPlanta,CodFamilia,Precio)
VALUES (17,'Skimmia japonica',7,17.35);
GO

	if object_id('Empleado') is not null
	drop table Empleado;
	go
	CREATE TABLE Empleado (
	DNI char(9) primary key,
	Nombre varchar(100),
	Puesto varchar(20),
	FechaDeNacimiento date,
	NHijos int,)

	USE JardinBotanicoBasicas
	if object_id('Prueba') is not null
	drop table Prueba;
	go
	CREATE TABLE Prueba (
	Id int identity primary key,
	Dato char(20),)

insert prueba (Dato) values('Elemento 1');
insert prueba (Dato) values('Elemento 2');

select * from prueba;

DELETE FROM Prueba where id=2





set identity_insert Prueba on;

insert prueba (id,Dato) values (4,'Elemento 4');

set identity_insert Prueba off;

insert prueba (Dato) values ('Elemento 2');
insert prueba (Dato) values ('Elemento 3');
insert prueba (Dato) values ('Elemento 5');
select * from prueba;

Drop table Prueba;

select * from Prueba;


insert Empleado (DNI, Nombre, Puesto,FechaDeNacimiento,NHijos) values ('32456789H','María','Jefa','27/3/1975','1');
insert Empleado (DNI, Nombre, Puesto,FechaDeNacimiento,NHijos) values ('23456789W','Juan','Técnico','23/4/1968','0');
insert Empleado (DNI, Nombre, Puesto,FechaDeNacimiento,NHijos) values ('45454545J','Ana','Jardinero','21/1/1980','3');
insert Empleado (DNI, Nombre, Puesto,FechaDeNacimiento,NHijos) values ('66565465G','Antonio','Jardinero','23/05/1978','1');

Delete from Empleado where DNI='66565465G';
Select * from Empleado where DNI='66565465G';
update Empleado
set FechaDeNacimiento='21/1/1985'
where DNI='45454545J';