package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private CommentService commentService;


    @RequestMapping(value="/image/{id}/{title}/comments",method = RequestMethod.POST)
    public String showImage(@PathVariable("id") Integer id,@PathVariable("title") String title,@RequestParam("comment") String text ,Model model,HttpSession session) {
        User user=(User)session.getAttribute("loggeduser");
        Image image = imageService.getImageByTitle(id,title);

        Comment comment=new Comment();
        comment.setText(text);
        comment.setDate(new Date());
        comment.setUsers(user);
        comment.setImages(image);
        commentService.uploadComment(comment);

        return "redirect:/images/"+id+"/"+title;
    }


}
