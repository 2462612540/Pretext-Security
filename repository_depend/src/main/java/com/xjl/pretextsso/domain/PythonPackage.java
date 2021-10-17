package com.xjl.pretextsso.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Classname Package 包名
 * @Description
 * @Date 2021/10/17 10:10
 * @Created by xjl
 */
@Component
@Data
public class PythonPackage {
    private String packagename;
    private String version;
}
