package tool;

import java.util.ArrayList;
import java.util.Collections;

public class TestSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList name=new ArrayList();
		name.add(5);
		name.add(-5);
		name.add(15);
		name.add(25);
		name.add(95);
		name.add(10);		
		Collections.sort(name);
		for(int i=0;i<name.size();i++){
			System.out.println(name.get(i));
		}
	}

}
