package edu.seu.mapper;

import edu.seu.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xuyi
 * @since 2021-12-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void updatePassword(String password, Long id);
}
