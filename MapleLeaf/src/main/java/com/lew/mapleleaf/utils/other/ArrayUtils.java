/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lew.mapleleaf.utils.other;

import com.lew.mapleleaf.utils.common.StringUtils;

import java.util.Stack;

public class ArrayUtils {

    /**
     * (2.01)、使用选择排序法，对数组intArray进行排序
     *
     * @param intArray  待排序的数组
     * @param ascending 升序
     */
    public static void sortingByChoose(int[] intArray, boolean ascending) {
        for (int cankaozhi = 0; cankaozhi < intArray.length - 1; cankaozhi++) {
            int zhongjian = intArray[cankaozhi];
            int zuixiao = 0;
            for (int zujian = cankaozhi + 1;
                 zujian <= intArray.length - 1;
                 zujian++) {
                boolean type;
                if (ascending) {
                    type = zhongjian > intArray[zujian];
                } else {
                    type = zhongjian < intArray[zujian];
                }
                if (type) {
                    zhongjian = intArray[zujian];
                    zuixiao = zujian;
                }
            }

            if (zuixiao != 0) {
                int f = intArray[zuixiao];
                intArray[zuixiao] = intArray[cankaozhi];
                intArray[cankaozhi] = f;
            }
        }
    }


    /**
     * (2.02)、使用插入排序法，对数组intArray进行排序
     *
     * @param intArray  待排序的数组
     * @param ascending 升序
     */
    public static void sortingByInsert(int[] intArray, boolean ascending) {
        for (int i = 1; i < intArray.length; i++) {
            int t = intArray[i];
            int y = -1;
            for (int j = i - 1; j >= 0; j--) {
                boolean type;
                if (ascending) {
                    type = t < intArray[j];
                } else {
                    type = t > intArray[j];
                }
                if (!type) break;
                intArray[j + 1] = intArray[j];
                y = j;
            }
            if (y > -1) intArray[y] = t;
        }
    }


