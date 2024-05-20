package br.edu.fatec.CRUDProdutoPedidoSpringData.model;

import java.io.Serializable;
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

public class ProdutoPedidoId implements Serializable {
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private Pedido pedido;
}

