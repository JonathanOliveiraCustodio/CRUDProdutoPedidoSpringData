package br.edu.fatec.CRUDProdutoPedidoSpringData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import br.edu.fatec.CRUDProdutoPedidoSpringData.model.Produto;


public interface IProdutoRepository extends JpaRepository<Produto, Integer> {
	
	@Procedure(name = "Produto.sp_iud_produto")
	String sp_iud_produto(
			@Param("acao") String acao,
		    @Param("codProduto") int codProduto,
		    @Param("nomeProduto") String nomeProduto,
		    @Param("valorUnitario") float valorUnitario
		);

}
