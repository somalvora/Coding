class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s==null || s.length()==0){
            return true;
        }
        if(wordDict==null || wordDict.size()==0){
            return false;
        }
        
        HashMap<Integer,Boolean> map = new HashMap<Integer,Boolean>();
        HashSet<String> dictionary = new HashSet<String>();
        
        int max = 0;
        
        for(String current : wordDict){
            max = Math.max(max,current.length());
            dictionary.add(current);
            if(dictionary.contains(s)){
                return true;
            }
        }
        
        return search(s,dictionary, 0, max, map);
    }
    
    
    public boolean search(String s, HashSet<String> dictionary, int start, int max, HashMap<Integer,Boolean> map){
        
        if(start==s.length()){
            return true;
        }
        
        if(map.containsKey(start)){
            return map.get(start);
        }
        
        
        for(int i=start;i < (start + max) && i<s.length()  ;i++){
            
            String check = s.substring(start,i+1);
            if(!dictionary.contains(check)){
                continue;
            }
            
            if(search(s,dictionary,i+1, max, map)){
                map.put(start,true);
                return true;
            }
        }
        
        map.put(start,false);
        return false;
        
    }
    

    
}