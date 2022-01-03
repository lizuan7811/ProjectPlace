package han.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import han.Ingre.IngreDAO;
import han.Ingre.IngreDAOImpl;
import han.Ingre.IngreVO;

@WebServlet("/Shop_SearchServlet")
public class Shop_SearchServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String name = req.getParameter("name");
		System.out.println("接收到前端傳來的資料：" + "name=" + name);
		
		if (name.equals("%%")) {
			JSONArray array = new JSONArray();
			resp.getWriter().print(array.toString());
		}else {
			
		IngreDAO dao = new IngreDAOImpl();
		List<IngreVO> list = dao.findName(name);
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		int m=list.size();
		for(int i=0; i<m; i++) {
			obj.clear();
			obj.put("idIngre", list.get(i).getIdIngre());
			obj.put("name", list.get(i).getName());
			obj.put("price", list.get(i).getPrice());
			obj.put("unit", list.get(i).getUnit());
			obj.put("gran", list.get(i).getGran());
			obj.put("sell", list.get(i).getSell());
//			System.out.println(list.get(i).getName());
			array.put(obj.toMap());	
		}
		resp.getWriter().print(array.toString());
		}
		
	}
}