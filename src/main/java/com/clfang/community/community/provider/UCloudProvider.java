package com.clfang.community.community.provider;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName:UcloudProvider
 * Package:com.clfang.community.community.provider
 * Description:
 *上传图片
 * @Date:2020/4/10 21:37
 * @Author:CLFang
 */
@Service
public class UCloudProvider {
    @Value("${ucloud.ufile.accessKey}")
    private String accessKey;
    @Value("${ucloud.ufile.secretKey}")
    private String secretKey;
    @Value("${ucloud.ufile.bucket}")
    private String bucket;
    @Value("${ucloud.ufile.prixurl}")
    private String prixUrl;//回显图片地址

    public String upload(byte[] bytes,String fileName){
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);
        //uuid + 扩展名
        String key = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
        ByteArrayInputStream byteInputStream=new ByteArrayInputStream(bytes);
        //System.out.println(byteInputStream.toString()+"***"+byteInputStream);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //System.out.println(putRet.key);
            //System.out.println(putRet.hash);
            return prixUrl+putRet.key+"?t="+new Date().getTime();
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }
}