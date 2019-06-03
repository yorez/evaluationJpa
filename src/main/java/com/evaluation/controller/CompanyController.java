package com.evaluation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.evaluation.domain.Company;
import com.evaluation.service.CompanyService;
import com.evaluation.vo.PageMaker;
import com.evaluation.vo.PageVO;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/company/**")
@Slf4j
public class CompanyController {

	@Setter(onMethod_ = { @Autowired })
	private CompanyService companyService;

	@GetMapping("/list")
	public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("controller : company list by " + vo);

		Page<Company> result = companyService.getList(vo);
		model.addAttribute("result", new PageMaker<>(result));
	}

	@GetMapping("/register")
	public void registerGET() {
		log.info("controller : company register get");
	}

	@PostMapping("/register")
	public String registerPost(Company vo, RedirectAttributes rttr) {
		log.info("controller : company register post " + vo);

		companyService.register(vo);
		rttr.addFlashAttribute("msg", "success");

		return "redirect:/company/list";
	}

	@GetMapping("/view")
	public void view(Long cno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("controller : company view get" + cno);

		companyService.get(cno).ifPresent(company -> model.addAttribute("vo", company));
	}

	@GetMapping("/modify")
	public void modifyGet(Long cno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("controller : company modfiy get " + cno);

		companyService.get(cno).ifPresent(company -> model.addAttribute("vo", company));
	}

	@PostMapping("/modify")
	public String modifyPost(Company company, PageVO vo, RedirectAttributes rttr) {
		log.info("controller : company modify post" + company);

		companyService.modify(company);

		rttr.addFlashAttribute("msg", "modify");
		rttr.addAttribute("cno", company.getCno());

		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/company/view";
	}

	@PostMapping("/delete")
	public String delete(Long cno, PageVO vo, RedirectAttributes rttr) {
		log.info("controller : company delete " + cno);

		companyService.remove(cno);
		rttr.addFlashAttribute("msg", "remove");
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/company/list";

	}

	@GetMapping("/turnList")
	public void turnList(@ModelAttribute("cno") Long cno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("controller : company surveyList");

		companyService.get(cno).ifPresent(company -> model.addAttribute("vo", company));
	}
}
