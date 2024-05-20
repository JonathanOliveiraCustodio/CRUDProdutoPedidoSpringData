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
<title>Produtos</title>

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
	function editarProduto(codProduto) {
		window.location.href = 'produto?cmd=alterar&codProduto=' + codProduto;
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
				<h1 class="display-5 fw-bold">Manutenção de Produtos</h1>
				<div class="d-flex gap-2 justify-content-center py-2">
					<form action="produto" method="post" class="row g-3 mt-3">
						<label for="data" class="form-label col-md-1">Código
							Produto:</label>
						<div class="col-md-2">
							<input class="form-control" type="number" min="0" step="1"
								id="codProduto" name="codProduto" placeholder="Codigo Produto"
								value='<c:out value="${produto.codProduto }"></c:out>'>
						</div>
						<div class="col-md-1">
							<input type="submit" id="botao" name="botao"
								class="btn btn-primary" value="Buscar"
								onclick="return validarBusca()">
						</div>
						<label for="data" class="form-label col-md-1">Nome :</label>
						<div class="col-md-3">
							<input class="form-control" type="text" id="nomeProduto"
								name="nomeProduto" placeholder="Nome"
								value='<c:out value="${produto.nomeProduto }"></c:out>'>
						</div>
						<label for="data" class="form-label col-md-1">Valor
							Unitario: </label>
						<div class="col-md-3">
							<input class="form-control" type="number" step="any"
								id="valorUnitario" name="valorUnitario"
								placeholder="Valor Unitário"
								value='<c:out value="${produto.valorUnitario }"></c:out>'>
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
		<c:if test="${not empty produtos}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th class="titulo-tabela" colspan="9"
							style="text-align: center; font-size: 23px;">Lista de
							Produtos</th>
					</tr>
					<tr>
						<th>Selecionar</th>
						<th>Código</th>
						<th>Nome</th>
						<th>Valor Unitario</th>
					</tr>
				</thead>
				<tbody class="table-group-divider">
					<c:forEach var="p" items="${produtos}">
						<tr>
							<td><input type="radio" name="opcao" value="${p.codProduto}"
								onclick="editarProduto(this.value)"
								${p.codProduto eq codigoEdicao ? 'checked' : ''} /></td>
							<td><c:out value="${p.codProduto}" /></td>
							<td><c:out value="${p.nomeProduto}" /></td>
							<td><c:out value="${p.valorUnitario}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
