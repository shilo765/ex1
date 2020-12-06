package api;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class DWGraph_DS implements directed_weighted_graph {
    private HashMap<Integer, node_data> nodes;
    private HashMap<String, edge_data> edges;
    public static int ModeCount = 0;
    @Override
    public node_data getNode(int key) {

        return (node_data) this.nodes.get(key);
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        return this.edges.get(src+","+dest);
    }

    @Override
    public void addNode(node_data n) {
        if(nodes.put(n.getKey(),n)!=null)
            ModeCount++;
    }//small test

    @Override
    public void connect(int src, int dest, double w) {
    edge_data ed=new EdgeData(src,dest,w);
    if(edges.put(src+","+dest,ed)!=null)
        ModeCount++;
    }

    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        HashSet<edge_data> nei=new HashSet<>();
        for(Integer str:nodes.keySet())
            if(str.toString().split(",")[0].equals(node_id))
                nei.add(edges.get(str));
        return nei;
    }

    @Override
    public node_data removeNode(int key) {
        if(nodes.get(key)!=null){
        for(String str: edges.keySet())
            if(str.split(",")[0].equals(key)||str.split(",")[1].equals(key))
            {edges.remove(str);ModeCount++;} ModeCount++;}
        return nodes.remove(key);
    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        if(edges.get(src+","+dest)!=null)
            ModeCount++;
        return edges.remove(src+","+dest);
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int getMC() {
        return ModeCount;
    }
}
