package edu.seu.controller;


import edu.seu.common.lang.Result;
import edu.seu.entity.User;
import edu.seu.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xuyi
 * @since 2021-12-01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequiresAuthentication  // 需要认证才能访问
    @GetMapping("/index")
    public Object index(){
        User data = userService.getById(1L);
        return Result.success(data);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.success(user);
    }
}
