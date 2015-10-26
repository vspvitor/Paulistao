CREATE DATABASE Paulistao
USE Paulistao
DROP DATABASE Paulistao

-------------------------------------------@CRIAÇÃO E INSERÇÃO DE DADOS NA  TABELA TIMES@-----------------------------------------------
DROP TABLE Times

CREATE TABLE Times(
codigoTime INT PRIMARY KEY IDENTITY,
nomeTime VARCHAR(50) UNIQUE NOT NULL,
cidade VARCHAR(50) NOT NULL,
estadio VARCHAR(50) NOT NULL)

INSERT INTO Times VALUES
('Audax', 'São Paulo', 'José Liberatti'),
('Botafogo', 'Ribeirão Preto', 'Santa Cruz'),
('Bragantino', 'Bragança Paulista', 'Nabi Abi Chedid'),
('Capivariano', 'Capivari', 'Arena Capivari'),
('Corinthians', 'São Paulo', 'Arena Corinthians'),
('Ituano', 'Itu', 'Novelli Júnior'),
('Linense', 'Lins', 'Gilberto Siqueira Lopes'),
('Marília', 'Marília', 'Bento de Abreu'),
('Mogi Mirim', 'Mogi Mirim', 'Vail Chaves'),
('Palmeiras', 'São Paulo', 'Allianz Parque'),
('Penapolense', 'Penápolis', 'Tenente Carriço'),
('Ponte Preta', 'Campinas', 'Moisés Lucarelli'),
('Portuguesa', 'São Paulo', 'Canindé'),
('Red Bull Brasil', 'Campinas', 'Moisés Lucarelli'),
('Rio Claro', 'Rio Claro', 'Augusto Schmidt Filho'),
('Santos', 'Santos', 'Vila Belmiro'),
('São Bento', 'Sorocaba', 'Walter Ribeiro'),
('São Bernardo', 'São Bernardo do Campo', 'Primeiro de Maio'),
('São Paulo', 'São Paulo', 'Morumbi'),
('XV de Piracicaba', 'Piracicaba', 'Barão de Serra Negra')

SELECT * FROM Times

-------------------------------------------@CRIAÇÃO DA TABELA GRUPOS@---------------------------------------------------
DROP TABLE Grupos

CREATE TABLE Grupos(
codigoGrupo INT,
grupo CHAR(1) CHECK(grupo = 'A' OR
grupo = 'B' OR
grupo = 'C' OR
grupo = 'D'),
codigoTime INT NOT NULL,
PRIMARY KEY (codigoGrupo, codigoTime),
FOREIGN KEY (codigoTime) REFERENCES Times (codigoTime)
)

SELECT * FROM Grupos

-------------------------------------------@CRIAÇÃO DA TABELA JOGOS@---------------------------------------------------
DROP TABLE Jogos

TRUNCATE TABLE Jogos

CREATE TABLE Jogos(
codigoJogo INT IDENTITY,
codigoTimeA INT NOT NULL,
codigoTimeB INT NOT NULL,
golsTimeA INT,
golsTimeB INT,
data DATETIME NOT NULL,
PRIMARY KEY (codigoJogo, codigoTimeA, codigoTimeB)
)

SELECT * FROM Jogos

