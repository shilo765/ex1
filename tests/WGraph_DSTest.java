package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;

import ex1.NodeInfo;
import ex1.WGraph_DS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class WGraph_DSTest {
    /**Test all the gets methods on WGraph_ds(except getEdge)*/
    @Test
    public void getsDefaultTest()
    {
        NodeInfo n1 =new NodeInfo();
        WGraph_DS g1 =new WGraph_DS();
        if(!g1.getNods().isEmpty())
            fail("the nodes Hashmaps must be empty");
        assertNotEquals(n1,g1.getNode(n1.getKey()));
        if(g1.getV().size()!=0)
            fail("the nodes Hashmaps must be empty");
        if(g1.getMC()!=0)
            fail("the MC ,ust be 0");
    }
    /**test check if the addNose and remove Node done properly*/
    @Test
    public void addAndRemoveNodeTest()
    {
        NodeInfo n1 =new NodeInfo();
        WGraph_DS g1 =new WGraph_DS();
        g1.addNode(n1.getKey());
        if(!(g1.getV().contains(n1)))
            fail("addNode not work properly");
        g1.removeNode(n1.getKey());
        if(g1.getNods().size()!=0)
            fail("removeNode not work properly");
        }
        /**test check all the method connecting to the edges */
        @Test
        public void edgeTests()
        {
            boolean temp;
            NodeInfo n1 =new NodeInfo();
            NodeInfo n2 =new NodeInfo();
            WGraph_DS g1 =new WGraph_DS();
            g1.connect(n1.getKey(),n2.getKey(),3);
            temp=g1.hasEdge(n1.getKey(), n2.getKey());
            if(temp)
                fail("u cant connect nodes without add them to the graph first(or hasEdge) ");
            g1.addNode(n1.getKey());
            g1.addNode(n2.getKey());
            g1.connect(n1.getKey(),n2.getKey(),3);
            temp=g1.hasEdge(n1.getKey(), n2.getKey());
            if(!temp)
                fail("the connect method not work properly(or hasEdge)");
            if(g1.getEdge(n1.getKey(), n2.getKey())==-1)
                fail("the getEdge method not work properly(or hasEdge)");
            g1.removeEdge(n1.getKey(), n2.getKey());
            temp=g1.hasEdge(n1.getKey(), n2.getKey());
            if(temp)
                fail("the remove method not work properly(or hasEdge)");
        }
    /** test the equality of the graph*/
    @Test
    public void equals()
    {
        WGraph_DS g1=new WGraph_DS();

        WGraph_DS g2=new WGraph_DS();
        assertEquals(g1,g1);
        assertEquals(g2,g2);
        g1.addNode(7);
        g2=g1.copy();
        assertEquals(g1,g2);
        System.out.println(g2);
    }
    @Test
    public void test1000000()
    {
        WGraph_DS g1=new WGraph_DS();
        for(int i=1;i<=1000000;i++)
            g1.addNode(i);
        for(int i=1;i<=999990;i++)
        {   g1.connect(i,i+1,7);
            g1.connect(i,i+2,7);
            g1.connect(i,i+3,7);
            g1.connect(i,i+4,7);
            g1.connect(i,i+5,7);
            g1.connect(i,i+6,7);
            g1.connect(i,i+7,7);
            g1.connect(i,i+8,7);
            g1.connect(i,i+9,7);
            g1.connect(i,i+10,7);
        }
    }
}

