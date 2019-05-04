package project;

public class Graph {
		public static int NULL_NODE=-1;
	    public int A[][];
	    public int n;
	    public int OutEdge[][];
	    public int numOutEdge[];
	    public int InEdge[][];
	    public int numInEdge[];
	    public Graph(int n){
	        this.n=n;
	        A=new int[n][n];
	        /*for(int i=0;i<n;i++){
	            A[i]=new int[n];
	        }*/

	        numOutEdge=new int[n];
	        numInEdge=new int[n];

	        OutEdge=new int[n][0];
	        InEdge=new int[n][0];

	        for(int i=0;i<n;i++){
	            for(int j=0;j<n;j++){
	                A[i][j]=NULL_NODE;
	            }
	        }
	    }
	    void GetOutEdge(){
	        for(int i=0;i<n;i++){
	            int sum=0;
	            for(int j=0;j<n;j++){
	                if(A[i][j]!=NULL_NODE){
	                    sum++;
	                }
	            }
	            numOutEdge[i]=sum;
	            numInEdge[i]=sum;

	            OutEdge[i]=new int[sum];
	            InEdge[i]=new int[sum];
	        }

	        for(int i=0;i<n;i++){
	                int sum=0;
	                //cout<<numOutEdge[i]<<endl;
	            for(int j=0;j<n;j++){
	                if(A[i][j]!=NULL_NODE){
	                    OutEdge[i][sum]=j;
	                    InEdge[i][sum]=j;
	                    sum++;
	                    //cout<<i<<" "<<j<<endl;
	                }
	            }
	        }
	    }
	    boolean contains(int node1,int node2){
	        return A[node1][node2]>0?true:false;
	    }
	    void addEdge(int node1,int node2){
	        A[node1][node2]=1;
	        A[node2][node1]=1;
	    }
	    void show(){
	        for(int i=0;i<n;i++){
	            for(int j=0;j<n;j++){
	                //cout<<A[i][j]<<" ";
	            }
	            //cout<<endl;
	        }
	    }
}
