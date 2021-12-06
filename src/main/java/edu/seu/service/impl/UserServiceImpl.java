package edu.seu.service.impl;

import edu.seu.entity.User;
import edu.seu.mapper.UserMapper;
import edu.seu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xuyi
 * @since 2021-12-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void updatePassword(@NotNull String md5, @NotNull Long id) {
        userMapper.updatePassword(md5, id);
    }
}
