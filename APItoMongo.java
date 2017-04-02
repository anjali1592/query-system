

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class APItoMongo
 */
@WebServlet("/APItoMongo")
public class APItoMongo extends HttpServlet {
	public DB dbObject;
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws UnknownHostException 
     * @see HttpServlet#HttpServlet()
     */
    public APItoMongo() throws UnknownHostException {
    	super();
        MongoClient mg = new MongoClient();
        DB db = mg.getDB( "test" );
        dbObject = db;
        System.out.println("Connected");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DOGET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> apiName = new ArrayList<String>(); 
		System.out.println("DOPOST");
		Date date = null;
		HttpSession session = request.getSession(true);
//		session.setAttribute("id_", "hhjhj");
		String cat = request.getParameter("EnterCategory");
		System.out.println(cat);
		String checkAttribute = request.getParameter("criteria");
		
		if(checkAttribute.equals("Category")){
			apiName = getQueryResults(cat);
		}
		else if(checkAttribute.equals("Protocols")){
			apiName = getQueryProtocol(cat);
		}
		else if(checkAttribute.equals("Tags")){
        	apiName = getQueryTag("API",cat);
		}
		else if(checkAttribute.equals("TagsMash")){
        	apiName = getQueryTag("Mashup",cat);
		}
		else if(checkAttribute.equals("Rating")){
			Float rate = Float.valueOf(cat);
			System.out.println("rate: "+rate);
			String radio = request.getParameter("radiobutton");
			if(radio.equals("RadioLess")){
				apiName = getQueryRating(rate);
				
			}
			else{
				apiName = getQueryRatingGreater(rate);
			}
        	
		}
		
		else if(checkAttribute.equals("UpdatedYear")){

			 apiName = getQueryYear("API",cat);
		}
        else if(checkAttribute.equals("Key")){
        	 apiName = getQueryKey1("API",cat);
	    }
        else if(checkAttribute.equals("KeyMash")){
       	 apiName = getQueryKey1("Mash",cat);
	    }
        else if(checkAttribute.equals("UpdatedYearMash")){
        	 apiName = getQueryYear("Mashup",cat);
        }
        else if(checkAttribute.equals("APIUSED")){
        	apiName = getQueryAPI(cat);
        }
		session.setAttribute("listData", apiName);
	    String redirect = "ResponsePage.jsp";
	    response.sendRedirect(redirect);
	   
	    
		
	}
	public ArrayList<String> getQueryAPI(String cat){
		ArrayList<String> api = new ArrayList<String>();
		DBCollection c ;
		c = dbObject.getCollection("Mashup");
		BasicDBObject obj = new BasicDBObject();
		obj.put("APIs.API+URL.componenetAPI", cat);
		DBCursor cu = c.find(obj);
		
	
		while(cu.hasNext()){
			api.add((String) cu.next().get("name"));
			//System.out.println(cu.next().get("APIs"));
		}
		
		return api;
	}
	public ArrayList<String> getQueryResults(String category){
		ArrayList<String> api = new ArrayList<String>();
		DBCollection c ;
		c = dbObject.getCollection("temp");
		BasicDBObject obj = new BasicDBObject();
		obj.put("category", category);
		DBCursor cu = c.find(obj);
		while(cu.hasNext()){
			api.add((String) cu.next().get("name"));
			//System.out.println(cu.next().get("id"));
		}
		return api;
	}
	
	public ArrayList<String> getQueryProtocol(String category){
		ArrayList<String> api = new ArrayList<String>();
		DBCollection c ;
		c = dbObject.getCollection("temp");
		BasicDBObject obj = new BasicDBObject();
		obj.put("protocols", category);
		DBCursor cu = c.find(obj);
		while(cu.hasNext()){
			api.add((String) cu.next().get("name"));
			//System.out.println(cu.next().get("id"));
		}
		return api;
	}
	
	public ArrayList<String> getQueryTag(String coll,String category){
		ArrayList<String> api = new ArrayList<String>();
		DBCollection c ;
		if(coll.equals("API")){
		c = dbObject.getCollection("temp");
		}
		else{
			c =dbObject.getCollection("Mashup"); 
		}
		BasicDBObject obj = new BasicDBObject();
		BasicDBObject obj1 = new BasicDBObject();
	    BasicDBList list = new BasicDBList();
		
		if(coll.equals("API")){
	    
		//	obj1.put("Tags", category);
		list.add(category);
		obj.put("Tags", new BasicDBObject("$in",list));
		//obj.put("Tags", category);
		}
		else{
			obj.put("tags", category);
		}
		DBCursor cu = c.find(obj);
		while(cu.hasNext()){
			api.add((String) cu.next().get("name"));
			//System.out.println(cu.next().get("Tags"));
		}
		return api;
	}
	
