package etu.model;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class BuildGraph {

	private final int[] metaData;

	public BuildGraph(int[]            metaData) {
		this.metaData = metaData;
	}

//	public createNode(){
//		Bin
//	}

	public Graph build(){
		Graph graph = new SingleGraph("G", false, true);

		for (Node node : graph){
			node.addAttribute("ui.label", node.getAttributeCount());
		}

		graph.addAttribute("ui.stylesheet",
				"graph{fill-color: gray;} " +
						"node{ " +
						"shape: circle;  " +
						"size: 30px, 30px; " +
						"fill-color: white;" +
						"stroke-mode: plain;" +
						"text-mode: normal;" +
						"text-mode: normal; " +
						"text-style: bold; " +
						"text-size: 20; " +
						"text-alignment: center;" +
						"size-mode: dyn-size;}");

//		int d = 4;
		int offset = 4;
		int axis_x = 0;
		int level = 0, count_el_on_vlv = 0;
		for (int i = 1; i < metaData.length-1; i++) {
			count_el_on_vlv = (int)Math.pow(2, i);

			if( i == count_el_on_vlv && i!=0 ) {
				offset = (int)Math.pow(-1,i)*offset;
				axis_x += offset;
				offset /= 2;
				level++;
				System.out.println("level++");
			}
			Node w = graph.addNode(String.valueOf(i));
			w.setAttribute("xy", axis_x, -level);

//			if(i == 0) {
//				w.setAttribute("xy", 0, 0);
//				level++;
//			}else if( i == 1 || i == 2){
//				w.setAttribute("xy", Math.pow(-1, i) * d, -level);
//				offset /=2;
//				level++;
//			}else {
//				w.setAttribute("xy", level * d + Math.pow(-1, i+1) * offset, -level);
//				offset /= 2;
//			}
			w.setAttribute("label", metaData[i]);
		}

		for (int i = 1, k = 0; i < metaData.length; i++) {
			graph.addEdge(String.valueOf(i), String.valueOf(k), String.valueOf(i), true);
			if(i % 2 == 0) {
				k++;
			}
		}


		return graph;
	}
}