package atividadeExemplo.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import atividadeExemplo.dao.GenericDao;
import atividadeExemplo.dominio.Responsavel;

@ManagedBean(name = "responsavelMBean")
@SessionScoped
public class ResponsavelMBean {
	
	private static final String TELA_CADASTRO = "tela_cadastro_responsavel";
	private static final String PAGINA_INICIAL = "index";

	private Responsavel responsavel;
	
	private void clear() {
		responsavel = new Responsavel();
	}
	
	public String iniciarCadastrar() {
		clear();
		return TELA_CADASTRO;
	}
	
	public String salvar() {
		GenericDao<Responsavel> gDao = new GenericDao<>();
		if(!gDao.findByField(Responsavel.class, "nome", responsavel.getNome()).isEmpty()) {
			addMensagem("Já existe um responsável cadastrado com esse nome.", FacesMessage.SEVERITY_ERROR);
			return null;
		}
		gDao.criar(responsavel);
		addMensagem("Responsável cadastrado com sucesso!", FacesMessage.SEVERITY_INFO);
		return PAGINA_INICIAL;
	}
	
	public String paginaInicial() {
		return PAGINA_INICIAL;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	
	public void addMensagem(String mensagem, Severity tipo) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, mensagem , ""));
	}
}
