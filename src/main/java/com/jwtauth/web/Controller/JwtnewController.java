package com.jwtauth.web.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import com.jwtauth.web.helper.JwtUtil;
import com.jwtauth.web.model.Changesem;
import com.jwtauth.web.model.Courses;
import com.jwtauth.web.model.Getcourse;
import com.jwtauth.web.model.JwtRequest;
import com.jwtauth.web.model.JwtResponse;
import com.jwtauth.web.model.Messages;
import com.jwtauth.web.model.Modifysem;
import com.jwtauth.web.model.Mycourse;
import com.jwtauth.web.model.Mycredits;
import com.jwtauth.web.model.Myuser;
import com.jwtauth.web.model.Resultmodel;
import com.jwtauth.web.model.Semes;
import com.jwtauth.web.model.Semesters;
import com.jwtauth.web.model.Subjects;
import com.jwtauth.web.model.Teachers;
import com.jwtauth.web.model.Totaluser;
import com.jwtauth.web.model.User;
import com.jwtauth.web.model.Usersem;
import com.jwtauth.web.model.Usersemres1;
import com.jwtauth.web.repo.Curserepository;
import com.jwtauth.web.repo.Semesterrepo;
import com.jwtauth.web.repo.Teacherrepo;
import com.jwtauth.web.repo.UserRepository;
import com.jwtauth.web.repo.Userresultrepo;
import com.jwtauth.web.repo.Usersemesterrepo;
import com.jwtauth.web.Services.CustomUserDetailService;
import com.jwtauth.web.repo.Messagerepo;
import com.jwtauth.web.model.Editcourse;

@RestController
@CrossOrigin(origins ="https://collegecoursemanagement.netlify.app/")
public class JwtnewController {
	 @Autowired
	    private AuthenticationManager authenticationManager;

		Random random = new Random(10000);

	    @Autowired
	    private CustomUserDetailService customUserDetailsService;

	    @Autowired
	    private JwtUtil jwtUtil;
	    
	    @Autowired
	    private Messagerepo messagerepo;
	    
	    @Autowired
	    private UserRepository userrepository;
	    
	    @Autowired
	    private Curserepository courserepository;
	    
	    @Autowired
	    private Semesterrepo semesterepo;
	    
	    @Autowired
	    private Teacherrepo teachrepo;

	    
	    @Autowired
	    private Usersemesterrepo usersemrepo;

	    @Autowired
	    private Userresultrepo userresult;
	    
	    
	    @CrossOrigin(origins="https://collegecoursemanagement.netlify.app/")

