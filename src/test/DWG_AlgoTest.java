package test;

import api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DWG_AlgoTest {
    @Test
    public void copyCloneTest()
    {
        dw_graph_algorithms dwa=new DWGraph_Algo();
        dw_graph_algorithms dwa2;
        DWGraph_DS dwg=new DWGraph_DS();

        NodeData nd1=new NodeData();
        dwa.getGraph().addNode(nd1);
        dwa2=dwa;
        if(dwa2.getGraph().nodeSize()==0)
            fail("method getGraph not work properly");
        dwg=(DWGraph_DS)dwa.copy();
        if(!dwa.getGraph().equals(dwg))
            fail("method equals not work properly");
        dwg.removeNode(nd1.getKey());
       // dwa2.getGraph().removeNode(nd1.getKey());
        if(dwa.getGraph().nodeSize()!=1||dwg.nodeSize()!=0)
            fail("method copy not work properly");
        if(dwa.getGraph().equals(dwg))
            fail("method equals not work properly");
        //dwa.save("hh");
        //dwa.load("hh");
    }
}
