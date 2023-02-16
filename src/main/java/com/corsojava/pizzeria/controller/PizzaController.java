package com.corsojava.pizzeria.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.pizzeria.model.Pizza;
import com.corsojava.pizzeria.repository.PizzaRepository;

import jakarta.validation.Valid;

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
	public String show(@PathVariable("id") Integer id, Model model) {
		Pizza  singolaPizza = pizzaRepository.getReferenceById(id);
		model.addAttribute("pizza",singolaPizza);
		return "pizzaDetail";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Pizza newPizza = new Pizza();
		model.addAttribute("pizza" ,newPizza);
		return "create";
	}
	
	@PostMapping("/create")
	public String strore(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult ,Model model) {
		
		if(bindingResult.hasErrors()) {
			return "/create";
		}
		
		pizzaRepository.save(formPizza);
		
		return "redirect:/pizza";
	}
	
	
	

}
