package br.edu.fatec.CRUDProdutoPedidoSpringData.model;

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
@Table (name ="produto")

@NamedStoredProcedureQuery(name = "Produto.sp_iud_produto", procedureName = "sp_iud_produto", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "acao", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "codProduto", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "nomeProduto", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "valorUnitario", type = Float.class),	
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "saida", type = String.class) })
public class Produto {
	
	@Id
	@Column(name = "codProduto", nullable = false)	
	private int codProduto;
	
	@Column(name = "nomeProduto", length = 20,  nullable = false)
	private String nomeProduto;
	
	@Column(name = "valorUnitario",  nullable = false)
	private float valorUnitario;
	
	@Override
    public String toString() {
        return nomeProduto;
    }
	
}


