package edu.seu.mapper;

import edu.seu.entity.Blog;
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
public interface BlogMapper extends BaseMapper<Blog> {

}
