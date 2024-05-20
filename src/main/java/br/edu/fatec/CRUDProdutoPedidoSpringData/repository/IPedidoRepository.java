package br.edu.fatec.CRUDProdutoPedidoSpringData.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import br.edu.fatec.CRUDProdutoPedidoSpringData.model.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Procedure(name = "Pedido.sp_iud_pedido")
	String sp_iud_pedido(
			@Param("acao") String acao,
		    @Param("idPedido") int idPedido,
		    @Param("dataPedido") Date dataPedido
		);

}
