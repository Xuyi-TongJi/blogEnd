package edu.seu.service.impl;

import edu.seu.entity.User;
import edu.seu.mapper.UserMapper;
import edu.seu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
