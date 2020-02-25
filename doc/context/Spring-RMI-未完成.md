# Spring RMI
- Spring 远程服务调用
## DEMO
### 服务提供方
- 服务提供方需要准备**接口**、**接口实现泪**
- 接口
```java
public interface IDemoRmiService {
    int add(int a, int b);
}

```
- 接口实现
```java
public class IDemoRmiServiceImpl implements IDemoRmiService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
```
- xml配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="demoRmiService" class="com.huifer.source.spring.rmi.impl.IDemoRmiServiceImpl"/>
    <bean id="demoRmi" class="org.springframework.remoting.rmi.RmiServiceExporter">
        <!--        服务-->
        <property name="service" ref="demoRmiService"/>
        <!--        服务名称-->
        <property name="serviceName" value="springRmi"/>
        <!--        服务接口-->
        <property name="serviceInterface" value="com.huifer.source.spring.rmi.IDemoRmiService"/>
        <!--        注册端口-->
        <property name="registryPort" value="9999"/>
    </bean>
</beans>
```
- 运行
```java
public class RMIServerSourceCode {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("rmi/RMISourceCode.xml");
    }
}

```
### 服务调用方
- xml 配置
```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="rmiClient" class="org.springframework.remoting.rmi.RmiProxyFactoryBean">
        <property name="serviceUrl" value="rmi://localhost:9999/springRmi"/>
        <property name="serviceInterface" value="com.huifer.source.spring.rmi.IDemoRmiService"/>
    </bean>


</beans>
```
**注意:rmi使用小写**
大写报错信息:
```text
Exception in thread "main" org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'rmiClient' defined in class path resource [rmi/RMIClientSourceCode.xml]: Invocation of init method failed; nested exception is org.springframework.remoting.RemoteLookupFailureException: Service URL [RMI://localhost:9999/springRmi] is invalid; nested exception is java.net.MalformedURLException: invalid URL scheme: RMI://localhost:9999/springRmi
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1795)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:559)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:478)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:309)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:272)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:306)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:176)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:877)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:953)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:579)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:163)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:90)
	at com.huifer.source.spring.rmi.RMIClientSourceCode.main(RMIClientSourceCode.java:7)
Caused by: org.springframework.remoting.RemoteLookupFailureException: Service URL [RMI://localhost:9999/springRmi] is invalid; nested exception is java.net.MalformedURLException: invalid URL scheme: RMI://localhost:9999/springRmi
	at org.springframework.remoting.rmi.RmiClientInterceptor.lookupStub(RmiClientInterceptor.java:209)
	at org.springframework.remoting.rmi.RmiClientInterceptor.prepare(RmiClientInterceptor.java:147)
	at org.springframework.remoting.rmi.RmiClientInterceptor.afterPropertiesSet(RmiClientInterceptor.java:134)
	at org.springframework.remoting.rmi.RmiProxyFactoryBean.afterPropertiesSet(RmiProxyFactoryBean.java:68)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1868)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1791)
	... 12 more
Caused by: java.net.MalformedURLException: invalid URL scheme: RMI://localhost:9999/springRmi
	at java.rmi.Naming.intParseURL(Naming.java:290)
	at java.rmi.Naming.parseURL(Naming.java:237)
	at java.rmi.Naming.lookup(Naming.java:96)
	at org.springframework.remoting.rmi.RmiClientInterceptor.lookupStub(RmiClientInterceptor.java:201)
	... 17 more

