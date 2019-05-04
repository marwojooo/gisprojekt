package project;
import project.Graph;
public class State { 
	int core_len;
    int core_1[];
    int core_2[];
    Graph g1, g2;
    int n1, n2;
    boolean M[][];

  State(Graph ag1,Graph ag2){
      g1=ag1;
      g2=ag2;
      n1=g1.n;
      n2=g2.n;

      core_len=0;

      core_1=new int[n1];
      core_2=new int[n2];
      M=new boolean[n1][n2];

      int i,j;

      for(i=0; i<n1; i++){
    	  core_1[i]=Graph.NULL_NODE;
      }
      for(i=0; i<n2; i++){
    	  core_2[i]=Graph.NULL_NODE;
      }
      for(i=0; i<n1; i++)
    	  for(j=0; j<n2; j++)
    		  M[i][j]=(g1.numInEdge[i] <= g2.numInEdge[j] &&
               			g1.numOutEdge[i] <= g2.numOutEdge[j]) ?
               			true: false;
  	}
  
  	State(State state){
  		g1=state.g1;
		g2=state.g2;
		n1=state.n1;
		n2=state.n2;

		core_len=state.core_len;

		core_1=new int[n1];
		core_2=new int[n2];
		M=new boolean [n1][n2];

		int i,j;

		for (i=0; i<core_len; i++)
			M[i]=null;

		for (i=core_len; i<n1; i++){ 
			M[i]=new boolean[n2];
		}

		for(i=0; i<n1; i++)
			core_1[i]=state.core_1[i];
		for(i=0; i<n2; i++)
			core_2[i]=state.core_2[i];
		for(i=core_len; i<n1; i++)
			for(j=0; j<n2; j++)
				M[i][j]=state.M[i][j];
  		}


  		Pair nextPair(int prev_n1, int prev_n2){ 
  			if (prev_n1==Graph.NULL_NODE){ 
  				prev_n1=core_len;
  				prev_n2=0;
  			}
  			else if (prev_n2==Graph.NULL_NODE)
  				prev_n2=0;
  			else
  				prev_n2++;

  			if (prev_n2>=n2){ 
  				prev_n1++;
  				prev_n2=0;
  			}

  			if (prev_n1!=core_len)
  				return null;
  			while (prev_n2<n2 && M[prev_n1][prev_n2]==false)
  				prev_n2++;
  			if (prev_n2<n2){
  				return new Pair(prev_n1,prev_n2);
  			}
  			else
  				return null;
}


  		boolean isFeasiblePair(int node1, int node2){
  			return M[node1][node2]!=false;
  		}

  		void addPair(int node1, int node2){

  			core_1[node1]=node2;
  			core_2[node2]=node1;

  			core_len++;
  			//cout<<"aha";
  			int k;

  			for(k=core_len; k<n1; k++)
  				M[k][node2]=false;
  			
  			/*System.out.print("\n");  			
  			for(int i=core_len-1;i<n1;i++){
  				for(int j=0;j<n2;j++){
  					System.out.print(M[i][j]+" ");
  				}
  				System.out.print("\n");
  			}*/
  			refine();
  			/*for(int i=core_len-1;i<n1;i++){
  				for(int j=0;j<n2;j++){
  					System.out.print(M[i][j]+" ");
  				}
  				System.out.print("\n");
  			}
  			System.out.print("\n");
  			*/
}

  		void refine(){
  			for(int i=core_len; i<n1; i++)
  				for(int j=0; j<n2; j++)
  					if (M[i][j]){ 
  						boolean edge_ik, edge_jl;
  						for(int k=0; k<core_len; k++){ 
  							int l=core_1[k];
  							edge_ik=g1.contains(i,k);
  							edge_jl=g2.contains(j,l);
  							//System.out.print(i+" "+j+" "+edge_ik+" "+edge_jl+"\n");
  							if (edge_jl==false&&edge_ik==true) { 
  								M[i][j]=false;
  								break;
  							}
  						}
  					}
  		}
  		State Clone(){ 
  			return new State(this);
  		}

  		boolean isGoal() { return core_len==n1; }
  		boolean isDead() { if (n1>n2) return true;boolean f=true;
                    for(int i=core_len; i<n1; i++){ 
                    	for(int j=0; j<n2; j++)
                    		if (M[i][j]!=false) break;f=false;
                    	if(f) {
                        return true;}
                    	f=true;
                        //next_row: ;
                      }
                     return false;
                  }
  		void backTrack(){
  		}

  		void GetCoreSet(int c1[], int c2[]){ 
  			int i,j;
  			for (i=0,j=0; i<n1; i++)
  				if (core_1[i] != Graph.NULL_NODE){ 
  					c1[j]=i;
  					c2[j]=core_1[i];
  					j++;
  				}
  		}
    
}