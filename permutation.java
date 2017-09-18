
//No duplicates
//Given a collection of distinct numbers, return all possible permutations.

class Solution {
    //backtracking
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        helper(output,list,nums);
        return output;
        
    }
    
    public void helper(List<List<Integer>> output, List<Integer> current, int[] nums ){
        
        if(current.size()==nums.length){
            List<Integer> list = new ArrayList<Integer>(current);
            output.add(list);
            return;
        }
        
        for(int i=0;i<nums.length;i++){
            if(current.contains(nums[i])) continue;
            
            //System.out.println("Adding="+nums[i]+ " before="+current);
            
            current.add(nums[i]);
            helper(output,current,nums);
            current.remove(current.size()-1);
        }
        
    }
    
}
