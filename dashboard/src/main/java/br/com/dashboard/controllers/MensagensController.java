package br.com.dashboard.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.dashboard.models.dto.Mensagem;
import br.com.dashboard.repositories.DataHoraBarramento;
import br.com.dashboard.repositories.ListaOperacoes;
import br.com.dashboard.repositories.ZabbixGraphComponent;

@Controller
@RequestMapping("/")
public class MensagensController {
	
	@Autowired
	private ListaOperacoes listaOperacoes;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("/mensagens/lista");
		long countRegistros = listaOperacoes.getCountRegistros();
		
		modelAndView.addObject("objReg", countRegistros);
		
		DataHoraBarramento dataHora = new DataHoraBarramento();
		String dataHoraBarramento = dataHora.getDataHoraBarramento();
		
		modelAndView.addObject("dataHoraBarramento", dataHoraBarramento);

		List<Mensagem> resultListMsgs = getListaMensagens();

		modelAndView.addObject("mensagens", resultListMsgs);
		
		//Gera os gráficos da aba Infraestrutura de TI
		modelAndView = new ZabbixGraphComponent().montaZabbixGraph(modelAndView);

		return modelAndView;
	}
	
	
	@RequestMapping(value = "/body", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> refresh() {
		HashMap<String, Object> map = new HashMap<>();
		
		long countRegistros = listaOperacoes.getCountRegistros();
		
		map.put("objReg", countRegistros);
		
		DataHoraBarramento dataHora = new DataHoraBarramento();
		String dataHoraBarramento = dataHora.getDataHoraBarramento();
		
		map.put("dataHoraBarramento", dataHoraBarramento);

		List<Mensagem> resultListMsgs = getListaMensagens();
		
		map.put("mensagens", montaTabelaMsgs(resultListMsgs));

		return map;
	}

	/**
	 * Coleta as mensagens de informe tramitadas pelas operações
	 * @return
	 */
	private List<Mensagem> getListaMensagens() {
		List<Mensagem> resultListMsgs = listaOperacoes.getListaMensagens();
		return resultListMsgs;
	}
	
	@RequestMapping(value = "/body/graphZabbix", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> refresh_graphs_zabbix() {
		HashMap<String, Object> map = new HashMap<>();
		
		//Gera os gráficos da aba Infraestrutura de TI
		map = new ZabbixGraphComponent().montaZabbixGraph(map);

		return map;
	}
	
	@RequestMapping(value = "/graph", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getGraph( ){
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<String> operacao = listaOperacoes.getGrafico();
		
		String tabela = "<tabela id=\"tdGraph\" class=\"table.graficosInformes\">";

		int i = 0;
		for (String grafico : operacao) {
			if( i == 0 ){
				tabela += "<tr><td id=\"tdGraph\"><img id=\"graph\" src=\"data:image/png;base64," + grafico + "\" /></td>";
				i = 1;
			} else if( i == 1 ){
				tabela += "<td id=\"tdGraph\"><img id=\"graph\" src=\"data:image/png;base64," + grafico + "\" /></td></tr>";
				i = 0;
			}
		}
		
		if( (operacao.size() % 2) != 0 ){
			tabela = "<td id=\"tdGraph\"></td></tr>";
		}
		
		tabela += "</table>";
		
		map.put("graph", tabela);
		
		return map;
	}
	
	/**
	 * Monta a tabela de lista de operações nas atualizações de tela
	 * @param resultListMsgs
	 * @return
	 */
	private String montaTabelaMsgs(List<Mensagem> resultListMsgs){
		String tabela = "<table id=\"tbMensagens\" class=\"body\">";
		
		for (Mensagem mensagem : resultListMsgs) {
			tabela += "<tr>"+
			"<td class=\"ope\">" + mensagem.getOperacao() + "</td>" +
			"<td class=\"body\">" + mensagem.getTotalAdjudicacoes() + "</td>" +
			"<td class=\"body\">" + mensagem.getTotalInformes() + "</td>" +
			"</tr>";
		}
		
		tabela += "</table>";
		
		return tabela;
	}
}