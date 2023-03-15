package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;
import java.util.List;


public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        public WorldState w;
        public int moveLen;
        public SearchNode pre;
        public SearchNode(WorldState A, int len,SearchNode p){
            this.w = A;
            this.moveLen = len;
            this.pre = p;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.moveLen+this.w.estimatedDistanceToGoal() - o.moveLen - o.w.estimatedDistanceToGoal();
        }
    }
    private MinPQ<SearchNode> PQ;
    private int resultMove;
    private List<WorldState> resultWords;

    public Solver(WorldState initial){
        resultMove = 0;
        resultWords = new LinkedList<>();
        PQ = new MinPQ<>();

        WorldState temp = initial;
        SearchNode s = new SearchNode(temp,0,null);
        resultWords.add(s.w);
        PQ.insert(s);

        while(true){
            SearchNode p = PQ.delMin();
            temp = p.w;
            resultWords.add(p.w);
            if(temp.isGoal()){
                resultMove = p.moveLen;
                break;
            }

            for(WorldState x : temp.neighbors()){
                SearchNode q = new SearchNode(x,p.moveLen+1,p);
                if(q.pre.equals(p)){
                    continue;
                }
                PQ.insert(q);
            }

        }
    }
    public int moves(){
        return resultMove;
    }

    public Iterable<WorldState> solution(){
        return resultWords;
    }
}
