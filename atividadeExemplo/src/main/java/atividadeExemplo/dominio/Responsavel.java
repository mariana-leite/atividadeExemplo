package atividadeExemplo.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="responsavel", schema="comum")
public class Responsavel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_resp")
	@SequenceGenerator(name="seq_resp", sequenceName="comum.responsavel_sequence", allocationSize=1)
	@Column(name="id_responsavel")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	public Responsavel() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}