```
- 运行
```java
public class RMIClientSourceCode {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("rmi/RMIClientSourceCode.xml");
        IDemoRmiService rmiClient = context.getBean("rmiClient", IDemoRmiService.class);
        int add = rmiClient.add(1, 2);
        System.out.println(add);
    }
}
```

---
## 服务端初始化
- `org.springframework.remoting.rmi.RmiServiceExporter`,该类定义了spring的远程服务信息
### RmiServiceExporter
- 基础属性如下
```java
 public class RmiServiceExporter extends RmiBasedExporter implements InitializingBean, DisposableBean {
/**
     * 服务名称,rmi的服务名,在Spring中使用ref指向impl实现类
     */
    private String serviceName;

    /**
     *
     */
    private int servicePort = 0;  // anonymous port

    private RMIClientSocketFactory clientSocketFactory;

    private RMIServerSocketFactory serverSocketFactory;

    private Registry registry;

    /**
     * 注册的IP地址
     */
    private String registryHost;

    /**
     * 注册的端口
     */
    private int registryPort = Registry.REGISTRY_PORT;

    /**
     * 进行远程对象调用的Socket工厂
     */
    private RMIClientSocketFactory clientSocketFactory;

    /**
     * 接收远程调用服务端的Socket工厂
     */
    private RMIServerSocketFactory serverSocketFactory;

    /**
     * true: 该参数在获取{@link Registry}时测试是否建立连接,如果建立连接则直接使用,否则创建新连接
     * 是否总是创建{@link Registry}
     */
    private boolean alwaysCreateRegistry = false;

    /**
     * 设置替换RMI注册表中的绑定关系
     */
    private boolean replaceExistingBinding = true;

    private Remote exportedObject;

    /**
     * 创建{@link Registry}
     */
    private boolean createdRegistry = false;
}
```
- 属性设置不说了,看接口实现了那些
    1. InitializingBean: 初始化bean调用`afterPropertiesSet`方法
    2. DisposableBean: 摧毁bean调用`destroy`方法

```java

    @Override
    public void afterPropertiesSet() throws RemoteException {
        prepare();
    }



    public void prepare() throws RemoteException {
        // 校验service
        checkService();

        // 服务名称校验
        if (this.serviceName == null) {
            throw new IllegalArgumentException("Property 'serviceName' is required");
        }

        // Check socket factories for exported object.
        if (this.clientSocketFactory instanceof RMIServerSocketFactory) {
            this.serverSocketFactory = (RMIServerSocketFactory) this.clientSocketFactory;
        }
        if ((this.clientSocketFactory != null && this.serverSocketFactory == null) ||
                (this.clientSocketFactory == null && this.serverSocketFactory != null)) {
            throw new IllegalArgumentException(
                    "Both RMIClientSocketFactory and RMIServerSocketFactory or none required");
        }

        // Check socket factories for RMI registry.
        if (this.registryClientSocketFactory instanceof RMIServerSocketFactory) {
            this.registryServerSocketFactory = (RMIServerSocketFactory) this.registryClientSocketFactory;
        }
        if (this.registryClientSocketFactory == null && this.registryServerSocketFactory != null) {
            throw new IllegalArgumentException(
                    "RMIServerSocketFactory without RMIClientSocketFactory for registry not supported");
        }

        this.createdRegistry = false;

        // Determine RMI registry to use.
        if (this.registry == null) {
            // registry 获取
            this.registry = getRegistry(this.registryHost, this.registryPort,
                    this.registryClientSocketFactory, this.registryServerSocketFactory);
            this.createdRegistry = true;
        }

        // Initialize and cache exported object.
        // 初始化并且获取缓存的object
        this.exportedObject = getObjectToExport();

        if (logger.isDebugEnabled()) {
            logger.debug("Binding service '" + this.serviceName + "' to RMI registry: " + this.registry);
        }

        // Export RMI object.
        // 导出RMI object
        if (this.clientSocketFactory != null) {
            UnicastRemoteObject.exportObject(
                    this.exportedObject, this.servicePort, this.clientSocketFactory, this.serverSocketFactory);
        }
        else {
            UnicastRemoteObject.exportObject(this.exportedObject, this.servicePort);
        }

        // Bind RMI object to registry.
        // 对象绑定,把serviceName 和 到处的RMI对象进行绑定,JDK实现
        try {
            if (this.replaceExistingBinding) {
                this.registry.rebind(this.serviceName, this.exportedObject);
            }
            else {
                this.registry.bind(this.serviceName, this.exportedObject);
            }
        }
        catch (AlreadyBoundException ex) {
            // Already an RMI object bound for the specified service name...
            unexportObjectSilently();
            throw new IllegalStateException(
                    "Already an RMI object bound for name '" + this.serviceName + "': " + ex.toString());
        }
        catch (RemoteException ex) {
            // Registry binding failed: let's unexport the RMI object as well.
            unexportObjectSilently();
            throw ex;
        }
    }

