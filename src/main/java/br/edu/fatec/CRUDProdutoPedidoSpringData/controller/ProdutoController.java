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
import br.edu.fatec.CRUDProdutoPedidoSpringData.model.Produto;
import br.edu.fatec.CRUDProdutoPedidoSpringData.repository.IProdutoRepository;

@Controller
public class ProdutoController {

	@Autowired
	private IProdutoRepository dRep;

	@RequestMapping(name = "produto", value = "/produto", method = RequestMethod.GET)
	public ModelAndView produtoGet(@RequestParam Map<String, String> allRequestParam, ModelMap model) {

		String erro = "";
		String saida = "";

		List<Produto> produtos = new ArrayList<>();

		Produto p = new Produto();

		try {

			String cmd = allRequestParam.get("cmd");
			String codProduto = allRequestParam.get("codProduto");

			if (cmd != null) {
				if (cmd.contains("alterar")) {

					p.setCodProduto(Integer.parseInt(codProduto));
					p = buscarProduto(p);

				} else if (cmd.contains("excluir")) {
					 p.setCodProduto(Integer.parseInt(codProduto));
					 saida = excluirProduto(p);

				}
				 produtos = listarProdutos();
			}

		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("produto", p);
			model.addAttribute("produtos", produtos);

		}

		return new ModelAndView("produto");
	}

	@RequestMapping(name = "produto", value = "/produto", method = RequestMethod.POST)
	public ModelAndView produtoPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {

		String cmd = allRequestParam.get("botao");
		String codProduto = allRequestParam.get("codProduto");
		String nomeProduto = allRequestParam.get("nomeProduto");
		String valorUnitario = allRequestParam.get("valorUnitario");

		String saida = "";
		String erro = "";
		Produto p = new Produto();

		List<Produto> produtos = new ArrayList<>();

		if (cmd != null && !cmd.isEmpty() && cmd.contains("Limpar")) {
			p = null;

		} else {

			if (!cmd.contains("Listar")) {
				p.setCodProduto(Integer.parseInt(codProduto));
			}
			if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
				p.setNomeProduto(nomeProduto);
				p.setValorUnitario(Float.parseFloat(valorUnitario));

			}
			try {
				if (cmd.contains("Cadastrar")) {
					saida = cadastrarProduto(p);
					p = null;
				}
				if (cmd.contains("Alterar")) {
					saida = alterarProduto(p);
					p = null;
				}
				if (cmd.contains("Excluir")) {
					saida = excluirProduto(p);
					p = null;
				}
				if (cmd.contains("Buscar")) {
					p.setCodProduto(Integer.parseInt(codProduto));
					p = buscarProduto(p);
					if (p == null) {
						saida = "Nenhum Produto encontrado com o código especificado.";

					}
				}

				if (cmd.contains("Listar")) {
					produtos = listarProdutos();
				}
			} catch (SQLException | ClassNotFoundException e) {
				erro = e.getMessage();
			}
		}

		model.addAttribute("saida", saida);
		model.addAttribute("erro", erro);
		model.addAttribute("produto", p);
		model.addAttribute("produtos", produtos);

		return new ModelAndView("produto");
	}

	private String cadastrarProduto(Produto p) throws SQLException, ClassNotFoundException {
		String saida = dRep.sp_iud_produto("I", p.getCodProduto(), p.getNomeProduto(), p.getValorUnitario());
		return saida;
	}

	private String alterarProduto(Produto p) throws SQLException, ClassNotFoundException {
		String saida = dRep.sp_iud_produto("U", p.getCodProduto(), p.getNomeProduto(), p.getValorUnitario());
		return saida;
	}

	private String excluirProduto(Produto p) throws SQLException, ClassNotFoundException {
		String saida = dRep.sp_iud_produto("D", p.getCodProduto(), p.getNomeProduto(), p.getValorUnitario());
		return saida;
	}

	private Produto buscarProduto(Produto p) throws SQLException, ClassNotFoundException {
		Optional<Produto> produtoOptional = dRep.findById(p.getCodProduto());
		if (produtoOptional.isPresent()) {
			return produtoOptional.get();
		} else {
			return null;
		}
	}

	private List<Produto> listarProdutos() throws SQLException, ClassNotFoundException {
		List<Produto> produtos = dRep.findAll();
		return produtos;
	}

}