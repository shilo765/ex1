package ex1.tests;

import ex1.NodeInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeInfoTest{
    /**test if all the values on a new node are at default and the get methods work properly*/
    @Test
    public void defaultNewNode()
    {

        NodeInfo n1=new NodeInfo();
        if(n1.getKey()<0)
            fail("invalid key value");
        if(!n1.getNi().isEmpty())
            fail("neighbors hasMap must be first empty");
        if (!n1.getInfo().equals(""))
            fail("info value must be blank" );
        if(n1.getTag()!=-1)
            fail("tag must be -1");
        if(n1.getLastNei()!=-1)
            fail("lastNei must be -1");
        if(!n1.getWeightMap().isEmpty())
            fail("hi");
    }
    /** test the sets and add/remove of the node*/
    @Test
    public void setsAddRemove()
    {
        NodeInfo n1=new NodeInfo();
        NodeInfo n2=new NodeInfo();
        n1.setInfo("great");
        n1.setLastNei(-3);
        n1.setTag(-3);
        n1.addNi((n2));
        assertEquals(n1.getInfo(),"great");
        assertEquals(n1.getLastNei(),-3);
        assertEquals(n1.getTag(),-3);
        if(!n1.getNi().contains(n2))
            fail("not add properly");
        n1.removeNode(n2);
        if(n1.getNi().contains(n2))
            fail("not remove properly");
        n1.addToWeightMap(n2.getKey(), 3);
        assertEquals(n1.getWeight(n2.getKey()),3);
        n1.removeWeight(n2.getKey());
        if(!n1.getWeightMap().isEmpty())
            fail("remove edge not work properly");
    }
    /** test the equality of the node*/
    @Test
    public void equals()
    {
        NodeInfo n1=new NodeInfo();
        NodeInfo n2=new NodeInfo();
        assertEquals(n1,n1);
        assertEquals(n2,n2);
        assertNotEquals(n1,n2);
        n2=n1.copy();
        assertEquals(n1,n2);

    }


}


