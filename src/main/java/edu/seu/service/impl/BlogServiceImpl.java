package edu.seu.service.impl;

import edu.seu.entity.Blog;
import edu.seu.mapper.BlogMapper;
import edu.seu.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
