package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.caelum.ingresso.desconto.Desconto;

@Entity
public class Ingresso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Sessao sessao;
	
	private BigDecimal preco = BigDecimal.ZERO;
	
	@ManyToOne
	private Lugar lugar;
	@Enumerated(EnumType.STRING)
	private TipoDeIngresso tipoDeIngresso;
	
	//construtor padrão para não ocorrer erro no Hibernate
	public Ingresso() {
		
	}
	
	/*no contrutor aplica o desconto sobre o preço*/
	public Ingresso(Sessao sessao, Desconto tipoDeDesconto,TipoDeIngresso tipoDeIngresso,Lugar lugar) {
	
		this.sessao = sessao;
		this.preco = tipoDeDesconto.aplicarDescontoSobre(sessao.getPreco());
		this.tipoDeIngresso = tipoDeIngresso;
		this.lugar = lugar;
		
	}
	
	public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar) {
		this.sessao = sessao;
		this.preco = tipoDeIngresso.aplicaDesconto(sessao.getPreco());
		this.tipoDeIngresso = tipoDeIngresso;
		this.lugar = lugar;
		
	}

	public Sessao getSessao() {
		return sessao;
	}
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	public BigDecimal getPreco() {
		return preco.setScale(2,RoundingMode.HALF_UP);
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public TipoDeIngresso getTipoDeIngresso() {
		return tipoDeIngresso;
	}

	public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
		this.tipoDeIngresso = tipoDeIngresso;
	}
	
	
	

}
