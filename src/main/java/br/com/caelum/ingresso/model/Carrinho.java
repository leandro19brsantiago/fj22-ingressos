package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Carrinho {

	private List<Ingresso> ingressos = new ArrayList<Ingresso>();

	public void add(Ingresso ingresso) {
		ingressos.add(ingresso);

	}

	// verifica se o lugar está ou não no carrinho
	public boolean isSelecionado(Lugar lugar) {
		return ingressos.stream().map(Ingresso::getLugar).anyMatch(lugarDoIngresso -> lugarDoIngresso.equals(lugar));
	}

	// retorna o total a pagar
	public BigDecimal getTotal() {

		return ingressos.stream().map(Ingresso::getPreco).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

	}

	//limpa o carrinho ao finalizar a compra
	public void limpa() {
		this.ingressos.clear();
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public Compra toCompra() {
		return new Compra(ingressos);
	}

}
