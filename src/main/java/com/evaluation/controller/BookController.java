package com.evaluation.controller;

import com.evaluation.domain.Book;
import com.evaluation.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * <code>BookController</code>객체는 Book정보를 관리한다.
 */
@Controller
@RequestMapping("/book/*")
@Slf4j
public class BookController {

	@Autowired
	BookService bookService;

	/**
	 * Book 등록 화면을 표시한다.
	 */
	@GetMapping("/register")
	public void register() {

	}

	/**
	 * Book 정보를 등록한다.
	 * 
	 * @param book book 정보
	 * @param rttr 재전송 정보
	 * @return 북 목록 페이지
	 */
	@PostMapping("/register")
	public String register(Book book, RedirectAttributes rttr) {
		log.info("register" + book);

		bookService.register(book);

		rttr.addFlashAttribute("msg", "register");
		return "redirect:/book/list";
	}

	/**
	 * Book 정보를 읽어온다.
	 * 
	 * @param bno   book id
	 * @param model 화면 전달 정보
	 */
	@GetMapping("/read")
	public void read(int bno, Model model) {
		log.info("read" + bno);

		model.addAttribute("book", bookService.read(bno).orElse(null));
	}

	/**
	 * Book 정보를 수정한다.
	 * 
	 * @param book book 정보
	 * @param rttr 재전송 정보
	 * @return 목록 페이지
	 */
	@PostMapping("/modify")
	public String modify(Book book, RedirectAttributes rttr) {
		log.info("modify" + book);

		bookService.modify(book);

		rttr.addFlashAttribute("msg", "modify");
		return "redirect:/book/list";
	}

	/**
	 * Book 정보를 삭제한다.
	 * 
	 * @param bno  book id
	 * @param rttr 재전송 정보
	 * @return 목록 페이지
	 */
	@PostMapping("/remove")
	public String remove(int bno, RedirectAttributes rttr) {
		log.info("remove " + bno);

		bookService.remove(bno);

		rttr.addFlashAttribute("msg", "remove");
		return "redirect:/book/list";
	}

	/**
	 * Book 목록을 불러온다.
	 * 
	 * @param model 화면 전달 정보
	 */
	@GetMapping("/list")
	public void list(Model model) {
		bookService.findAll().ifPresent(origin -> {
			model.addAttribute("result", origin);
		});
	}

	/**
	 * 하나의 Book에 속한 회답 정보를 불러온다.
	 * 
	 * @param bno   book id
	 * @param model 화면 전달 정보
	 */
	@GetMapping("/contents")
	public void contents(int bno, Model model) {
		model.addAttribute("bno", bno);
		model.addAttribute("result", bookService.read(bno).map(Book::getContents).orElse(null));
	}
}
