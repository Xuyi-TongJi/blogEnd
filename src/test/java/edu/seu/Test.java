package edu.seu;

import cn.hutool.crypto.SecureUtil;

public class Test {
    @org.junit.jupiter.api.Test
    void test01() {
        System.out.println(SecureUtil.md5("111111"));
    }
}
