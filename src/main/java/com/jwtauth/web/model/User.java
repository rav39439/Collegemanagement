package com.jwtauth.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "persons")
public class User {

	
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String username;
	    private String password;
	    private String email;
	    private String rol;
	    private boolean enabled;
	    private String state;
	    private String city;
	    private int pincode;
	    private int pin;
	    private String mycourse;
	    private String father;
	    private String gender;
	    private String phoneno;
	    private String address;
	    private String filename;
	    private String downloadURL;
	    
	    @OneToMany(mappedBy = "persons",cascade = CascadeType.ALL,orphanRemoval = true)
		 

	    private List<Usersem> usersem=new ArrayList<>();
	    
	    
	    
	    public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getPincode() {
			return pincode;
		}

		public void setPincode(int pincode) {
			this.pincode = pincode;
		}

		public int getPin() {
			return pin;
		}

		public void setPin(int pin) {
			this.pin = pin;
		}

		public String getFather() {
			return father;
		}

		public void setFather(String father) {
			this.father = father;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getPhoneno() {
			return phoneno;
		}

		public void setPhoneno(String phoneno) {
			this.phoneno = phoneno;
		}

		
	    
	    

	    //more properties as your project requirements


	    public String getMycourse() {
			return mycourse;
		}

		public void setMycourse(String mycourse) {
			this.mycourse = mycourse;
		}

		public List<Usersem> getUsersem() {
			return usersem;
		}

		public void setUsersem(List<Usersem> usersem) {
			this.usersem = usersem;
		}

		public User() {
	    }

	    public User(Long id, String username, String password, String email, String rol, boolean enabled) {
	        this.id = id;
	        this.username = username;
	        this.password = password;
	        this.email = email;
	        this.rol = rol;
	        this.enabled = enabled;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	  
	  

		public String getRol() {
			return rol;
		}

		public void setRol(String rol) {
			this.rol = rol;
		}

		public boolean isEnabled() {
	        return enabled;
	    }

	    public void setEnabled(boolean enabled) {
	        this.enabled = enabled;
	    }
	    
	    

	    public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public String getDownloadURL() {
			return downloadURL;
		}

		public void setDownloadURL(String downloadURL) {
			this.downloadURL = downloadURL;
		}

		@Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", username='" + username + '\'' +
	                ", password='" + password + '\'' +
	                ", email='" + email + '\'' +
	                ", rol='" + rol + '\'' +
	                ", enabled=" + enabled +
	                '}';
	    }
}
