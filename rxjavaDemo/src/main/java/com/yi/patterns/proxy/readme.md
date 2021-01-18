# 代理

## 静态代理
StudentServiceProxy 就是一个静态代理，在执行之前就已经确定了代理类是谁了

## 动态代理
1）实现InvocationHandler接口
2）使用Proxy生成代理类，

在使用的地方
```
Proxy.newProxyInstance(
    service.getClassLoader(),
    new Class<?>[] {service},
    new InvocationHandler() {
      @Override
      public @Nullable Object invoke(Object proxy, Method method, @Nullable Object[] args)
          throws Throwable {}
}）
```