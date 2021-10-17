package com.xjl.pretextsso.vo;

/**
 * @Classname PackageVo
 * @Description TODO
 * @Date 2021/10/17 12:47
 * @Created by xjl
 */
public interface PackageResultVo {

    //将数据保存在本地
    public void SaveLocal(String usename);

    //将数据保存在远端
    public void SaveRemote(String usename);
}
