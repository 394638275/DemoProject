package com.lew.mapleleaf.utils.math;

/**
 * Created by Richie on 2017/7/22
 */

public class Fibonacci {

    /**
     * 递归算法 n大于40延时就明显了
     */
    public static long fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 多项式算法 由于long的范围限制，结果在n属于[0,92]正确。
     * 当n为8个9时有明显延迟(已溢出)
     */
    public static long fib2(int n) {
        if (n == 0)
            return 0;

        long[] f = new long[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    /**
     * 矩阵算法 n在[0,92]内结果正确
     * 当n为9个9时仍然无明显延迟(已溢出)
     */
    public static long fib3(int n) {
        Matrix2 m=new Matrix2(0,1,1,1);
        m=m.fastPower(n);

        return m.getMatrix()[0][1];
    }
}

/**
 * 二维矩阵类
 * 功能实现：矩阵乘法；快速求幂；
 * @author Moilk
 *
 */
class Matrix2 {
    private long[][] pmatrix;

    public Matrix2() {
        pmatrix = new long[2][2];
    }

    public Matrix2(long a, long b, long c, long d) {
        setMatrix(new long[][] { { a, b }, { c, d } });
    }

    /**
     * 快速幂
     * @param n 幂
     * @return 运算结果
     */
    public Matrix2 fastPower(int n){
        Matrix2 result=new Matrix2(1,0,1,0);    //初始化为单位矩阵
        Matrix2 tmp=this;
        while(n!=0){
            if((n&1)==1){
                result=multi(result,tmp);
            }
            tmp=multi(tmp,tmp);
            n>>=1;
        }

        return result;
    }

    /**
     * 矩阵乘法
     * @param m1 矩阵1
     * @param m2 矩阵2
     * @return 乘积
     */
    public Matrix2 multi(Matrix2 m1,Matrix2 m2) {

        Matrix2 tmp = new Matrix2();
        tmp.getMatrix()[0][0] = m1.getMatrix()[0][0] * m2.getMatrix()[0][0] + m1.getMatrix()[0][1] * m2.getMatrix()[1][0];
        tmp.getMatrix()[0][1] = m1.getMatrix()[0][0] * m2.getMatrix()[0][1] + m1.getMatrix()[0][1] * m2.getMatrix()[1][1];
        tmp.getMatrix()[1][0] = m1.getMatrix()[1][0] * m2.getMatrix()[0][0] + m1.getMatrix()[1][1] * m2.getMatrix()[1][0];
        tmp.getMatrix()[1][1] = m1.getMatrix()[1][0] * m2.getMatrix()[0][1] + m1.getMatrix()[1][1] * m2.getMatrix()[1][1];

        return tmp;
    }

    public long[][] getMatrix() {
        return pmatrix;
    }

    public void setMatrix(long[][] pmatrix) {
        this.pmatrix = pmatrix;
    }
}
