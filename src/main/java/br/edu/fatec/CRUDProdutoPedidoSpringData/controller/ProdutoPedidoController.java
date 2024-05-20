package br.edu.fatec.CRUDProdutoPedidoSpringData.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.edu.fatec.CRUDProdutoPedidoSpringData.model.Pedido;
import br.edu.fatec.CRUDProdutoPedidoSpringData.model.Produto;
import br.edu.fatec.CRUDProdutoPedidoSpringData.model.ProdutoPedido;
import br.edu.fatec.CRUDProdutoPedidoSpringData.model.ProdutoPedidoId;
import br.edu.fatec.CRUDProdutoPedidoSpringData.repository.IPedidoRepository;
import br.edu.fatec.CRUDProdutoPedidoSpringData.repository.IProdutoPedidoRepository;
import br.edu.fatec.CRUDProdutoPedidoSpringData.repository.IProdutoRepository;

@Controller
public class ProdutoPedidoController {

	@Autowired
	private IPedidoRepository pedRep;

	@Autowired
	private IProdutoRepository prodRep;

	@Autowired
	private IProdutoPedidoRepository ppRep;

	@RequestMapping(name = "produtoPedido", value = "/produtoPedido", method = RequestMethod.GET)
	public ModelAndView produtoPedidoGet(@RequestParam Map<String, String> allRequestParam, ModelMap model) {

		String erro = "";
		String saida = "";

		List<Pedido> pedidos = new ArrayList<>();
		List<Produto> produtos = new ArrayList<>();

		try {

			pedidos = listarPedidos();
			produtos = listarProdutos();

		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("pedidos", pedidos);
			model.addAttribute("produtos", produtos);

		}

		return new ModelAndView("produtoPedido");
	}

	@RequestMapping(name = "produtoPedido", value = "/produtoPedido", method = RequestMethod.POST)
	public ModelAndView produtoPedidoPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {

		String cmd = allRequestParam.get("botao");
		String qtde = allRequestParam.get("qtde");
		String produto = allRequestParam.get("produto");
		String pedido = allRequestParam.get("pedido");
		String saida = "";
		String erro = "";
		Produto prod = new Produto();
		Pedido ped = new Pedido();
		ProdutoPedido pp = new ProdutoPedido();

		List<ProdutoPedido> produtosPedidos = new ArrayList<>();
		List<Produto> produtos = new ArrayList<>();
		List<Pedido> pedidos = new ArrayList<>();

		try {

			if (!cmd.contains("Listar")) {
				prod.setCodProduto(Integer.parseInt(produto));
				prod = buscarProduto(prod);
				pp.setProduto(prod);
				ped.setIdPedido(Integer.parseInt(pedido));
				ped = buscarPedido(ped);
				pp.setPedido(ped);
			}
			pedidos = listarPedidos();
			produtos = listarProdutos();

			if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
				pp.setQtde(Integer.parseInt(qtde));
				// deixa o campo vazio, pois trigger que realiza o upddate do total
				pp.setValorTotal(0);

			}

			if (cmd.contains("Cadastrar")) {
				saida = cadastrarProdutoPedido(pp);
				pp = null;
			}
			if (cmd.contains("Alterar")) {
				saida = alterarProdutoPedido(pp);
				pp = null;
			}
			if (cmd.contains("Excluir")) {

				saida = excluirProdutoPedido(pp);
				pp = null;
			}
			if (cmd.contains("Buscar")) {

				System.out.println("Produto " + prod);
				System.out.println("Pedido " + ped);

				pp = buscarProdutoPedido(prod, ped);

				if (pp == null) {
					saida = "Nenhum Produto e Pedido encontrado com a Combinação.";
					pp = null;
				}
			}
			if (cmd != null && !cmd.isEmpty() && cmd.contains("Limpar")) {
				pp = null;
			}

			if (cmd.contains("Listar")) {
				produtosPedidos = listarProdutosPedidos(Integer.parseInt(produto), Integer.parseInt(pedido));
			}
			
			if (cmd.contains("Listar Todos")) {
				produtosPedidos = listarTodosProdutosPedidos();
			}

		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("produtoPedido", pp);
			model.addAttribute("produtos", produtos);
			model.addAttribute("pedidos", pedidos);
			model.addAttribute("produtosPedidos", produtosPedidos);

		}

		return new ModelAndView("produtoPedido");
	}

	private String cadastrarProdutoPedido(ProdutoPedido pp) throws SQLException, ClassNotFoundException {
		String saida = ppRep.sp_iud_produto_pedido("I", pp.getProduto().getCodProduto(), pp.getPedido().getIdPedido(),
				pp.getQtde(), pp.getValorTotal());
		return saida;
	}

	private String alterarProdutoPedido(ProdutoPedido pp) throws SQLException, ClassNotFoundException {
		String saida = ppRep.sp_iud_produto_pedido("U", pp.getProduto().getCodProduto(), pp.getPedido().getIdPedido(),
				pp.getQtde(), pp.getValorTotal());
		return saida;
	}

	private String excluirProdutoPedido(ProdutoPedido pp) throws SQLException, ClassNotFoundException {
		String saida = ppRep.sp_iud_produto_pedido("D", pp.getProduto().getCodProduto(), pp.getPedido().getIdPedido(),
				pp.getQtde(), pp.getValorTotal());
		return saida;
	}

	private List<Pedido> listarPedidos() throws SQLException, ClassNotFoundException {
		List<Pedido> pedidos = pedRep.findAll();
		return pedidos;
	}

	private List<Produto> listarProdutos() throws SQLException, ClassNotFoundException {
		List<Produto> produtos = prodRep.findAll();
		return produtos;
	}

	private Produto buscarProduto(Produto p) throws SQLException, ClassNotFoundException {
		Optional<Produto> produtoOptional = prodRep.findById(p.getCodProduto());
		if (produtoOptional.isPresent()) {
			return produtoOptional.get();
		} else {
			return null;
		}
	}

	private Pedido buscarPedido(Pedido p) throws SQLException, ClassNotFoundException {
		Optional<Pedido> pedidoOptional = pedRep.findById(p.getIdPedido());
		if (pedidoOptional.isPresent()) {
			return pedidoOptional.get();
		} else {
			return null;
		}
	}

	private ProdutoPedido buscarProdutoPedido(Produto prod, Pedido ped) throws SQLException, ClassNotFoundException {
		ProdutoPedidoId ppId = new ProdutoPedidoId(prod, ped);
		Optional<ProdutoPedido> pedidoOptional = ppRep.findById(ppId);
		if (pedidoOptional.isPresent()) {
			return pedidoOptional.get();
		} else {
			return null;
		}
	}

	private List<ProdutoPedido> listarProdutosPedidos(int codProduto, int idPedido)
			throws SQLException, ClassNotFoundException {
		List<ProdutoPedido> produtosPedidos = ppRep.fn_produto_pedido(codProduto, idPedido);
		return produtosPedidos;
	}
	
	private List<ProdutoPedido> listarTodosProdutosPedidos() throws SQLException, ClassNotFoundException {
		List<ProdutoPedido> produtosPedidos = ppRep.findAll();
		return produtosPedidos;
	}

}