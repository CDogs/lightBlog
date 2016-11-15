package com.cdogs.lightBlog.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * �ļ���������
 * Created by CDogs on 2016/6/14.
 */
public class FileUtils {

    /**
     * �����ϴ��ļ������ؾ���·��
     * @param upload
     * @param basePath
     * @return
     */
    public static String saveFile(File upload, String basePath){

        String originFileName = upload.getName();
        System.out.println(originFileName);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd");
        String subPath = simpleDateFormat.format(new Date());
        File dir = new File(basePath + subPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String path = basePath + subPath + UUID.randomUUID() + originFileName.substring(originFileName.indexOf("."));

        upload.renameTo(new File(path));

        return path;

    }

    /**
     * �����ϴ��ļ�(MultparFile)�����ؾ���·��
     * @param upload
     * @param basePath
     * @return
     */
    public static String saveMultipartFile(MultipartFile upload, String basePath){

        String originFileName = upload.getOriginalFilename();
        System.out.println(originFileName);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd");
        String subPath = simpleDateFormat.format(new Date());
        File dir = new File(basePath + subPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String path = basePath + subPath + UUID.randomUUID() + originFileName.substring(originFileName.indexOf("."));

        try {
            upload.transferTo(new File(path));
        } catch (IOException e) {
            System.out.println("�ļ��ϴ�ʧ��");
            e.printStackTrace();

        }

        return path;

    }

    /**
     * �����ϴ��ļ�(MultparFile),�����ض�·��,�������·��
     * @param upload
     * @param basePath
     * @return
     */
    public static String saveMultipartFileRelative(MultipartFile upload, String basePath){

        String originFileName = upload.getOriginalFilename();
        System.out.println(originFileName);

        File dir = new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String path = basePath + UUID.randomUUID() + originFileName.substring(originFileName.indexOf("."));

        try {
            upload.transferTo(new File(path));
        } catch (IOException e) {
            System.out.println("�ļ��ϴ�ʧ��");
            e.printStackTrace();

        }

        String revativePath = path.substring(path.indexOf("/"));
        return revativePath;

    }
}
