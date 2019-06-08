package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.desconto.Desconto;
import br.com.caelum.ingresso.desconto.SemDesconto;
import br.com.caelum.ingresso.desconto.DescontoParaEstudante;
import br.com.caelum.ingresso.desconto.DescontoParaBancos;

public enum TipoDeIngresso {

	INTEIRO(new SemDesconto()), 
	ESTUDANTE(new DescontoParaEstudante()),
	BANCO(new DescontoParaBancos());
	
	private final Desconto desconto;

	TipoDeIngresso(Desconto desconto) {
		this.desconto = desconto;
	}

	public BigDecimal aplicaDesconto(BigDecimal valor) {
		return desconto.aplicarDescontoSobre(valor);
	}

	public String getDescricao() {
		return desconto.getDescricao();
	}

}
