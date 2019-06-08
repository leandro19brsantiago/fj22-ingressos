package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class DescontoParaEstudante implements Desconto {

	private BigDecimal metade = new BigDecimal(2.0);
	
	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		
		return precoOriginal.divide(metade);
	}

	@Override
	public String getDescricao() {
		
		return "Desconto Estudante";
	}

}
