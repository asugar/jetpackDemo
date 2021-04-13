package com.yi.patterns.factory;

/**
 * 验证泛型 super and extends
 *
 * @author wanghuayi
 * @since 2021/4/12
 */
public class Productor3 {
    public IProduct createProduct(Class<? extends IProduct> clz) throws InstantiationException, IllegalAccessException {
        return clz.newInstance();
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Productor3 p3 = new Productor3();
        p3.createProduct(ProductImp.class).create();
    }
}


