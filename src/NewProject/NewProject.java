package NewProject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NewProject {
	public static void main(String[] args)  throws IOException{
			ArrayList< Object>stopList=new ArrayList<>();
			ArrayList< Object>routeList=new ArrayList<>();
			JSONParser parser=new JSONParser();
			FileWriter fileWriter=new FileWriter("/home/isa/桌面/output.txt");
			try {
			org.json.simple.JSONObject 	stop=(org.json.simple.JSONObject) parser.parse(new FileReader("/home/isa/桌面/GetSTOP"));
			org.json.simple.JSONObject 	route=(org.json.simple.JSONObject) parser.parse(new FileReader("/home/isa/桌面/GetRoute"));
			org.json.simple.JSONArray stopInfo =(org.json.simple.JSONArray) stop.get("BusInfo");
			org.json.simple.JSONArray routeInfo =(org.json.simple.JSONArray) route.get("BusInfo");
			
			for (int i = 0; i < routeInfo.size(); i++) {
				JSONObject stopId = (JSONObject) stopInfo.get(i);
				stopList.add(stopId.get("routeId"));
				JSONObject routeId = (JSONObject) routeInfo.get(i);
				routeList.add(routeId.get("Id"));
			}
		for (int i = 0; i <routeList .size(); i++) {
			for (int j = 0; j < stopList.size(); j++) {
				if ((routeList.get(i)).equals(stopList.get(j))) {
		
//					JSONArray arrayStart=new JSONArray();
//					arrayStart.add("stopname: "+((JSONObject)stopInfo.get(i)).get("nameZh").toString());
//					arrayStart.add("stopid: "+stopList.get(i).toString());
//					arrayStart.add("isback: "+((JSONObject)stopInfo.get(i)).get("goBack").toString());
					
					ArrayList<HashMap<String,Object>> list=new ArrayList<>();
					HashMap<String, Object>map=new HashMap<>();

					map.put("seqNo" ,Double.parseDouble(((((JSONObject)stopInfo.get(i)).get("seqNo").toString()))));
					map.put("stopname",((JSONObject)stopInfo.get(i)).get("nameZh"));
					map.put("stopid",stopList.get(i));
					
//					list.add(Double.parseDouble(((((JSONObject)stopInfo.get(i)).get("seqNo").toString()))));
					list.add(map);
					
					for (int k = 0; k < list.size(); k++) {
						if ((k+1)<list.size()) {
							if ((Double)list.get(k).get("seqNo")>(Double)list.get(k+1).get("seqNo") ){
							
								Collections.swap(list, k, k+1);
							}
							
						}
					}
				

					JSONObject output=new JSONObject();
					output.put("nameZh",((JSONObject)routeInfo.get(j)).get("nameZh"));
					output.put("Id",routeList.get(j));
//					output.put("stop", arrayStart);
					
					
//					output.put("stopNameZh",((JSONObject)stopInfo.get(i)).get("nameZh"));					
//					output.put("Id",stopList.get(i));
//					output.put("goBack",((JSONObject)stopInfo.get(i)).get("goBack"));

					fileWriter.write(output.toJSONString());
//					System.out.println("\nJSON Object: " + output);
					System.out.println(list);
				}
			}
		}	
		System.out.println("successful");
		fileWriter.close();

			} catch (Exception e) {
				 e.printStackTrace();
			}
			
	}

}
