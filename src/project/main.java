package project;

public class main {
	public static boolean check(Graph g1,Graph g2,int c1[],int c2[]) {
		int M[][]=new int[g1.n][g1.n];
		for(int i=0;i<g1.n;i++) {
			for(int j=0;j<g1.n;j++) {
				M[i][j]=g2.A[c2[i]][c2[j]];
				//System.out.print(M[i][j]+" ");
				if(g1.A[i][j]>0&&M[i][j]<=0) {
					System.out.print(":((((((((");
					return false;
				}
				
			}
			//System.out.print("\n");
		}
		return true;
	}
	public static boolean match(State  s,int c1[],int c2[] ){
	    if(s.isGoal()){
	        s.GetCoreSet(c1, c2);
	        System.out.print("znaleziono\n");
	        for(int i=0;i<5;i++) {
				System.out.print("("+c1[i]+","+c2[c1[i]]+")\n");
			}
	        System.out.print("ooooooooo: "+check(s.g1,s.g2,c1,c2)+"\n");
	        return check(s.g1,s.g2,c1,c2);

	    }
	    if(s.isDead()){
	        return false;
	    }

	    int n1=Graph.NULL_NODE,n2=Graph.NULL_NODE;
	    Pair next=null;
	    boolean found = false;
	    while(!found&&(next=s.nextPair(n1,n2))!=null){
	        n1=next.a;
	        n2=next.b;
	        if(s.isFeasiblePair(n1,n2)){
	            State copy=s.Clone();
	            copy.addPair(n1,n2);
	            found=match(copy,c1,c2);

	        }
	    }

	    return found;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g1=new Graph(7);
		Graph g2=new Graph(5);
		g1.addEdge(3, 1);
		g1.addEdge(3, 0);
		g1.addEdge(3, 2);
		g1.addEdge(2, 4);
		g1.addEdge(4,5);
		g1.addEdge(5,6);
		g1.addEdge(6,1);
		g1.addEdge(6,0);
		g1.addEdge(5,0);
		g1.GetOutEdge();
		
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		g2.addEdge(0, 3);
		g2.addEdge(2, 3);
		g2.GetOutEdge();
		
		State s1=new State(g2,g1);
		int c1[]=new int[7];
		int c2[]=new int[5];
		if(match(s1,c1,c2)) {
			System.out.print("TRUE\n");
		}else {
			System.out.print("FALSE\n");
		}
		for(int i=0;i<5;i++) {
			System.out.print("("+c1[i]+","+c2[c1[i]]+")\n");
		}
	}

}
