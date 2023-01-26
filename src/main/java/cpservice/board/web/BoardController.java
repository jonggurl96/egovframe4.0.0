package cpservice.board.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cpservice.board.domain.BoardVO;
import cpservice.board.service.BoardService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@Value("#{pageConfigBean['page_size']}")
	private String pageSize;
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(Criteria cri, Model model) throws Exception {
		logger.info("read content bno = " + cri.getBno() + ", page = " + cri.getPage() + ", rcpp = " + cri.getRcpp());
		model.addAttribute("cri", cri);
		model.addAttribute("vo", service.select(cri.getBno()));
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modify(Criteria cri, Model model) throws Exception {
		logger.info("Move to modify page .......");
		logger.info("modify get method" + cri);
		model.addAttribute("cri", cri);
		model.addAttribute("vo", service.select(cri.getBno()));
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO vo,
			@RequestParam("page") int page,
			@RequestParam("rcpp") int rcpp,
			RedirectAttributes rttr) throws Exception {
		logger.info("Update " + vo);
		service.modify(vo);
		rttr.addFlashAttribute("msg", "수정이 완료되었습니다.");
		return "redirect:/board/read?bno=" + vo.getBno() + "&page=" + page + "&rcpp=" + rcpp;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(Criteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("Remove content where bno = " + cri.getBno());
		service.remove(cri.getBno());
		rttr.addFlashAttribute("msg", "삭제되었습니다.");
		return "redirect:/board/SPList?page=" + cri.getPage() + "&rcpp=" + cri.getRcpp();
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public void regist() throws Exception {
		logger.info("Move to regist page .......");
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(BoardVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("Regist content: " + vo);
		service.regist(vo);
		rttr.addFlashAttribute("msg", "글이 등록되었습니다.");
		return "redirect:/board/SPList?page=1&rcpp=10";
	}
	
	@RequestMapping("/board/home")
	public String home(HttpServletRequest request) throws Exception {
		logger.info("home .......");
		if(request.getSession().getAttribute("loginInfo") != null) {
			return "redirect:/board/SPList?page=1&rcpp=10";
		}
		return "redirect:/user/login";
	}
	
	@RequestMapping("/SPList")
	public void splist(@RequestParam("page") int page, 
			@RequestParam("rcpp") int rcpp, 
			@RequestParam(value = "tag", required = false) String tag, 
			@RequestParam(value = "keyword", required = false) String keyword, Model model) throws Exception{
		logger.info("SPList ......");
		logger.info("page: " + page + ", rcpp: " + rcpp);
		
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(page);
		pageInfo.setRecordCountPerPage(rcpp);
		pageInfo.setPageSize(Integer.parseInt(pageSize));
		if(tag != null) {
			pageInfo.setTotalRecordCount(service.getCountSearched(tag, keyword));
			model.addAttribute("tag", tag);
			model.addAttribute("keyword", keyword);
		} else {
			pageInfo.setTotalRecordCount(service.getCount());
			model.addAttribute("tag", "all");
			model.addAttribute("keyword", "");
		}
		
		logger.info("SPList pageInfo page = " + pageInfo.getCurrentPageNo());
		logger.info("pageInfo: page, rcpp, totalPageCount");
		logger.info("" + pageInfo.getCurrentPageNo() + ", " + pageInfo.getRecordCountPerPage() + ", " + pageInfo.getTotalPageCount());
		model.addAttribute("pageInfo", pageInfo);
	}
	
}
