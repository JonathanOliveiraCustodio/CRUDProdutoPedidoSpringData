package br.edu.fatec.CRUDProdutoPedidoSpringData.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import br.edu.fatec.CRUDProdutoPedidoSpringData.model.ProdutoPedido;
import br.edu.fatec.CRUDProdutoPedidoSpringData.model.ProdutoPedidoId;

public interface IProdutoPedidoRepository extends JpaRepository<ProdutoPedido, ProdutoPedidoId> {

	List<ProdutoPedido> fn_produto_pedido(int produto, int pedido);

	@Procedure(name = "ProdutoPedido.sp_iud_produto_pedido")
	String sp_iud_produto_pedido(@Param("acao") String acao, @Param("codProduto") int codProuto,
			@Param("idPedido") int idPedido, @Param("qtde") int qtde, @Param("valorUnitario") float valorUnitario);

}