-------------------------------------------@PROCEDURE DE SORTEIO DOS DEMAIS TIMES@---------------------------------------------------
/*
Essa procedure só pode ser executada depois da execução da procedure sp_cabecadegrupo (será executada dentro da sp_cabecadegrupo),
porém deve ser criada antes. 
*/
	
	CREATE PROCEDURE sp_sorteio
	AS
	DECLARE @codigo INT
	SET @codigo = 1
	
	WHILE ( (SELECT COUNT(*) FROM Grupos WHERE codigoGrupo = 1 ) < 5 )
	BEGIN
		SET @codigo = (SELECT TOP 1 codigoTime FROM Times ORDER BY NEWID())
		
		IF NOT EXISTS(SELECT codigoTime FROM Grupos WHERE codigoTime = @codigo)
		INSERT INTO Grupos VALUES
		(1,'A', @codigo)
	END
	
	WHILE ( (SELECT COUNT(*) FROM Grupos WHERE codigoGrupo = 2 ) < 5 )
	BEGIN
		SET @codigo = (SELECT TOP 1 codigoTime FROM Times ORDER BY NEWID())
		
		IF NOT EXISTS(SELECT codigoTime FROM Grupos WHERE codigoTime = @codigo)
		INSERT INTO Grupos VALUES
		(2,'B', @codigo)
	END
	
	WHILE ( (SELECT COUNT(*) FROM Grupos WHERE codigoGrupo = 3 ) < 5 )
	BEGIN
		SET @codigo = (SELECT TOP 1 codigoTime FROM Times ORDER BY NEWID())
		
		IF NOT EXISTS(SELECT codigoTime FROM Grupos WHERE codigoTime = @codigo)
		INSERT INTO Grupos VALUES
		(3,'C', @codigo)
	END
	
	WHILE ( (SELECT COUNT(*) FROM Grupos WHERE codigoGrupo = 4 ) < 5 )
	BEGIN
		SET @codigo = (SELECT TOP 1 codigoTime FROM Times ORDER BY NEWID())
		
		IF NOT EXISTS(SELECT codigoTime FROM Grupos WHERE codigoTime = @codigo)
		INSERT INTO Grupos VALUES
		(4,'D', @codigo)
		
	END
		

	DROP PROCEDURE sp_sorteio
	EXEC sp_sorteio
	
-------------------------------------------@SORTEIO DO CABEÇA DE GRUPO@---------------------------------------------------
	
	CREATE PROCEDURE sp_cabecadegrupo
	AS
	DISABLE TRIGGER t_grupos ON Grupos
	DECLARE @random INT,
			@grupo CHAR(1),
			@aux INT,
			@cont INT
			SET @cont = 1
		
		WHILE (SELECT COUNT(*) FROM Grupos) < 4 AND @cont < 5
		BEGIN
			
			SET @random = CAST(RAND() * 5 AS INT)
			IF @random = 0
			SET @random = 1
			SET @grupo = 'A'
			IF @random = 1
			SET @grupo = 'A'
			IF @random = 2
			SET @grupo = 'B'
			IF @random = 3
			SET @grupo = 'C'
			IF @random = 4
			SET @grupo = 'D'
				IF @cont = 1
				BEGIN
					IF NOT EXISTS(SELECT codigoTime FROM Grupos WHERE codigoTime = 16)
						IF NOT EXISTS(SELECT codigoGrupo FROM Grupos WHERE codigoGrupo = @random)
						INSERT INTO Grupos VALUES(@random, @grupo, 16)
					SET @cont = @cont + 1
				END
				IF @cont = 2
				BEGIN
					IF NOT EXISTS(SELECT codigoTime FROM Grupos WHERE codigoTime = 10)
						IF NOT EXISTS(SELECT codigoGrupo FROM Grupos WHERE codigoGrupo = @random)
						BEGIN
							INSERT INTO Grupos VALUES(@random, @grupo, 10)
							SET @cont = @cont + 1
						END
						ELSE 
						SET @cont = 2
				END
				IF @cont = 3
				BEGIN
					IF NOT EXISTS(SELECT codigoTime FROM Grupos WHERE codigoTime = 5)
						IF NOT EXISTS(SELECT codigoGrupo FROM Grupos WHERE codigoGrupo = @random)
						BEGIN
							INSERT INTO Grupos VALUES(@random, @grupo, 5)
							SET @cont = @cont + 1
						END
						ELSE
						SET @cont = 3
				END
				IF @cont = 4
				BEGIN
					IF NOT EXISTS(SELECT codigoTime FROM Grupos WHERE codigoTime = 19)
						IF NOT EXISTS(SELECT codigoGrupo FROM Grupos WHERE codigoGrupo = @random)
						BEGIN
							INSERT INTO Grupos VALUES(@random, @grupo, 19)
							SET @cont = @cont + 1
						END
						ELSE
						SET @cont = 4
				END
		END
		EXEC sp_sorteio
	
		
	DROP PROCEDURE sp_cabecadegrupo
	EXEC sp_cabecadegrupo
	
	
	SELECT * FROM Grupos
		
	SELECT CAST(RAND() * 5 AS INT)
-------------------------------------------@PESQUISAS@---------------------------------------------------
SELECT  codigoGrupo,grupo, nomeTime FROM Times
INNER JOIN Grupos
ON Times.codigoTime = Grupos.codigoTime
ORDER BY codigoGrupo, grupo, NEWID()

