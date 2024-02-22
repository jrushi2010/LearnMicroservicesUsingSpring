#Configuring Auto Generation of Swagger Documentation

	<dependency>
		<groupId>org.springdoc</groupId>
		<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
		<version>2.3.0</version>
	</dependency>

http://localhost:8085/swagger-ui/index.html#/


#Versioning Rest Api - Options

Uri Versioning - Twitter

		http://localhost:8085/v1/person
 		http://localhost:8085/v2/person
   
Request Parameter Versioning - Amazon

	http://localhost:8085/person?version=1
 	http://localhost:8085/person?version=2
  
(Custom) headers Versioning - Microsoft

	SAME-URL headers=[X-API-VERSION=1]
 	SAME-URL headers=[X-API-VERSION=2]
  
Media type versioning (content negotiation or accept header) - Github

	SAME-URL produces=application/vnd.company.app-v1+json
 	SAME-URL produces=application/vnd.company.app-v1+json




#HATEOAS 
hypermedia as the Engine of Application state

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-hateoas</artifactId>
		</dependency>

whenever you go to a website you can see the data and the perform actions for example we aee looking at a github repository over here you can see the data about the specific repository you can see that it has 431 stars you can also see the different branches so on this website you can see a lot of data in addition you can also perform actions on this specific repository. 

How about enehancing your REST API to tell consumers how to perform subsequent actions?

so the question heteos is asking is how about enhancing your rest api not only to give the data back but also to give the information back about how to perform actions on those resources.

		{
			"name":"Adam",
   			"birthDate":"2022-08-16",
      			"_links": {
	 			"all-users": {
     					"href":"http://localhost:8085/users"
	  			}
      			}
	 	}

   
over here you can see that we have the name, birthdate as the response in addition we also have links in here which tell consumer how to perform subsequent actions, if you want to implement some thing like that you have couple of options.

1. Custom Format and Implementation
   	Difficult to maintain
2. Use Standard Implementation
   	HAL (JSON Hypertext Application Language): Simple format that gives a consistent easy way to hyperlink between resources in your API


#Customizing REST API Responses - Filtering and more.. 

 Serialization - process of converting object into stream (example: JSON)
 for example we are returning an list of users back, converting this to a JSON or to an XML is what is called serialization 
 The most popular JSON Serialization in java is Jackson 
 Until now whatever the structure of the User is exctaly at the response, so we have id,name and the birthdate and that is eactly at the response, in certain situation you might not want to return the exact structure as what you have in here you might want to customize the rest api response that is being return by jackson framework, lets look at the few options to customize in the next step.

1. Customize filed names in response
    	@JSONProperty("user_name")
2.  Return only selected fields
   	Filtering - for example you might have a password defined in your bean and you dont want to send passwords out in the response.
    There are two types of filtering -
    	1. static filtering: Same filtering for a bean across different REST API
    		@JsonIgnoreProperties, @JsonIgnore
    	2. Dynamic Filtering: Customize filtering for a bean for specific REST API
    		@JsonFilter with FilterProvider 


