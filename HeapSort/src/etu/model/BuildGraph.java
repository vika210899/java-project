package etu.model;

import etu.model.heapsort.BinTree.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.LinkedList;
import java.util.Queue;

public class BuildGraph {

	private final NodeTree nodeTree;
	Graph graph = new SingleGraph("G", false, true);

	public BuildGraph (NodeTree nodeTree) {
	    this.nodeTree = nodeTree;
	}

	public void createNodes(NodeTree b, int d, int offset, int k) {
		if (b == null) {
            return;
        }
        System.out.print("The value of the root is: "); b.print();
        int i = 0;
		int count = 1;
		Queue<NodeTree> queue = new LinkedList<>();
		queue.add(b);
		do {
			NodeTree bt = queue.remove();
			if( count == 1){
				Node w = graph.addNode(String.valueOf(i++));
				w.setAttribute("xy", d, -bt.getLevel() * 2);
				w.setAttribute("label", bt.getValue());
				System.out.print("root: " + bt.getValue()+" lev: "+bt.getLevel());
				bt.setLevel();
				System.out.println();

			}
			if(1 != count++) offset /= 2;
			if (bt.getLeft() != null) {
				Node e = graph.addNode(String.valueOf(i++));
				e.setAttribute("xy", d - offset, -bt.getLeft().getLevel() * 2);

				e.setAttribute("label", bt.getLeft().getValue());
				System.out.print("left: "+bt.getLeft().getValue()+" lev: "+bt.getLeft().getLevel());
				bt.getLeft().setLevel();
				System.out.println();

				queue.add(bt.getLeft());

			}
			if (bt.getRight() != null) {
				Node q = graph.addNode(String.valueOf(i++));
				q.setAttribute("xy", d + offset ,  -bt.getRight().getLevel() * 2);
				q.setAttribute("label", bt.getRight().getValue());
				System.out.print( "right: "+bt.getRight().getValue()+" lev: "+bt.getRight().getLevel());
				bt.getRight().setLevel();
				System.out.println();

				queue.add(bt.getRight());
			}
		} while (!queue.isEmpty());
	}

	public Graph build(){

		createNodes(nodeTree, 0, 8, 0);

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
