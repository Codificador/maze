package Classes;

import Search.Bfs;
import Search.Search;
import java.util.ArrayList;

/**
 *
 * @author EngComp
 */
public class Node {

    public static final char EMPTY      = '*';
    public static final char BLOCKED    = '#';
    public static final char PATH       = 'x';
    public static final char BEGIN      = 'B';
    public static final char END        = 'E';
    
    private char    _type = EMPTY;
    private int     _row, _col;
    private Board   _board;
    private Node    _parent;
    private boolean _visited;

    public Node(int row, int col, Board b) {
        this._row = row;
        this._col = col;
        this._board = b;
        this._visited = false;
        this._parent = null;
    }

    public boolean isVisited() {
        return _visited;
    }

    public void markAsVisited() {
        this._visited = true;
    }

    public Node getDown() {
        if (_row + 1 >= _board.getSize())
            return null;
        return _board.get(_row + 1, _col);
    }

    public Node getUp() {
        if (_row - 1 < 0)
            return null;
        return _board.get(_row - 1, _col);
    }

    public Node getRight() {
        if (_col + 1 >= _board.getSize())
            return null;
        return _board.get(_row, _col + 1);
    }

    public Node getLeft() {
        if (_col - 1 < 0)
            return null; 
        return _board.get(_row, _col - 1);
    }

    public Node getParent() {
        return _parent;
    }

    public void setParent(Node parent) {
        this._parent = parent;
    }

    public void markAsBloked() {
        this._type = Node.BLOCKED;
    }
    
    public void markAsPath() {
        this._type = Node.PATH;
    }
    
    public void markAsEmpty() {
        this._type = Node.EMPTY;
    }
    
    public boolean isEmpty() {
        return this._type == Node.EMPTY || this._type == Node.BEGIN;
    }

    public char getType() {
        return _type;
    }
    
    public ArrayList<Node> getAdjList() {
        Node u, d, r, l;
        ArrayList<Node> ls = new ArrayList<>();
        
        u = getUp();
        d = getDown();
        r = getRight();
        l = getLeft();
        
        if(u != null) ls.add(u);
        if(d != null) ls.add(d);
        if(r != null) ls.add(r);
        if(l != null) ls.add(l);
        
        return ls;   
    }

    public void setType(char type) {
        this._type = type;
    }

    public int getRow() {
        return _row;
    }

    public int getCol() {
        return _col;
    }
 
    @Override
    public String toString() {
        return "" + this._type;// + " " + row + " " + col;
    }

    @Override
    public boolean equals(Object obj) {
        Node other = (Node) obj;
        return other._row == this._row && other._col == this._col;
    }

    public static void main(String[] args) {
        Board b = new Board(100, 8);
        
        /*
        Node begin = b.getBegin();
        Node target = b.getEnd();

        Queue<Node> fila = new LinkedList<>();
        fila.add(begin);
        begin.markAsVisited();

        Node curr = null;
        while (!fila.isEmpty()) {
            curr = fila.remove();
            
            if (curr.equals(target)) {
                break;
            }
            
            ArrayList<Node> adj = curr.getAdjList();
            for(Node node : adj) {
                if(!node.isVisited() && node.isEmpty()) {
                    fila.add(node);
                    node.markAsVisited();
                    node.setParent(curr);
                }
            }        
        }
        
        b.setPath(curr);
                */
        Search s = new Bfs(b);
        Node path = s.run();
        b.set(path);
        /*
        try{    
            Node n = b.next();
            do{
                System.out.println("" + n.getCol() + " " + n.getRow());
            }while((n = b.next()) != null);
        }catch(EmptyStackException e){}   
         */
        System.out.println("" + b);
    }
    
}