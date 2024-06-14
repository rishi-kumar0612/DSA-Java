/*
author : rishi kumar
cwid : 20015656

citation 1 :https://www.geeksforgeeks.org/insertion-in-red-black-tree/

 */

import java.io.*;
public class RBTree
{
    private Node root;
    private static final String FILENAME = "rbtree_visualization.svg";


    private static class Node
    {
        int value;
        Node left_node;
        Node right_node;
        Node parent_node;
        boolean node_isRed;


        Node(int v)
        {
            this.value = v;
            left_node = null;
            right_node = null;

            parent_node = null;
            node_isRed = true;
        }
    }


    public RBTree() {
        root = null;
    }


    public void insert(int v)
    {
        Node node = new Node(v);
        insert(node);
    }


    private void insert(Node node)
    {
        Node parent_node = null;
        Node temp_node = root;



        while (temp_node != null)
        {
            parent_node = temp_node;
            if (node.value < temp_node.value)
            {
                temp_node = temp_node.left_node;
            } else
            {
                temp_node = temp_node.right_node;
            }
        }


        node.parent_node = parent_node;


        if (parent_node == null) {
            root = node;
            node.node_isRed = false;
            return;
        }


        if (node.value < parent_node.value) {
            parent_node.left_node = node;
        } else {

            parent_node.right_node = node;
        }


        fixInsertion(node);
    }


    private void fixInsertion(Node node)
    {
        while (node != root && node.parent_node.node_isRed)
        {
            if (node.parent_node == node.parent_node.parent_node.left_node)
            {
                Node other_parent_node = node.parent_node.parent_node.right_node;
                if (other_parent_node != null && other_parent_node.node_isRed)
                {
                    // Case 1: Uncle is red
                    node.parent_node.node_isRed = false;
                    other_parent_node.node_isRed = false;
                    node.parent_node.parent_node.node_isRed = true;
                    node = node.parent_node.parent_node;
                } else
                {
                    if (node == node.parent_node.right_node)
                    {

                        node = node.parent_node;
                        rotateLeft(node);
                    }

                    node.parent_node.node_isRed = false;
                    node.parent_node.parent_node.node_isRed = true;
                    rotateRight(node.parent_node.parent_node);
                }
            } else
            {
                Node uncle = node.parent_node.parent_node.left_node;
                if (uncle != null && uncle.node_isRed)
                {

                    node.parent_node.node_isRed = false;
                    uncle.node_isRed = false;
                    node.parent_node.parent_node.node_isRed = true;
                    node = node.parent_node.parent_node;
                } else
                {
                    if (node == node.parent_node.left_node)
                    {

                        node = node.parent_node;
                        rotateRight(node);
                    }

                    node.parent_node.node_isRed = false;
                    node.parent_node.parent_node.node_isRed = true;
                    rotateLeft(node.parent_node.parent_node);
                }

            }

        }

        root.node_isRed = false;
    }


    private void rotateLeft(Node node)
    {
        Node right_child_node = node.right_node;
        node.right_node = right_child_node.left_node;
        if (right_child_node.left_node != null)
        {
            right_child_node.left_node.parent_node = node;
        }
        right_child_node.parent_node = node.parent_node;
        if (node.parent_node == null)
        {
            root = right_child_node;
        } else if (node == node.parent_node.left_node)
        {
            node.parent_node.left_node = right_child_node;
        } else
        {
            node.parent_node.right_node = right_child_node;
        }
        right_child_node.left_node = node;
        node.parent_node = right_child_node;
    }


    private void rotateRight(Node node)
    {
        Node left_child_node = node.left_node;
        node.left_node = left_child_node.right_node;
        if (left_child_node.right_node != null)
        {
            left_child_node.right_node.parent_node = node;
        }
        left_child_node.parent_node = node.parent_node;
        if (node.parent_node == null)
        {
            root = left_child_node;
        } else if (node == node.parent_node.right_node)
        {
            node.parent_node.right_node = left_child_node;
        } else
        {
            node.parent_node.left_node = left_child_node;
        }
        left_child_node.right_node = node;
        node.parent_node = left_child_node;
    }


    public void printTree()
    {
        try {
            FileWriter fw = new FileWriter(FILENAME);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\">");
            printNode(root, pw, 400, 50, 200);
            pw.println("</svg>");

            pw.close();
            bw.close();
            fw.close();

            System.out.println("RBTree has been printed to " + FILENAME);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    private void printNode(Node node, PrintWriter pw, int x, int y, int spacing)
    {
        if (node != null)
        {
            String color = node.node_isRed ? "red" : "black";
            String style = "fill:" + color + ";stroke:black;stroke-width:2px;";
            String text = Integer.toString(node.value);

            pw.println("<circle cx=\"" + x + "\" cy=\"" + y + "\" r=\"20\" style=\"" + style + "\"/>");
            pw.println("<text x=\"" + x + "\" y=\"" + (y + 7) + "\" text-anchor=\"middle\" style=\"fill:white;\">" + text + "</text>"); // Set text color to white

            if (node.left_node != null)
            {
                pw.println("<line x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" + (x - spacing) + "\" y2=\"" + (y + 70) + "\" stroke=\"black\" stroke-width=\"2px\"/>");
                printNode(node.left_node, pw, x - spacing, y + 70, spacing / 2);
            }

            if (node.right_node != null)
            {
                pw.println("<line x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" + (x + spacing) + "\" y2=\"" + (y + 70) + "\" stroke=\"black\" stroke-width=\"2px\"/>");
                printNode(node.right_node, pw, x + spacing, y + 70, spacing / 2);
            }
        }
    }



    public static void main(String[] args)
    {
        RBTree rbTree = new RBTree();

        rbTree.insert(10);
        rbTree.insert(20);
        rbTree.insert(30);
        rbTree.insert(40);
        rbTree.insert(50);
        rbTree.insert(25);


        rbTree.printTree();
    }
}




