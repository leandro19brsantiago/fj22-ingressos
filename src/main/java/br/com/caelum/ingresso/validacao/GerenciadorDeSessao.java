package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	
	private List<Sessao> sessaoDaSala;

	public GerenciadorDeSessao(List<Sessao> sessaoDaSala) {
		
		this.sessaoDaSala = sessaoDaSala;
	}
	
	//ferifica se o filme cabe na sessao
	public boolean cabe(Sessao sessaoNova) {
		
		if (terminaAmanha(sessaoNova)) {
			return false;
		}
		
		return sessaoDaSala.stream().noneMatch(sessaoExistente -> horarioIsConflitante(sessaoExistente, sessaoNova));
	}
	
	//verifica se a sessão passa para o outro dia
	public boolean terminaAmanha(Sessao sessao) {
		
		LocalDateTime terminoSessaoNova = getTerminoSessaocomDiaHoje(sessao);
		LocalDateTime ultimoSegundoDeHoje  = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		
		return true;
	}
	
	
	private LocalDateTime getTerminoSessaocomDiaHoje(Sessao sessao) {
		
		LocalDateTime inicioSessaoNova = getInicioSessaocomDoaDeHoje(sessao);
		
		return inicioSessaoNova.plus(sessao.getFilme().getDuracao());
	}

	private LocalDateTime getInicioSessaocomDoaDeHoje(Sessao sessao) {
		
		LocalDate hoje = LocalDate.now();
		
		return sessao.getHorario().atDate(hoje);
	}

	//verifica os horários da sessão existente e da nova sessão
	private boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova) {
		
		
		LocalDateTime horarioSessaoExistente = getInicioSessaocomDoaDeHoje(sessaoExistente);
		LocalDateTime terminoSessaoExistente = getTerminoSessaocomDiaHoje(sessaoExistente);
		
		LocalDateTime horarioSessaoNova = getInicioSessaocomDoaDeHoje(sessaoNova);
		LocalDateTime terminoSessaoNova = getTerminoSessaocomDiaHoje(sessaoNova);
		
		boolean terminaAntes = terminoSessaoNova.isBefore(horarioSessaoExistente);
		boolean comecaDepois = terminoSessaoExistente.isBefore(horarioSessaoNova);
		
		if (terminaAntes || comecaDepois) {
		return false;
		}
		return true;
		}
		
	
}
