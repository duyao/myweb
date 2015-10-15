package com.dy.controller;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dy.services.AssignService;
import com.dy.services.StudentService;
import com.dy.tools.Front;

public class Download extends HttpServlet {
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type=request.getParameter("type");
		
		String taskid=request.getParameter("taskid");
		String fileName=request.getParameter("filename");
		String stuid=null;
		if("downMy".equals(type)){
			HttpSession session=request.getSession();
			stuid=(String)session.getAttribute("stuid");
		}else if("downOther".equals(type)){
			stuid=request.getParameter("stuid");
		}
		
		
		
		//�����Ӵ����Ļ��������
		fileName = Front.getNewString(fileName);
		
		
		//�����ݿ��в����ļ����λ��
        String path=new AssignService().getPath(taskid, stuid);
        

        File file=new File(path + "\\" + fileName);
        //����ļ�������
        if(!file.exists()){
            request.setAttribute("message", "��Ҫ���ص���Դ�ѱ�ɾ������");
            request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
            return;
        }
        //�����ļ���
        String realname = fileName.substring(fileName.indexOf("_")+1);
        //������Ӧͷ��������������ظ��ļ�
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //��ȡҪ���ص��ļ������浽�ļ�������
        FileInputStream in = new FileInputStream(path + "\\" + fileName);
        //���������
        OutputStream out = response.getOutputStream();
        //����������
        byte buffer[] = new byte[1024];
        int len = 0;
        //ѭ�����������е����ݶ�ȡ������������
        while((len=in.read(buffer))>0){
            //��������������ݵ��������ʵ���ļ�����
            out.write(buffer, 0, len);
        }
        //�ر��ļ�������
        in.close();
        //�ر������
        out.close();

	}

	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}