	    @RequestMapping(value = "/token", method = RequestMethod.POST)
	    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//<Map<String, String>>
	        System.out.println("Inside Controller");
	        System.out.println(jwtRequest);
	        try {
	        	

	            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));


	        } catch (UsernameNotFoundException e) {
	            e.printStackTrace();
	            throw new Exception("Bad Credentials");
	        }

	        System.out.println("new Controller");
	        //fine area..
	        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
	        System.out.println("the user daeails gkdhfhglkalgagj");

	       // System.out.println(jwtRequest.getUsername());
	       // System.out.println(jwtRequest.getPassword());


	        String token = this.jwtUtil.generateToken(userDetails);
	       // System.out.println("JWT " + token);

	        //{"token":"value"}
	        User user=this.userrepository.findByUsername(jwtRequest.getUsername());
	        Map<String, String> body = new HashMap<>();
	        body.put("message", token);
	        body.put("role", user.getRol());
	        System.out.println(body);

	        //return new ResponseEntity<>(body, HttpStatus.OK);

	        return ResponseEntity.ok(new JwtResponse(token,user.getRol(),user.getUsername(),user.getId()));

	    }
	    
	    @PostMapping("/newregister")
	    public ResponseEntity<?> registeruser(@RequestBody User user){
	    	
			int pin = random.nextInt(99999);
			user.setEnabled(true);
			user.setPin(pin);
			user.setRol("ROLE_USER");
			this.userrepository.save(user);
			
		 return ResponseEntity.ok().body("user is successfully registered");
			
			
	    	
	    }
	    
	    
	    
	    
	    @PostMapping("/addcourse")
	    public ResponseEntity<Courses> addcourse(@RequestBody Courses course , Semesters sem){
	    	Courses mycourse=new Courses();
	    	mycourse.setCourseduration(course.getCourseduration());
	    	mycourse.setCoursefees(course.getCoursefees());
	    	mycourse.setCoursetructure(course.getCoursetructure());
	    	//mycourse.setCid(course.getCid());
	    	mycourse.setCoursename(course.getCoursename());
	    	//sem.setCourses(mycourse);
	    	 List<Semesters> semesters = new ArrayList<>();

			 for(Semesters semester : course.getSemesters()) {
				   // semesters.add(semester);
				 semester.setCourses(mycourse);
				 mycourse.getSemesters().add(semester);

				    // any other property.
				}
	    	//mycourse.getSemesters()
	    	//mycourse.setSemesters(course.getSemesters());
	    	//aextract.setBook(note);
			//note.getExtractnotes().add(aextract);
	    
	   

	   //sem.getCourses().setCid(mycourse.getCid());
	    	this.courserepository.save(mycourse);
	    	return ResponseEntity.ok().body(mycourse);
	    }
	    
	    @PostMapping("/assignteachers")
	    public ResponseEntity<Teachers> assignteacehers(@RequestBody Teachers teachers ,Semesters sem ){
	    	Courses acourse=this.courserepository.findCourseByname(teachers.getCoursespec());
	    	for(Semesters semester : acourse.getSemesters()) {
				   // semesters.add(semester);
	    		if(semester.getSemesterpart().equals(teachers.getSemesterspec())) {
		    		 System.out.println("sdgaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

	    			 semester.getTeachers().add(teachers);
					 teachers.getSemesters().add(semester);
					sem=semester;
					// this.semesterepo.save(semester);
						
					
	    		}
				

				    // any other property.
				}
	    	if(sem!=null) {
	    		this.semesterepo.save(sem);
	    	}
	    	else {
	    		 System.out.println("sddddddddddddddddddddddddddddd");

	    	}
	    	
	    	return ResponseEntity.ok().body(teachers);
	    }
	    
//	    
//	    @PostMapping("/assigncourses")
//	    public ResponseEntity<Semesters> assignteacehers(@RequestBody Semesters teachers ){
//	    	
//	    	
//	    	return ResponseEntity.ok().body(teachers);
//	    }
//	    //-----------------------adding teacher to single sem---------------------------------------------
//	    @PostMapping("/addteachertocourse")
//	    	public ResponseEntity<Modifysem> addsem(@RequestBody Modifysem sems , Semesters sem){
//	    	
//	    	Teachers teacher=this.teachrepo.findTeachById(sems.getTcode());
//	    	
//	    	Courses course=this.courserepository.findCourseByname(sems.getCoursename());
//	    	for(Semesters semester : course.getSemesters()) {
//	    		if(semester.getSemesterpart().equals(sems.getSemester())) {
//                    sem=semester;
//	    	}
//	    	}
////	    	Semesters semester=course.getSemesters().
////	    Semesters sem=this.semesterepo.findSemesterById(sems.getSemester());
//	    
//	    	
//	    	sem.getTeachers().add(teacher);
//	    	teacher.getSemesters().add(sem);
//	    	this.semesterepo.save(sem);
//	    	
//	    	return ResponseEntity.ok().body(sems);
//	    	
//	    }
//	    
//	 /./--------------------------------------------------------------------------------------------------------
	    
	    
	    
	    
	    
	    @PostMapping("/editsemteacher")
	    public ResponseEntity<String> editsem(@RequestBody Changesem se , Semesters oldsem , Semesters newsem){
	    	Teachers teacher=this.teachrepo.findTeachById(se.getTname());
	    	
	    	Courses course=this.courserepository.findCourseByname(se.getCoursename());
	    	
	    	
	    	for(Semesters mysem : course.getSemesters()) {
	    		if(mysem.getSemesterpart().equals(se.getOldsem())) {
	    			oldsem=mysem;
	    		}
	    	}
	    	
	    	for(Semesters myssem : course.getSemesters()) {
	    		if(myssem.getSemesterpart().equals(se.getNewsem())) {
	    			newsem=myssem;
	    		}
	    	}
	    	
   		 System.out.println("sddddddddddddddddddddddddddddd");

		   // Semesters sem=this.semesterepo.findSemesterById(se.getOldsem());
	    	//Semesters lsem=this.semesterepo.findSemesterById(se.getNewsem());
		    //teacher.getSemesters().remove(oldsem);
		 // teacher.getSemesters().
//   		 for( int i = 0; i < teacher.getSemesters().size(); i++ )
//		  {
//   			 
//   			 Semesters semo=teacher.getSemesters().get(i);
//   			 if(oldsem.getSemesterpart().equals(semo.getSemesterpart())) {
//   				 
//   				 teacher.getSemesters().remove(semo);
//   				teacher.getSemesters().add(newsem);
//   			 }
//   			 
//		  }
		   teacher.getSemesters().remove(oldsem);
	    	teacher.getSemesters().add(newsem);
	    	oldsem.getTeachers().remove(teacher);
	    	newsem.getTeachers().add(teacher);
	    	this.teachrepo.save(teacher);
	    	return ResponseEntity.ok().body("edited");

	    	
	    }
	   
	    @DeleteMapping("/deleteteacher/{id}")
	    
	    public ResponseEntity<String> deleteteach(@PathVariable ("id") int id){
	    	
	    	
	    	this.teachrepo.deleteById((long) id);
	    	
	    	return ResponseEntity.ok().body("data deleted");
	    	
	    }
	    
	    
  @PostMapping("/deletecourse")
	    
	    public ResponseEntity<String> deletecourse(@RequestBody Courses id){
	    	
	    	System.out.println("deleting the data ---------------------");
	    	//this.courserepository.deleteById((long) id);
	    	this.courserepository.delete(id);
	    	
	    	return ResponseEntity.ok().body("data deleted");
	    	
	    }
	    
  @PostMapping("/addteachtocourse")
  
  public ResponseEntity<Modifysem> addcourseto(@RequestBody Modifysem sems){
	  
	  
	  Teachers teacher=this.teachrepo.findTeachById(sems.getTname());
	 Courses course=this.courserepository.findCourseByname(sems.getCoursename());
	 for( int j = 0; j < course.getSemesters().size(); j++ )
	  {
	  //for (Semesters temp : ) {
		Semesters temp=course.getSemesters().get(j);
 
		  //Semesters sem=this.semesterepo.findSemesterById(temp);
		  for( int i = 0; i < sems.getSem().size(); i++ )
		  {
			  
			  System.out.println("this is newwwwwwwwwwwwww");

			  String semname=sems.getSem().get(i);
			  if(semname!=null) {
			  //Semesters anothersem=this.semesterepo.findSemesterById(semname);
		  if((temp.getSemesterpart().equals(semname))&&(!teacher.getSemesters().contains(temp))) {
			  
			  teacher.getSemesters().add(temp);
			temp.getTeachers().add(teacher);
			  this.semesterepo.save(temp);
		  }
			  }
		 }
		
          
      }
	
  	return ResponseEntity.ok().body(sems);

	  
  }
  
@PostMapping("/addcoursetoteach")
  
  public ResponseEntity<Modifysem> addteachto(@RequestBody Modifysem sems, Semesters msem){
	  
	 Courses course=this.courserepository.findCourseByname(sems.getCoursename());
	 
	 
	 
	 for( int j = 0; j < course.getSemesters().size(); j++ )
	  {

		 Semesters mysem=course.getSemesters().get(j);
		 
 		if(mysem.getSemesterpart().equals(sems.getSemester())) {
 			msem=mysem;
 			
			
 			 System.out.println("the name of the teacher is");	

		 for( int i = 0; i < sems.getTeachers().size(); i++ )
		  {	
			 
			 String teachername=sems.getTeachers().get(i);
			 //Teachers teach=this.teachrepo.findTeachById(teachername);
		
			 if(teachername!=null) {
			 
				 Teachers teach=this.teachrepo.findTeachById(teachername);
				 System.out.println("the name of the teacher is");
			 System.out.println(teach);
			 if((teach!=null)&&(!msem.getTeachers().contains(teach))) {
				msem.getTeachers().add(teach); 
				teach.getSemesters().add(msem);
				
				  this.semesterepo.save(msem);
				  System.out.println("this is newwwwwwwwwwwwww");
			 }
			 }
		  }
 			
 		}		
 		//}
 	}
	  //Semesters semes=this.semesterepo.findSemesterById(sems.getSemester());
	  
//	  for (String teacher : sems.getTeachers()) {
//		  Teachers teach=this.teachrepo.findTeachById(teacher);
//		  teach.getSemesters().add(msem);
//		  
//		  msem.getTeachers().add(teach);
//		  this.semesterepo.save(msem)
//          
//      }
	
  	return ResponseEntity.ok().body(sems);

	  
  }



  
  @PostMapping("/semesterresult")
  
  public ResponseEntity<Resultmodel> semresult(@RequestBody Resultmodel userresult,Usersemres1 usersemres){
	  
	  User user=this.userrepository.findByUsername(userresult.getUsername());
	  
	  
	  
	  for(int i=0;i<user.getUsersem().size();i++) {
		  Usersem allsem=user.getUsersem().get(i);
		  System.out.println("this is newwwwwwwwwwwwww");

		  if(allsem.getMysemname().equals(userresult.getSemestername())) {
			  
			  //usersemres.setMarks(userresult.getMarks());
			  for(int j=0;j<userresult.getMarks().size();j++) {
				  int marks=userresult.getMarks().get(j);
				  usersemres.setMarks(marks);
				  usersemres.setSemname(userresult.getSemestername());
				  usersemres.setCoursename(userresult.getCoursename());
				  usersemres.setSub(userresult.getSubjects().get(j));
				  usersemres.setUsersem(allsem);
				  allsem.getResult().add(usersemres);

				  this.usersemrepo.save(allsem);

			  }
			  
			  
			 
			 
			
			
		  }
		 
	  }
	  
	  return ResponseEntity.ok(userresult);
  }
  
  
  
  @PostMapping("/setcredits")

  public ResponseEntity<Mycredits> setusercredits(@RequestBody Mycredits mycredits ,Usersem usersem){
	  
	  User user=this.userrepository.findByUsername(mycredits.getUsername());
	  System.out.println(user);
	  usersem.setCredit(mycredits.getCredits());
	  usersem.setMysemname(mycredits.getSemname());
	  usersem.setNoofexam(mycredits.getNoofexam());
	  usersem.setFeepaid(mycredits.getFeepay());
	  
	  user.getUsersem().add(usersem);
	  usersem.setPersons(user);
	  this.userrepository.save(user);
	  
	  return ResponseEntity.ok(mycredits);
  }
 
@RequestMapping("/coursedetails")

public ResponseEntity<Courses> coursedetails(@RequestBody Getcourse course ,User user){
	
	User myuser=this.userrepository.findByUsername(course.getUsername());
	Courses mycourse=this.courserepository.findCourseByname(myuser.getMycourse());
	
	return ResponseEntity.ok(mycourse);
	
}

@RequestMapping("/Resultdetails")
public ResponseEntity<List<Usersemres1>> Resultdetails(@RequestBody Getcourse course ,User user,Usersem seme){

User myuser=this.userrepository.findByUsername(course.getUsername());

System.out.println(myuser);
System.out.println(myuser.getUsersem().size());
///for(int i=0;i<myuser.getUsersem().size();i++) {
	for(Usersem sem:myuser.getUsersem()) {
	//Usersem sem=user.getUsersem().get(i);
	if(sem.getMysemname().equals(course.getSemname())) {
		seme=sem;
	}
}
return ResponseEntity.ok(seme.getResult());

}  

@RequestMapping("/userdetails/{user}")

public ResponseEntity<User> getuser(@PathVariable("user") String myuser){
	
	User user=this.userrepository.findByUsername(myuser);
	return ResponseEntity.ok(user);
}

@RequestMapping("/allusers")

public ResponseEntity<List<User>> allusers(){
	
	List<User> allusers=this.userrepository.findAll();
	return ResponseEntity.ok(allusers);
}

@RequestMapping("/yourcourseteach/{user}")

public ResponseEntity<List<Teachers>> yourteachers(@PathVariable("user") String myuser){
	
	User user=this.userrepository.findByUsername(myuser);
	Courses course=this.courserepository.findCourseByname(user.getMycourse());
//	List<Semesters> sems=course.getSemesters();
//	
//	List <Teachers> teachers=new ArrayList<>();
//	
//for(Semesters sem:sems) {
//	for(Teachers teacher : sem.getTeachers()) {
//		teachers.add(teacher);
//	}
//}
List<Teachers> teachers=this.teachrepo.findTeachByCourse(course.getCoursename());
return ResponseEntity.ok(teachers);

}

@RequestMapping("/allteachers")

public ResponseEntity<List<Teachers>> allteachers(){
	
	List<Teachers> teachers=this.teachrepo.findAll();
	return ResponseEntity.ok(teachers);
}



@PostMapping("/addcoursewithteachers")
public ResponseEntity<Courses> addcoursewithteachers(@RequestBody Courses course ,Subjects subject){
	
	Semesters sem=new Semesters();

	for(int j=0;j<(course.getSemesters().size());j++) {
		System.out.println("Sdfaga");

	 Semesters semester=course.getSemesters().get(j);
	System.out.println(course.getSemesters().size());

	 sem.setCourses(semester.getCourses());
	 sem.setSemestercontent(semester.getSemestercontent());
	 sem.setSemesterduration(semester.getSemesterduration());
	 sem.setSemesterpart(semester.getSemesterpart());
	 Subjects sub=new Subjects();
	 for(int i=0;i<semester.getSubjects().size();i++) {
	 Subjects subjects=semester.getSubjects().get(i);
	 
	 subjects.setSemesters(semester);
	 //semester.getSubjects().add(subjects);
		System.out.println(semester.getSubjects().size());

	 }
	 sem.setSubjects(semester.getSubjects());
	 //sem.setCourses(course);
	 semester.setCourses(course);
		this.semesterepo.save(semester);

	 
	}
	//course.getSemesters().add(sem);
	
this.courserepository.save(course);
//---------------------------------------------------------------------------------------------------------------------	
//	Courses mycourse=new Courses();
//	mycourse.setCourseduration(course.getCourseduration());
//	mycourse.setCoursefees(course.getCoursefees());
//	mycourse.setCoursetructure(course.getCoursetructure());
//	mycourse.setCoursename(course.getCoursename());
//	// List<Semesters> semesters = new ArrayList<>();
//
//	 
//	
//	for(Semesters semester : course.getSemesters()) {
//		  //for(int j=0;j<course.getSemesters().size();j++) {
//		 //Semesters semester=course.getSemesters().get(j);
//			//for(int i=0;i<5;i++) {
//				
//				for(Subjects sub:semester.getSubjects()) {
//				
//				subject.setSubject(sub.getSubject());
//				
//				//semester.getSubjects().add()
//				//semester.getSubjects().add(subjects)
//
//					///System.out.println(semester.getTeachers().size()) ;
//					//subjects.setSubject(semester.getSubjects().get(i));
//				System.out.println(course.getSemesters().size()) ;
////if(teach.getSemesterspec().equals(semester.getSemesterpart())) {
//	
////	 teach.getSemesters().add(semester);
////	 semester.getTeachers().add(teach);
//	 
//				subject.setSemesters(semester);
//
//	//sub.setSemesters(semester);
//	 semester.getSubjects().add(subject);
//	
//	 //this.semesterepo.save(semester);
//			
//		 }
//			
//
//			// 
//		
//		 
//		 
//		 
//		 semester.setCourses(mycourse);
//		 mycourse.getSemesters().add(semester);
//
//		    // any other property.
//		}
//	//mycourse.getSemesters()
//	//mycourse.setSemesters(course.getSemesters());
//	//aextract.setBook(note);
//	//note.getExtractnotes().add(aextract);
//
//
//
////sem.getCourses().setCid(mycourse.getCid());
//	
//	
	
	
	//-----------------------------------------------------------------------------------------------------------------
	return ResponseEntity.ok().body(course);
}

@PostMapping("/updateuser")

public ResponseEntity<String> updateuser( @RequestBody Myuser user){
	
	User myuser=this.userrepository.getById(user.getId());
	
	myuser.setCity(user.getCity());
	myuser.setAddress(user.getAddress());
	myuser.setEmail(user.getEmail());
	myuser.setMycourse(user.getMycourse());
	myuser.setUsername(user.getUsername());
	myuser.setPhoneno(user.getPhoneno());
	myuser.setState(user.getState());
	myuser.getUsersem().get(0).setFeepaid(user.getFeepay());
	myuser.getUsersem().get(1).setFeepaid(user.getFeepaysem());

	this.userrepository.save(myuser);
	
	return ResponseEntity.ok().body("successful");
	
	
}



@PostMapping("/updateteacher")

public ResponseEntity<String> updateteacher(@RequestBody Teachers teacher){
	
	Teachers myteach=this.teachrepo.findTeacherById(teacher.getTid());
	myteach.setCoursespec(teacher.getCoursespec());
	myteach.setTeacherdegree(teacher.getTeacherdegree());
	myteach.setTeachername(teacher.getTeachername());
	myteach.setWorkexp(teacher.getWorkexp());
myteach.setTeacherskills(teacher.getTeacherskills());
this.teachrepo.save(myteach);
return ResponseEntity.ok().body("success");
}

@CrossOrigin(origins ="https://collegecoursemanagement.netlify.app/")
@RequestMapping("/allcourses")

public ResponseEntity<List<Courses>> allcourses(){
	
	List<Courses> courses=this.courserepository.findAll();
	return ResponseEntity.ok(courses);
}

@PostMapping("/Message")
	public ResponseEntity<Messages> savemessage(@RequestBody Messages message){
		
		this.messagerepo.save(message);
		
		
		return ResponseEntity.ok(message);
		
	}

@CrossOrigin(origins ="https://collegecoursemanagement.netlify.app/")

@RequestMapping("/getmessages")
public ResponseEntity<List<Messages>> allmessages(){
	
List<Messages> allmessages=this.messagerepo.findAll();
return ResponseEntity.ok(allmessages);
}


@CrossOrigin(origins ="https://collegecoursemanagement.netlify.app/")

@PostMapping("/register")
public ResponseEntity<?> registerstudent(@RequestBody Totaluser user,Usersem mycredits ){
	
	
	Usersem mynewcred=new Usersem();
	
	Usersem mynewsem=new Usersem();
	Usersem mynewsemester=new Usersem();

	User myuser=new User();
	
	int pin = random.nextInt(99999);
	
	myuser.setAddress(user.getAddress());
	myuser.setUsername(user.getUsername());
	myuser.setCity(user.getCity());
	myuser.setDownloadURL(user.getDownloadURL());
	myuser.setEmail(user.getEmail());
	myuser.setFather(user.getFather());
	myuser.setFilename(user.getFilename());
	myuser.setGender(user.getGender());
	myuser.setMycourse(user.getMycourse());
	myuser.setPassword(user.getPassword());
	myuser.setPhoneno(user.getPhoneno());
	myuser.setPincode(user.getPincode());
	myuser.setState(user.getState());
	myuser.setEnabled(true);
	myuser.setPin(pin);
	myuser.setRol("ROLE_USER");
	//mycredits.set(user.getUsername());
	mycredits.setFeepaid(user.getFeepay());
	mycredits.setNoofexam(user.getNoofexam());
	mycredits.setMysemname(user.getSemname());
	mycredits.setCredit(user.getCredits());
	
	mynewcred.setFeepaid(user.getFeepaysem());
	mynewcred.setNoofexam(user.getNoofexam());
	mynewcred.setMysemname(user.getAsemname());
	mynewcred.setCredit(user.getCredits());
	
	mynewsem.setFeepaid(user.getFeepay1());
	mynewsem.setNoofexam(user.getNoofexam());
	mynewsem.setMysemname(user.getSemname3());
	mynewsem.setCredit(user.getCredits());
	
	mynewsemester.setFeepaid(user.getFeepay2());
	mynewsemester.setNoofexam(user.getNoofexam());
	mynewsemester.setMysemname(user.getSemname4());
	mynewsemester.setCredit(user.getCredits());
	
	
	
	
	List<Usersem> usersm=Arrays.asList(mycredits,mynewcred);
	//
	//myuser.getUsersem().addAll(usersm);
/////////////////////////////confusion point ----------------this.usersemrepo.save(mynewcred);

	//myuser.getUsersem().addAll(usersm);
	myuser.getUsersem().add(mycredits);
	myuser.getUsersem().add(mynewcred);
	myuser.getUsersem().add(mynewsem);
	myuser.getUsersem().add(mynewsemester);


mycredits.setPersons(myuser);
	
	mynewcred.setPersons(myuser);
	
	mynewsem.setPersons(myuser);
	mynewsemester.setPersons(myuser);
	
	this.userrepository.save(myuser);
System.out.println(mycredits.getMysemname());
System.out.println(mynewcred.getMysemname());

	
 return ResponseEntity.ok().body("user is successfully registered");
	
	
	
}

@RequestMapping("/getusersoncourse/{coursename}")


public ResponseEntity<List<User>> getusersoncourse(@PathVariable("coursename") String coursename){
	
	List<User> myusers=this.userrepository.findByCourse(coursename);
	
	
	return ResponseEntity.ok().body(myusers);
}

@RequestMapping("/getcourse/{coursename}")

public ResponseEntity<Courses> getcourse(@PathVariable("coursename") String coursename){

Courses course=this.courserepository.findCourseByname(coursename);


return ResponseEntity.ok().body(course);
}



@RequestMapping("/getcoursebyusername/{user}")

public ResponseEntity<Courses> getcoursebyuser(@PathVariable("user") String username){

	
	User user=this.userrepository.findByUsername(username);
Courses course=this.courserepository.findCourseByname(user.getMycourse());


return ResponseEntity.ok().body(course);
}

@DeleteMapping("/deletestudent/{id}")

public ResponseEntity<String> deletestudent(@PathVariable ("id") int id){
	
	
	this.userrepository.deleteById((long) id);
	
	return ResponseEntity.ok().body("data deleted");
	
}

@PostMapping("/Editcourse")

public ResponseEntity<String> Editcourse(@RequestBody Editcourse ecourse){
	
	Courses course=this.courserepository.findCourseById(ecourse.getCid());
	
	
	course.setCoursename(ecourse.getCoursename());
	course.setCoursetructure(ecourse.getCoursestructure());
	//for(int i=0;i<course.getSemesters().size();i++) {
	//	Semesters sem=course.getSemesters().get(i);
		//ecourse.get
	//}
	
	course.getSemesters().get(0).setSemestercontent(ecourse.getSemestercontent1());
	course.getSemesters().get(1).setSemestercontent(ecourse.getSemestercontent2());
	course.getSemesters().get(2).setSemestercontent(ecourse.getSemestercontent3());
	course.getSemesters().get(3).setSemestercontent(ecourse.getSemestercontent4());

	
this.courserepository.save(course);
return ResponseEntity.ok().body("success");
}



}

