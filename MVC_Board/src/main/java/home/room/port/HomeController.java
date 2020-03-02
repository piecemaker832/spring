package home.room.port;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import home.room.port.board.service.BoardService;
import home.room.port.commons.paging.PageMaker;
import home.room.port.commons.paging.SearchCriteria;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private final BoardService boardService;
	
	public HomeController(BoardService boardService) {
		// TODO Auto-generated constructor stub
		this.boardService = boardService;
	}

	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String list(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,Model model) throws Exception {
		logger.info("list...");
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(boardService.countSearchedArticles(searchCriteria));
		model.addAttribute("board",boardService.listSearch(searchCriteria));
		model.addAttribute("pageMaker",pageMaker);
		return "/board/list";
	}
	
}
