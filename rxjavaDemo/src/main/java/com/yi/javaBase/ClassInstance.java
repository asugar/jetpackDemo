package com.yi.javaBase;

public class ClassInstance {
    /**
     * 加载 -- 链接（验证，准备，解析） -- 初始化 -- 使用 -- 卸载
     * 准备：为类变量分配内存，并且赋予初值，默认的初值
     * 调用静态变量（非final）的时候，静态变量会执行初始化
     */
    public static final String name = "xiaoyi";

    public static String address = "address";

    static {
        System.out.println("static invoked  $address" + name + " - " + address);
    }

    public ClassInstance() {
        address = "address2";
        System.out.println("construct invoked ----- ");
    }
}
