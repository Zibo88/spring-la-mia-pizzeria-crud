package com.corsojava.pizzeria.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.pizzeria.model.Pizza;
import com.corsojava.pizzeria.repository.PizzaRepository;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
	
	@Autowired
	private  PizzaRepository pizzaRepository;
	
	@GetMapping
	public String index(@RequestParam(name="keyword", required = false) String keyword, Model model) {
		List <Pizza> elencoPizze;
		if(keyword == null) {
			elencoPizze = pizzaRepository.findAll();
		}else {
			elencoPizze = pizzaRepository.findByNomeLike("%"+keyword+"%");
		}
		
		model.addAttribute("elencoPizze", elencoPizze);
		return "index";
	}
	
	@GetMapping("/{id}")
	public String dettaglioPizza(@PathVariable("id") Integer id, Model model) {
		Pizza  singolaPizza = pizzaRepository.getReferenceById(id);
		model.addAttribute("pizza",singolaPizza);
		return "pizzaDetail";
	}
	
	
	

}
