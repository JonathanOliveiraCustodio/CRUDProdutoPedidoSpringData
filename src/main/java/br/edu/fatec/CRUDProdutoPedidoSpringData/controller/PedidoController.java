package br.edu.fatec.CRUDProdutoPedidoSpringData.controller;

import java.sql.Date;
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
import br.edu.fatec.CRUDProdutoPedidoSpringData.repository.IPedidoRepository;


@Controller
public class PedidoController {

	@Autowired
	private IPedidoRepository pedRep;

	@RequestMapping(name = "pedido", value = "/pedido", method = RequestMethod.GET)
	public ModelAndView pedidoGet(@RequestParam Map<String, String> allRequestParam, ModelMap model) {

		String erro = "";
		String saida = "";

		List<Pedido> pedidos = new ArrayList<>();
		Pedido p = new Pedido();
		
		try {
			String cmd = allRequestParam.get("cmd");
			String idPedido = allRequestParam.get("idPedido");

			if (cmd != null) {
				if (cmd.contains("alterar")) {

					p.setIdPedido(Integer.parseInt(idPedido));
					p = buscarPedido(p);

				} else if (cmd.contains("excluir")) {
					 p.setIdPedido(Integer.parseInt(idPedido));
					 saida = excluirPedido(p);

				}
				pedidos = listarPedidos();
			}

		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("pedido", p);
			model.addAttribute("pedidos", pedidos);

		}

		return new ModelAndView("pedido");
	}

	@RequestMapping(name = "pedido", value = "/pedido", method = RequestMethod.POST)
	public ModelAndView produtoPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {

		String cmd = allRequestParam.get("botao");
		String idPedido = allRequestParam.get("idPedido");
		String dataPedido = allRequestParam.get("dataPedido");


		String saida = "";
		String erro = "";
		Pedido p = new Pedido();

		List<Pedido> pedidos = new ArrayList<>();

		if (cmd != null && !cmd.isEmpty() && cmd.contains("Limpar")) {
			p = null;

		} else {

			if (!cmd.contains("Listar")) {
				p.setIdPedido(Integer.parseInt(idPedido));
			}
			if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
				
				p.setDataPedido(Date.valueOf(dataPedido));
	
			}
			try {
				if (cmd.contains("Cadastrar")) {
					saida = cadastrarPedido(p);
					p = null;
				}
				if (cmd.contains("Alterar")) {
					saida = alterarPedido(p);
					p = null;
				}
				if (cmd.contains("Excluir")) {
					saida = excluirPedido(p);
					p = null;
				}
				if (cmd.contains("Buscar")) {
					p.setIdPedido(Integer.parseInt(idPedido));
					p = buscarPedido(p);
					if (p == null) {
						saida = "Nenhum Pedido encontrado com o código especificado.";

					}
				}

				if (cmd.contains("Listar")) {
					pedidos = listarPedidos();
				}
			} catch (SQLException | ClassNotFoundException e) {
				erro = e.getMessage();
			}
		}

		model.addAttribute("saida", saida);
		model.addAttribute("erro", erro);
		model.addAttribute("pedido", p);
		model.addAttribute("pedidos", pedidos);

		return new ModelAndView("pedido");
	}

	private String cadastrarPedido(Pedido p) throws SQLException, ClassNotFoundException {
		String saida = pedRep.sp_iud_pedido("I", p.getIdPedido(), p.getDataPedido());
		return saida;
	}

	private String alterarPedido(Pedido p) throws SQLException, ClassNotFoundException {
		String saida = pedRep.sp_iud_pedido("U", p.getIdPedido(), p.getDataPedido());
		return saida;

	}

	private String excluirPedido(Pedido p) throws SQLException, ClassNotFoundException {
		String saida = pedRep.sp_iud_pedido("D", p.getIdPedido(), p.getDataPedido());
		return saida;

	}

	private Pedido buscarPedido(Pedido p) throws SQLException, ClassNotFoundException {
		Optional<Pedido> pedidoOptional = pedRep.findById(p.getIdPedido());
		if (pedidoOptional.isPresent()) {
			return pedidoOptional.get();
		} else {
			return null;
		}
	}

	private List<Pedido> listarPedidos() throws SQLException, ClassNotFoundException {
		List<Pedido> pedidos = pedRep.findAll();
		return pedidos;
	}

}