package com.eduted.webservices.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eduted.webservices.model.Post;
import com.eduted.webservices.services.PostService;

@RestController
@RequestMapping("eduted/post")
public class PostResource
{
	@Autowired
	private PostService postService = new PostService();
	
/******************************************************************************************/
	
	@PostMapping("user/{userId}/profile/{profileId}/posts")
	@ResponseBody
	public ResponseEntity<String> createNewPost(@PathVariable("userId") String userId, @PathVariable("profileId") String profileId, @RequestBody Post post)
	{
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@PostMapping("profile/{id}/posts")
	@ResponseBody
	public ResponseEntity<String> createNewPost(@PathVariable("id") String profileId, @RequestBody Post post)
	{
		if(profileId == null || post == null)
			return new ResponseEntity<String>("No Data Found...[ERROR]", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<String>(postService.addNewPost(profileId, post), HttpStatus.OK);
	}
	
	@GetMapping("profile/{profileId}/posts/{postId}")
	public ResponseEntity<Post> searchPostById(@PathVariable("profileId") String profileId, @PathVariable("postId") String postId)
	{
		if(profileId == null || postId == null)
			return new ResponseEntity<Post>(new Post(), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Post>(postService.getPostById(profileId, postId), HttpStatus.OK);
	}
	
	@GetMapping("profile/{profileId}/posts")
	public ResponseEntity<List<Post>> searchPostByProfileId(@PathVariable("profileId") String profileId)
	{
		if(profileId == null)
			return new ResponseEntity<List<Post>>(new ArrayList<Post>(), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<Post>>(postService.getPostByProfileId(profileId), HttpStatus.OK);
	}

	@GetMapping("posts/{postId}")
	public ResponseEntity<Post> searchPostById(@PathVariable("postId") String postId)
	{
		if(postId == null)
			return new ResponseEntity<Post>(new Post(), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Post>(postService.getPostById(postId), HttpStatus.OK);
	}
}
