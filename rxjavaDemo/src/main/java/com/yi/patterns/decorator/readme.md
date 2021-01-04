# 装饰模式
结构型，利用组合得方式实现功能得扩展
## 核心角色

* Component接口
相当于汽车接口，所有得被包装类/包装类都继承这个接口

* ConcreteComponent类
被包装得实现类，例子中得奔驰骑车/宝马汽车

* Decorator抽象类
所有得包装类都继承Decorator抽象类，而Decorator类又实现了Component接口，这么做是为了实现多层嵌套包装。

* ConcreteDecorator类
具体得包装类，用域扩充被包装类得功能，比如例子中得自动驾驶/飞行功能得扩展

具体参考uml图

装饰模式在jdk得IO流中使用
