import java.util.Arrays;

public class CountSort {
    public static int[] countSort(int[] array){
        //1.得到数列的最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }
        }

        //2.根据数列最大值确定统计数组的长度
        int[] countArray = new int[max+1];

        //3.遍历数列，填充统计数组
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]]++;
        }

        //4.遍历统计数组，输出结果
        int index = 0;
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortedArray[index++] = i;
            }
        }
        return sortedArray;
    }

    //改进版的计数排序
    public static int[] countSort2(int[] array){
        //1.得到数列的最大值和最小值，并算出差值d
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }
            if (array[i] < max){
                min = array[i];
            }
        }
        int d = max -min;

        //2.创建统计数组并统计对应元素个数
        int[] countArray = new int[d+1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }

        //3.统计数组做变形，后面的元素等于前面的元素之和
        int sum = 0;
        for (int i = 0; i < countArray.length; i++) {
            sum += countArray[i];
            countArray[i] = sum;
        }

        //4.倒序遍历原始数组，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for (int i = array.length-1; i >= 0; i--) {
            sortedArray[countArray[array[i]-min-1]] = array[i];
            countArray[array[i]-min]--;
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] array = {4,4,6,5,3,2,8,1,7,5,6,0,10};
        int[] array2 = {95,94,91,98,90,99,89,11,7,5,6,0,10};
        int[] sortedArray = countSort(array);
        int[] sortedArray2 = countSort(array2);
        System.out.println(Arrays.toString(sortedArray));
        System.out.println(Arrays.toString(sortedArray2));
    }
}

/*
 原始数列的规模是N，最大最小整数的差值是M，时间复杂度和空间复杂度是多少？

 时间复杂度：代码第1，2，4步都涉及遍历原始数组，运算量都是N，第三步遍历统计数列，运算量都是M，所以总体运算量是3N+M，
 去掉系数，时间复杂度是o(N+M)

 空间复杂度：如果不考虑结果数组，只考虑统计数组大小的话，空间复杂度是o(M)
 */