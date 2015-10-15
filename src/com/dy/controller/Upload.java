package com.dy.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.domain.Assignment;
import com.dy.services.AssignService;

public class Upload extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//PrintWriter out = response.getWriter();
		
		//�õ���¼�ߵ�studi+taskid��Ϊ·��
		HttpSession session=request.getSession();
		String stuid=(String)session.getAttribute("stuid");
		String taskid=request.getParameter("taskid");
		//String trymydes = request.getParameter("mydes");
		
		
		//�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload/"+taskid+"/"+stuid);
        File file = new File(savePath);
        //�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
        if (!file.exists() && !file.isDirectory()) {
            //System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
            //����Ŀ¼
            file.mkdirs();
        }
        //��Ϣ��ʾ
        String message = "";
        String filename="";
     	String mydes="";
    	String value="";
        try{
            //ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
            //1������һ��DiskFileItemFactory����
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2������һ���ļ��ϴ�������
            ServletFileUpload upload = new ServletFileUpload(factory);
             //����ϴ��ļ�������������
            upload.setHeaderEncoding("UTF-8"); 
            //3���ж��ύ�����������Ƿ����ϴ���������
            if(!ServletFileUpload.isMultipartContent(request)){
                //���մ�ͳ��ʽ��ȡ����
                return;
            }
            //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
            List<FileItem> list = upload.parseRequest(request);
            
            for(FileItem item : list){
            	
                //���fileitem�з�װ������ͨ�����������
                if(item.isFormField()){
                	//�õ���������籾�ε��ύ���-mydes
                	mydes = item.getFieldName();
                    //�����ͨ����������ݵ�������������
                	value= item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    //System.out.println(mydes + "=" + value);
                    //�ύ��ȥ
                    request.setAttribute(mydes, value);
                }else{//���fileitem�з�װ�����ϴ��ļ�
                    //�õ��ϴ����ļ����ƣ�
                    filename = item.getName();
                    
                    //System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
                    //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
                    //��ӡ�����ļ�ʱxx\xx\xx\��б�ܴ������\\
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //��ȡitem�е��ϴ��ļ���������
                    InputStream in = item.getInputStream();
                    //����һ���ļ������
                    FileOutputStream out = new FileOutputStream(savePath+ "\\" + filename);
                    //����һ��������
                    byte buffer[] = new byte[1024];
                    //�ж��������е������Ƿ��Ѿ�����ı�ʶ
                    int len = 0;
                    //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
                    while((len=in.read(buffer))>0){
                        //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
                        out.write(buffer, 0, len);
                    }
                    //�ر�������
                    in.close();
                    //�ر������
                    out.close();
                    //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
                    item.delete();
                    message = "�ļ��ϴ��ɹ���";
                }
            }
            
           
            
            
            
        }catch (Exception e) {
            message= "�ļ��ϴ�ʧ�ܣ�";
            e.printStackTrace();
            
        }
        
        //��ȡʱ��
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String timeString=simpleDateFormat.format(new Date());
        //д�����ݿ���
        Assignment assignment=new Assignment(Integer.parseInt(taskid),Integer.parseInt(stuid), timeString, savePath, filename, value);
        Boolean boolean1=new AssignService().addAllAssgin(assignment);
        
        
        request.setAttribute("message",message);
        request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}
