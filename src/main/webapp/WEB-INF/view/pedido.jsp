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
<title>Pedidos</title>

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
<script>
	function editarPedido(idPedido) {
		window.location.href = 'pedido?cmd=alterar&idPedido=' + idPedido;
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
				<h1 class="display-5 fw-bold">Manutenção de Pedidos</h1>
				<div class="d-flex gap-2 justify-content-center py-2">
					<form action="pedido" method="post" class="row g-3 mt-3">
						<label for="data" class="form-label col-md-1">Código
							Pedido:</label>
						<div class="col-md-2">
							<input class="form-control" type="number" min="0" step="1"
								id="idPedido" name="idPedido" placeholder="Codigo Pedido"
								value='<c:out value="${pedido.idPedido }"></c:out>'>
						</div>
						<div class="col-md-1">
							<input type="submit" id="botao" name="botao"
								class="btn btn-primary" value="Buscar"
								onclick="return validarBusca()">
						</div>
						<label for="data" class="form-label col-md-1">Data Pedido:</label>
						<div class="col-md-3">
							<input class="form-control" type="date" id="dataPedido" name="dataPedido"
								placeholder="Nome"
								value='<c:out value="${pedido.dataPedido }"></c:out>'>
						</div>
				
						<div class="col-md-3">
							
						</div>
					
						<br />
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
						<div class="col-md-2 d-grid text-center"></div>
						<div class="col-md-2 d-grid text-center">
							<input type="submit" id="botao" name="botao" value="Listar"
								class="btn btn-primary">
						</div>
						<div class="col-md-2 d-grid text-center">
							<input type="submit" id="botao" name="botao" value="Limpar"
								class="btn btn-secondary">
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
		<c:if test="${not empty pedidos}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th class="titulo-tabela" colspan="9"
							style="text-align: center; font-size: 23px;">Lista de
							Disciplinas</th>
					</tr>
					<tr>
						<th>Selecionar</th>
						<th>Código</th>
						<th>Data</th>


					</tr>
				</thead>
				<tbody class="table-group-divider">
					<c:forEach var="p" items="${pedidos}">
						<tr>
							<td><input type="radio" name="opcao" value="${p.idPedido}"
								onclick="editarPedido(this.value)"
								${p.idPedido eq codigoEdicao ? 'checked' : ''} /></td>
							<td><c:out value="${p.idPedido}" /></td>
							<td><fmt:formatDate value="${p.dataPedido}"
									pattern="dd/MM/yyyy" /></td>
	


						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
