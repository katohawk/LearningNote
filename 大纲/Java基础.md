## Java基础

##### String buffer 与String builder 的区别

String 类型和StringBuffer的主要性能区别：String是不可变的对象, 因此在每次对String 类型进行改变的时候，都会生成一个新的 String 对象，然后将指针指向新的 String 对象，所以经常改变内容的字符串最好不要用 String ，因为每次生成对象都会对系统性能产生影响，特别当内存中无引用对象多了以后， JVM 的 GC 就会开始工作，性能就会降低。

StringBuffer 是线程安全的, StringBuilder 不是线程安全的。

##### String 为什么要设计成不可变的

String类是final的,其内部主要是由char数组来实现,其中的相关操作,例如substring,concat,replace,都是重新生成了一个新的字符串对象(可参见源码),最原始的字符串并没有被改变

不可变的好处最简单的原因就是安全,String是几乎每个类都会使用的类,如果他被改变,会产生很多危险,例如hashMap中的key



##### object类的equal 和hashcode 方法重写，为什么

equlas()和hashcode()都是可重写的方法

Object类中equals()方法是比较对象是否相等,hashcode()是返回了一个hash值,是一个native方法

所以只要不重写equals的话,两个对象则一定返回false

hashCode方法就是根据一定的规则将与对象相关的信息（比如对象的存储地址，对象的字段等）映射成一个数值，这个数值称作为散列值



所以当重写equlas方法时,有些想让两个不同的地址的对象相等,就必须要重写hashcode方法,因为hashcode值已经不一样了

```java
package com.cxh.test1;
 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
 
class People{
    private String name;
    private int age;
 
    public People(String name,int age) {
        this.name = name;
        this.age = age;
    }  
 
    public void setAge(int age){
        this.age = age;
    }
 
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return this.name.equals(((People)obj).name) && this.age== ((People)obj).age;
    }
    
    //@Override
    //public int hashCode() {
        // TODO Auto-generated method stub
     //   return name.hashCode()*37+age;
    //}
}
 
public class Main {
 
    public static void main(String[] args) {
 
        People p1 = new People("Jack", 12);
        System.out.println(p1.hashCode());
 
        HashMap<People, Integer> hashMap = new HashMap<People, Integer>();
        hashMap.put(p1, 1);
 
        System.out.println(hashMap.get(new People("Jack", 12)));//这里是new的新对象
    }
}
```

例如在哈希表中存储数据,如果要存储的类型只重写equals,这样当两个被判定为相等的对象,但是他们两个的存储地址肯定不同,所以在取值时肯定为null,

所以需要重写hashcode方法,让equals方法和hashcode方法始终在逻辑上保持一致性

##### int,long的取值范围以及BigDecimal，数值越界了如何处理？

int、char、long各占多少字节数

int与integer的区别

string 转换成 integer的方式及原理

讲一下常见编码方式

utf-8编码中的中文占几个字节；int型几个字节



内部类和静态内部类和匿名内部类，以及项目中的应用

静态内部类的设计意图

同步普通方法跟static方法之间的区别

父类的静态方法能否被子类重写

静态属性和静态方法是否可以被继承？是否可以被重写？以及原因？

静态内部类的设计意图

静态代理和动态代理的区别，什么场景使用



抽象类和接口的区别

Java为何引入泛型，泛型边界

注解如何获取，反射为何耗性能

类的初始化过程

类的初始化顺序依次是（静态变量、静态代码块）>（变量、代码块）>构造方法

不用锁如何保证int自增安全

深拷贝浅拷贝

谈谈对java多态的理解

final，finally，finalize的区别

Java的异常体系

NIO

