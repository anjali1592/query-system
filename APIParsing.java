import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class APIParsing {
	 DB dbObject;
   public void createJSON() throws JSONException, IOException{
	   BufferedReader br = new BufferedReader(new FileReader("APIparsing.txt"));
	   String file = "";
	   file = br.readLine();
	   while(file != null){
		   String[] splitFile = file.split(file);
		   JSONObject jo = new JSONObject();
		   jo.put("id", splitFile[0]);
		   
	   }
	  
   }
   APIParsing() throws UnknownHostException{
	   MongoClient mg = new MongoClient();
	   DB db = mg.getDB("test");
	   dbObject = db;
	   System.out.println("connn");
   }
	public static void connect() throws UnknownHostException {
		MongoClient mg = new MongoClient();
		DBCollection dbc;
		BasicDBObject obj;
		
		DB db = mg.getDB("test");
		System.out.println("Connected");
		dbc = db.getCollection("people");
		obj = new BasicDBObject("title", "MongoDB")
				.append("description", "database").append("likes", 100)
				.append("url", "http://www.tutorialspoint.com/mongodb/")
				.append("by", "tutorials point");

		dbc.insert(obj);
		
	}

	public void parsin() throws IOException, JSONException, ParseException {
		String beforeParsing = "";
		String[] parse = null;
		//String parseString = "";
		//String[] afterParsing = null;
		DBCollection dbc;
		// FileInputStream f = new FileInputStream("APIparsing.txt");
		// File fp = new File("Apiparsing.txt");
		BufferedReader br = new BufferedReader(new FileReader("APIparsing.txt"));
		beforeParsing = br.readLine();
		//BasicDBList list = new BasicDBList();
		BasicDBObject jo;
	
		while (beforeParsing != null) {
			String date_iso = "yyyy-MM-dd'T'HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(date_iso);
			parse = beforeParsing.split("\\$" + "\\#" + "\\$");
		 jo = new BasicDBObject();
			jo.put("id", parse[0]);
			jo.put("tilte", parse[1]);
			jo.put("summary", parse[2]);
			System.out.println("parse: "+parse[3]);
			if(parse[3] != null && !parse[3].isEmpty() ){
				
			jo.put("rating", Float.valueOf(parse[3]));
			}
			else{
				jo.put("rating", parse[3]);
			}
			jo.put("name", parse[4]);
			jo.put("label", parse[5]);
			jo.put("author", parse[6]);
			jo.put("description", parse[7]);
			jo.put("type", parse[8]);
			jo.put("downloads", parse[9]);
		    jo.put("useCount", parse[10]);
		    jo.put("sampleUrl", parse[11]);
			jo.put("downloadUrl", parse[12]);
			Date d = (Date)sdf.parse(parse[13]);
			jo.put("dateModified", d);
		    jo.put("remoteFeed", parse[14]);
			jo.put("numComments", parse[15]);
			jo.put("commentsUrl", parse[16]);
			
			jo.put("category", parse[18]);
			jo.put("protocols", parse[19]);
			jo.put("serviceEndpoint", parse[20]);
			jo.put("version", parse[21]);
			jo.put("wsdl", parse[22]);
			jo.put("data formats", parse[23]);
			jo.put("apigroups", parse[24]);
			
			jo.put("clientInstall", parse[26]);
			jo.put("authentication", parse[27]);
			jo.put("ssl", parse[28]);
			jo.put("readonly", parse[29]);
			jo.put("VendorApiKits", parse[30]);
			jo.put("CommunityApiKits", parse[31]);
			jo.put("blog", parse[32]);
			jo.put("forum", parse[33]);
			jo.put("support", parse[34]);
			jo.put("accountReq", parse[35]);
			jo.put("commercial", parse[36]);
			jo.put("provider", parse[37]);
			jo.put("managedBy", parse[38]);
			jo.put("nonCommercial", parse[39]);
			jo.put("dataLicensing", parse[40]);
			jo.put("fees", parse[41]);
			jo.put("limits", parse[42]);
			jo.put("terms", parse[43]);
			jo.put("company", parse[44]);
			
			Date date = (Date) sdf.parse(parse[45]);
			
			jo.put("updated", date);
			
			
			String parseString17 = parse[17];
			BasicDBList list17 = new BasicDBList();
			if(parseString17.length() > 1){
				String [] afterParsing17 = parseString17.split("\\#" + "\\#" + "\\#");
				for(int j = 0 ;j < afterParsing17.length;j++){
					list17.add(afterParsing17[j]);
				}
			}
			
			String parseString25 = parse[25];
			BasicDBList list25 = new BasicDBList();
			if(parseString25.length() > 1){
				String [] afterParsing25 = parseString25.split("\\#" + "\\#" + "\\#");
				for(int j = 0 ;j < afterParsing25.length;j++){
					list25.add(afterParsing25[j]);
				}
			}

				jo.put("Tags", list17);
				jo.put("example", list25);
			    System.out.println(jo.toString());
				dbc = dbObject.getCollection("temp");
				dbc.insert(jo);
				beforeParsing = br.readLine();
			}
		
			
		}
		

	
    
	public void mashup() throws IOException, JSONException, ParseException{
		String beforeParsing = "";
		String[] parse = null;
		String parseString = "";
		String[] afterParsing = null;
		DBCollection dbc;
		BufferedReader br = new BufferedReader(new FileReader("Mashup.txt"));
		beforeParsing = br.readLine();
		BasicDBList list = new BasicDBList();
		BasicDBObject jo = new BasicDBObject();
	
		while (beforeParsing != null) {
			String date_iso = "yyyy-MM-dd'T'HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(date_iso);
			jo = new BasicDBObject();
			parse = beforeParsing.split("\\$" + "\\#" + "\\$");
			
			jo.put("id", parse[0]);
			jo.put("tilte", parse[1]);
			jo.put("summary", parse[2]);
			jo.put("rating", parse[3]);
			jo.put("name", parse[4]);
			jo.put("label", parse[5]);
			jo.put("author", parse[6]);
			jo.put("description", parse[7]);
			jo.put("type", parse[8]);
			jo.put("downloads", parse[9]);
		    jo.put("useCount", parse[10]);
		    jo.put("sampleUrl", parse[11]);
		    Date d = (Date) sdf.parse(parse[12]);
			jo.put("dateModified", d);
			jo.put("numComments", parse[13]);
		    jo.put("commentsUrl", parse[14]);
		    
			Date date = (Date) sdf.parse(parse[17]);
		    jo.put("updated", date);
		    
			
			String parseString15 = parse[15];
			BasicDBList list15 = new BasicDBList();
			if(parseString15.length() > 1){
				String [] afterParsing15 = parseString15.split("\\#" + "\\#" + "\\#");
				for(int j = 0 ;j < afterParsing15.length;j++){
				String split_on_dollar15 = afterParsing15[j];
				
					String[] afterSplit15 = split_on_dollar15.split("\\$" + "\\$" + "\\$");
					if(afterSplit15.length > 1){
					BasicDBObject obj = new BasicDBObject();
					obj.put("componenetAPI", afterSplit15[0]);
					obj.put("ComponenetURL", afterSplit15[1]);
					BasicDBObject add_to_list = new BasicDBObject();
					add_to_list.put("API+URL", obj);
					list15.add(add_to_list);
					}
				
				else{
                list15.add(afterParsing15[j]);
				}
				}
				
			}
			String parseString16 = parse[16];
			BasicDBList list16 = new BasicDBList();
			if(parseString16.length() > 1){
				String [] afterParsing16 = parseString16.split("\\#" + "\\#" + "\\#");
				for(int j = 0 ;j < afterParsing16.length;j++){
					String split_on_dollar16 = afterParsing16[j];
					String[] afterSplit16 = split_on_dollar16.split("\\$" + "\\$" + "\\$");
					if(afterSplit16.length > 1){
					BasicDBObject obj = new BasicDBObject();
					obj.put("componenetAPI", afterSplit16[0]);
					obj.put("ComponenetURL", afterSplit16[1]);
					BasicDBObject add_to_list = new BasicDBObject();
					add_to_list.put("API+URL", obj);
					list16.add(add_to_list);
					
					
					}
					else{
						list16.add(afterParsing16[j]);
						
					}
					}
				
			}
	
				
			jo.put("tags", list15);
			jo.put("APIs", list16);
				
			
	       System.out.println(jo.toString());
			dbc = dbObject.getCollection("Mashup");
			dbc.insert(jo);
			beforeParsing = br.readLine();
		}
		

	}
	public static void main(String args[]) throws IOException, JSONException, ParseException {
		APIParsing ap = new APIParsing();
	   ap.parsin();
	   ap.mashup();

	}
}
