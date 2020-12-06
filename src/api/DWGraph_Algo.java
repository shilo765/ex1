package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class DWGraph_Algo implements dw_graph_algorithms{
    directed_weighted_graph dwg=new DWGraph_DS();
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
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        return null;
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
            JsonParser parser=new JsonParser();
            DWGraph_Algo dwa=new DWGraph_Algo();
            Gson json=new Gson();
            Object obj= parser.parse(new FileReader(".\\"+file+".json"));
            JsonObject jsOb=(JsonObject)obj;
            String key=jsOb.get("key").toString();
            this.dwg=dwa.getGraph();
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
