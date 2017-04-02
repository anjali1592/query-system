# query-system
Developed a Query System which enables users to query the database to retrieve API data based on different criteria such as ratings, protocols and category of the APIs. One can also retrieve API data by specifying keywords in their search. 
MongoDB was used as the database and the application was developed using jsp, Servlets.

Installation Steps:


1. Install Apache Tomcat Server 8
2. Install MonogDB
3. Create collections temp and Mashup in the mongo shell using the following commands:
             db.createCollection(“temp”)
             db.createCollection(“Mashup”)
      4) Import the project WebServices(Assignment3) in eclipse . Execute the APIParsing.java filelocated in WebServices(Assignment3)/src/APIParsing.java  to parse the data and insert values in temp and Mashup respectively.
      5) Create indexes on title, summary and description in both temp and Mashup collection using the following command
          db.temp.createIndex({tilte:"text",summary:"text",description:"text"})
          db.Mashup.createIndex({title:"text",summary:"text",description:"text"})
      6) Import Project ApiAndMashup in eclipse. Run the welcome.jsp on tomcat server.
      7) Copy the URL to the web browser.

