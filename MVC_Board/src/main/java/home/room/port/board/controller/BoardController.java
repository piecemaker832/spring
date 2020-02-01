package home.room.port.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import home.room.port.board.domain.BoardVO;
import home.room.port.board.service.BoardService;
import home.room.port.commons.paging.PageMaker;
import home.room.port.commons.paging.SearchCriteria;
import home.room.port.reply.domain.ReplyVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		// TODO Auto-generated constructor stub
		this.boardService = boardService;
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String writeGET() {
		logger.info("write GET...");
		
		return "/board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writePOST(BoardVO boardVO, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("write POST...");
		logger.info(boardVO.toString());
		boardVO.setBoardContent(boardVO.getBoardContent().replace("\r\n", "<br>"));
		boardService.create(boardVO);
		redirectAttributes.addFlashAttribute("msg","regSuccess");
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,Model model) throws Exception {
		logger.info("list...");
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(boardService.countSearchedArticles(searchCriteria));
		model.addAttribute("board",boardService.listSearch(searchCriteria));
		model.addAttribute("pageMaker",pageMaker);
		return "/board/list";
	}
	
	//조회
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String read(@RequestParam("boardNo") int boardNo,@ModelAttribute("searchCriteria")SearchCriteria searchCriteria,
			Model model) throws Exception {
		logger.info("read. . . ");
		boardService.updateHit(boardNo);
		model.addAttribute("board",boardService.read(boardNo));
		model.addAttribute("replyVO", new ReplyVO());
		return "/board/read";
	}
	
	//수정페이지 이동
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modifyGET(@RequestParam("boardNo") int boardNo, @ModelAttribute("searchCriteria")SearchCriteria searchCriteria,
			Model model) throws Exception {
		logger.info("modifyGET . . .");
		model.addAttribute("board",boardService.read(boardNo));
	
		return "/board/modify";
	}
	
	//수정처리
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPOST(BoardVO boardVO,
			@ModelAttribute("searchCriteria")SearchCriteria searchCriteria,
			RedirectAttributes redirectAttributes) throws Exception {
		logger.info("modifyPOST...");
		boardService.update(boardVO);
		redirectAttributes.addAttribute("page",searchCriteria.getPage());
		redirectAttributes.addAttribute("perPageNum",searchCriteria.getPerPageNum());
		redirectAttributes.addAttribute("searchType",searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword",searchCriteria.getKeyword());
		redirectAttributes.addFlashAttribute("mgs","modSuccess");
		
		return "redirect:/board/list";
	}
	
	//삭제 처리
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(@RequestParam("boardNo") int boardNo,
			@ModelAttribute("searchCriteria")SearchCriteria searchCriteria,
			RedirectAttributes redirectAttributes) throws Exception {
		logger.info("remove...");
		
		boardService.delete(boardNo);
		redirectAttributes.addAttribute("page",searchCriteria.getPage());
		redirectAttributes.addAttribute("perPageNum",searchCriteria.getPerPageNum());
		redirectAttributes.addAttribute("searchType",searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword",searchCriteria.getKeyword());
		redirectAttributes.addFlashAttribute("msg","delSuccess");
		
		return "redirect:/board/list";
	}
	
	//답글 등록 페이지
	@RequestMapping(value="/response", method=RequestMethod.GET)
	public String replyGET(@RequestParam("boardNo") int boardNo,@ModelAttribute("searchCriteria")SearchCriteria searchCriteria,
			Model model) throws Exception {
		logger.info("response GET...");
		
		model.addAttribute("board",boardService.read(boardNo));
		
		return "/board/response";
	}
	
	//답글 등록
	@RequestMapping(value="/response",method=RequestMethod.POST)
	public String replyPOST(BoardVO boardVO,RedirectAttributes redirectAttributes) throws Exception{
		logger.info("response POST...");
		logger.info(boardVO.toString());
		
		boardService.responseCreate(boardVO);
		redirectAttributes.addFlashAttribute("msg","regSuccess");

		return "redirect:/board/list";
	}

}
