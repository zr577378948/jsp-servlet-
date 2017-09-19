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
		//��ȡǰ̨�����option��ֵ
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
		 * ����
		 */
		//����һ��
		/*String fileName = request.getParameter("fileName");
		System.out.println(fileName);
		//�������ļ�
		String path = "d:/"+fileName;
		Reader fr = new FileReader(path);
		Writer out = response.getWriter();
		//ͨ��������Ӧͷ����֪׼���ظ����ͻ��˵ı�����Ӧ�Ĵ���ʽ�͸�ʽ����С�ȸ�����Ϣ
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
		//��������
		String fileName = request.getParameter("fileName");
//		�������ļ�
		String path = "d:/"+fileName;
//		��ȡ�ļ�
		FileInputStream in = new FileInputStream(path);
//		����ļ�
		OutputStream out = response.getOutputStream();
		//ͨ��������Ӧͷ����֪׼���ظ����ͻ��˵ı�����Ӧ�Ĵ���ʽ�͸�ʽ����С�ȸ�����Ϣ
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
		 * �ϴ�
		 */
	     //ʹ��smartupload��ʵ���ϴ�
		SmartUpload  su = new SmartUpload();
		//��ȡ����ҳ������ϣ���ʼ���ϴ���Ϣ��pageContext��
		// pageContext = _jspxFactory.getPageContext(this, request, response,
			//null, true, 8192, true);
		//�ǽ�����ҳ��index.jspҳ�����Ϣ����ʼ����smartupload�����С�����ҳ���е���Ϣ�ʹ浽��su������
		su.initialize(JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true));
		try {
			//���ò������ϴ�������
			su.setDeniedFilesList("java,txt");
			//�����������ϴ�5MB�ļ�
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
