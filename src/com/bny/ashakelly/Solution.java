package com.bny.ashakelly;

//Question: Asta And Kelly Like Prgramming. But Asha Practices More Thari Kelly. Not Wanting To Fall Beind. Kelly Resolves To Practice More.
//3 5 1 input
public class Solution {
    static int minNum(int A, int K, int P) {
        // No catching up because Asha is ahead (positive P) and Kelly cannot catch up.
        if (K <= A) {
            return -1;
        }
        return P / (K - A) + 1;
    }

    public static void main(String[] args) {
        System.out.println(minNum(3, 5, 5));
    }
}
