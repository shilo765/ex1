package ex1.tests;

import ex1.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;


import static org.junit.jupiter.api.Assertions.*;

public class WGraph_AlgoTest {
    public WGraph_Algo graphExample()
    {
        WGraph_Algo wg=new WGraph_Algo();
        wg.getGraph().addNode(3);
        wg.getGraph().addNode(5);
        wg.getGraph().addNode(1);
        wg.getGraph().addNode(2);
        wg.getGraph().addNode(18);
        wg.getGraph().addNode(4);
        wg.getGraph().addNode(10);
        wg.getGraph().addNode(9);
        wg.getGraph().connect(1,3,1);
        wg.getGraph().connect(3,2,2);
        wg.getGraph().connect(3,5,8);
        wg.getGraph().connect(3,18,2);
        wg.getGraph().connect(2,5,3);
        wg.getGraph().connect(18,5,30);
        wg.getGraph().connect(18,4,4);
        wg.getGraph().connect(18,10,2);
        wg.getGraph().connect(10,9,2);
        wg.getGraph().connect(10,4,1);
        return wg;
    }
    /**test the shortestPathDist that work properly*/
    @Test
    public void distTest(){
        WGraph_Algo wg=new WGraph_Algo();
        if(wg.shortestPathDist(2,5)!=-1)
            fail("u cant search number that not on the graph");
        wg.getGraph().addNode(3);
        if(wg.shortestPathDist(3,3)!=0)
            fail("its not return the right number in the case we send equals values");
        wg=graphExample();
        if(wg.shortestPathDist(1,9)!=7)
            fail("shortestPathDist not work properly");
        wg.getGraph().addNode(30);
        if(wg.shortestPathDist(1,30)!=-1)
            fail("shortestPathDist not work properly");
    }
    /**test check the isConnect method */
    @Test
    public void isConnectTest()
    {
        WGraph_Algo wg=new WGraph_Algo();
        if(!wg.isConnected())
            fail("0 nodes in the graph must should return true");
        wg.getGraph().addNode(8);
        if(!wg.isConnected())
            fail("1 nodes in the graph must should return true");
        wg.getGraph().addNode(9);
        if(wg.isConnected())
            fail("its need to be unConnect in this example");
        wg.getGraph().connect(8,9,17);
        if(!wg.isConnected())
            fail("its need to be connect in this example");
        wg=graphExample();
        if(!wg.isConnected())
            fail("its need to be connect in this example");
        wg.getGraph().addNode(90);
        if(wg.isConnected())
            fail("its need to be connect in this example");
        wg.shortestPath(1,10);
    }
    @Test
    public void shortestPathTest()
    {
        WGraph_Algo wg=new WGraph_Algo();
        LinkedList<NodeInfo> l1;
        assertNull(wg.shortestPath(5,3));
        wg.getGraph().addNode(8);
        assertNull(wg.shortestPath(5,3));
        wg.getGraph().addNode(7);
        assertNull(wg.shortestPath(8,7));
        wg.getGraph().connect(7,8,10);
        l1=(LinkedList)wg.shortestPath(7,8);
        assertEquals(l1.getFirst().getKey(),7);
        l1.removeFirst();
        assertEquals(l1.getFirst().getKey(),8);
        wg=graphExample();
        l1=(LinkedList)wg.shortestPath(1,9);
        assertEquals(l1.getFirst().getKey(),1);
        assertEquals(l1.getLast().getKey(),9);
        assertNull(wg.shortestPath(0,8));
    }
    /** test the copy and equals on WG_Algo*/
    @Test
    public void equals()
    {
        weighted_graph_algorithms g1=new WGraph_Algo();
        WGraph_Algo g2=new WGraph_Algo();
        g1.getGraph().addNode(7);
        g1.getGraph().addNode(8);
        g1.copy();
        g2.setWGraph((WGraph_DS)g1.copy());
        assertEquals(g1,g2);
    }
    /** test sava and load*/
    @Test
    public void sAndlTest()
    {
        WGraph_Algo wg=new WGraph_Algo() ;
        WGraph_Algo wg2=new WGraph_Algo() ;
        wg.save("test");
        wg2.load("test");
        assertEquals(wg2.getGraph(),wg.getGraph());
        wg= graphExample();
        wg.shortestPath(1,9);
        //System.out.println(wg);
        if(!wg.save("test"))
            fail("save method not work properly");
        wg2.load("test");
        assertEquals(wg2.getGraph(),wg.getGraph());
        wg.getGraph().removeEdge(1,3);
        wg.save("test");
        wg2.load("test");
        assertEquals(wg2.getGraph(),wg.getGraph());
    }
}
