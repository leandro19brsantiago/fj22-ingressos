package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

// controller para o Spring
@Controller
public class SessaoController {

	@Autowired
	private SessaoDao sessaoDao;
	@Autowired // injeção de dependência
	private SalaDao salaDao;
	@Autowired
	private FilmeDao filmeDao;

	/*
	 * atende a requisição da URL sesao que deve receber um id da sala utilzando a
	 * anotação @RequestParam SessaoFrom representa o formulario preenchido
	 */
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {
		/*
		 * ModelAndView passando para o seu construtor o caminho da página que
		 * retornaremos para o usuário
		 */
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");// devolve para sessao/sessao
		// adiciona ao objeto a sala e os filmes
		modelAndView.addObject("sala", salaDao.findOne(salaId));
		modelAndView.addObject("filmes", filmeDao.findAll());
		modelAndView.addObject("form", form);// devolve o formulario com os insumos

		return modelAndView;
	}

	/*
	 * método para salvar a sessao utiliza o @Valid que valida os campos do
	 * formulário e retorna o resultado da validação no BindingResult
	 */

	@PostMapping(value = "/admin/sessao")
	@Transactional // gerencia pelo hibernate a persistencia com roolback caso ocorra erro
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {
		if (result.hasErrors())
			return form(form.getSalaId(), form);
		Sessao sessao = form.toSessao(salaDao, filmeDao);
		
		List<Sessao> sessoesDaSala = sessaoDao.buscaSessoesDaSala(sessao.getSala());
		
		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoesDaSala);
		
		if (gerenciadorDeSessao.cabe(sessao)) {
			sessaoDao.save(sessao);
			return new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");
		}
		return form(form.getFilmeId(), form);
		
	}
	
	
	
}
