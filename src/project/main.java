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
	       // System.out.print("znaleziono\n");
	        //for(int i=0;i<5;i++) {
			//	System.out.print("("+c1[i]+","+c2[c1[i]]+")\n");
			//}
	        //System.out.print("ooooooooo: "+check(s.g1,s.g2,c1,c2)+"\n");
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
		//Graph g1=new Graph(7);
		Graph g2=new Graph(5);
		//g1.fullGraph();
		//g1.GetOutEdge();
		AnimalGraph z=new AnimalGraph();
		Animal a1=new Animal("Lew",2);
		Animal a2=new Animal("Koza",2);
		Animal a3=new Animal("Pies",1);
		a2.addException("Lew");
		//a3.addException("Lew");
		z.addAnimal(a1);
		z.addAnimal(a2);
		z.addAnimal(a3);
		z.init();
		
		//a2.name="O";
		//z.init();
		//a3=null;
		//z.deleteAnimal(a3);
		//z.init();
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		g2.addEdge(0, 3);
		g2.addEdge(0, 4);
		g2.GetOutEdge();
		
		State s1=new State(g2,z.g1);
		int c1[]=new int[z.g1.n];
		int c2[]=new int[g2.n];
		if(match(s1,c1,c2)) {
			System.out.print("TRUE\n");
			for(int i=0;i<g2.n;i++) {
				System.out.print("("+c1[i]+","+z.ID[c2[c1[i]]]+")\n");
			}
		}else {
			System.out.print("FALSE\n");
		}
	}

}
