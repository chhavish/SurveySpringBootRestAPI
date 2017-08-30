package com.survey.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.survey.springboot.dao.OptionDAO;
import com.survey.springboot.model.Option;
import com.survey.springboot.model.Post;
import com.survey.springboot.model.Users;
import com.survey.springboot.service.CalculateWinnerService;
import com.survey.springboot.service.PostService;
import com.survey.springboot.service.UserService;
import com.survey.springboot.service.VotingService;
import com.survey.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UserService userService; // Service which will do all data
								// retrieval/manipulation work
	
	@Autowired
	PostService postService;
	
	@Autowired
	VotingService votingService;
	
	@Autowired
	CalculateWinnerService calculateWinnerService;
	

	// -------------------Retrieve All
	// Users---------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<Users>> listAllUsers() {
		List<Users> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// User------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		Users user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}

	// -------------------Create a
	// User-------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Users user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A User with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------------Register User-----------------------------//

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@AuthenticationPrincipal @RequestBody Users user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {}  or username {} already exist", user.getName(), user.getUsername());
			return new ResponseEntity(
					new CustomErrorType("Unable to register user with u " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User
	// ------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Users user) {
		logger.info("Updating User with id {}", id);

		Users currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return new ResponseEntity<Users>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a
	// User-----------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		Users user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<Users> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
	}

	
	// ------------------- Create a Post-----------------------------
		@RequestMapping(value = "/user/post", method = RequestMethod.POST)
		public ResponseEntity<?> createPost(@AuthenticationPrincipal @RequestBody Post post, UriComponentsBuilder ucBuilder) {
			logger.info("Creating Post : {}", post);

			postService.createPost(post);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/user/post/{id}").buildAndExpand(post.getPostId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		} 
		
		
		// -------------------Retrieve Single
		// Post------------------------------------------

		@RequestMapping(value = "/user/post/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getPost(@PathVariable("id") long postId) {
			logger.info("Fetching User with id {}", postId);
			Post post = postService.getPostByPostId(postId);
			if (post == null) {
				logger.error("Post with id {} not found.", postId);
				return new ResponseEntity(new CustomErrorType("Post with id " + postId + " not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Post>(post, HttpStatus.OK);
		}

		// ------------------- Vote on a Post-----------------------------
		
		@RequestMapping(value="/user/vote/{postId}/{optionId}", method = RequestMethod.PUT)
		public ResponseEntity<?> voteForPost(@PathVariable("postId") long postId, @PathVariable("optionId") long optionId){
			logger.info("Voting for post {postId} on option {optionId}");
			
			Option votedForoption = votingService.vote(postId, optionId);
			return new ResponseEntity<Option>(votedForoption, HttpStatus.OK);
			
		}
		
		
		
		// --------------------------Calculate Winner for a Post --------------------------------------//
		@RequestMapping(value="/winner/{postId}", method = RequestMethod.GET)
		public ResponseEntity<?> calcualteWinnerForPost(@PathVariable("postId") long postId){
			
			Option option = calculateWinnerService.calculateWinner(postId);
			
			return new ResponseEntity<Option>(option, HttpStatus.OK);
		}
		
}