-- PESQUISA GRUPO 1/A
SELECT  codigoGrupo,grupo, nomeTime FROM Times
INNER JOIN Grupos
ON Times.codigoTime = Grupos.codigoTime
WHERE codigoGrupo = 1
ORDER BY codigoGrupo, grupo

-- PESQUISA GRUPO 2/B
SELECT  codigoGrupo,grupo, nomeTime FROM Times
INNER JOIN Grupos
ON Times.codigoTime = Grupos.codigoTime
WHERE codigoGrupo = 2
ORDER BY codigoGrupo, grupo, NEWID()

-- PESQUISA GRUPO 3/C
SELECT  codigoGrupo,grupo, nomeTime FROM Times
INNER JOIN Grupos
ON Times.codigoTime = Grupos.codigoTime
WHERE codigoGrupo = 3
ORDER BY codigoGrupo, grupo, NEWID()

-- PESQUISA GRUPO 4/D
SELECT  codigoGrupo,grupo, nomeTime FROM Times
INNER JOIN Grupos
ON Times.codigoTime = Grupos.codigoTime
WHERE codigoGrupo = 4
ORDER BY codigoGrupo, grupo, NEWID()

-----------------------------------------@GERA OS JOGOS APARTIR DOS GRUPOS@-----------------------------------------------
/*
Essa prodecure só pode ser executada após a definição do cabeça de grupo e do resto dos grupos.
*/

CREATE PROCEDURE sp_gerajogos
AS
	DISABLE TRIGGER t_jogos ON Jogos
	DECLARE @data DATE,
			-- Time 1
			@codigoTimeA INT,
			
			-- Time 2
			@codigoTimeB INT,
			
			-- Contador de jogos
			@cont INT,
			
			/*Se ocorrer algum erro, durante
			 a formação do grupo será adicionado + 1 à essa variável,
			 caso tantas vezes esse contador for acionado ele adicionará
			 na variavel @contErroCritico
			  */
			@contErro INT,
			/* Se ocorrer algum erro, durante
			 a formação do grupo será adicionado + 1 à essa variável,
			 caso tantas vezes esse contador for acionado ele deletará a tabela
			 e começará todo o processo novamente */
			@contadorErroCritico INT,
			
			-- armazena o tamanho do id atual
			@resetaId INT
		
		SET @data='2015-02-01'
		SET @cont = 0
		SET @contErro = 0
		SET @contadorErroCritico = 0

WHILE(@data != '2015-03-23')
BEGIN
	SET @codigoTimeA = (SELECT CONVERT(INT, 1 + (21-1)*RAND()))
	SET @codigoTimeB = (SELECT CONVERT(INT, 1 + (21-1)*RAND()))
	IF(@codigoTimeA != @codigoTimeB)
	BEGIN
		IF((SELECT DATEPART(WEEKDAY, @data)) = 4 OR
			(SELECT DATEPART(WEEKDAY, @data))= 1)
		BEGIN
			IF(@cont<10)
			BEGIN
				SET @contErro = @contErro + 1
				IF(@contErro = 300)
				BEGIN
					DELETE Jogos WHERE data=@data
					SET @resetaId = (SELECT COUNT(codigoJogo) FROM jogos)
					DBCC CHECKIDENT ('Jogos', RESEED, @resetaId) 
					SET @cont = 0
					SET @contErro = 0
					SET @contadorErroCritico = @contadorErroCritico+ 1
				END

				IF(@contadorErroCritico = 500)
				BEGIN
					DELETE Jogos
					SET @resetaId = (SELECT COUNT(codigoJogo) FROM jogos)
					DBCC CHECKIDENT ('Jogos', RESEED, @resetaId)
					SET @data = '2015-02-01'
					SET @contadorErroCritico = 0
					SET @cont = 0
					SET @contErro = 0
				END

				IF(((SELECT grupo FROM Grupos WHERE codigoTime = @codigoTimeA)
				 != (SELECT grupo FROM Grupos WHERE codigoTime = @codigoTimeB)))
				BEGIN
					IF(NOT EXISTS(SELECT * FROM Jogos 
					WHERE codigoTimeA=@codigoTimeA AND data=@data))
					BEGIN	
						IF(NOT EXISTS(SELECT * FROM Jogos 
						WHERE codigoTimeA=@codigoTimeB AND data=@data))
						BEGIN
							IF(NOT EXISTS(SELECT * FROM Jogos
							WHERE codigoTimeB=@codigoTimeA AND data=@data))
							BEGIN
								IF(NOT EXISTS(SELECT * FROM Jogos
								WHERE codigoTimeB=@codigoTimeB AND data=@data))
								BEGIN
									IF(NOT EXISTS(SELECT * FROM Jogos
									WHERE (codigoTimeA=@codigoTimeB AND codigoTimeB=@codigoTimeA)))
									BEGIN
										IF(NOT EXISTS(SELECT * FROM Jogos
										WHERE codigoTimeA=@codigoTimeA AND codigoTimeB=@codigoTimeB))
										BEGIN	
											-- ADICIONANDO DADOS NA TABELA JOGOS				
											INSERT INTO Jogos (codigoTimeA, codigoTimeB, data)
											 VALUES (@codigoTimeA, @codigoTimeB, @data)
											SET @cont = @cont + 1
										END
									END
								END
							END
						END
					END	
				END
			END
			ELSE
			BEGIN
				SET @data = (SELECT DATEADD(DAY, 1, @data))
				SET @cont = 0
			END
		END
		ELSE
		BEGIN
			SET @data = (SELECT DATEADD(DAY, 1, @data))
		END
	END
