package fr.sae_graphes.main;

import static org.junit.Assert.assertEquals;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void rigorousTest()
    {
        Graph<String, DefaultEdge> graph = GraphTypeBuilder
				.directed()
				.allowingMultipleEdges(true)
				.allowingSelfLoops(true)
				.vertexSupplier(SupplierUtil.createStringSupplier())
				.edgeSupplier(SupplierUtil.createDefaultEdgeSupplier())
				.buildGraph();
        
        String v0 = graph.addVertex();
        String v1 = graph.addVertex();
        String v2 = graph.addVertex();
        
        graph.addEdge(v0, v1);
        graph.addEdge(v0, v2);
        graph.addEdge(v1, v2);
        
        assertEquals(3, graph.vertexSet().size());
        assertEquals(3, graph.edgeSet().size());
        
        assertEquals(2, graph.outDegreeOf(v0));
        assertEquals(1, graph.outDegreeOf(v1));
        assertEquals(0, graph.outDegreeOf(v2));
    }

    public static Graph<String, DefaultEdge> generateGraph() {
		Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
		graph.addVertex("Acteur 1");
		graph.addVertex("Acteur 2");
		graph.addVertex("Acteur 3");
		graph.addVertex("Acteur 4");

		graph.addEdge("Acteur 1", "Acteur 2");
		graph.addEdge("chou", "alacreme");
		graph.addEdge("chocolat", "noir");
		graph.addEdge("pamplemousse", "a");

		return graph;
	
	}
    @Test
    public void TestCollabCommun(){
      Graph<String, DefaultEdge> graph = GraphTypeBuilder
    }
}