```

#### checkService
- 校验service 是否为空
```java
    /**
     * Check whether the service reference has been set.
     *
     * 校验service
     * @see #setService
     */
    protected void checkService() throws IllegalArgumentException {
        Assert.notNull(getService(), "Property 'service' is required");
    }
```

#### getRegistry
- 获取 Registry 实例
    - 简单说就是通过host和port创建socket
```java
    protected Registry getRegistry(String registryHost, int registryPort,
                                   @Nullable RMIClientSocketFactory clientSocketFactory, @Nullable RMIServerSocketFactory serverSocketFactory)
            throws RemoteException {

        if (registryHost != null) {
            // Host explicitly specified: only lookup possible.
            if (logger.isDebugEnabled()) {
                logger.debug("Looking for RMI registry at port '" + registryPort + "' of host [" + registryHost + "]");
            }
            // 通过 host port socket创建
            Registry reg = LocateRegistry.getRegistry(registryHost, registryPort, clientSocketFactory);
            testRegistry(reg);
            return reg;
        }

        else {
            return getRegistry(registryPort, clientSocketFactory, serverSocketFactory);
        }
    }
```
#### getObjectToExport
- 初始化并且获取缓存的object
```java
    protected Remote getObjectToExport() {
        // determine remote object
        if (getService() instanceof Remote &&
                (getServiceInterface() == null || Remote.class.isAssignableFrom(getServiceInterface()))) {
            // conventional RMI service
            // 获取service
            return (Remote) getService();
        }
        else {
            // RMI invoker
            if (logger.isDebugEnabled()) {
                logger.debug("RMI service [" + getService() + "] is an RMI invoker");
            }
            // RMI 包装类
            /**
             * 1. getProxyForService() 获取代理的接口
             * 2. 创建RMI包装对象 RmiInvocationWrapper
             */
            return new RmiInvocationWrapper(getProxyForService(), this);
        }
    }

```
#### getProxyForService
- 获取代理服务
```java
    protected Object getProxyForService() {
        //  service 校验
        checkService();
        // 校验接口
        checkServiceInterface();

        // 代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        // 添加代理接口
        proxyFactory.addInterface(getServiceInterface());

        if (this.registerTraceInterceptor != null ? this.registerTraceInterceptor : this.interceptors == null) {
            // 添加切面
            proxyFactory.addAdvice(new RemoteInvocationTraceInterceptor(getExporterName()));
        }
        if (this.interceptors != null) {
            AdvisorAdapterRegistry adapterRegistry = GlobalAdvisorAdapterRegistry.getInstance();
            for (Object interceptor : this.interceptors) {
                proxyFactory.addAdvisor(adapterRegistry.wrap(interceptor));
            }
        }
        // 设置代理类
        proxyFactory.setTarget(getService());

        proxyFactory.setOpaque(true);

        // 获取代理对象
        return proxyFactory.getProxy(getBeanClassLoader());
    }

```
- 这里要注意,切片方法的`invoke`调用
- `RmiInvocationWrapper`的`invoke`方法会在调用方调用接口时候触发


#### rebind 和 bind 
- 这部分源码在: `sun.rmi.registry.RegistryImpl`
```java
    public void rebind(String var1, Remote var2) throws RemoteException, AccessException {
        checkAccess("Registry.rebind");
        this.bindings.put(var1, var2);
    }


```
```java
    public void bind(String var1, Remote var2) throws RemoteException, AlreadyBoundException, AccessException {
        checkAccess("Registry.bind");
        synchronized(this.bindings) {
            Remote var4 = (Remote)this.bindings.get(var1);
            if (var4 != null) {
                throw new AlreadyBoundException(var1);
            } else {
                this.bindings.put(var1, var2);
            }
        }
    }
```
- 共同维护`    private Hashtable<String, Remote> bindings = new Hashtable(101);`这个对象