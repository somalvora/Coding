
        
//LeetCode Problem : 336. Palindrome Pairs
/*
There are several cases to be considered that isPalindrome(s1 + s2):

Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.

Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.

Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.

Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.

To make the search faster, build a HashMap to store the String-idx pairs.
*/        
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        
        if(words==null || words.length<=1){
            return output;
        }
        
        //indices
        HashMap<String,Integer> map = new HashMap<>();
        
        //populate map
        for(int i=0;i<words.length;i++){
            map.put(words[i],i);
        }
        
        //if "" present
        if(map.containsKey("")){
            int index1 = map.get("");
            for(String current: words){
                if(isPalindrome(current)){
                    int index2 = map.get(current);
                    if(index1==index2) continue;
                    output.add(Arrays.asList(index1,index2));
                    output.add(Arrays.asList(index2,index1));
                }
            }
        }
        
        //why only one?
        //if reverse is palindrome 
        for(String current : words){
            String reverse = reverse(current);
            if(map.containsKey(reverse)){
                int index1 = map.get(reverse);
                int index2 = map.get(current);
                if(index1==index2) continue;
                output.add(Arrays.asList(index1,index2));
                //output.add(Arrays.asList(index2,index1));
            }
        }
        
        for(int i=0;i<words.length;i++){
            String current = words[i];
            
            for(int cut=1;cut<current.length();cut++){    
                //System.out.println("cut="+current.substring(0,cut));
                if(isPalindrome(current.substring(0,cut))){
                    String reverse = reverse(current.substring(cut));
                    if(map.containsKey(reverse)){
                        int index2 = map.get(reverse);
                        if(i==index2) continue;
                        output.add(Arrays.asList(index2,i));
                    }
                }
                
                if(isPalindrome(current.substring(cut))){
                    String reverse = reverse(current.substring(0,cut));
                    if(map.containsKey(reverse)){
                        int index2 = map.get(reverse);
                        if(i==index2) continue;
                        output.add(Arrays.asList(i,index2));
                    }
                }

            
            }
        }
        
        
        
        return output;
    }
    
    
    public String reverse(String s){
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
    
    public boolean isPalindrome(String s){
        
        int start = 0;
        int end = s.length()-1;
        
        while(start<=end){
            if(s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        
        return true;        
    }
    
}