package com.oj.zoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oj.zoj.model.entity.Post;
import java.util.Date;
import java.util.List;

/**
 * 帖子数据库操作
 *
 * @author <a href="https://github.com/Missiu">huzhihao</a>
 * @from <a href="https://www.huzhihao.top">个人博客</a>
 */
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 查询帖子列表（包括已被删除的数据）
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

}




