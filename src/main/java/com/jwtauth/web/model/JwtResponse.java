package com.jwtauth.web.model;

public class JwtResponse {
	 String token;
	 String userrole;
	 String username;
	 long userid;

	    public JwtResponse() {
	    }

	    

		public JwtResponse(String token,String userrole,String username,Long long1) {
	        this.token = token;
	        this.userrole=userrole;
	        this.userid=long1;
	        this.username=username;
	    }

	    public String getUserrole() {
			return userrole;
		}

		public void setUserrole(String userrole) {
			this.userrole = userrole;
		}

		public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }



		public String getUsername() {
			return username;
		}



		public void setUsername(String username) {
			this.username = username;
		}



		public long getUserid() {
			return userid;
		}



		public void setUserid(long userid) {
			this.userid = userid;
		}



	
	    
	   
}