END

DROP PROCEDURE sp_gerajogos

EXEC sp_gerajogos 

SELECT * FROM Jogos

-- Pesquisa por data

SELECT T1.nomeTime AS Time_1, golsTimeA AS Gols_time_1,
golsTimeB AS Gols_time_2, T2.nomeTime AS Time_2,
CONVERT(CHAR(10), data, 103) AS Data_da_partida
FROM Jogos 
INNER JOIN Times AS T1
ON T1.codigoTime = Jogos.codigoTimeA
INNER JOIN Times AS T2
ON T2.codigoTime = Jogos.codigoTimeB
WHERE Jogos.data = '01/02/2015' 
ORDER BY data

SELECT codigoJogo,T1.nomeTime AS Time_1, golsTimeA AS Gols_time_1,
golsTimeB AS Gols_time_2, T2.nomeTime AS Time_2,
CONVERT(CHAR(10), data, 103) AS Data_da_partida
FROM Jogos 
INNER JOIN Times AS T1
ON T1.codigoTime = Jogos.codigoTimeA
INNER JOIN Times AS T2
ON T2.codigoTime = Jogos.codigoTimeB
WHERE Jogos.data = '01/02/2015' 
ORDER BY data


UPDATE jOGOS SET golsTimeA = 1, golsTimeB = 2 WHERE codigoTimeA = 3 AND codigoTimeB = 1
UPDATE jOGOS SET golsTimeA = 3, golsTimeB = 3 WHERE codigoTimeA = 4 AND codigoTimeB = 5
UPDATE jOGOS SET golsTimeA = 4, golsTimeB = 2 WHERE codigoTimeA = 4 AND codigoTimeB = 3
UPDATE jOGOS SET golsTimeA = 3, golsTimeB = 1 WHERE codigoTimeA = 19 AND codigoTimeB = 20
UPDATE jOGOS SET golsTimeA = 4, golsTimeB = 7 WHERE codigoTimeA = 2 AND codigoTimeB = 20
UPDATE jOGOS SET golsTimeA = 5, golsTimeB = 6 WHERE codigoTimeA = 7 AND codigoTimeB = 9
UPDATE jOGOS SET golsTimeA = 6, golsTimeB = 9 WHERE codigoTimeA = 5 AND codigoTimeB = 10
UPDATE jOGOS SET golsTimeA = 8, golsTimeB = 6 WHERE codigoTimeA = 19 AND codigoTimeB = 10
UPDATE jOGOS SET golsTimeA = 1, golsTimeB = 3 WHERE codigoTimeA = 1 AND codigoTimeB = 18
UPDATE jOGOS SET golsTimeA = 1, golsTimeB = 3 WHERE codigoTimeA = 2 AND codigoTimeB = 10
UPDATE jOGOS SET golsTimeA = 1, golsTimeB = 3 WHERE codigoTimeA = 8 AND codigoTimeB = 10

-----------------------------------------------------@FUNCTION fn_campeonato---------------------------------------------
-- Primeiro devemos criar essa function porque ela servirá como base para a próxima.
-- Tem a função de carregar a situação atual do campeonato

