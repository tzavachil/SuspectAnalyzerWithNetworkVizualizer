import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;


public class SuspectsNetwork extends JFrame {
	private JPanel mainPanel;
	private JPanel graphPanel;
	private JPanel diameterPanel;
	private JTextField diameterField;
	
	private JFrame frame;
	private Registry registry;
	
	public SuspectsNetwork(Registry aRegistry, JFrame frame) {
		this.registry = aRegistry;
		this.frame = frame;
		
		//setting graph's panel
		graphPanel = new JPanel();
		Graph<Suspect,Integer> graph = new UndirectedSparseGraph<>();	//creating a new undirected graph
		construct_graph(aRegistry,graph);	//constructing graph
		
		//graph's visualization
		FRLayout<Suspect,Integer> fr = new FRLayout<>(graph);
		Dimension dimension = new Dimension(300,300);
		fr.setSize(dimension);
		Layout<Suspect,Integer> layout = fr;
		VisualizationViewer<Suspect,Integer> vv = new VisualizationViewer<>(layout);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>() {
			@Override	//label with suspect's code name for each vertex
			public String transform(Suspect s) {
				return s.getCodeName();
			}
		});
		
		graphPanel.add(vv);
		
		//setting diameter's panel
		diameterPanel = new JPanel();
		diameterField = new JTextField("Diameter = " + DistanceStatistics.diameter(graph),37);
		diameterField.setEditable(false);
		diameterPanel.add(diameterField);
		
		mainPanel = new JPanel(); //main panel in the window
		mainPanel.setLayout(new BorderLayout());
		
		mainPanel.add(graphPanel, BorderLayout.WEST); //adding graph's panel main frame's north side
		mainPanel.add(diameterPanel, BorderLayout.SOUTH); //adding diameter's panel main frame's south side
		
		this.setContentPane(mainPanel);
		this.setTitle("Suspects Network");
		this.setSize(400, 400);
		this.setResizable(false);
		this.setVisible(true);
		frame.setVisible(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void construct_graph(Registry aRegistry, Graph<Suspect,Integer> graph) {
		ArrayList<Suspect> suspects = aRegistry.getSuspects();
		
		//adding Vertex for each Suspects
		for(Suspect aSuspect : suspects) {
			graph.addVertex(aSuspect);
		}
		
		//Adding edges while looping suspec's vertices
		int i=0; //counting vertices
		for(Suspect aSuspect : suspects) {
			ArrayList<Suspect> common = aSuspect.getCommonPartners(aSuspect);
			for(Suspect s : common) {
				if(aSuspect.isConnectedTo(s)) {
					graph.addEdge(i,aSuspect, s);
					i++;
				}
			}
		}
	}
}
