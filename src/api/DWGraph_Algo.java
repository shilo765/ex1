package api;

import com.google.gson.*;

import java.io.*;
import java.util.*;

public class DWGraph_Algo implements dw_graph_algorithms{
    directed_weighted_graph dwg=new DWGraph_DS();
    private PriorityQueue<NodeData> pq=new PriorityQueue<>();
    private static int nodeCount;

    @Override
    public void init(directed_weighted_graph g) {
        this.dwg=g;
    }

    @Override
    public directed_weighted_graph getGraph() {
        return this.dwg;
    }

    @Override
    public directed_weighted_graph copy() {
        DWGraph_DS dwg2=new DWGraph_DS();
        return ((DWGraph_DS)this.dwg).copy();
    }

    @Override
    public boolean isConnected() {
        if (this.dwg.getV().size()==1||this.dwg.getV().size()==0)
            return true;
        node_data tempNd=null;
        boolean b1=true;
        for(node_data n1:this.dwg.getV())
        {
            if(tempNd==null)
                tempNd=n1;
            else
            {
                b1=b1&&(shortestPathDist(tempNd.getKey(),n1.getKey())!=-1);
            }
        }
        if(!b1)
            return false;
        ((DWGraph_DS)dwg).reverse();
        for(node_data n1:this.dwg.getV())
        {
            if(tempNd==null)
                tempNd=n1;
            else
            {
                b1=b1&&(shortestPathDist(tempNd.getKey(),n1.getKey())!=-1);
            }
        }
        ((DWGraph_DS)dwg).reverse();
        return b1;
    }
    /**set all the info to be -1 like defult*/
    public void setAllInfo()
    {
        for(node_data n1 :dwg.getV())
            n1.setInfo("-1");
    }
    @Override
    public double shortestPathDist(int src, int dest) {
        this.pq.clear();
        setAllInfo();
        nodeCount=0;

        NodeData temp=new NodeData();
        if(!dwg.getV().contains(dwg.getNode(src))||!dwg.getV().contains(dwg.getNode(dest)))
            return -1;
        nodeCount=1;
        if(src==dest)
            return 0;
        dwg.getNode(src).setInfo("0");
        pq.add((NodeData)dwg.getNode(src));
        while (!pq.isEmpty())
        {

            temp=pq.poll();
            for (edge_data e1: dwg.getE(temp.getKey())) {
                if(dwg.getNode(e1.getDest()).getInfo().equals("-1")||Double.parseDouble(dwg.getNode(e1.getDest()).getInfo())>Double.parseDouble(temp.getInfo())+e1.getWeight())
                {
                    ((NodeData)dwg.getNode(e1.getDest())).setLastNei(temp.getKey());
                    if(dwg.getNode(e1.getDest()).getInfo().equals("-1"))
                        nodeCount++;
                    else
                        pq.remove(dwg.getNode(e1.getDest()));
                    dwg.getNode(e1.getDest()).setInfo((Double.parseDouble(temp.getInfo())+e1.getWeight())+"");
                    pq.add((NodeData) dwg.getNode(e1.getDest()));
                }
            }
        }
        return Double.parseDouble(dwg.getNode(dest).getInfo());
    }
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        LinkedList<node_data> l1=new LinkedList<>();
        double temp=shortestPathDist(src,dest);
        NodeData n1=new NodeData();
        if(src<1||dest<1)
            return l1;
        if(dwg.getV().size()==0)
            return l1;
        if(!dwg.getV().contains(dwg.getNode(src))||!dwg.getV().contains(dwg.getNode(dest)))
            return l1;
        if(src==dest){
            l1.addFirst(dwg.getNode(src));
            return l1;}
        if(temp==-1)
            return l1;
        n1=(NodeData)dwg.getNode(dest);
        while(n1.getKey()!=src)
        {
            l1.addFirst(n1);
            n1=(NodeData) dwg.getNode(n1.getLastNei());
        }
        l1.addFirst(n1);
        return l1;
    }

    @Override
    public boolean save(String file) {
        Gson gson=new Gson();
        String json=gson.toJson(this);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(".\\"+file+".json"));
            bw.write(json);
            bw.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean load(String file) {
        try {
            directed_weighted_graph dwG=new DWGraph_DS();
            JsonParser parser = new JsonParser();
            DWGraph_Algo dwa = new DWGraph_Algo();
            Gson json = new Gson();
            Object obj = parser.parse(new FileReader(".\\" + file + ".json"));
            JsonObject jsOb = (JsonObject) obj;
            JsonObject dwg = jsOb.get("dwg").getAsJsonObject();
            System.out.println(dwg.toString());
            JsonObject nodes = dwg.get("nodes").getAsJsonObject();
            for (String node : nodes.keySet()) {
                NodeData n1 = new NodeData();
                JsonObject nodeKey = nodes.get(node).getAsJsonObject();
                n1.setKey(Integer.parseInt(node));
                n1.setTag(((JsonObject) nodes.get(node)).get("tag").getAsInt());
                n1.setInfo(((JsonObject) nodes.get(node)).get("info").getAsString());
                n1.setLastNei(((JsonObject) nodes.get(node)).get("lastNei").getAsInt());
                JsonObject gl = nodeKey.get("gl").getAsJsonObject();
                ((GeoLocation)n1.getLocation()).setX(gl.get("x").getAsDouble());
                ((GeoLocation)n1.getLocation()).setY(gl.get("y").getAsDouble());
                ((GeoLocation)n1.getLocation()).setZ(gl.get("z").getAsDouble());
                ((GeoLocation)n1.getLocation()).setX(gl.get("x").getAsDouble());
                ((GeoLocation)n1.getLocation()).setDistance(gl.get("distance").getAsDouble());
                dwG.addNode(n1);
            }
            JsonObject edges = dwg.get("edges").getAsJsonObject();
            for(String edge : edges.keySet())
            {
                JsonObject edgeKey = edges.get(edge).getAsJsonObject();
                edge_data ed=new EdgeData(Integer.parseInt(edge.split(",")[0]),Integer.parseInt(edge.split(",")[1]),edgeKey.get("weight").getAsDouble());
                ed.setInfo(edgeKey.get("info").getAsString());
                ed.setTag(edgeKey.get("tag").getAsInt());
                dwG.connect(Integer.parseInt(edge.split(",")[0]),Integer.parseInt(edge.split(",")[1]),edgeKey.get("weight").getAsDouble());
                dwG.getEdge(Integer.parseInt(edge.split(",")[0]),Integer.parseInt(edge.split(",")[1])).setTag(edgeKey.get("tag").getAsInt());
                dwG.getEdge(Integer.parseInt(edge.split(",")[0]),Integer.parseInt(edge.split(",")[1])).setInfo(edgeKey.get("info").getAsString());
            }
            this.init(dwG);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DWGraph_Algo that = (DWGraph_Algo) o;
        return dwg.equals(that.dwg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dwg);
    }
}
