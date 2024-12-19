--1 crear base de datos
create database Facturas
go
use Facturas
go
--2 crear tabla FAC_T_Articulos
if object_id('FAC_T_Articulo') is not null
drop table FAC_T_Articulo;
go
create table FAC_T_Articulo
(
CodArticulo integer primary key,
NombreArticulo varchar(50) not null unique,
TipoArticulo varchar(50),
PrecioActual numeric(10,2) not null
);
--3 crear tabla FAC_T_Cliente
if object_id('FAC_T_Cliente') is not null
drop table FAC_T_Cliente;
go
create table FAC_T_Cliente
(
CodCliente integer primary key,
NombreCliente varchar(60) not null,
DatosCliente varchar(60) default 'Desconocido',
Municipio varchar(50),
FechaAlta datetime default getdate(),
FechaNacimiento datetime null
);
--cargar datos
set dateformat dmy
go
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 1,'Antonio','C/uno nº 3','S/C Tenerife','01/03/2012','15/04/1970')
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 2,'Juan','C/la hornera nº 7','S/C Tenerife' ,'22/05/2012','29/06/1982' )
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 3,'María','C/el pino nº 7','La Laguna','22/05/2010','15/06/1960')
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 7,'Ana','C/el monte nº 6','S/C Tenerife','15/10/2012','26/12/1963')
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 12,'Juana','C/la estaca nº 77','S/C Tenerife','23/05/2009','15/12/1963')
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 22,'Los Espacios S.L.','Rambla nº 17','S/C Tenerife','15/04/2012',null)
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 33,'La Reserva S.A.','Puerto nº 66','S/C Tenerife','23/12/2011',null)
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 66,'TITSA','Intercambiador','La Laguna','14/08/2012',null)
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 99,'Contado','Sin datos','S/C Tenerife','23/1/2010',null)
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 80,'Juana María','C/La hoguera 23','S/C Tenerife','23/10/2010','26/12/1963')
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 81,'Fernando','Av Los Majuelos 7','S/C Tenerife','15/1/2010','2/11/1968')
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 82,'Isabel','Finca España','La Laguna','17/12/2011','14/5/1975')
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 83,'Ana Luisa','C/la una nº 34','S/C Tenerife','24/6/2012','26/05/1950')
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 84,'Francisco Javier',default,'Tacoronte','15/7/2010',null)
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 85,'María Luisa','C/La laguna nº 77','S/C Tenerife','18/6/2011',null)
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 86,'Antonio Juan',default,'S/C Tenerife','19/1/2010','12/12/1980')
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 87,'José',default,'Adeje','3/12/2011',null)
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 88,'Mauricio',default,'S/C Tenerife','14/8/2012','15/06/1980')
insert FAC_T_Cliente (
CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento )
values ( 89,'Elena','Sin datos','La Laguna','23/1/2010',null)
go
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
22,'llave ajustable 200mm','Herramienta',12.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
25,'llave allen 1.5','Herramienta',6.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
28,'llave combinada 6','Herramienta',5.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
36,'martillo bellota','Herramienta',10.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
37,'martillo ebanista','Herramienta especializada',13.20 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
39,'destornillador plano','Herramienta',1.55 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
40,'destornillador philips','Herramienta',2.25 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
46,'tenaza','Herramienta especializada',2.34 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
50,'mordaza bloqueable','Accesorios',10.25 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
60,'alicate pelacables','Herramienta especializada',10.15 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
75,'alicate corte diagonal','Herramienta especializada',13.20 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
80,'taladro sin cable especial','Herramienta eléctrica',102.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
90,'taladro atornillador sin cable','Herramienta eléctrica',145.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
98,'taladro con cable','Herramienta eléctrica',76.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
105,'destornillador eléctrico sin cable','Herramienta eléctrica',80.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
110,'sierra de calar','Herramienta eléctrica',12.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
120,'sierra circular','Herramienta eléctrica',112.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
150,'lijadora orbital','Herramienta eléctrica',110.00 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
300,'tornillo 3mm','Tornillería',0.20 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
310,'tuerca 3mm','Tornillería',0.10 )
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual ) values (
888,'tornillo redondo','Tornillería',23.50 )
go
--4.- Insertar una fila en la tabla FAC_T_Articulo
--CodArticulo: 205
--NombreArticulo: Sierra circular especial
--TipoArticulo: Herramienta eléctrica
--PrecioActual: 158,50
insert FAC_T_Articulo ( CodArticulo,NombreArticulo,TipoArticulo,PrecioActual )
values ( 205,'Sierra circular especial','Herramienta eléctrica',158.50 );
--5.- Insertar una fila en la tabla FAC_T_Cliente
--CodCliente: 45
--NombreCliente: Laura González González
--DatosCliente: C/La Marina nº 3
--Municipio: S/C Tenerife
--FechaAlta: valor por defecto
--FechaNacimiento: 25/09/1990

INSERT INTO FAC_T_Cliente (CodCliente, NombreCliente, DatosCliente, Municipio, FechaAlta, FechaNacimiento)
VALUES ('45', 'Laura González González', 'C/La Marina nº 3', 'S/C Tenerife', default, 1990-09-25);


