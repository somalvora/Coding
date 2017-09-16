// This is the text editor interface. 
// Anything you type or change here will be seen by the other person in real time.



/*

here is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
For example, Given the following words in dictionary,
[
  "wrt","wrf",  "er",  "ett",  "rftt"
]
The correct order is: "wertf".

*/

import java.io.*;
import java.util.*;


class Solution {
    
    public static void main(String[] args){
        String[] input = {"wrt","wrf",  "er",  "ett",  "rftt"};
        System.out.println(alienOrder(input));
    }
    
    
    public static String alienOrder(String[] words) {
        
        if(words==null || words.length==0){
            return "";
        }   
        
        HashMap<Character,Integer> degree = new HashMap<Character,Integer>();
        StringBuilder sb = new StringBuilder();
        HashMap<Character,HashSet<Character>> dependency = new HashMap<Character,HashSet<Character>>();
        
        for(String current:words){
            for(char c:current.toCharArray()){
                degree.put(c,0);
            }
        }
        
        for(int i=1;i<words.length;i++){
            
            String first = words[i-1];
            String second = words[i];
            
            int min = Math.min(first.length(),second.length());
            
            for(int j=0;j<min;j++){
                
                char c1 = first.charAt(j);
                char c2 = second.charAt(j);
                
                // "wrt",  "wrf","er",
                
                if(c1!=c2){
                    
                    HashSet<Character> set = new HashSet<Character>();
                    
                    if(dependency.containsKey(c1)){
                        set = dependency.get(c1);
                    }

                    if(!set.contains(c2)){
                        set.add(c2);
                        dependency.put(c1,set);
                        degree.put(c2,degree.get(c2)+1);
                    }
                    
                    
                    break;
                }

            }
        }
            
            

            //BFS
            Queue<Character> queue = new LinkedList<Character>();
            
            //adding not dependent one first
            for(char c : degree.keySet()){
                if(degree.get(c)==0){
                    queue.add(c);
                }
            }
            
            while(!queue.isEmpty()){
                
                Character c = queue.remove();
                sb.append(c);
                
                if(dependency.containsKey(c)){
                    for(char entry : dependency.get(c)){
                        degree.put(entry,degree.get(entry)-1);
                        
                        
                        if(degree.get(entry)==0){
                            queue.add(entry);
                        }
                    }
                }
            }
            
            

            if(sb.length()!=degree.size()){
                return "";
            }
            
            return sb.toString();
            
        }

    
}