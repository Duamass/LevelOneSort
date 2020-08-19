import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {
    public static double[] bucketSort(double[] array){
        //1.得到数列的最大值和最小值，并算出差值d
        double max = array[0];
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }
            if (array[i] < min){
                min = array[i];
            }
        }
        double d = max - min;

        //2.初始化桶
        int bucketNum = array.length;
        ArrayList<LinkedList<Double>>  bucketList = new ArrayList<LinkedList<Double>>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Double>());
        }

        //3.遍历原始数组，将每个元素放入桶中
        for (int i = 0; i < array.length; i++) {
            int num = (int)((array[i] - min) * (bucketNum-1) / d);
            bucketList.get(num).add(array[i]);
        }

        //4.对每个桶内部进行排序
        for (int i = 0; i < bucketList.size(); i++) {
            //JDK底层采用了归并排序或归并的优化版本
            Collections.sort(bucketList.get(i));
        }

        //5.输出全部元素
        double[] sortedArray = new double[array.length];
        int index = 0;
        for (LinkedList<Double> list: bucketList) {
            for (double element :list){
                sortedArray[index] = element;
                index++;
            }
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        double[] array = new double[]{4.12,6,421,0.0023,3.0,2.123,8.122,4.12,10.09};
        double[] sortedArray = bucketSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}
/*
 假设原始数列有n个元素，分成m个桶(我们采用的分桶方式m=n)，平均每个桶里的元素为n/m.
 下面逐步分析算法复杂度：
 第一步求数列最大最小值，运算量为n。
 第二步创建空桶，运算量为m。
 第三步遍历原始数列，运算量为n。
 第四步在每个桶内做排序，由于使用了o(nlogn)的排序算法，所以运算量为n/m*log(n/m)*m。
 第五步输出排序数列，运算量为n。

 加起来，总的运算量为 3n+m+n/m * log(n/m) * m = 3n+m+n(logn-logm)

 去掉系数，时间复杂度为：o(n+m+n(logn-logm))

 至于空间复杂度：
 空桶占用的空间 + 数列在桶中占用的空间 = o(m+n).

*/
