package com.nbdeli.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.nbdeli.test.common.constant.TencentAIConstants;
import com.nbdeli.test.common.utils.AiRequestBean;
import com.nbdeli.test.common.utils.Base64Util;
import com.nbdeli.test.common.utils.FileUtil;
import com.nbdeli.test.common.utils.MapUtil;
import com.nbdeli.test.dom.jsonentity.PornMsg;
import com.nbdeli.test.dom.jsonentity.TagList;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dukaixiang
 * @date 2019/4/30.
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) throws IOException {
//        //智能AI对话
//        Map mapParams=new HashMap<>();
//        mapParams.put("session","10000");
//        mapParams.put("question","test");
//        AIQuestion jsonEntity= JSONObject.parseObject(AiRequestBean.getResultByURL(TencentAIConstants.URL4DIALOGUE,mapParams),AIQuestion.class);
//        if(jsonEntity.getRet().equals("0")){
//            System.out.println(jsonEntity.getData().getAnswer());
//        }else {
//            System.out.println("返回错误消息："+jsonEntity.getMsg());
//        }

//        //大头贴修饰
//        Map mapParams=new HashMap<>();
//        mapParams.put("sticker","3");//模板编号
//        String filePath="C:\\Users\\Administrator\\Pictures\\mypicture";
//        File file=new File(filePath+"\\1.jpg");
//        byte[] imageData = FileUtil.readFileByBytes(file);
//        //准备好图片base64数据
//        String img64 = Base64Util.encode(imageData);
//        mapParams.put("image",img64);
//        ImageMsg jsonEntity=JSONObject.parseObject(AiRequestBean.getResultByURL(TencentAIConstants.URL4PURIKURA,mapParams), ImageMsg.class);
//        if(jsonEntity.getRet().equals("0")){
//            PictureTurn.generateImage(jsonEntity.getData().getImage(),filePath+"\\3.jpg");
//        }else {
//            System.out.println("返回错误消息："+jsonEntity.getMsg());
//        }

//        //颜龄检测
//        Map mapParams=new HashMap<>();
//        String filePath="C:\\Users\\Administrator\\Pictures\\mypicture";
//        File file=new File(filePath+"\\1.jpg");
//        byte[] imageData = FileUtil.readFileByBytes(file);
//        //准备好图片base64数据
//        String img64 = Base64Util.encode(imageData);
//        mapParams.put("image",img64);
//        ImageMsg jsonEntity= JSONObject.parseObject(AiRequestBean.getResultByURL(TencentAIConstants.URL4AGE_FACE,mapParams), ImageMsg.class);
//        if(jsonEntity.getRet().equals("0")){
//            PictureTurn.generateImage(jsonEntity.getData().getImage(),filePath+"\\3.jpg");
//        }else {
//            System.out.println("返回错误消息："+jsonEntity.getMsg());
//        }


//        //美食识别
//        Map mapParams=new HashMap<>();
//        String filePath="C:\\Users\\Administrator\\Pictures\\mypicture";
//        File file=new File(filePath+"\\1.jpg");
//        byte[] imageData = FileUtil.readFileByBytes(file);
//        //准备好图片base64数据
//        String img64 = Base64Util.encode(imageData);
//        mapParams.put("image",img64);
//        FoodMsg jsonEntity= JSONObject.parseObject(AiRequestBean.getResultByURL(TencentAIConstants.URL4IMAGE_FOOD,mapParams), FoodMsg.class);
//        if(jsonEntity.getRet().equals("0")){
//            if(jsonEntity.getData().getFood().equals("false")){
//                System.out.println("不是食物图片哦！");
//            }else {
//                System.out.println("是食物图片哦！");
//            }
//        }else {
//            System.out.println("返回错误消息："+jsonEntity.getMsg());
//        }

        //图片鉴黄
        Map mapParams=new HashMap<>();
        String filePath="C:\\Users\\Administrator\\Pictures\\mypicture";
        File file=new File(filePath+"\\jy.jpg");
        byte[] imageData = FileUtil.readFileByBytes(file);
        //准备好图片base64数据
        String img64 = Base64Util.encode(imageData);
        mapParams.put("image",img64);
        String jsonResult=AiRequestBean.getResultByURL(TencentAIConstants.IMAGE_OBJECT,mapParams);
        System.out.println(jsonResult);
        PornMsg jsonEntity= JSONObject.parseObject(AiRequestBean.getResultByURL(TencentAIConstants.IMAGE_PORN,mapParams),PornMsg.class);
        if(jsonEntity.getRet().equals("0")){
            Map baseMap= MapUtil.convert2HashMap("normal","正常","hot","性感","porn","黄色图像","female-genital","女性阴部",
                    "female-breast","女性胸部","male-genital","男性阴部","pubes","阴毛","anus","肛门","sex","性行为","normal_hot_porn","图像为色情的综合值");
            List<TagList> tagList= jsonEntity.getData().getTag_list();
            for (TagList tagObj:tagList) {
                System.out.println(baseMap.get(tagObj.getTag_name())+":"+"可信百分比-"+tagObj.getTag_confidence_f()+","+"可信值-"+tagObj.getTag_confidence());
                baseMap.put(tagObj.getTag_name(),tagObj.getTag_confidence());
            }
            if(baseMap.get("porn")!=null&&Integer.valueOf(baseMap.get("porn").toString())>83){
                System.out.println("色情图片");
            }else if(baseMap.get("hot")!=null&&baseMap.get("normal")!=null&&(Integer.valueOf(baseMap.get("hot").toString())>Integer.valueOf(baseMap.get("normal").toString()))){
                System.out.println("性感图片");
            }else {
                System.out.println("正常图片");
            }
        }else {
            System.out.println("返回错误消息："+jsonEntity.getMsg());
        }



    }
}
