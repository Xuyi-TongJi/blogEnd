package edu.seu;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import edu.seu.entity.User;
import edu.seu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VueblogApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User one = userService.getOne(new QueryWrapper<User>().eq("username", "xuyi"));
        System.out.println(SecureUtil.md5(one.getPassword()));
    }

    @Test
    void test02(){
        User user = new User();
        user.setId(1L);
        user.setPassword("11111111");
        userService.updatePassword(SecureUtil.md5(user.getPassword()), user.getId());
    }
}
