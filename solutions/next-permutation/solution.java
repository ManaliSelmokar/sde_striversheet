class Solution {
    void reverse(int i,int j,int[] arr){
        while(i<j){
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;
            j--;
        }
    }
    public void nextPermutation(int[] nums) {
        int n =nums.length;
        int k=-1;
        for(int i=n-1;i>0;i--){
            if(nums[i-1]<nums[i]){
                k=i-1;
                break;
            }
        }

        if(k==-1){
            reverse(0,n-1,nums);