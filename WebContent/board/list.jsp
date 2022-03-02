<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
	int sPage = (int) request.getAttribute("sPage");
	int ePage = (int) request.getAttribute("ePage");
	int ttPage = (int) request.getAttribute("ttPage");
%>
	{
		"sp" : "<%= sPage %>",
		"ep" : "<%= ePage %>",
		"ttp" : "<%= ttPage %>",
		"data" : [
<%				
					for(int i = 0; i < list.size(); i++){
						
						BoardVO vo = list.get(i);
						if(i>0){
							out.println(",");
						}
%>
						{
							"num" : "<%=vo.getNum() %>",
							"subject" : "<%=vo.getSubject()  %>",
							"writer" : "<%=vo.getWriter()  %>",
							"mail" : "<%=vo.getMail()  %>",
							"content" : "<%=vo.getContent()  %>",
							"hit" : "<%=vo.getHit()  %>",
							"wdate" : "<%=vo.getWdata()  %>"
						}
<%
					} // for end
%>						
				]	
	}