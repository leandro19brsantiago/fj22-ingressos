package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class DescontoParaBancos implements Desconto {

	
	
	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		
		return precoOriginal.subtract(trintaPorCentro(precoOriginal));
	}

	private BigDecimal trintaPorCentro(BigDecimal precoOriginal) {
		
		return precoOriginal.multiply(new BigDecimal("0.3"));
	}

	@Override
	public String getDescricao() {
		
		return "Desconto Banco";
	}

}
