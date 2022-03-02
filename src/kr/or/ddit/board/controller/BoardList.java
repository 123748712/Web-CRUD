package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/List.do")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// 요청데이터
		int sPage = Integer.parseInt(request.getParameter("page")); // request.getParameter("page") => String type임
		
		// 한 화면에 출력할 페이지 수
		int perPage = 2;
		
		// 한 페이지에 출력할 글 갯수
		int perList = 5;
		
		// 전체 글 갯수 조회 (Service객체 필요)
		BoardServiceImpl service = BoardServiceImpl.getService();
		int count = service.countList();
		
		// 전체 페이지 수 = 전체 글 겟수 / 페이지 당 글 갯수
		int totalPage = (int) Math.ceil((double)count / (double)perList);
		System.out.println(">>>>>>>>>> " + 21 / perList);
		System.out.println(">(double)> " + (double)21 / (double)perList);
		
		// 페이지에 표현할 게시글 번호 구하기 start - end
		int start = (sPage-1) * perList + 1;
		// start = (sPage(현재페이지)-1)*5+1
		// 			(1page-1)*5+1 = 1
		// 			(2page-1)*5+1 = 6
		
		// 한 페이지의 끝 번호
		int end = start + perList -1;
		// end = start + perList -1;
		//		 1 + 5 - 1 = 5;
		//		 6 + 5 - 1 = 10;
		
		// 전체 글 갯수보다 end값이 클 경우가 발생할 가능성 있음
		if(end > count) {
			end = count;
		}

		int startPage = ((sPage-1) / perPage * perPage) + 1;
		// 화면에 표시될 대 페이지 번호 구하기 startPage ~ endPage
		// startPage = ((sPage-1) / perPage * perPage) + 1
		//				(1page-1) / 2 * 2) + 1 | (2page-1) / 2 * 2) + 1 = 1
		//				(2page-1) / 2 * 2) + 1 | (4page-1) / 2 * 2) + 1 = 3
		//				(3page-1) / 2 * 2) + 1 | (6page-1) / 2 * 2) + 1 = 5
		
		int endPage = startPage + perPage -1;
		// endPage = startPage + perPage -1;
		// 			1page / 2page일 때 1 + 2 - 1 = 2;
		// 			3page / 4page일 때 3 + 2 - 1 = 4;
		// 			5page / 6page일 때 5 + 2 - 1 = 6;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO>list = service.boardList(map);
		
		// jsp로 값을 보내기 위해 request에 저장
		request.setAttribute("list", list);
		request.setAttribute("sPage", startPage);
		request.setAttribute("ePage", endPage);
		request.setAttribute("ttPage", totalPage);
		
		request.getRequestDispatcher("board/list.jsp").forward(request, response);
	}
}