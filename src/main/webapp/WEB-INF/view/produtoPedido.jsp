<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Realizar Pedido</title>

<script>
	function validarBusca() {
		var cpf = document.getElementById("codigo").value;
		if (cpf.trim() === "") {
			alert("Por favor, insira um código.");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div>
		<jsp:include page="headerIndex.jsp" />
	</div>
	<br />
	<div class="container py-4">
		<div class="p-5 mb-4 bg-body-tertiary rounded-3 text-center shadow">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">Realizar Pedidos</h1>

				<div class="d-flex gap-2 justify-content-center py-2">

					<form action="produtoPedido" method="post" class="row g-3 mt-3">

						<label for="data" class="form-label col-md-1">Produto:</label>
						<div class="col-md-2">
							<select class="form-select" id="produto" name="produto">
								<option value="0">Escolha um Produto</option>
								<c:forEach var="p" items="${produtos }">
									<c:if
										test="${(empty produto) || (p.codProduto ne produtoPedido.produto.codProduto) }">
										<option value="${p.codProduto }">
											<c:out value="${p }" />
										</option>
									</c:if>
									<c:if test="${p.codProduto eq produtoPedido.produto.codProduto }">
										<option value="${p.codProduto }" selected="selected"><c:out
												value="${p }" /></option>
									</c:if>
								</c:forEach>
							</select>
						</div>

						<div class="col-md-1"></div>

						<label for="data" class="form-label col-md-1">Pedido:</label>
						<div class="col-md-3">
							<select class="form-select" id="pedido" name="pedido">
								<option value="0">Escolha um Pedido</option>
								<c:forEach var="p" items="${pedidos }">
									<c:if test="${(empty pedido) || (p.iPedido ne produtoPedido.pedido.idPedido) }">
										<option value="${p.idPedido }">
											<c:out value="${p }" />
										</option>
									</c:if>
									<c:if test="${p.idPedido eq produtoPedido.pedido.idPedido }">
										<option value="${p.idPedido }" selected="selected"><c:out
												value="${p }" /></option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<label for="cargaHoraria" class="form-label col-md-1">Quantidade:</label>
						<div class="col-md-3">
							<input class="form-control" type="number" min="0" step="1"
								id="qtde" name="qtde" placeholder="Quantidade"
								value='<c:out value="${produtoPedido.qtde }"></c:out>'>
						</div>
						<label class="form-label col-md-1">
							</label>
						<div class="col-md-3">
			
						</div>

				
						<div class="col-md-3"></div>
						<label for="turno" class="form-label col-md-1"></label>
						<div class="col-md-3"></div>
					
						<div class="col-md-2 d-grid text-center">
							<input type="submit" id="botao" name="botao" value="Buscar"
								class="btn btn-primary">
						</div>
						
						
						<div class="col-md-2 d-grid text-center">
							<input type="submit" id="botao" name="botao" value="Cadastrar"
								class="btn btn-success">
						</div>
						<div class="col-md-2 d-grid text-center">
							<input type="submit" id="botao" name="botao" value="Alterar"
								class="btn btn-success">
						</div>
						<div class="col-md-2 d-grid text-center">
							<input type="submit" id="botao" name="botao" value="Excluir"
								class="btn btn-danger">
						</div>
					
						<div class="col-md-2 d-grid text-center">
							<input type="submit" id="botao" name="botao" value="Listar"
								class="btn btn-primary">
						</div>
						<div class="col-md-2 d-grid text-center">
							<input type="submit" id="botao" name="botao" value="Limpar"
								class="btn btn-secondary">
						</div>
						
						<div class="col-md-2 d-grid text-center">
							<input type="submit" id="botao" name="botao" value="Listar Todos"
								class="btn btn-primary">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty erro }">
			<h2>
				<b><c:out value="${erro }" /></b>
			</h2>
		</c:if>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty saida }">
			<h3>
				<b><c:out value="${saida }" /></b>
			</h3>
		</c:if>
	</div>
	<br />
	<div class="container py-4 text-center d-flex justify-content-center"
		align="center">
		<c:if test="${not empty produtosPedidos}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th class="titulo-tabela" colspan="9"
							style="text-align: center; font-size: 23px;">Lista de
							Pedidos e Produtos</th>
					</tr>
					<tr>
						<th>Código Produto</th>
						<th>Nome Produto</th>
						<th>Id Pedido</th>
						<th>Data Pedido</th>
						<th>Quantidade</th>
						<th>Valor Total</th>
					</tr>
				</thead>
				<tbody class="table-group-divider">
					<c:forEach var="p" items="${produtosPedidos}">
						<tr>
							<td><c:out value="${p.produto.codProduto }" /></td>
							<td><c:out value="${p.produto.nomeProduto }" /></td>
							<td><c:out value="${p.pedido.idPedido }" /></td>	
							<td><fmt:formatDate value="${p.pedido.dataPedido}"
									pattern="dd/MM/yyyy" /></td>
							<td><c:out value="${p.qtde }" /></td>
							<td><fmt:formatNumber type="currency" value="${p.valorTotal}" /></td>
				
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
