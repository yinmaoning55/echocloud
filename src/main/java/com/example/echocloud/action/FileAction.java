package com.example.echocloud.action;



import com.example.echocloud.domain.Music;
import com.example.echocloud.mapper.IMusicDao;
import com.example.echocloud.util.HBaseConn;
import com.example.echocloud.util.HBaseUtil;
import com.soft.common.util.DateUtil;
import com.soft.common.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class FileAction {
    @Autowired
    IMusicDao musicDao;
    @RequestMapping("file/dowmload")
    public String musicUpload(@RequestParam("dowmload") MultipartFile file, Music music, Model model,HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
        long maximunSize=31457280;
        System.out.println("传输文件大小"+file.getSize());
        String returnPage = "uploadImg";
        if(musicDao.getMusic(music)==null){
            try {
                //重命名该文件
                HBaseUtil.getRow("echo",music.getMusic_name2());
                String old_name=file.getOriginalFilename();
                System.out.println("文件名："+old_name);
                String file_name=DateUtil.dateToDateString(new Date(),"yyyyMMddHHmmssSSS")+old_name.substring(old_name.indexOf("."));
                //设置保存文件位置
                System.out.println("重命名文件名："+file_name);
                String savePath = "admin/images/datas";
                System.out.println(savePath);
                httpSession.setAttribute("music_name2",file_name);





            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("tip", "no");
                model.addAttribute("errorString", "后台服务器异常");
                return returnPage;
            }
        }else {
            return null;
        }


        return returnPage;
    }
//    @RequestMapping("file/download")
//    public String musicDownLoad(@RequestParam("download") MultipartFile file, String num,ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//        if()
//    }

}