	public ArrayList<String> getQueryRating(float category){
		ArrayList<String> api = new ArrayList<String>();
		ArrayList<Float> apif = new ArrayList<Float>();
		DBCollection c ;
		int i=0;
		c = dbObject.getCollection("temp");
		BasicDBObject obj = new BasicDBObject();
		obj.put("rating", new BasicDBObject("$lt",category));
		DBCursor cu = c.find(obj);
		while(cu.hasNext()){
			i++;
			api.add((String) cu.next().get("name"));
			//System.out.println(cu.next().get("rating"));
		}
		System.out.println(i);
		return api;
	}
	
	public ArrayList<String> getQueryRatingGreater(float category){
		ArrayList<String> api = new ArrayList<String>();
		ArrayList<Float> apif = new ArrayList<Float>();
		DBCollection c ;
		int i =0;
		c = dbObject.getCollection("temp");
		BasicDBObject obj = new BasicDBObject();
		obj.put("rating", new BasicDBObject("$gt",category));
		DBCursor cu = c.find(obj);
		while(cu.hasNext()){
			i++;
			api.add((String) cu.next().get("name"));
			//apif.add((Float) cu.next().get("rating"));
			//System.out.println(cu.next().get("rating"));
		}
		System.out.println(i);
		return api;
	}
	
	public ArrayList<String> getQueryYear(String coll,String year){
		//System.out.println(date);
		int year2 = Integer.parseInt(year);
		ArrayList<String> api = new ArrayList<String>();
		DBCollection c ;
		if(coll.equals("API")){
		c = dbObject.getCollection("temp");
		}
		else{
			System.out.println("in mashup");
			c = dbObject.getCollection("Mashup");
		}
		DBObject year1 = new BasicDBObject("$project", new BasicDBObject("name",1).append("year",new BasicDBObject("$year","$updated")));
		DBObject year_match = new BasicDBObject("$match",new BasicDBObject("year",year2));
		AggregationOutput ao = c.aggregate(year1,year_match);
		for (DBObject result : ao.results()) {
			String name = (String) result.get("name");
			api.add(name);
			// System.out.println(result);
			 }
		return api;
	}
	
	public ArrayList<String> getQueryKey1(String coll,String keyword){
		//System.out.println(date);
		//System.out.println("key: "+keyword);
		ArrayList<String> api = new ArrayList<String>();
		DBCollection c ;
		String key_search ="";
	
		String [] splitkey = keyword.split(" ");
		//key_search = "\"" + keyword+"\"";
		for(int i = 0 ;i < splitkey.length;i++){
		//	System.out.println("splitkey: "+splitkey[i]);
			key_search = key_search + "\""+splitkey[i]+"\"";
			
			String a = "\" \" \" \"";
			System.out.println("a: "+a);
			
			
		}
	//	key_search = key_search + "\"";
		System.out.println("key_search: "+key_search);
		if(coll.equals("API")){
		c = dbObject.getCollection("temp");
		}
		else{
			c = dbObject.getCollection("Mashup");
		}
		BasicDBObject  bdo = new BasicDBObject();
		String searchKey = "\"" + keyword +"\"";
		BasicDBObject key = new BasicDBObject("$search",key_search);
		bdo.put("$text", key);
		DBCursor cu = c.find(bdo);
		while(cu.hasNext()){
			
			api.add((String) cu.next().get("name"));
			//apif.add((Float) cu.next().get("rating"));
			//System.out.println(cu.next().get("rating"));
		}
		return api;
	}


	public ArrayList<String> getQueryKey(String coll,String keyword){
		//System.out.println(date);
		System.out.println("key: "+keyword);
		ArrayList<String> api = new ArrayList<String>();
		DBCollection c ;
		if(coll.equals("API")){
		c = dbObject.getCollection("temp");
		}
		else{
			c = dbObject.getCollection("Mashup");
		}
		BasicDBObject  bdo = new BasicDBObject();
		BasicDBList bdl = new BasicDBList();
		List<BasicDBObject> list = new ArrayList<BasicDBObject>();
		//BasicDBObject 
		BasicDBObject obj = new BasicDBObject("tilte",new BasicDBObject("$regex",keyword));
		BasicDBObject obj1 = new BasicDBObject("summary",new BasicDBObject("$regex",keyword));
		BasicDBObject obj2 = new BasicDBObject("description",new BasicDBObject("$regex",keyword));
		bdl.add(obj);
		bdl.add(obj1);
		bdl.add(obj2);
		bdo.put("$or", bdl);
		DBCursor cu = c.find(bdo);
		while(cu.hasNext()){
			
			api.add((String) cu.next().get("name"));
			//apif.add((Float) cu.next().get("rating"));
			//System.out.println(cu.next().get("rating"));
		}
		return api;
	}


}