CREATE FUNCTION fn_campeonato()
RETURNS @tabela TABLE(
			codigoTime INT,
			nomeTime VARCHAR(20),
			numeroJogosDisputados INT,
			vitorias INT,
			empates INT,
			derrotas INT,
			golsMarcados INT,
			golsSofridos INT,
			saldoGols INT,
			pontos INT)
AS
BEGIN
	DECLARE	@nomeTime VARCHAR(20),
			@numeroJogosDisputados INT,
			@vitorias INT,
			@empates INT,
			@derrotas INT,
			@golsMarcados INT,
			@golsSofridos INT,
			@saldoGols INT,
			@estado VARCHAR(20),
			@pontos INT,
			@time INT
			SET @time = 1
			
		WHILE (SELECT COUNT(*) FROM @tabela) < 20
		BEGIN
		
		SET @nomeTime = (SELECT nomeTime FROM Times WHERE codigoTime = @time)
		SET @numeroJogosDisputados = (SELECT COUNT(*) FROM Jogos
										WHERE (codigoTimeA = @time OR codigoTimeB = @time) AND
										(golsTimeA IS NOT NULL AND golsTimeB IS NOT NULL))
										
		SET @vitorias = (SELECT COUNT(*) FROM Jogos WHERE (codigoTimeA = @time AND golsTimeA > golsTimeB) OR
															(codigoTimeB = @time AND golsTimeA < golsTimeB))
		SET @pontos = @vitorias * 3
		SET @empates = (SELECT COUNT(*) FROM Jogos WHERE (codigoTimeA = @time AND golsTimeA = golsTimeB) OR
															(codigoTimeB = @time AND golsTimeA = golsTimeB))
		SET @pontos = @pontos + @empates
		SET @derrotas = (SELECT COUNT(*) FROM Jogos WHERE (codigoTimeA = @time AND golsTimeA < golsTimeB) OR
															(codigoTimeB = @time AND golsTimeA > golsTimeB))
		
		SET @golsMarcados = (SELECT ISNULL(SUM(golsTimeA),0) FROM Jogos WHERE codigoTimeA = @time AND golsTimeA IS NOT NULL) +
													 (SELECT ISNULL(SUM(golsTimeB),0) FROM Jogos WHERE
													  codigoTimeB = @time AND golsTimeB IS NOT NULL)
													  
		SET @golsSofridos = (SELECT  ISNULL(SUM(golsTimeB),0) FROM Jogos WHERE codigoTimeA = @time AND golsTimeB IS NOT NULL ) +
													 (SELECT ISNULL(SUM(golsTimeA),0) FROM Jogos WHERE
													  codigoTimeB = @time AND golsTimeB IS NOT NULL )
		SET @saldoGols = @golsMarcados - @golsSofridos
		INSERT INTO @tabela VALUES (@time, @nomeTime, @numeroJogosDisputados, @vitorias,
		 @empates, @derrotas, @golsMarcados, @golsSofridos, @saldoGols, @pontos)
		
		 SET @time = @time + 1
		END
		RETURN
	END

		
SELECT * FROM fn_campeonato() ORDER BY pontos DESC, vitorias DESC, golsMarcados , saldoGols DESC
DROP FUNCTION fn_campeonato

-----------------------------------------------------@FUNCTION fn_grupos---------------------------------------------
-- Depois criaremos essa function.
-- Tem a função de definir os grupos e mostrar os classificados e rebaixados do campeonato.

CREATE FUNCTION fn_grupos(@codigoGrupo INT)
RETURNS @tabela TABLE(
			codigoTime INT,
			nomeTime VARCHAR(20),
			numeroJogosDisputados INT,
			vitorias INT,
			empates INT,
			derrotas INT,
			golsMarcados INT,
			golsSofridos INT,
			saldoGols INT,
			pontos INT,
			estado VARCHAR(20))
