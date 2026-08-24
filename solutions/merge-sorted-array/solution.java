class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1 , p2 = n-1 ,i = m+n-1;
        while(p2 >= 0 ){
            System.out.println("p2 = "+p2);
            if(p1 >= 0 && nums1[p1] > nums2[p2]){
                System.out.println("-------if--------");
                System.out.println("intial i = "+i);
                System.out.println("initial p1 = "+p1);
                System.out.println("nums1[i] = "+nums1[i]);
                System.out.pri