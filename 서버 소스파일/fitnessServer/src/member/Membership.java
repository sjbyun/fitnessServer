package member;

import info.MemberInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbworking.DBWorkforMembership;

/**
 * Servlet implementation class Membership
 */
@WebServlet("/Membership")
public class Membership extends HttpServlet {
	private DBWorkforMembership dbwm ;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Membership() {
        super();
        // TODO Auto-generated constructor stub
        this.dbwm = new DBWorkforMembership();
        if(dbwm.dbConnect())
        	System.out.println("db연결 성공");
        else
        	System.out.println("db연결 실패");
        	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		ArrayList<MemberInfo> list = null;
		String forwardURL = null;
		
		String uri = request.getRequestURI();
		pw.println("GET - " + uri);
		switch(uri.substring(uri.lastIndexOf('/'), uri.length())){
		case "/ShowAllMemberList" :
			list = this.dbwm.showAllMemberList(Integer.parseInt(request.getParameter("startIdx")), Integer.parseInt(request.getParameter("cnt")));
			request.setAttribute("memberlist", list);
			System.out.println("test val - "+list.get(0).getName());
			request.setAttribute("test", list.get(0).getName());
			
			request.getSession().setAttribute("pageCnt", Integer.parseInt(request.getParameter("cnt")));
			request.getSession().setAttribute("totalCnt", this.dbwm.getTotalMemberCnt());
			request.getSession().setAttribute("pageNum", Integer.parseInt(request.getParameter("startIdx")) / Integer.parseInt(request.getParameter("cnt")));
			forwardURL = "/membershipList.jsp";
			break;
			
		case "/SearchMemberList" :
			list = this.dbwm.searchedMemberList(request.getParameter("name"), Integer.parseInt(request.getParameter("startIdx")), Integer.parseInt(request.getParameter("cnt")));
			request.setAttribute("memberlist", list);
			
			request.getSession().setAttribute("pageCnt", Integer.parseInt(request.getParameter("cnt")));
			request.getSession().setAttribute("totalCnt", this.dbwm.getTotalMemberCnt());
			request.getSession().setAttribute("pageNum", Integer.parseInt(request.getParameter("startIdx")) / Integer.parseInt(request.getParameter("cnt")));
			forwardURL = "/membershipList.jsp";
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		PrintWriter pw = response.getWriter();
		boolean result = false;
		
		switch(uri.substring(uri.lastIndexOf('/'), uri.length())){
		case "/tryLogin" :
			System.out.println("loginId - "+request.getParameter("loginId"));
			result = this.dbwm.tryLogin(request.getParameter("loginId"), request.getParameter("password"));
			pw.println("{'result' : '"+result+"'}");
			break;
			
		case "/InsertMember" :
			System.out.println("insert");
			result = this.dbwm.insertMember(request.getParameter("name"), Integer.parseInt(request.getParameter("age")), request.getParameter("phone"), request.getParameter("email"), 
					Boolean.parseBoolean(request.getParameter("PT")), Integer.parseInt(request.getParameter("contterm")), Integer.parseInt(request.getParameter("lockerRoomNum")), Boolean.parseBoolean(request.getParameter("isInfoAllow")));
			System.out.println("insert result - "+result);
			pw.println("{'result' : '"+result+"', 'name' : '"+request.getParameter("name")+"'}");
			break;
			
		case "/UpdateMember" : 
			System.out.println("update memberinfo");
			break;
			
		case "/DeleteMember" :
			System.out.println("delete Member");
			result = this.dbwm.deleteMember(request.getParameter("phone"));
			pw.println("{ 'result' : '"+result+"', 'phone' : '"+request.getParameter("phone")+"' }");
			break;
		}
	}

}
