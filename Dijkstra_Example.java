import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dijkstra_Example {

    static class Graph{
        class Node{
        int d;
        int w; 

        public Node(int d, int w) {
            this.d = d;
            this.w = w;
        }

            @Override
            public String toString() {
                return "{d=" + d + ", w=" + w + '}';
            }
        
        
    }
        int n;
        ArrayList<Node>[] adjMatrix;

        public Graph(int n) {
            this.n = n;
            adjMatrix = new ArrayList[n*2];
            for(int i=0;i<n*2;i++)
            {
                adjMatrix[i] = new ArrayList<>();
            }
        }
        public void addEdge(int s, int d, int w) {
            s--;
            d--;
            Node x = new Node(d, w);
            adjMatrix[s].add(x);
        }
        int minVertex(boolean [] min, int [] k){
            int leastKey = Integer.MAX_VALUE;
            int vertex = -1;
            for (int i = 0; i <n ; i++) {
                if(min[i]==false && leastKey>=k[i]){
                    leastKey = k[i];
                    vertex = i;
                }
            }
            return vertex;
        }

        int getTwoCount(ArrayList<Integer> arr)
        {
            int countTwo = 0;
            for(int i=0;i<arr.size();i++)
            {
                if (arr.get(i) == 2)
                    countTwo++;
            }
            return countTwo;
        }
        void enhance()
        {
            for (int i=0;i<n;i++)
            {
                for (int j=0;j<adjMatrix[i].size();j++)
                {
                    adjMatrix[i+n].add(new Node(adjMatrix[i].get(j).d+n, adjMatrix[i].get(j).w));
                }
            }
            for (int i=0;i<n;i++)
            {
                int x = adjMatrix[i].size();
                for (int j=0;j<x;j++)
                {
                    if (adjMatrix[i].get(j).w == 0)
                    {
                        adjMatrix[i].add(new Node((adjMatrix[i].get(j).d+n), 0));
                    }
                }
            }
            n=n*2;
        }
        public void dijkstra(int src){
            /*for (int i = 0; i <n ; i++) {
                System.out.print(i + " -> ");
                for (int v = 0; v <adjMatrix[i].size() ; v++) {
                    System.out.print(adjMatrix[i].get(v).toString() + ", ");
                }
                System.out.println();
            }*/
            boolean[] check = new boolean[n];
            int [] d = new int[n];
            ArrayList<Integer>[]edges;
            edges = new ArrayList[n];
            for (int i = 0; i <n ; i++) {
                d[i] = Integer.MAX_VALUE;
                edges[i] = new ArrayList<>();
            }
            d[src] = 0;
            for (int i = 0; i <n ; i++) {
                int u = minVertex(check, d);
                
                check[u] = true;
                /*for (int xx = 0; xx <n ; xx++) {
                System.out.print("(" + d[xx] + "+" + check[xx] + "), ");
            }
                System.out.println();*/
                for (int v = 0; v <adjMatrix[u].size() ; v++) {
                        int v1 = adjMatrix[u].get(v).d;
                        if(check[v1]==false){
                            int updatedKey =  d[u]+adjMatrix[u].get(v).w;
                            
                            if(updatedKey<=d[v1])
                            {
                                d[v1] = updatedKey;
                            }
                        }
                }
            }
            /*for (int i = 0; i <n ; i++) {
                System.out.print("(" + d[i] + "+" + check[i] + "), ");
            }*/
            if (d[n-1] == Integer.MAX_VALUE)
                System.out.println(-1);
            else
                System.out.println(d[n-1]);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String []splitted = line.split(" ");
        int n = Integer.parseInt(splitted[0]);
        int m = Integer.parseInt(splitted[1]);
        
        Graph graph = new Graph(n);
        for (int x = 0;x<m;x++)
        {
            line = scanner.nextLine();
            splitted = line.split(" ");
            graph.addEdge(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2])-1);
        
        }
        int sourceVertex = 0;
        graph.enhance();
        graph.dijkstra(sourceVertex);
    }
}
