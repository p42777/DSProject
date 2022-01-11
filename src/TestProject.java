import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
    	// Used as Main.java
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int search = 20;
		if(request.getParameter("searchNum") != null) {
			search = Integer.parseInt(request.getParameter("searchNum"));
		}
		
		// search.jsp
		if(request.getParameter("keyword")== null) { 
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		
		// googleitem.jsp
		GoogleQuery google; 
		google = new GoogleQuery(request.getParameter("keyword"));
		HashMap<String, String> query = google.query();
		int index = query.size();
		String[][] s = new String[index][2];
		request.setAttribute("query", s);
		
		int num = 0;
		for(Entry<String, String> entry : query.entrySet()) { 
		    String key = entry.getKey();
		    s[num][0] = key;
		    String val = entry.getValue();
		    s[num][1] = val;
		    num = num + 1;
		}
		
		KeywordList kLst = google.kLst;
		for(int i = 0 ; i < kLst.lst.size() ; i++) {
			 s[i][0] = kLst.lst.get(i).name;
			 s[i][1] = kLst.lst.get(i).url;
		}
		request.getRequestDispatcher("googleitem.jsp").forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}