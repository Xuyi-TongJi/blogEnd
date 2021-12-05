package edu.seu.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.seu.common.lang.Result;
import edu.seu.entity.Blog;
import edu.seu.service.BlogService;
import edu.seu.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xuyi
 * @since 2021-12-01
 */
@RestController
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    // @RequiresAuthentication
    public Result list(@RequestParam(defaultValue = "1")Integer currentPage){
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(pageData);
    }

    @GetMapping("/blog/{id}")
    // @RequiresAuthentication
    public Result detail(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已删除");
        return Result.success(blog);
    }

    @PostMapping("/blog/edit")
    @RequiresAuthentication
    public Result edit(@RequestBody @Validated Blog blog){
        Blog temp = null;
        // 修改逻辑
        if (blog.getId() != null){
            temp = blogService.getById(blog.getId());
            // 只能修改自己的文章
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()), "没有权限编辑");
        } else {  // 新建逻辑
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        // 后面几个参数不复制
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);
        return Result.success(null);
    }
}
