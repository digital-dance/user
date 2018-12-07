///*
// * 名称：ImageUploadController.java
//
// * 项目：Midea消费金融项目
// * 描述：影像上传Controller类
// * 创建人：李海飞
// * 创建时间：2016-8-31
// * 修改人：
// * 修改时间：
// * 修改内容：
// */
//package com.digital.dance.home.imageUpload;
//
//import java.io.BufferedInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.digital.dance.user.commons.Log;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.digital.dance.user.commons.GsonUtils;
//import com.digital.dance.user.commons.AppPropsConfig;
//import com.digital.dance.user.commons.ResponseVo;
////import com.digital.dance.user.commons.WeChatApiUtil;
//import com.digital.dance.home.BaseController;
//import com.digital.dance.commons.security.Base64Coder;
//import com.digital.dance.document.business.bo.BusinessDocBO;
//import com.digital.dance.document.business.service.BusDubboService;
//
//
///**
// *
// * @author  xinyuliu
// * @version user v1.0
// * @since   user v1.0
// */
//
//@Controller
//@RequestMapping("/upload")
//public class ImageUploadController extends BaseController
//{
//    Log log = new Log(ImageUploadController.class);
//    //@Autowired
//    private BusDubboService busDubboService;
//    /**
//     * * 图片到byte数组
//     * * @param path
//     * * @return
//     * @throws IOException */
//    public static byte[] image2byte(String path) throws IOException {
//        File file = new File(path);
//        byte[] data = null;
//        BufferedInputStream  input = new BufferedInputStream(new FileInputStream(file));
//        ByteArrayOutputStream output = new ByteArrayOutputStream((int)file.length());
//        try {
//            byte[] buf = new byte[ 1024 ];
//            int numBytesRead = 0;
//            while( ( numBytesRead = input.read( buf ) ) != -1 ) {
//                output.write( buf, 0, numBytesRead );
//            }
//            data = output.toByteArray();
//        } catch( FileNotFoundException ex1 ) {
//
//        } catch( IOException ex1 ) {
//
//        }finally{
//            try {
//                output.close();
//                input.close();            }
//            catch( IOException e ) {
//
//            }
//        }
//        return data;
//    }
//
//
//    /**
//     * 影像上传
//     *
//     * @param
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("imageUpload")
//    @ResponseBody
//    public ResponseVo upload(HttpServletRequest request,
//            HttpServletResponse response,@RequestParam(required=false) String imgPath)  {
//        ResponseVo responsevo=new ResponseVo();
//        BusinessDocBO bo = new BusinessDocBO();
//        bo.setBusinessType( 4L );
//        bo.setBusinessSubType( 7L );
//        bo.setBusinessNo( "0" );
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        String fileName = sdf.format(new Date());
//        bo.setFileName(fileName+".png");
//        String userName=getUserName(request);
//        bo.setCreateUserId(userName);
//        String orderId="";
//        try
//        {
//        ByteArrayOutputStream out= null;// (ByteArrayOutputStream)WeChatApiUtil.downloadMedia("",PropsConfig.getPropValue("access_token"),imgPath);
//        Long id = busDubboService.uploadBusinessDoc( bo, out.toByteArray() );
//        orderId= Long.toString(id);
//        }catch (Exception e)
//
//        {
//            e.printStackTrace();
//            responsevo.setCode("1");
//            responsevo.setMsg("上传图片失败");
//            return responsevo;
//            // TODO Auto-generated catch block
//
//        }
//        responsevo.setCode("1001");
//        responsevo.setMsg("上传图片成功");
//        responsevo.setResult(orderId);
//        return responsevo;
//    }
//
//    /**
//     * 照片核查
//     * @param
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("imageCheck")
//    @ResponseBody
//    public String check(HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//
//        byte[] bytes = image2byte("F:/test/bb.png");
//        String imageStr = Base64Coder.encode(bytes).toString();
//
//
//        return imageStr;
//    }
//
//    public static void main(String[] args)
//    {
//        ResponseVo responsevo=new ResponseVo();
//        responsevo.setCode("1001");
//        responsevo.setMsg("上传图片成功");
//        responsevo.setResult("4545s4df45sdf4s5dfsdf45s5");
//        System.out.println(GsonUtils.toJson(responsevo));
//    }
//}
