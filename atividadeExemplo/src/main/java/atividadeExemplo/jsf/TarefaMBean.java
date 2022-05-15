package atividadeExemplo.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import atividadeExemplo.dao.GenericDao;
import atividadeExemplo.dao.TarefaDao;
import atividadeExemplo.dominio.PrioridadeEnum;
import atividadeExemplo.dominio.Responsavel;
import atividadeExemplo.dominio.SituacaoEnum;
import atividadeExemplo.dominio.Tarefa;

@ManagedBean(name = "tarefaMBean")
@SessionScoped
public class TarefaMBean {
	
	private static final String TELA_CADASTRO = "tela_cadastro_tarefa";
	private static final String TELA_BUSCA = "tela_busca";
	private static final String PAGINA_INICIAL = "index";
	
	private Tarefa tarefa = new Tarefa();
	private List<SelectItem> comboPrioridades;
	private List<SelectItem> comboSituacoes;
	private List<Tarefa> listaTarefa = new ArrayList<>();
	
	private void clear() {
		tarefa = new Tarefa();
		tarefa.setResponsavel(new Responsavel());
		tarefa.setPrioridade(null);
		tarefa.setSituacao(SituacaoEnum.EM_ANDAMENTO);
	}
	
	public String paginaInicial() {
		return PAGINA_INICIAL;
	}
	
	public String iniciarCadastrar() {
		clear();
		tarefa.setPrioridade(PrioridadeEnum.BAIXA);
		return TELA_CADASTRO;
	}
	
	public String salvar() {
		if(tarefa.getResponsavel().getId() == null || tarefa.getResponsavel().getId() <= 0) {
			addMensagem("Informe um responsável.", FacesMessage.SEVERITY_ERROR);
			return TELA_CADASTRO;
		}
		GenericDao<Tarefa> gDao = new GenericDao<>();
		if (tarefa.getId() != null && tarefa.getId() > 0) {
			gDao.atualizar(tarefa);
			addMensagem("Tarefa atualizada com sucesso!", FacesMessage.SEVERITY_INFO);
		} else {
			gDao.criar(tarefa);
			addMensagem("Tarefa cadastrada com sucesso!", FacesMessage.SEVERITY_INFO);
		}
		return PAGINA_INICIAL;
	}
	
	public String iniciarBuscar() {
		clear();
		GenericDao<Tarefa> gDao = new GenericDao<>();
		listaTarefa = gDao.findByField(Tarefa.class, "situacao", SituacaoEnum.EM_ANDAMENTO.getDescricao());
		return TELA_BUSCA;
	}
	
	public String buscar() {
		TarefaDao gDao = new TarefaDao();
		listaTarefa = gDao.findTarefas(tarefa.getId(), tarefa.getTitulo(), tarefa.getResponsavel() == null ? null : tarefa.getResponsavel().getId(), tarefa.getSituacao());	
		return TELA_BUSCA;
	}
	
	public Collection<SelectItem> getComboPrioridades(){
		if(comboPrioridades == null) {
			comboPrioridades = PrioridadeEnum.getComboPrioridades();
		}
		return comboPrioridades;
	}
	
	public Collection<SelectItem> getComboSituacoes(){
		if(comboSituacoes == null)
			comboSituacoes = SituacaoEnum.getComboSituacoes();
		return comboSituacoes;
	}
	
	public Collection<SelectItem> getComboResponsaveis(){
 		GenericDao<Responsavel> gDao = new GenericDao<>();
		List<Responsavel> listaResponsavel = gDao.findAll(Responsavel.class);
		List<SelectItem> itens = new ArrayList<>();
		for(Responsavel responsavel : listaResponsavel)
			itens.add(new SelectItem(responsavel.getId(), responsavel.getNome()));
		return itens;
	}
	
	public String excluir(Tarefa tarefaSelecionada) {
		GenericDao<Tarefa> gDao = new GenericDao<>();
		gDao.excluir(tarefaSelecionada);
		addMensagem("Tarefa excluída com sucesso!", FacesMessage.SEVERITY_INFO);
		listaTarefa.remove(tarefaSelecionada);
		return TELA_BUSCA;
	}
	
	public void concluir(Tarefa tarefaSelecionada) {
		tarefaSelecionada.setSituacao(SituacaoEnum.CONCLUIDA);
		GenericDao<Tarefa> gDao = new GenericDao<>();
		gDao.atualizar(tarefaSelecionada);
		addMensagem("Tarefa concluída com sucesso!", FacesMessage.SEVERITY_INFO);
		buscar();
	}
	
	public String iniciarAlterar(Tarefa tarefaSelecionada) {
		tarefa = tarefaSelecionada;
		return TELA_CADASTRO;
	}
	
	public void addMensagem(String mensagem, Severity tipo) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, mensagem , ""));
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getListaTarefa() {
		return listaTarefa;
	}

	public void setListaTarefa(List<Tarefa> listaTarefa) {
		this.listaTarefa = listaTarefa;
	}
	

}