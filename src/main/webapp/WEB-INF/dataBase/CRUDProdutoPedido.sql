USE master
CREATE DATABASE CrudProdutoPedido
GO
USE CrudProdutoPedido 
GO
CREATE PROCEDURE sp_iud_produto
    @acao CHAR(1),
    @codProduto INT,
    @nomeProduto VARCHAR(20),
    @valorUnitario FLOAT,
    @saida VARCHAR(100) OUTPUT
AS
BEGIN
    IF (@acao = 'I')
    BEGIN
        INSERT INTO produto (codProduto, nomeProduto, valorUnitario)
        VALUES (@codProduto, @nomeProduto, @valorUnitario)
        SET @saida = 'Produto inserido com sucesso'
    END
    ELSE IF (@acao = 'U')
    BEGIN
        UPDATE produto
        SET nomeProduto = @nomeProduto, valorUnitario = @valorUnitario
        WHERE codProduto = @codProduto
        SET @saida = 'Produto alterado com sucesso'
    END
    ELSE IF (@acao = 'D')
    BEGIN
        DELETE FROM produto WHERE codProduto = @codProduto
        SET @saida = 'Produto excluído com sucesso'
    END
    ELSE
    BEGIN
        SET @saida = 'Operação inválida'
        RETURN
    END
END
GO
CREATE PROCEDURE sp_iud_pedido
    @acao CHAR(1),
    @idPedido INT,
    @dataPedido DATE,
    @saida VARCHAR(100) OUTPUT
AS
BEGIN
    IF (@acao = 'I')
    BEGIN
        INSERT INTO pedido (idPedido, dataPedido)
        VALUES (@idPedido, @dataPedido);
        SET @saida = 'Pedido inserido com sucesso';
    END
    ELSE IF (@acao = 'U')
    BEGIN
        UPDATE pedido
        SET dataPedido = @dataPedido
        WHERE idPedido = @idPedido;
        SET @saida = 'Pedido alterado com sucesso';
    END
    ELSE IF (@acao = 'D')
    BEGIN
        DELETE FROM pedido WHERE idPedido = @idPedido;
        SET @saida = 'Pedido excluído com sucesso';
    END
    ELSE
    BEGIN
        SET @saida = 'Operação inválida';
        RETURN;
    END
END;
GO
CREATE PROCEDURE sp_iud_produto_pedido
    @acao CHAR(1),
    @codProduto INT,
    @idPedido INT,
    @qtde INT,
    @valorUnitario FLOAT,
    @saida VARCHAR(100) OUTPUT
AS
BEGIN
    IF (@acao = 'I')
    BEGIN
        INSERT INTO produtoPedido (codProduto, idPedido, qtde, valorTotal)
        VALUES (@codProduto, @idPedido, @qtde, @valorUnitario * @qtde)
        SET @saida = 'ProdutoPedido inserido com sucesso'
    END
    ELSE IF (@acao = 'U')
    BEGIN
        UPDATE produtoPedido
        SET qtde = @qtde, valorTotal = @valorUnitario * @qtde
        WHERE codProduto = @codProduto AND idPedido = @idPedido
        SET @saida = 'ProdutoPedido alterado com sucesso'
    END
    ELSE IF (@acao = 'D')
    BEGIN
        DELETE FROM produtoPedido 
        WHERE codProduto = @codProduto AND idPedido = @idPedido
        SET @saida = 'ProdutoPedido excluído com sucesso'
    END
    ELSE
    BEGIN
        SET @saida = 'Operação inválida'
        RETURN
    END
END
GO
CREATE FUNCTION fn_produto_pedido (
    @codProduto INT,
    @idPedido INT
)
RETURNS TABLE
AS
RETURN
(
    SELECT
        p.codProduto,
        p.nomeProduto,
        p.valorUnitario,
        pp.qtde,
        pp.qtde * p.valorUnitario AS valorTotal, -- Calculando o valorTotal
        ped.idPedido,
        FORMAT(ped.dataPedido, 'dd/MM/yyyy') AS dataPedido
    FROM
        produtoPedido pp
    JOIN
        produto p ON pp.codProduto = p.codProduto
    JOIN
        pedido ped ON pp.idPedido = ped.idPedido
    WHERE
        pp.codProduto = @codProduto
        AND pp.idPedido = @idPedido
);
GO
CREATE TRIGGER trg_atualizar_valor_total_produto_pedido
ON produtoPedido
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE produtoPedido
    SET valorTotal = inserted.qtde * p.valorUnitario
    FROM inserted
    INNER JOIN produto p ON inserted.codProduto = p.codProduto
    WHERE produtoPedido.codProduto = inserted.codProduto
    AND produtoPedido.idPedido = inserted.idPedido;
END;







SELECT *FROM produtoPedido