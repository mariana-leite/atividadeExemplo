package atividadeExemplo.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public enum SituacaoEnum {

	EM_ANDAMENTO("Em andamento"),
	CONCLUIDA("Concluída");
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
	private SituacaoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public static List<SelectItem> getComboSituacoes(){
		List<SelectItem> itens = new ArrayList<>();
		for(SituacaoEnum situacao : SituacaoEnum.values())
			itens.add(new SelectItem(situacao, situacao.getDescricao()));
		return itens;
	}
	
}
