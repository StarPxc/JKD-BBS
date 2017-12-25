package com.pxc.post.controller;


import com.github.pagehelper.PageHelper;
import com.pxc.post.bean.Post;
import com.pxc.post.service.PostService;
import com.pxc.reply.bean.Reply;
import com.pxc.reply.service.ReplyService;
import com.pxc.user.bean.User;
import com.pxc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by PXC on 2017/6/20.
 */
@Controller
@RequestMapping("post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private UserService userService;
    /**
      *@Author Ethan
      *@Date 2017/6/28 18:09
      *@Description 初始化页面
      */
    @RequestMapping("")
    public String index(HttpServletRequest request) {
        String pageNumString=request.getParameter("pageNum")!=null?request.getParameter("pageNum"):"0";
        String sizeString=request.getParameter("size")!=null?request.getParameter("size"):"5";
        int pageNum=Integer.parseInt(pageNumString);
        int size=Integer.parseInt(sizeString);
        List<Post> posts= postService.findAllPosts(pageNum*size,size);
        int postCount=postService.CountPostsNum();
        //设置post对象的replyCount属性
        for (int i = 0; i < posts.size() ; i++) {
            posts.get(i).setPostReplyCount(posts.get(i).getReplies().size());
        }
        List<Reply> replies=replyService.findLastedItem();
        List<Post> hotPosts=postService.findHotPosts();
        request.setAttribute("posts",posts);
        request.setAttribute("replies",replies);
        request.setAttribute("hotPosts",hotPosts);
        request.setAttribute("postCount",postCount);
        request.setAttribute("currentPage",pageNum+1);
        return "index";
    }
    /**
      *@Author Ethan
      *@Date 2017/6/28 18:10
      *@Description 帖子详情页面，通过传入的id找到对应的帖子
      */
    @RequestMapping("/viewPost/{post_id}")
    public String viewPost(HttpServletRequest request, @PathVariable String post_id) {
        int id=Integer.parseInt(post_id);
        Post post=postService.findPostWithUserAndReplyById(id);
        int count=post.getPostViewCount();
        count++;
        post.setPostViewCount(count);
        postService.update(post);
        List<Reply> replies=replyService.findReplyByPostId(id);
        User user=userService.findUserWithLastPostsById(post.getUser().getId());
            List<Post> userLastPosts=user.getPosts();
        request.setAttribute("post",post);
        request.setAttribute("userLastPosts",userLastPosts);
        request.setAttribute("replies",replies);
        return "viewPost";
    }
    /**
      *@Author Ethan
      *@Date 2017/6/28 18:11
      *@Description 跳转到创建帖子的页面
      */
    @RequestMapping("/createPostView")
    public String viewPost() {
        return "createPostView";
    }

    /**
      *@Author Ethan
      *@Date 2017/6/28 18:11
      *@Description 创建帖子
      */
    @RequestMapping("/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
            String markupStr=request.getParameter("markupStr");
            String postTitle=request.getParameter("postTitle");
            User user= (User) request.getSession().getAttribute("user");
            int createPostCount= user.getCreatePostCount();
            createPostCount++;
            user.setCreatePostCount(createPostCount);
            userService.update(user);
            Post post=new Post();
            post.setPostContent(markupStr.trim());
            post.setPostCreateTime(new Date());
            post.setPostUpdateTime(new Date());
            post.setPostTitle(postTitle.trim());
            post.setUserId(user.getId());
            post.setUser(user);
            post.setPostReplyCount(0);
            postService.createPost(post);
    }
    /**
      *@Author Ethan
      *@Date 2017/7/2 9:40
      *@Description 自动完成的查询
      */
    @RequestMapping(value = "/searchTitle",produces="text/html;charset=UTF-8")
    @ResponseBody
    public List<Post> searchTitle(HttpServletRequest request){
        String searchTitle=request.getParameter("searchTitle");
        List<Post> searchPosts;
        searchPosts = postService.searchPosts(searchTitle);
        if(searchPosts.size()>0){

            return searchPosts;
        }else{

            return null;
        }

    }
    @RequestMapping("/search")

    public String search(String searchTitle,HttpServletRequest request,HttpServletResponse response){
        List<Post> posts=postService.searchPosts(searchTitle);
        List<Reply> replies=replyService.findLastedItem();
        List<Post> hotPosts=postService.findHotPosts();
        request.setAttribute("posts",posts);
        request.setAttribute("replies",replies);
        request.setAttribute("hotPosts",hotPosts);

        return "index";
    }

    /**
     * 上传图片
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String img(MultipartFile Mfile) throws IOException {
        //拿到名称
        String fileName=Mfile.getOriginalFilename();
        System.out.println(fileName);
        File file=new File("/root/imgs/"+fileName);
        Mfile.transferTo(file);
        return "http://120.25.88.41/imgs/"+fileName;

    }

    /**
     * 删除帖子
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String deletePost(HttpServletRequest request){
        String a=request.getParameter("id");
        int id=Integer.parseInt(request.getParameter("id"));
            if(postService.deletePost(id)){
                return "success";
            }else {
                return "fail";
            }
    }

}
