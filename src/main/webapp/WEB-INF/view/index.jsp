<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<title>CRUD Sistema AGIS</title>
</head>
<body>
	<div>
		<jsp:include page="headerIndex.jsp" />
	</div>
	<div class="container py-4" >
		<div class="p-5 mb-4 bg-body-tertiary rounded-3 text-center shadow">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">Bem-vindo ao CRUD - Produto e Pedido</h1>
		      	<p class="lead mb-4">Escolha sua opção:</p>
		        <div class="d-flex gap-2 justify-content-center py-2">
			      <a href="produto" class="btn btn-primary d-inline-flex align-items-center">Manter Produto</a>
			      <a href="pedido" class="btn btn-primary d-inline-flex align-items-center">Manter Pedido</a>
			      <a href="produtoPedido" class="btn btn-primary d-inline-flex align-items-center">Realizar Pedido</a>
	      		</div>
		    </div>
		</div>
	</div>
</body>
</html>
