package atividadeExemplo.Utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import atividadeExemplo.dominio.SituacaoEnum;

@Converter(autoApply = true)
public class SituacaoConverter implements AttributeConverter<SituacaoEnum, String>{

	@Override
	public String convertToDatabaseColumn(SituacaoEnum situacao) {
		 if (situacao == null) {
	            return null;
	        }
	        return situacao.getDescricao();
	}

	@Override
	public SituacaoEnum convertToEntityAttribute(String descricao) {
		if(descricao == null)
			return null;
		for(SituacaoEnum situacao : SituacaoEnum.values()) {
			if(situacao.getDescricao().equals(descricao))
				return situacao;
		}
		return null;
	}

}
