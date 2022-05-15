package atividadeExemplo.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public enum PrioridadeEnum {

	ALTA("Alta"),
	MEDIA("Média"),
	BAIXA("Baixa");
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
	private PrioridadeEnum(String descricao) {
		this.descricao = descricao;
	}
	
	
	public static List<SelectItem> getComboPrioridades(){
		List<SelectItem> itens = new ArrayList<>();
		for(PrioridadeEnum prioridade : PrioridadeEnum.values())
			itens.add(new SelectItem(prioridade, prioridade.getDescricao()));
		return itens;
	}
	
}

