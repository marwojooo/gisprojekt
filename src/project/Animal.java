package project;

import java.util.LinkedList;

public class Animal {
	public String name;
	public int numberCage;
	LinkedList<String> exception;
	Animal(String name,int n){
		this.name=name;
		numberCage=n;
		exception=new LinkedList<String>();
	}
	void addException(String name) {
		String[] names=new String[exception.size()];
		
		exception.toArray(names);
		for(int i=0;i<exception.size();i++) {
			if(name==names[i]) {
				return;
			}
		}
		
		exception.add(name);
	}
	void deleteException(String name) {
		for(int i=0;i<exception.size();i++) {
			if(name==exception.get(i)) {
				exception.remove(name);
				return;
			}
		}
	}

	
}