    /**
     * (2.03)、使用冒泡排序法，对数组intArray进行排序
     *
     * @param intArray  待排序的数组
     * @param ascending 升序
     */
    public static void sortingByBubbling(int[] intArray, boolean ascending) {
        int size = intArray.length - 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean type;
                if (ascending) {
                    type = intArray[j] > intArray[j + 1];
                } else {
                    type = intArray[j] < intArray[j + 1];
                }
                if (type) {
                    int temp = intArray[j];
                    intArray[j] = intArray[j + 1];
                    intArray[j + 1] = temp;
                }
            }
        }
    }


    /**
     * (2.04)、使用递归快排法，对数组intArray进行排序
     *
     * @param intArray  待排序的数组
     * @param ascending 排序的方式，用本类中的静态字段指定
     */
    public static void sortingByFastRecursion(int[] intArray, int start, int end, boolean ascending) {
        int tmp = intArray[start];
        int i = start;

        if (ascending) {
            for (int j = end; i < j; ) {
                while (intArray[j] > tmp && i < j) {
                    j--;
                }
                if (i < j) {
                    intArray[i] = intArray[j];
                    i++;
                }
                if (i < j) {
                    intArray[j] = intArray[i];
                    j--;
                }
            }
        } else {
            for (int j = end; i < j; ) {
                while (intArray[j] < tmp && i < j) {
                    j--;
                }
                if (i < j) {
                    intArray[i] = intArray[j];
                    i++;
                }
                if (i < j) {
                    intArray[j] = intArray[i];
                    j--;
                }
            }
        }

        intArray[i] = tmp;
        if (start < i - 1) {
            sortingByFastRecursion(intArray, start, i - 1, ascending);
        }
        if (end > i + 1) {
            sortingByFastRecursion(intArray, i + 1, end, ascending);
        }
    }


    /**
     * (2.05)、使用栈快排法，对数组intArray进行排序
     *
     * @param intArray  待排序的数组
     * @param ascending 升序
     */
    public static void sortingByFastStack(int[] intArray, boolean ascending) {
        Stack<Integer> sa = new Stack<>();
        sa.push(0);
        sa.push(intArray.length - 1);
        while (!sa.isEmpty()) {
            int end = sa.pop();
            int start = sa.pop();
            int i = start;
            int j = end;
            int tmp = intArray[i];
            if (ascending) {
                while (i < j) {
                    while (intArray[j] > tmp && i < j) {
                        j--;
                    }
                    if (i < j) {
                        intArray[i] = intArray[j];
                        i++;
                    }
                    if (i < j) {
                        intArray[j] = intArray[i];
                        j--;
                    }
                }
            } else {
                while (i < j) {
                    while (intArray[j] < tmp && i < j) {
                        j--;
                    }
                    if (i < j) {
                        intArray[i] = intArray[j];
                        i++;
                    }
                    if (i < j) {
                        intArray[j] = intArray[i];
                        j--;
                    }
                }
            }

            intArray[i] = tmp;
            if (start < i - 1) {
                sa.push(start);
                sa.push(i - 1);
            }
            if (end > i + 1) {
                sa.push(i + 1);
                sa.push(end);
            }
        }
    }


    /**
     * 将数组颠倒
     */
    public static Object[] upsideDown(Object[] objects) {
        int length = objects.length;
        Object tem;
        for (int w = 0; w < length / 2; w++) {
            tem = objects[w];
            objects[w] = objects[length - 1 - w];
            objects[length - 1 - w] = tem;
        }
        return objects;
    }


    /**
     * Inteher数组转换成int数组
     */
    public static int[] integersToInts(Integer[] integers) {
        int[] ints = new int[integers.length];
        for (int w = 0; w < integers.length; w++) {
            ints[w] = integers[w];
        }
        return ints;
    }


    /**
     * 将给定的数组转换成字符串
     *
     * @param integers     给定的数组
     * @param startSymbols 开始符号
     * @param separator    分隔符
     * @param endSymbols   结束符号
     * @return 例如开始符号为"{"，分隔符为", "，结束符号为"}"，那么结果为：{1, 2, 3}
     */
    public static String toString(int[] integers, String startSymbols, String separator, String endSymbols) {
        boolean addSeparator = false;
        StringBuilder sb = new StringBuilder();
        //如果开始符号不为null且不空
        if (StringUtils.isNotEmpty(startSymbols)) {
            sb.append(startSymbols);
        }

        //循环所有的对象
        for (int object : integers) {
            //如果需要添加分隔符
            if (addSeparator) {
                sb.append(separator);
            }
            sb.append(object);
            addSeparator = true;
        }

        //如果结束符号不为null且不空
        if (StringUtils.isNotEmpty(endSymbols)) {
            sb.append(endSymbols);
        }
        return sb.toString();
    }


    /**
     * 将给定的数组转换成字符串
     *
     * @param integers  给定的数组
     * @param separator 分隔符
     * @return 例如分隔符为", "那么结果为：1, 2, 3
     */
    public static String toString(int[] integers, String separator) {
        return toString(integers, null, separator, null);
    }


    /**
     * 将给定的数组转换成字符串，默认分隔符为", "
     *
     * @param integers 给定的数组
     * @return 例如：1, 2, 3
     */
    public static String toString(int[] integers) {
        return toString(integers, null, ", ", null);
    }


    /**
     * 将给定的数组转换成字符串
     *
     * @param objects      给定的数组
     * @param startSymbols 开始符号
     * @param separator    分隔符
     * @param endSymbols   结束符号
     * @return 例如开始符号为"{"，分隔符为", "，结束符号为"}"，那么结果为：{1, 2, 3}
     */
    public static String toString(Object[] objects, String startSymbols, String separator, String endSymbols) {
        boolean addSeparator = false;
        StringBuilder sb = new StringBuilder();
        //如果开始符号不为null且不空
        if (StringUtils.isNotEmpty(startSymbols)) {
            sb.append(startSymbols);
        }
        //循环所有的对象
        for (Object object : objects) {
            //如果需要添加分隔符
            if (addSeparator) {
                sb.append(separator);
            }
            sb.append(object);
            addSeparator = true;
        }

        //如果结束符号不为null且不空
        if (StringUtils.isNotEmpty(endSymbols)) {
            sb.append(endSymbols);
        }
        return sb.toString();
    }


    /**
     * 将给定的数组转换成字符串
     *
     * @param objects   给定的数组
     * @param separator 分隔符
     * @return 例如分隔符为", "那么结果为：1, 2, 3
     */
    public static String toString(Object[] objects, String separator) {
        return toString(objects, null, separator, null);
    }


    /**
     * 将给定的数组转换成字符串，默认分隔符为", "
     *
     * @param objects 给定的数组
     * @return 例如：1, 2, 3
     */
    public static String toString(Object[] objects) {
        return toString(objects, null, ", ", null);
    }
}