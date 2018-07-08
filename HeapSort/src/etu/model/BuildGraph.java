package etu.model;

import etu.model.heapsort.BinTree.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Stack;

public class BuildGraph {

	private final NodeTree nodeTree;
	Graph graph = new SingleGraph("G", false, true);
	int i = 0;

	public BuildGraph (NodeTree nodeTree) {
		this.nodeTree = nodeTree;
	}

	public void createNodes(NodeTree node, int offset) {
		if (node == null) {
			return;
		}

		int max_depth = BinTree.maxDepth(node);

		if( i == 0 ){
			Node w = graph.addNode(String.valueOf(i++));
			w.setAttribute("xy", 0, -node.getLevel()* 30);
			w.setAttribute("label", node.getValue());
		}

		if (node.getLeft() != null) {
			Node e = graph.addNode(String.valueOf(i++));
			e.setAttribute("xy", offset - 30*Math.pow(2, max_depth - node.getLeft().getLevel()), - node.getLeft().getLevel() * 30);
			e.setAttribute("label", node.getLeft().getValue());
			createNodes(node.getLeft(), offset - 30*(int)Math.pow(2, max_depth - node.getLeft().getLevel()));
		}

		if (node.getRight() != null) {
			Node q = graph.addNode(String.valueOf(i++));
			q.setAttribute("xy", offset + 30*Math.pow(2, max_depth - node.getRight().getLevel()) ,  -node.getRight().getLevel() * 30);
			q.setAttribute("label", node.getRight().getValue());
			createNodes(node.getRight(), offset + 30*(int)Math.pow(2, max_depth - node.getRight().getLevel()));
		}
	}

	public void createEdges(){
		for (int j = 1, k = 0; j < i; j++) {
			graph.addEdge(String.valueOf(j), String.valueOf(k), String.valueOf(j), true);
			if(j % 2 == 0) {
				k++;
			}
		}
	}

	public Graph build(){

		createNodes(nodeTree, 5);
//		createEdges();

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
//		int offset = 4;
//		int axis_x = 0;
//		int level = 0, count_el_on_vlv = 0;
//		for (int i = 1; i < metaData.length-1; i++) {
//			count_el_on_vlv = (int)Math.pow(2, i);
//
//			if( i == count_el_on_vlv && i!=0 ) {
//				offset = (int)Math.pow(-1,i)*offset;
//				axis_x += offset;
//				offset /= 2;
//				level++;
//				System.out.println("level++");
//			}
//			NodeTree w = graph.addNode(String.valueOf(i));
//			w.setAttribute("xy", axis_x, -level);
//
////			if(i == 0) {
////				w.setAttribute("xy", 0, 0);
////				level++;
////			}else if( i == 1 || i == 2){
////				w.setAttribute("xy", Math.pow(-1, i) * d, -level);
////				offset /=2;
////				level++;
////			}else {
////				w.setAttribute("xy", level * d + Math.pow(-1, i+1) * offset, -level);
////				offset /= 2;
////			}
//			w.setAttribute("label", metaData[i]);
//		}
//
//		for (int i = 1, k = 0; i < metaData.length; i++) {
//			graph.addEdge(String.valueOf(i), String.valueOf(k), String.valueOf(i), true);
//			if(i % 2 == 0) {
//				k++;
//			}
//		}


		return graph;
	}
}