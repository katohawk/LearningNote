# Retrofit源码解读

```java
public <T> T create(final Class<T> service) {
    Utils.validateServiceInterface(service);
    if (validateEagerly) {
      eagerlyValidateMethods(service);
    }
    return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[] { service },
        new InvocationHandler() {
          private final Platform platform = Platform.get();
          @Override public Object invoke(Object proxy, Method method, @Nullable Object[] args)
              throws Throwable {
            // If the method is a method from Object then defer to normal invocation.
            if (method.getDeclaringClass() == Object.class) {
              return method.invoke(this, args);
            }
            if (platform.isDefaultMethod(method)) {
              return platform.invokeDefaultMethod(method, service, proxy, args);
            }
            ServiceMethod<Object, Object> serviceMethod =
                (ServiceMethod<Object, Object>) loadServiceMethod(method);
            OkHttpCall<Object> okHttpCall = new OkHttpCall<>(serviceMethod, args);
            return serviceMethod.adapt(okHttpCall);
          }
        });
  }
```

1.retrofit的核心是create方法 主要思想是动态代理
其中proxy.newInstance方法的invoke回调相当于一个类继承了Service那个接口，并且方法都实现了invoke方法

动态代理就是 创建了类 调用了方法

2.适配adapt方法大概是将一个getUser方法转换成一个可以请求http的Call,每一个Call对应一个callAdapter

3.adapt方法是做线程切换，默认是handler实现

4.retrofit的enqueue实际上调用的是okhttp的enqueue，并且回调

5.读取并暂存各种配置 然后创建okhttpCall
然后serviceMethod.adapt方法来把okhttpCall换成其他的可以切换线程的call，例如handler或者rxjava

builder模式的好处
减少初始化成本以及
更改参数的开销
属性非常多时方便