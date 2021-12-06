package edu.seu.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.seu.common.dto.LoginDto;
import edu.seu.common.lang.Result;
import edu.seu.entity.User;
import edu.seu.service.UserService;
import edu.seu.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class AccountController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AccountController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码不正确");
        }
        log.debug(SecureUtil.md5(user.getPassword()));
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map());
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Validated User user){
        User userExist = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (userExist != null) {
            return Result.fail("用户已存在");
        } else {
            User newUser = new User();
            BeanUtil.copyProperties(user, newUser);
            newUser.setCreated(LocalDateTime.now());
            newUser.setStatus(0);
            newUser.setPassword(SecureUtil.md5(user.getPassword()));
            userService.save(newUser);
            return Result.success("注册成功");
        }
    }

    @GetMapping("/username")
    public Result username(@RequestParam(name = "username")String username){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user == null){
            return Result.success(true);
        } else{
            // 用户名已存在
            return Result.success(false);
        }
    }

    @PostMapping("/password")
    @RequiresAuthentication
    public Result password(@RequestBody User user){
        if((user.getPassword()).equals(userService.getById(user.getId()))){
            return Result.fail("前后两次密码不可相同");
        } else {
            userService.updatePassword(SecureUtil.md5(user.getPassword()), user.getId());
            return Result.success("修改成功！");
        }
    }
}
