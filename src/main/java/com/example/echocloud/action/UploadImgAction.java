package com.example.echocloud.action;

import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.echocloud.util.HBaseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.soft.common.util.DateUtil;
import com.soft.common.util.UploadFile;

@Controller
public class UploadImgAction {
	public static String path = "/application.properties";  //保存数据库连接信息的属性文件的相对路径
	public static Properties props = new Properties();
	static{
		props = new Properties();
		try {
			props.load(UploadImgAction.class.getClassLoader().getResourceAsStream(path));
		} catch (Exception e) {
			props = new Properties();
		}
	}
	
	/**
	 * @Title: UploadImg
	 * @Description: 上传文件
	 * @return String
	 */
	@RequestMapping(value="admin/UploadImg.action",method=RequestMethod.POST)
	public String UploadImg(@RequestParam("upload") MultipartFile file,String num,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
           long maximunSize=31457280;
		System.out.println("传输文件大小"+file.getSize());
		String returnPage = "uploadImg";
		try {
			//重命名该文件
			String old_name=file.getOriginalFilename();
			System.out.println("文件名："+old_name);
			String file_name=DateUtil.dateToDateString(new Date(),"yyyyMMddHHmmssSSS")+old_name.substring(old_name.indexOf("."));
			//设置保存文件位置
			System.out.println("重命名文件名："+file_name);
			String savePath = "admin/images/datas";
			System.out.println(savePath);
			if ("2".equals(num)) {

			    httpSession.setAttribute("music_name2",file_name);
                System.out.println("----------------"+HBaseUtil.getRow("echo","1").toString());
				HBaseUtil.putRow("echo",file_name,"music","music_name2",file);

				returnPage = returnPage+"2";
				System.out.println("returnPage:"+returnPage);
			}else if ("3".equals(num)) {
                httpSession.setAttribute("lrc_name",file_name);

				returnPage = returnPage+"3";
			}else {
				httpSession.setAttribute("pic_name",file_name);
				System.out.println((String)httpSession.getAttribute("pic_name"));
			}
			System.out.println("-----------------------------------------------");
			String saveFile="G:\\IdeaProject\\echocloud\\src\\main\\webapp\\admin\\images\\datas"+"\\"+file_name;
			System.out.println(".........................................");
			System.out.println("文件路径"+saveFile);
			//文件类型限制
			String allowedTypes = "image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/x-png,image/pjpeg";
			if ("2".equals(num)) {
				allowedTypes ="";
			}else if ("3".equals(num)) {
				allowedTypes = "";
			}
			//上传文件
			String errorString=UploadFile.upload(file, saveFile, file.getContentType(), file.getSize(), allowedTypes,Long.parseLong("31457280"));
			//判断上传结果
			if(!"".equals(errorString))
			{
				System.out.println(errorString);
				model.addAttribute("tip", "no");
				model.addAttribute("errorString", errorString);
				return returnPage;
			}
			model.addAttribute("tip", "ok");

			model.addAttribute("music_name2",file_name);
			model.addAttribute("filenameGBK",old_name);
			model.addAttribute("filelength",Math.round(file.getSize()/1024.0));
			return returnPage;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("tip", "no");
			model.addAttribute("errorString", "后台服务器异常");
			return returnPage;
		}
	}
}
