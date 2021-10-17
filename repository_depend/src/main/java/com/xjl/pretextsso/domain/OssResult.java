package com.xjl.pretextsso.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname Result
 * @Description TODO
 * @Date 2021/10/17 12:48
 * @Created by xjl
 */
@Data
@Component
public class OssResult {
    private String osstpye;
    private String ossname;
    private String ossversion;
    private String baseline;
    private String lastversion;
    private String wherefoud;
    private String balmeinfo;
    private String resilt;
    private Date time;
}
