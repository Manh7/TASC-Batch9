package array;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayDemo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // khai báo
        String[] arrString = {"Tú", "Tí", "Tèo"};

        for(int i=0; i<arrString.length; i++){
            System.out.println(arrString[i]);
        }


        int[] intArr = new int[4];
        intArr[0]= 5;
        intArr[1]= 10;
        intArr[2]= 43;
        intArr[3]= 30;


        Arrays.sort(intArr);
        for(int i : intArr){
            System.out.println(i);
        }

        System.out.println(intArr[2]); // truy xuất O(1)


        // mảng 2 chiều
        int [][] intMatrix = new int[2][3];
        for(int i=0; i<2; i++){
            for(int j=0; j<3; j++){
                intMatrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(Arrays.deepToString(intMatrix));
    }
}
