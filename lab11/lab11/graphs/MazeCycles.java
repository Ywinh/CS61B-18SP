package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean hasCycle = false;
    private int[] nodeTo;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = 0;
        t = maze.xyTo1D(maze.N(), maze.N());
        nodeTo = new int[maze.N()* maze.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfsCheck(-1,s);
    }

    // Helper methods go here
    // u is the parent index. Ensure not look back
    private void dfsCheck(int u,int v){
        marked[v] = true;
        announce();

        for(int w : maze.adj(v)){
            if(!marked[w]){
                nodeTo[w] = v;
                dfsCheck(v,w);
            }else if(w != u){
                hasCycle = true;
                edgeTo[w] = v;
                announce();
                //这个遍历顺序我不太懂，不是应该逆序访问吗
                for (int x = v; x != w; x = nodeTo[x]) {
                    edgeTo[x] = nodeTo[x];
                    announce();
                }
            }
            if(hasCycle){
                return;
            }
        }
    }
}