AS
BEGIN
	DECLARE
			@nomeTime VARCHAR(20),
			@numeroJogosDisputados INT,
			@vitorias INT,
			@empates INT,
			@derrotas INT,
			@golsMarcados INT,
			@golsSofridos INT,
			@saldoGols INT,
			@pontos INT,
			@contador INT,
			@contador2 INT,
			@estado VARCHAR(15)
			SET @contador = 1
		
			WHILE (SELECT COUNT(*) FROM tabela) <= 5 AND @contador <= 20
		BEGIN
		IF NOT EXISTS(SELECT codigoTime FROM Grupos WHERE
		 codigoGrupo = @codigoGrupo AND codigoTime = @contador)
		 BEGIN
			SET @contador = @contador + 1
		 END
		 ELSE IF EXISTS(SELECT codigoTime FROM Grupos WHERE
		 codigoGrupo = @codigoGrupo AND codigoTime = @contador)
		 BEGIN
		 SET @nomeTime = (SELECT nomeTime FROM Times WHERE codigoTime = @contador)
			SET @numeroJogosDisputados = (SELECT COUNT(*) FROM Jogos
										WHERE (codigoTimeA = @contador OR codigoTimeB = @contador) AND
										(golsTimeA IS NOT NULL AND golsTimeB IS NOT NULL))
										
		SET @vitorias = (SELECT COUNT(*) FROM Jogos WHERE (codigoTimeA = @contador AND golsTimeA > golsTimeB) OR
															(codigoTimeB = @contador AND golsTimeA < golsTimeB))
		SET @pontos = @vitorias * 3
		SET @empates = (SELECT COUNT(*) FROM Jogos WHERE (codigoTimeA = @contador AND golsTimeA = golsTimeB) OR
															(codigoTimeB = @contador AND golsTimeA = golsTimeB))
		SET @pontos = @pontos + @empates
		SET @derrotas = (SELECT COUNT(*) FROM Jogos WHERE (codigoTimeA = @contador AND golsTimeA < golsTimeB) OR
															(codigoTimeB = @contador AND golsTimeA > golsTimeB))
		
		SET @golsMarcados = (SELECT ISNULL(SUM(golsTimeA),0) FROM Jogos WHERE codigoTimeA = @contador AND golsTimeA IS NOT NULL) +
													 (SELECT ISNULL(SUM(golsTimeB),0) FROM Jogos WHERE
													  codigoTimeB = @contador AND golsTimeB IS NOT NULL)
													  
		SET @golsSofridos = (SELECT  ISNULL(SUM(golsTimeB),0) FROM Jogos WHERE codigoTimeA = @contador AND golsTimeB IS NOT NULL ) +
													 (SELECT ISNULL(SUM(golsTimeA),0) FROM Jogos WHERE
													  codigoTimeB = @contador AND golsTimeB IS NOT NULL )
			IF EXISTS(SELECT * FROM Auxiliar WHERE codigoTime = @contador) 
			BEGIN
				SET @estado = 'REBAIXADO'
			END
			ELSE IF NOT EXISTS(SELECT * FROM Auxiliar WHERE codigoTime = @contador)
			BEGIN
				SET @estado = 'PERMANECE'
		END
		
		SET @saldoGols = @golsMarcados - @golsSofridos
		INSERT INTO @tabela 
			VALUES (@contador, @nomeTime, @numeroJogosDisputados, @vitorias,
		 @empates, @derrotas, @golsMarcados, @golsSofridos, @saldoGols, @pontos, @estado)
		 SET @contador = @contador + 1
		END
		END
		RETURN
		END


------------------------------------@PROCEDURE QUE CHAMA FUNCTION@--------------------------------------------
-- Procedure responsável por chamar a fn_grupos

CREATE PROCEDURE sp_gruposFinal(@codigoGrupo INT)
AS
CREATE TABLE Auxiliar(codigoTime INT)
INSERT INTO Auxiliar SELECT TOP 4 codigoTime FROM dbo.fn_campeonato()
		ORDER BY pontos ASC, vitorias ASC, golsMarcados DESC, saldoGols ASC
SELECT * FROM fn_grupos(@codigoGrupo) ORDER BY pontos DESC, vitorias DESC, golsMarcados , saldoGols DESC
DROP TABLE Auxiliar	
--acaba aqui ^ 

EXEC sp_gruposFinal 1
EXEC sp_gruposFinal 2
EXEC sp_gruposFinal 3
EXEC sp_gruposFinal 4
DROP PROCEDURE sp_gruposFinal

SELECT * FROM fn_campeonato() 

