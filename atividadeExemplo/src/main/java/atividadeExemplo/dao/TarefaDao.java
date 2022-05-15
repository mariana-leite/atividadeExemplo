package atividadeExemplo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import atividadeExemplo.dominio.SituacaoEnum;
import atividadeExemplo.dominio.Tarefa;

public class TarefaDao extends GenericDao<Tarefa>{
	
	@SuppressWarnings("unchecked")
	public List<Tarefa> findTarefas(Integer numero, String tituloDescricao, Integer idResponsavel, SituacaoEnum situacao) {
		String where = " where 1=1 ";
		if(numero != null && numero > 0)
			where += " and t.id = " + numero;
		if(!tituloDescricao.isEmpty()) {
			where += " and (upper(t.titulo) = '" + tituloDescricao.toUpperCase() 
					+ "' or upper(t.descricao) = '" + tituloDescricao.toUpperCase() + "') ";
		}
		if(idResponsavel != null && idResponsavel > 0)
			where += " and t.responsavel.id = " + idResponsavel;
		if(situacao != null)
			where += " and t.situacao = '" + situacao.getDescricao() + "'";
		EntityManager entityManager = getEntityManager();
		List<Tarefa> resultado = entityManager.createQuery(" from Tarefa t " + where).getResultList();
		return resultado;
	}

}
