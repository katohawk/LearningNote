# LruCache算法

Least Recently Used 最近最少使用算法

## 缓存策略

当缓存满的时候,会优先删除近期最少使用的缓存对象

## 实现思路

根据特性,Android中的LruCache类内部是由LinkedHashMap来实现的,

LinkedHashMap是由双向链表来实现,他的特性是当插入或者访问已有的key的对象时,会把它放到map的前面,

这正好符合LruCache的思想

```java
public static final void main(String[] args) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(0, 0.75f, true);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);
        map.get(1);
        map.get(2);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());

        }
    }
```

输出结果

```
0:0
3:3
4:4
5:5
6:6
1:1
2:2
```