SELECT * FROM fn_campeonato() ORDER BY pontos DESC, vitorias DESC, golsMarcados , saldoGols DESC
-----------------------------------------------@TRIGGERS------------------------------------------------------
CREATE TRIGGER t_times
ON Times
INSTEAD OF INSERT, UPDATE, DELETE
AS
BEGIN
	RAISERROR('Não é possivel inserir, atualizar ou deletar a tabela times',16,1)
END

/* Teste TRIGGER t_times */
INSERT INTO Times VALUES
('ABC', 'Paulista', 'José Liberatti')
UPDATE Times SET nomeTime = 'Palmeiras B' WHERE codigoTime = 1
DELETE Times
SELECT * FROM Times
ENABLE TRIGGER t_times ON Times
DISABLE TRIGGER t_times ON Times

CREATE TRIGGER t_grupos
ON Grupos
INSTEAD OF INSERT, UPDATE, DELETE
AS
BEGIN
	RAISERROR('Não é possivel inserir, atualizar ou deletar a tabela grupos',16,1)
END

/* Teste TRIGGER t_grupos */
INSERT INTO Grupos VALUES(1, 'A', 10)
UPDATE Grupos SET grupo = 'Z' WHERE codigoGrupo = 1
DELETE Grupos
SELECT * FROM Grupos
ENABLE TRIGGER t_grupos ON Grupos
DISABLE TRIGGER t_grupos ON Grupos

CREATE TRIGGER t_jogos
on Jogos
INSTEAD OF INSERT, DELETE
AS
BEGIN
	RAISERROR('Não é possivel inserir ou deletar jogos. O que você pode fazer é atualizar o placar.',16,1)
END

/* Teste TRIGGER t_jogos */
DELETE Jogos
INSERT INTO Jogos VALUES (1, 2,1,0, '10/05/2015')
SELECT * FROM Jogos
ENABLE TRIGGER t_jogos ON Jogos
DISABLE TRIGGER t_jogos ON Jogos

-------------------------------@quartas de final @--------------------------------------------
-- Só pode ser executado depois da criação da function campeonato e a function grupos
-- Tem a função de fazer com que o primeiro grupo de cada time jogue com um segundo time de outro grupo

CREATE FUNCTION fn_quartas(@grupo1 INT, @grupo2 INT)
RETURNS @tabela TABLE(
codigoTime1 INT,
nomeTime1 VARCHAR(50),
codigoTime2 INT,
nomeTime2 VARCHAR(50))
AS
BEGIN
	
	DECLARE @time1 INT,
			@time2 INT,
			@nomeTime1 VARCHAR(50),
			@nomeTime2 VARCHAR(50)
	SET @time1 = (SELECT TOP 1 codigoTime FROM (select * from dbo.fn_grupos(@grupo1))
	as time1
	ORDER BY pontos desc, vitorias desc, golsMarcados desc, saldoGols desc)
	
	SET @time2 = ((SELECT TOP 2 codigoTime FROM (SELECT * FROM dbo.fn_grupos(@grupo2)) AS time2
	ORDER BY pontos DESC, vitorias DESC, golsMarcados DESC, saldoGols DESC) EXCEPT 
	(SELECT TOP 1 codigoTime FROM (SELECT * FROM dbo.fn_grupos(@grupo2))
	 AS timeA ORDER BY pontos DESC, vitorias DESC, golsMarcados DESC, saldoGols DESC))
	 
	 SET @nomeTime1 = (SELECT nomeTime FROM Times WHERE codigoTime = @time1)
	 SET @nomeTime2 = (SELECT nomeTime FROM Times WHERE codigoTime = @time2)
	
	INSERT @tabela VALUES(@time1, @nomeTime1, @time2, @nomeTime2)
RETURN
END	

-- DEVE SER CRIADA E EXECUTADA APÓS A CRIAÇÃO DA FUNCTION Quartas

CREATE PROCEDURE sp_quartas(@grupo1 INT, @grupo2 INT)
AS
CREATE TABLE Auxiliar(codigoTime INT)	
SELECT * FROM dbo.fn_quartas(@grupo1, @grupo2) 
DROP TABLE Auxiliar


--- Devem ser executados os seguintes EXECS:
EXEC sp_quartas 1, 2
EXEC sp_quartas 1, 2
EXEC sp_quartas 2, 1
EXEC sp_quartas 3, 4
EXEC sp_quartas 4, 3



DROP FUNCTION fn_quartas
DROP PROCEDURE sp_quartas

