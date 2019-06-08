package ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import br.com.caelum.ingresso.desconto.SemDesconto;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class DescontoTest {

	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		
		Sala sala = new Sala("Eldorado - IMax", new BigDecimal("20.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));

		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto(), TipoDeIngresso.ESTUDANTE,new Lugar("A",1));

		BigDecimal precoEsperado = new BigDecimal("32.50");

		Assert.assertEquals(precoEsperado, ingresso.getPreco());

	}
}
