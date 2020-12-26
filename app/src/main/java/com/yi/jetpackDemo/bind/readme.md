# 数据绑定相关：视图绑定和数据绑定

## ViewBindingFragment 单纯视图绑定
{
buildFeatures {
        viewBinding = true
    }
}

## 数据绑定
{
buildFeatures {
        dataBinding = true
    }
}

## DataBindingFragment 布局与绑定表达式
* 使用layout布局
* 将实体类或者集合类数据绑定
* 定义交互事件

## DataBindingFragment2 单向数据绑定
### 可观察类有三种类型：对象，字段和集合
* 可观察字段：八种基础数据类型（ObservableBoolean ObservableDouble）+ ObservableParcelable + ObservableField
* 可观察集合：ObservableArrayMap ObservableArrayList
* 可观察对象：BaseObservable

## DataBindingFragment3 双向数据绑定

## ObservableField vs liveData
## 两种方式的用法
## 两种方式的原理