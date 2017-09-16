/*

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

*/

class Solution {
    List<String> output = new ArrayList<String>();
    
    //https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/BreakMultipleWordsWithNoSpaceIntoSpace.java
    public List<String> wordBreak(String s, List<String> wordDict) {
        
        HashSet<String> dictionary = new HashSet<String>();
        HashMap<Integer,List<String>> dp = new HashMap<Integer,List<String>>();
        int max = 0;
        
        for(String s1:wordDict){
            max = Math.max(max,s1.length());
            dictionary.add(s1);
        }
        
        return search(s,0,max,dictionary,dp);
    }
    
    public List<String> search(String s, int start, int max, HashSet<String> dictionary,HashMap<Integer,List<String>> dp){
        if(start==s.length()){
            return Collections.singletonList("");
        }
        
        if(dp.containsKey(start)){
            return dp.get(start);
        }
        
        List<String> list = new ArrayList<String>();
        for(int i=start;i< (start+max) && i<s.length();i++){
            
            String check = s.substring(start,i+1);
            
            if(!dictionary.contains(check)){
                continue;
            }
            List<String> result = search(s,i+1,max,dictionary,dp);
            
            for(String current : result){
                String extraSpace = current.length()==0 ? "" : " ";
                list.add(check+extraSpace+current);   
            }
            
        }
        
        dp.put(start,list);
        return list;
        
    }
    
}
