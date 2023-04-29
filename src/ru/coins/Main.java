package ru.coins;

import static java.util.Arrays.copyOfRange;

public class Main {
    public static int variantNumber = 0; // used only for a prettier output, main algorithm does not use global variables at all
    public static void main(String[] args) {
        int N = 20;
        int[] coins = {2, 5, 8}; // should be in ascending order, otherwise sort should be added
        int variants = 0;
        int remainder;
        System.out.println("All possible variants:");
        for (int k = 1; k <= coins.length; k++) {
            remainder = N;
            variants += countVariants(copyOfRange(coins, 0, k), remainder, ". {");
        }
        System.out.println("Number of variants: " + variants);
    }

    static int countVariants(int[] coins, int remainder, String variant) {
        variant += coins[coins.length-1];
        int variants = 0;
        remainder -= coins[coins.length-1];
        if (remainder == 0) {
            variant += "}";
            variantNumber += 1; // a global variable, which is used only for a prettier output.
            variant = variantNumber + variant;
            System.out.println(variant);
            return 1;
        }
        else if (remainder < 0) {return 0;}
        else {
            variant += ", ";
            for (int k = 1; k <= coins.length; k++) {
                variants += countVariants(copyOfRange(coins, 0, k), remainder, variant);
            }
        }
        return variants;
    }
}
