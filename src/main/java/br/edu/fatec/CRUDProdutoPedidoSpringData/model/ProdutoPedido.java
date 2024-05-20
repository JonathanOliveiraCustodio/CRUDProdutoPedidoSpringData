package br.edu.fatec.CRUDProdutoPedidoSpringData.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produtoPedido")

@NamedNativeQuery(name = "ProdutoPedido.fn_produto_pedido", query = "SELECT * FROM fn_produto_pedido(?1,?2)", resultClass = ProdutoPedido.class)

@NamedStoredProcedureQuery(name = "ProdutoPedido.sp_iud_produto_pedido", procedureName = "sp_iud_produto_pedido", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "acao", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "codProduto", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "idPedido", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "qtde", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "valorUnitario", type = Float.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "saida", type = String.class) })

@IdClass(ProdutoPedidoId.class)
public class ProdutoPedido {

	@Id
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Produto.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "codProduto", nullable = false)
	private Produto produto;

	@Id
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Pedido.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idPedido", nullable = false)
	private Pedido pedido;

	@Column(name = "qtde", nullable = false)
	private int qtde;

	@Column(name = "valorTotal", nullable = false)
	private float valorTotal;

}
