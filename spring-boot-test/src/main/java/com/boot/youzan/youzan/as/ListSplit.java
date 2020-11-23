package com.boot.youzan.youzan.as;

import java.util.ArrayList;
import java.util.List;

/**
*@Description TODO
*@Author 王泽辉
*@Date 2020/11/18 18:07
*/
public class ListSplit {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < 520; i++) {
            list.add(i);
        }
        ListSplit.test1(list);
    }

    public static void test1(List<Object> dataList){
        int pointsDataLimit = 50;
        List<Object> newList = new ArrayList<Object>();
        for(int i=0;i<dataList.size();i++){//分批次处理
            newList.add(dataList.get(i));
            if(pointsDataLimit == newList.size()||i == dataList.size()-1){

                printList(newList);
                newList.clear();
            }
        }
    }


    public static void printList(List<Object> dataList){
        for(Object str : dataList){
            System.out.print(str + " -- ");
        }
    }
}
