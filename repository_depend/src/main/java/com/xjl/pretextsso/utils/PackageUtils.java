package com.xjl.pretextsso.utils;

import com.xjl.pretextsso.domain.PythonPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PackageUtils
 * @Description TODO
 * @Date 2021/10/17 10:15
 * @Created by xjl
 */
public class PackageUtils {

    //获取文件下requirement.txt文件，同时读取内容并封装返回
    public static List<PythonPackage> FindTxtPackage(String filepath) {
        File file = new File(filepath);
        ArrayList arrayList = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String s = null;
            while ((s = br.readLine()) != null) {
                String[] strings = s.split("==");
                PythonPackage pythonPackage = new PythonPackage();
                pythonPackage.setPackagename(strings[0]);
                pythonPackage.setVersion(strings[1]);
                arrayList.add(pythonPackage);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
