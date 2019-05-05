package project;

import java.util.LinkedList;

public class ZooGraph {
	public Graph g1;
	LinkedList<Integer> IDs;
	Integer[] ID;
	LinkedList<Edge> Edges;
	ZooGraph(){
		IDs=new LinkedList<Integer>();
		Edges=new LinkedList<Edge>();
	}
	void addNode(int id) {
		IDs.add(id);
	}
	void deleteNode(int id) {
		Integer[] A=new Integer[IDs.size()];
		IDs.toArray(A);
		int k=0;
		for(int i=0;i<IDs.size();i++) {
			if(A[i]==id) {
				k++;
				IDs.remove(i);
				IDs.addFirst(A[i]);
			}
		}
		Edge[] B=new Edge[Edges.size()];
		Edges.toArray(B);
		if(k>0) {
			for(int i=0;i<Edges.size();i++) {
				if(B[i].node1==id||B[i].node2==id) {
					
				}
			}
		}
		for(int j=0;j<k;j++) {
			IDs.removeFirst();
		}
	}
	void addEdge(int node1, int node2) {
		boolean n1=false,n2=false;
		Integer[] A=new Integer[IDs.size()];
		IDs.toArray(A);
		
		for(int i=0;i<A.length;i++) {
			if(A[i]==node1) {
				n1=true;
			}
			if(A[i]==node2) {
				n2=true;
			}
		}
		if(n1&&n2) {
			Edges.add(new Edge(node1,node2));
		}
	}
	void deleteEdge(int node1,int node2) {
		Edge[] A=new Edge[Edges.size()];
		Edges.toArray(A);
		int k=0;
		for(int i=0;i<IDs.size();i++) {
			if(A[i].node1==node1&&A[i].node2==node2) {
				k++;
				Edges.remove(i);
				Edges.addFirst(A[i]);
			}
		}
		for(int j=0;j<k;j++) {
			Edges.removeFirst();
		}
	}
	void addEdgeToGraph(int node1, int node2) {
		g1.addEdge(node1, node2);
		addEdge(node1,node2);
	}
	public void init() {
		ID=new Integer[IDs.size()];
		IDs.toArray(ID);
		g1=new Graph(IDs.size());
		
		Edge[] B=new Edge[Edges.size()];
		Edges.toArray(B);
		int k=-1,l=-1;
		for(int i=0;i<B.length;i++) {
			for(int j=0;j<ID.length;j++) {
				if(B[i].node1==ID[j]) {
					k=j;
				}
				if(B[i].node2==ID[j]) {
					l=j;
				}
			}
			if(k!=-1&&l!=-1) {
				g1.addEdge(l, k);
			}
		}
		g1.GetOutEdge();
		
	}
}
class Edge{
	int node1,node2;
	Edge(int node1,int node2){
		this.node1=node1;
		this.node2=node2;
	}
}
