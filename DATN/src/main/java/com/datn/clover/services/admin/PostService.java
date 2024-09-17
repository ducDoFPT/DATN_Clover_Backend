//package com.datn.clover.services.admin;
//
//import com.datn.clover.DTO.admin.PostBean;
//import com.datn.clover.entity.Account;
//import com.datn.clover.entity.Post;
//import com.datn.clover.inter.AccountJPA;
//import com.datn.clover.inter.PostJPA;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PostService {
//    @Autowired
//    PostJPA postJPA;
//
//    @Autowired
//    AccountJPA accountJPA;
//
//    public List<Post> getAllPosts() {return postJPA.findAll();}
//
//    public List<Post> getAllBrowesPosts() {return postJPA.getAllBrowsePosts();}
//
//    public List<Post> getAllDenouncePosts() {return postJPA.getAllDenouncePosts();}
//
//    public Post getPostById(String id) {
//        return postJPA.findById(id).orElse(null);
//    }
//
//    public Post addPost(Post post, PostBean postBean) {
//        post.setId(postBean.getId());
//        post.setTitle(postBean.getTitle());
//        post.setPostDay(postBean.getPostDay());
//        post.setContent(postBean.getContent());
//        post.setNumberLikes(0);
//        post.setStatus("Chờ xét duyệt");
//        post.setStypeShare("Mọi người");
//
//        Account account = accountJPA.findById(postBean.getAccountId()).orElse(null);
//        if (account != null) {
//            post.setAccount(account);
//        }
//        return postJPA.save(post);
//    }
//
////    public Post updatePost(Post post, PostSellerBean postBean) {}
//
//    public Post deletePost(String id) {
//        Post post = postJPA.findById(id).orElse(null);
//        postJPA.delete(post);
//        return post;
//    }
//
//    //Xét duyệt bài viết
//    public Post browsePosts(String id) {
//        Post post = postJPA.findById(id).orElse(null);
//        if (post != null) {
//            post.setStatus("Đang hoạt động");
//            return postJPA.save(post);
//        }
//        return null;
//    }
//
//    //Cảnh cáo khi bị tố cáo
//    public Post denouncePosts(String id) {
//        Post post = postJPA.findById(id).orElse(null);
//        if (post != null) {
//            Account account = accountJPA.findById(post.getAccount().getId()).orElse(null);
//            if (account == null) {
//                return null;
//            }else {
//                if (account.getStatus().equalsIgnoreCase("Đang hoạt động")) {
//                    account.setStatus("Cảnh cáo lần 1");
//                } else if (account.getStatus().equalsIgnoreCase("Cảnh cáo lần 1")) {
//                    account.setStatus("Cảnh cáo lần 2");
//                } else if (account.getStatus().equalsIgnoreCase("Cảnh cáo lần 2")) {
//                    account.setStatus("Cảnh cáo lần 3");
//                }
//
//                accountJPA.save(account);
//                deletePost(id);
//                return post;
//            }
//        }
//        return null;
//    }
//
//}
