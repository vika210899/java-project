package etu.swing;

import etu.controller.Controller;
import etu.model.BuildGraph;
import etu.model.FileReadArray;
<<<<<<< HEAD
import etu.model.heapSort.HeapSort;
=======
import etu.model.heapsort.BinTree.BinTree;
>>>>>>> main/master
import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import javax.swing.event.AncestorListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI extends JFrame {

    private final JPanel rightPanel;
    private final JPanel leftPanel;
    private final ButtonsPanel buttonsPanel = new ButtonsPanel();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new UI().setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private UI() throws HeadlessException, IOException {
        setPreferredSize(new Dimension(1000, 700));
        setResizable(false);

        ButtonsPanel buttons = new ButtonsPanel();
        rightPanel = new JPanel();
        leftPanel = new JPanel();

        rightPanel.setPreferredSize(new Dimension(200, 700));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        buttons.setPreferredSize(new Dimension(190, 100));
        buttons.setBounds(400, 0, 200, 120);
        rightPanel.add(buttons);
        rightPanel.add(Box.createRigidArea(new Dimension(110, 20)));

        Scanner in = new Scanner(new File("resource/input.dat"));
        FileReadArray fileArr = FileReadArray.init(in);

        BinTree binaryTree = new BinTree(fileArr.getArray());
        binaryTree.createBinTree();
        binaryTree.printTree(binaryTree.getRoot());

//        HeapSort heapSort = new HeapSort(fileArr.getArray());
//        heapSort.getSortedArray();

        BuildGraph graphB = new BuildGraph(binaryTree.getRoot());
        Graph graph = graphB.build();
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.disableAutoLayout(); //graph will tend to make nodes tied with each other close
        View view = viewer.addDefaultView(false);
        view.getCamera().resetView();
<<<<<<< HEAD
        view.getCamera().setViewPercent(4); //This will zoom of 200% on the view center.
=======
        view.getCamera().setViewPercent(2.5); //This will zoom of 200% on the view center.
>>>>>>> main/master
        ((ViewPanel) view).setPreferredSize( new Dimension(750, 630));

        JPanel graphPanel = new JPanel();
        graphPanel.add((JPanel)view);

        graphPanel.setPreferredSize(new Dimension(800, 700));
        graphPanel.setBorder(new TitledBorder("Graph"));

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(Box.createRigidArea(new Dimension(1000, 10)));
        leftPanel.add(graphPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(1000, 10)));

        JPanel rootPanel = new JPanel();
        rootPanel.setPreferredSize(new Dimension(1000, 700)); // size of MainWindow
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.X_AXIS)); //WARNINNNGGGGGGG!
        rootPanel.add(Box.createRigidArea(new Dimension(10, 700)));

        rootPanel.add(graphPanel);
        rootPanel.add(Box.createRigidArea(new Dimension(10, 700)));
        rootPanel.add(rightPanel);
        rootPanel.add(Box.createRigidArea(new Dimension(10, 700)));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //close app when close main window
        setContentPane(rootPanel);
        pack();
        setLocationRelativeTo(null); // position window in center
        rootPanel.setVisible(true);
    }

}
