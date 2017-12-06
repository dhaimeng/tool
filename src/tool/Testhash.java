package tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Testhash {
//	static Map<String,Double> map=new HashMap<String,Double>();
	static Map map=new HashMap();
	static Map Getmap(){
		map.put("dd",33.21);
		map.put("aa",3.1);
		map.put("cc",2.121);
		map.put("bb",-782.1);
//		map.put(4.2,   "a"); 
//		map.put(2.1,   "b"); 
//		map.put(4.9,   "c"); 
//		map.put(3.1,   "d"); 
		return map;
	}
	static Set hs=new HashSet();
	static Set Getset(){
		hs.add("aaa");
		hs.add("bbbb");
		hs.add("aaa");
		return hs;
	}
	public static void printset(Set hs2){
		Iterator it=hs2.iterator(); 
		while(it.hasNext()) { 
			System.out.println(it.next());
		} 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		map=Getmap();
		printMap1(map);
		System.out.println("@@@");
		sortbyValue(map);
//		hs=Getset();
//		hs.remove("bbbb");
//		printset(hs);
	}
	/*Key值降序升序输出*/
	public static void printMap1(Map map){
		Object[] key =  map.keySet().toArray();    
		Arrays.sort(key);  
		for(int i = 0; i<key.length; i++)
//		for(int i = key.length-1; i>=0; i--)    //�������
		{  
		     System.out.println(key[i]+" "+map.get(key[i]));  
		}
	}
	/*Key值输出*/
	public static void printMap2(Map map){
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			try {
				Object key = it.next();
				System.out.println(key.toString()+" "+map.get(key));
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
	/*按照value值排序输出*/
	public static void sortbyValue(Map<String,Double> map){
		ArrayList<Map.Entry<String,Double>> ID=new ArrayList<Map.Entry<String, Double>>(map.entrySet());		
		Collections.sort(ID, new Comparator<Map.Entry<String, Double>>() {   
		    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
		    	if ((o2.getValue() - o1.getValue())>0)  
		            return 1;  
		          else if((o2.getValue() - o1.getValue())==0)  
		            return 0;  
		          else   
		            return -1;  
		    }
		});
		//��HASHMAP�е���������
		for (Iterator iter = ID.iterator(); iter.hasNext();) 
		{
		   Map.Entry entry = (Map.Entry)iter.next();
		   String  key = (String)entry.getKey();
		   System.out.println(key.toString()+" "+map.get(key));  
		}
		String a=ID.get(0).toString();
		System.out.println(a);
	}

}
