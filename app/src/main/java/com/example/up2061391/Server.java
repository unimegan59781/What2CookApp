package com.example.up2061391;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    private final Scanner fileLink; // declares scanner file link

    private ArrayList<Node> nodeList; // declares empty array of type Node

    public Server(InputStream file) throws FileNotFoundException { // gets file from MainActivity

        fileLink = new Scanner(file); // gets input file

        nodeList = new ArrayList<Node>(); // sets list to new one

        while (hasAnotherLine()) { // loops round csv file and gets each line then separates it by comma and adds it as a new Node
            String line = getLine();
            String[] stringArray = split(line);
            Node newNode = new Node(intConvert(stringArray[0]), intConvert(stringArray[1]), intConvert(stringArray[2]), stringArray[3], stringArray[4]);
            nodeList.add(newNode);
        }

        Close(); // close file to avoid corruption
    }

    public String getLine() { // grab line method
        return fileLink.nextLine();
    }

    public void Close() { // closes file
        fileLink.close();
    }

    public boolean hasAnotherLine() { // check more lines method in fileLink
        return fileLink.hasNext();
    }

    public String[] split(String line) { // line split method
        return line.split(",");
    }

    private int intConvert(String item) { // converts string to int
        return Integer.parseInt(item);
    }

    public Node searchNode(int searchID) { // searches through nodeList sequentially and returns node location when it equals the searchID (desired node)
        for (Node node : nodeList) {
            if (node.getID() == searchID) {
                return node;
            }
        }
        return null; // returns null if not in nodeList
    }

    public Node searchPrevNode(int yOnClick, int currentNode) { // searches through nodeList and then if its 1 (yes) looks to see if yesID of the Node is the same as search node to find the previous path of node, if not searches for noID
        for (Node node : nodeList) {
            if(yOnClick == 1){
                if (node.getYesID() == currentNode) {
                    return node;
                }
            } else {
                if (node.getNoID() == currentNode) {
                    return node;
                }
            }
        }
        return null; // returns null if not in nodeList
    }
}