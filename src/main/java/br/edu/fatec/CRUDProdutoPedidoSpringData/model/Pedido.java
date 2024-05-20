package br.edu.fatec.CRUDProdutoPedidoSpringData.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")

@NamedStoredProcedureQuery(name = "Pedido.sp_iud_pedido", procedureName = "sp_iud_pedido", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "acao", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "idPedido", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataPedido", type = Date.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "saida", type = String.class) })

public class Pedido {

	@Id
	@Column(name = "idPedido", nullable = false)
	private int idPedido;

	@Column(name = "dataPedido", nullable = false)
	private Date dataPedido;

	 @Override
	    public String toString() {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        String dataFormatada = sdf.format(dataPedido);
	        return "Pedido: " + "Id Pedido=" + idPedido + ", Data Pedido=" + dataFormatada;
	    }

}
