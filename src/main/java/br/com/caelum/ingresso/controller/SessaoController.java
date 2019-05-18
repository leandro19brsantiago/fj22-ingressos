package br.com.caelum.ingresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;

// controller para o Spring
@Controller
public class SessaoController {

	@Autowired
	private SalaDao salaDao;
	@Autowired
	private FilmeDao filmeDao;

	/*
	 * atende a requisição da URL sesao que deve receber um id da sala utilzando a
	 * anotação @RequestParam
	 */
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId) {
		/*
		 * ModelAndView passando para o seu construtor o caminho da página que
		 * retornaremos para o usuário
		 */
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");
		//adiciona ao objeto a sala e os filmes
		modelAndView.addObject("sala", salaDao.findOne(salaId));
		modelAndView.addObject("filmes", filmeDao.findAll());
		return modelAndView;
	}

}