--6.- Modificar el precio de los artículos con precio menor o igual que 5 euros, incrementándolos un 10% su valor. 

use Facturas
select CodArticulo,NombreArticulo,TipoArticulo,PrecioActual
from FAC_T_Articulo
update FAC_T_Articulo
set PrecioActual=PrecioActual * 1.10
where PrecioActual<=5
go

--7.- Modificar la Direccion y la fecha de nacimiento del cliente Laura González González. --Los datos nuevos serán: 
--DatosCliente: C/Esmeralda nº 7 
--FechaNacimiento: 29/09/1990
select CodCliente,NombreCliente,DatosCliente,Municipio,FechaAlta,FechaNacimiento
from FAC_T_Cliente
update FAC_T_Cliente
set DatosCliente='C/Esmeralda nº 7',FechaNacimiento='29/09/1990'
where NombreCliente='Laura González González'
go

--8.- Borrar todos los artículos de precio menos que 1 euro. 
delete from FAC_T_Articulo
where PrecioActual<1

--9.- Mostrar nombre y precio de todos los artículos. 
select NombreArticulo,PrecioActual
from FAC_T_Articulo

--10.- Mostrar Nombre y Datos de los clientes que cumplen años en junio, ordenados por el número de años que cumplen de manera ascendente, 
--mostrando además la fecha de nacimiento en formato dia, mes y año y el nº de años que cumple. 

select NombreCliente,DatosCliente,convert (varchar,FechaNacimiento,103),DATEDIFF (year,FechaNacimiento,GETDATE())as años
from FAC_T_Cliente
where month (FechaNacimiento)=6 
order by años asc

--11.- Mostrar código, nombre y precio de los artículos de precio entre 10 y 50 euros (ambos inclusive), ordenados por el precio de manera descendente. 
use Facturas
select CodArticulo,NombreArticulo,PrecioActual
from FAC_T_Articulo
where PrecioActual between 10 and 50
order by PrecioActual desc
go

--12.- Mostrar clientes dados de alta en enero, marzo, abril o junio. 
select CodCliente,NombreCliente,FechaAlta
from FAC_T_Cliente
where month(fechaAlta)in (1,3,4,5)
go

--13.- Mostrar los artículos que contengan un número en su nombre.
select CodArticulo,NombreArticulo,PrecioActual
from FAC_T_Articulo
where NombreArticulo like '%[1-9}%'
go

 --14.- Mostrar en un único campo el codcliente unido con dos guiones y detrás el nombre completo de los clientes, denominando a la columna datos cliente, junto al año de alta del mismo. 
 select concat (Codcliente,'--',NombreCliente) as CodNomCliente, year (FechaAlta) as AñoAlta
 from Fac_t_cliente
 go

 --15.- Mostrar los clientes que cumplen años de ser clientes en mayo y que tengan fecha de nacimiento. 
 select CodCliente,NombreCliente,FechaAlta
 from FAC_T_Cliente
 where month(fechaAlta)=5 and FechaNacimiento is not null
 go

 --16-.- Mostrar todos los artículos con nombre que contenga 'destornillador' y de precio superior a 2 euros. 
 select CodArticulo,NombreArticulo,PrecioActual
 from FAC_T_Articulo
 where NombreArticulo like '%destornillador%' and PrecioActual>2
 go

 --17.- Mostrar los clientes cuyo mes de alta sea enero, marzo o mayo 
 select CodCliente,NombreCliente,FechaAlta
 from FAC_T_Cliente
 where month(fechaalta)in (1,3,5)
 go

 --18.- Mostrar el nombrecompleto, codcliente y meses de antigüedad a día de hoy de todos los clientes, ordenados por nombrecompleto.
select CodCliente,NombreCliente,datediff (month,FechaAlta,GETDATE()) as MesesAntiguedad
from FAC_T_Cliente
order by NombreCliente
go

 --19.- Mostrar el nombrecompleto el día de la semana en que se dio de alta cada cliente, ordenados por la fecha de alta de forma ascendente.
select NombreCliente,Fechaalta,datename(dw,FechaAlta) as DiaDeAlta
from FAC_T_Cliente
order by FechaAlta 

 --20.- Mostrar la estructura de la tabla clientes. 
 sp_columns FAC_T_Cliente

 --21.- Contar cuántos clientes tenemos. 
 select count(*)as clientes
 from FAC_T_Cliente
 go

 --22.- Contar cuántos clientes tenemos con fecha de nacimiento. 

  select count(*)as clientes
 from FAC_T_Cliente
 where FechaNacimiento is not null
 go

 --23.- Media del precio de los artículos que su tipo contenga Herramienta
 select avg (PrecioActual) as PrecioMedio
 from FAC_T_Articulo
 where TipoArticulo like '%herramienta%'
 go

  --24.- Mostrar ordenados los Tipos de artículo que tenemos.
select TipoArticulo
from FAC_T_Articulo
group by TipoArticulo
order by TipoArticulo
go

 --25.- Contar cuántos artículos tenemos de cada tipo de artículo, ordenados por el nº de artículos de mayor a menor. 
select tipoArticulo,count(NombreArticulo)
from FAC_T_Articulo
group by TipoArticulo
order by count(NombreArticulo) desc