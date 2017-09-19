package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/FileServlet")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取前台传入的option的值
		String option = request.getParameter("option");
		if("download".equals(option)){
			download(request, response);
		}else if("upload".equals(option)){
			upload(request, response);
		}

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 下载
		 */
		//方法一：
		/*String fileName = request.getParameter("fileName");
		System.out.println(fileName);
		//拷贝的文件
		String path = "d:/"+fileName;
		Reader fr = new FileReader(path);
		Writer out = response.getWriter();
		//通过设置响应头，告知准备回复给客户端的本次响应的处理方式和格式、大小等附加信息
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		response.setHeader("Content-Type", "text/plain");
		int data = fr.read();
		while(data!=-1){
			out.write(data);
			data = fr.read();
			out.flush();
		}
		out.close();
		fr.close();*/
		//方法二：
		String fileName = request.getParameter("fileName");
//		拷贝的文件
		String path = "d:/"+fileName;
//		读取文件
		FileInputStream in = new FileInputStream(path);
//		输出文件
		OutputStream out = response.getOutputStream();
		//通过设置响应头，告知准备回复给客户端的本次响应的处理方式和格式、大小等附加信息
				response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
				response.setHeader("Content-Type", "text/plain");
		int n;
		while((n=in.read())!=-1){
			out.write(n);
		}
		in.close();
		out.close();
	}

	protected void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 上传
		 */
	     //使用smartupload类实现上传
		SmartUpload  su = new SmartUpload();
		//获取请求页面的资料，初始化上传信息（pageContext）
		// pageContext = _jspxFactory.getPageContext(this, request, response,
			//null, true, 8192, true);
		//是将请求页面index.jsp页面的信息，初始化到smartupload对象中。请求页面中的信息就存到了su对象中
		su.initialize(JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true));
		try {
			//设置不允许上传的类型
			su.setDeniedFilesList("java,txt");
			//设置最大可以上传5MB文件
			su.setMaxFileSize(5*1024*1024);
			su.upload();
			File saveFile = new File("D:/test");
			su.save(saveFile.getPath());
		} catch (SmartUploadException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//String uname = su.getRequest().getParameter("uname");
	}


}
