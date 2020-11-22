package ex1.src;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
public class NodeInfo implements node_info,Comparable<NodeInfo> {
    private static int keyCount = 0;
    private int key = -1;
    private double tag = -1;
    private String info = "";
    private static double NodeEdges = 0.0D;
    private int lastNei = -1;
    private HashMap<Integer, node_info> nei;
    public static int neiCount;
    private HashMap<Integer,Double> weightMap;

    public NodeInfo() {
        this.key = ++keyCount;
        this.nei = new HashMap();
        this.weightMap=new HashMap<>();
        neiCount = 0;
    }
    public void setKey(int key)
    {
        this.key=key;
    }
    public void addToWeightMap(int node1,double w){this.weightMap.put(node1,w);}

    public double getWeight(int node1)
    {
        return this.weightMap.get(node1);
    }
    public HashMap getWeightMap(){ return this.weightMap;}

    public void removeWeight(int node1){this.weightMap.remove(node1);}

    public int getLastNei() {
        return this.lastNei;
    }

    public void setLastNei(int value) {
        this.lastNei = value;
    }
    @Override
    public int getKey() {
        return this.key == -1 ? 0 : this.key;
    }

    public Collection<node_info> getNi() {
        return this.nei.values();
    }

    public boolean hasNi(int key) {
        return this.nei.values().iterator().hasNext();
    }

    public void addNi(NodeInfo t) {
        if(t!=null)
        {
        if (!this.equals(t)) {
            this.nei.put(t.getKey(), t);
            ((NodeInfo)t).getHashNei().put(this.key,this);
            ++neiCount;
        }}

    }

    public void removeNode(NodeInfo node) {
        if (this.nei.containsKey(node.getKey())) {
            this.nei.remove(node.getKey());
            --neiCount;
        }

    }

    public int getNeiSize() {
        return neiCount;
    }
    @Override
    public double getTag() {
        return this.tag;
    }
    @Override
    public String getInfo() {
        return this.info;
    }

    public HashMap getHashNei(){return this.nei;}

    public NodeInfo copy() {
        NodeInfo n = new NodeInfo();
            n.setKey(this.key);
            n.setInfo(this.info);
            n.setTag(this.tag);
            n.setLastNei(this.lastNei);
            Iterator var3 = this.getNi().iterator();

            while(var3.hasNext()) {
                node_info m = (node_info)var3.next();
                n.getHashNei().put(m.getKey(), m);
            }
            Iterator var4 = this.weightMap.values().iterator();
            while(var3.hasNext()) {
                NodeInfo m = (NodeInfo)var3.next();
                n.getWeightMap().put(m.getKey(),n.getWeight(m.getKey()));
            }

            return this;
        }
    @Override
    public void setInfo(String s) {
        this.info = s;
    }
    @Override
    public void setTag(double t) {
        this.tag = t;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeInfo nodeInfo = (NodeInfo) o;
        return key == nodeInfo.key &&
                Double.compare(nodeInfo.tag, tag) == 0 &&
                lastNei == nodeInfo.lastNei &&
                info.equals(nodeInfo.info) &&
                nei.equals(nodeInfo.nei) &&
                weightMap.equals(nodeInfo.weightMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, tag, info, lastNei, nei, weightMap);
    }

    @Override
    public String toString() {
        String hashString="";
        for(double w1: weightMap.values())
            for(int k1: weightMap.keySet())
            hashString+=k1+"+"+w1+",";
        String neiString="";
        for(node_info n1: nei.values())
            neiString+=n1.getKey()+",";
        return "NodeInfo{" +
                "key=" + key +
                ", tag=" + tag +
                ", info='" + info + '\'' +
                ", lastNei=" + lastNei +
                ", nei=" + neiString +
                ", weightMap=" + hashString+
                        "}"+"\n";
        }


    @Override
    public int compareTo(NodeInfo n1)
    {
        if(this.getTag()>n1.getTag())
            return 1;
        if (this.getTag()<n1.getTag())
            return -1;
        return 0;
    }
    public double getNodesEdges() {
        return NodeEdges;
    }
}

