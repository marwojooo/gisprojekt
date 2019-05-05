package project;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class AnimalGraph {
	public LinkedList<Animal> animals;
	Graph g1;
	int n;
	public String[] ID;
	public String[][] exceptions;
	int[] numberException;
	public AnimalGraph(){
		n=0;
		animals=new LinkedList<Animal>();
	}
	void addAnimal(Animal a) {
		animals.add(a);
	}
	void deleteAnimal(Animal a) {
		Animal[] b=new Animal[animals.size()];
		animals.toArray(b);
		int k=0;
		for(int i=0;i<animals.size();i++) {
			if(b[i]==a) {
				k++;
				animals.remove(i);
				animals.addFirst(b[i]);
			}
		}
		
		for(int j=0;j<k;j++) {
			animals.removeFirst();
		}

	}
	void init() {

		Animal[] a=new Animal[animals.size()];
		animals.toArray(a);
		n=0;
		for(int i=0;i<animals.size();i++) {
			for(int j=0;j<a[i].numberCage;j++) {
				n++;
			}
		}
		
		ID=new String[n];
		exceptions=new String[n][0];
		numberException=new int[n];
		int k=0;
		for(int i=0;i<animals.size();i++) {
			for(int j=0;j<a[i].numberCage;j++) {
				ID[k]=a[i].name;
				exceptions[k]=new String[a[i].exception.size()];
				a[i].exception.toArray(exceptions[k]);
				numberException[k]=a[i].exception.size();
				k++;
			}
		}
		g1=new Graph(n);
		g1.fullGraph();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<numberException[i];j++) {
				for(int z=0;z<n;z++) {
					if(exceptions[i][j]==ID[z]) {
						g1.A[i][z]=Graph.NULL_NODE;
						g1.A[z][i]=Graph.NULL_NODE;
					}
				}
			}
		}
		g1.GetOutEdge();
	}
	
}
