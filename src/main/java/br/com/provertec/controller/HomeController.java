package br.com.provertec.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.provertec.model.CounterModel;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = 5819187659228214023L;
	
	@Getter @Setter
	private String text;
	@Getter
	private List<CounterModel> listOcorrencias;
	@Getter
	private int quantidadePalavrasDistintas = 0;
	
	public void performCounter() {
		listOcorrencias = new ArrayList<>();
		quantidadePalavrasDistintas = 0;
		
		if(text == null || text.isEmpty()) {
			showMessageWarn("Texto deve ser preenchido corretamente!");
			return;
		}
		
		// Quebra a String em partes por espaço
		String[] values = text.split("\\s");
		
		// Calcula as palavras unicas
		setQuantidadePalavrasDistintas(values);
		
		// Calcula a ocorrencia de cada palavra
		setListOcorrencias(values);
		
		showMessageInfo("Processamento finalizado com sucesso!");
	}
	
	private void setQuantidadePalavrasDistintas(String[] values) {
		Set<String> palavras = new HashSet<>();
		for(String value : values) {
			palavras.add(value);
		}
		quantidadePalavrasDistintas = palavras.size();
	}
	
	private void setListOcorrencias(String[] values) {
		Map<String, Integer> mapCounters = new HashMap<>();
		for(String value : values) {
			Integer occur = mapCounters.get(value);
			if(occur == null) {
				occur = 1;
			} else {
				occur++;
			}
			mapCounters.put(value, occur);
		}
		
		listOcorrencias = mapCounters.entrySet().stream()
				.map(entry -> {
					return new CounterModel(entry.getKey(), entry.getValue());
				})
				.collect(Collectors.toList());
	}
	
	private void showMessageWarn(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
		context.addMessage(null, message);
	}
	
	private void showMessageInfo(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		context.addMessage(null, message);
	}

}
