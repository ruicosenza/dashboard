package br.com.dashboard.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.dashboard.conf.ConfigProperties;
import br.com.dashboard.models.dto.DTOOperacao;

/**
 * Classe para consumo do serviço que consulta ao banco do JC3
 * 
 * @author contrui
 *
 */
@Component
public class RestClientConsumer {

	/**
	 * Retorna as operações com seus objetos adjudicados
	 * 
	 * @param operacoes
	 *            lista de operações
	 * @return lista de operações com seus objetos adjudicados
	 * @throws Exception
	 *             Caso ocorra alguma excessão ao consumo do serviço a lista de
	 *             objetos adjudicados será adquirada diretamente no banco JC3
	 */
	public List<DTOOperacao> getObjsAdjudicados(Collection<DTOOperacao> operacoes) throws Exception {
		List<String> objsAdjs = new ArrayList<>();
		Properties prop = new ConfigProperties().getProperty();
		List<DTOOperacao> opes = new ArrayList<>();

		String jc3_service = prop.getProperty("jc3_service");
		Client client = Client.create();
		WebResource webResource;
		ClientResponse response;
		Pattern pattern = Pattern.compile("uriObjItem\":\"(.*?)\",");
		Matcher matcher;

		for (DTOOperacao operacao : operacoes) {
			webResource = client.resource(jc3_service + operacao.getUriActTask());

			response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);
			System.out.println("Output from Server .... \n");

			matcher = pattern.matcher(output);

			while (matcher.find()) {
				objsAdjs.add(matcher.group(1));
				System.out.println( "uri_act_task: " + operacao.getUriActTask() + " obj_item_id: " + matcher.group(1) );
			}

			if (!objsAdjs.isEmpty()) {
				operacao.setObjsAdj(objsAdjs);
				objsAdjs = new ArrayList<>();
			}
			opes.add(operacao);
		}

		return opes;
	}
}