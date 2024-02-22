Configuring Auto Generation of Swagger Documentation

	<dependency>
		<groupId>org.springdoc</groupId>
		<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
		<version>2.3.0</version>
	</dependency>

http://localhost:8085/swagger-ui/index.html#/


Versioning Rest Api - Options

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





