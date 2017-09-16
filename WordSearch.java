/*
Word Search II :
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].


*/

public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> output = new ArrayList<String>();
        
        if(board==null || board.length==0 || words==null || words.length==0){
            return output;
        }
        
        TrieNode root = new TrieNode();
        populateTrie(words,root);
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                search(board,i,j,root,output);
            }
        }
        
        
        return output;
    }
    
    public void search(char[][] board, int x, int y, TrieNode p, List<String> output ){
        
        if(x<0 || x == board.length || y<0 || y==board[0].length || board[x][y]=='#' ){
            return;
        }
        char save = board[x][y];
        
        
        p = p.children[save-'a'];
        
        if(p==null){
            return; 
        }
        else if(p.word!=null){
                output.add(p.word);
                p.word = null;  //for deduplicates
        }
        
        board[x][y]='#';
        
        search(board,x+1,y,p,output);
        search(board,x-1,y,p,output);
        search(board,x,y+1,p,output);
        search(board,x,y-1,p,output);

        board[x][y] = save;
    }
    
    public void populateTrie(String[] words, TrieNode root){
        
        for(String current : words){
            TrieNode traverse = root;
            for(int i=0;i<current.length();i++){
                char c = current.charAt(i);
                if(traverse.children[c-'a']==null){
                    traverse.children[c-'a'] = new TrieNode();
                }
                traverse = traverse.children[c-'a'];
            }
            traverse.word = current;
        }
        
    }
    
}


class TrieNode{
    TrieNode[] children;
    String word;
    public TrieNode(){
        children = new TrieNode[26];
        word = null;
    }
    
}