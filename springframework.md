## 核心特性

* IOC容器（IOC Container）
* Spring事件（Events）
* 资源管理（Resources）
* 国际化（i18n ）
* 校验（Validation）
* 数据绑定（Data Binding）
* 类型转换（Type Conversion）
* Spring表达式（Spring Express Language）
* 面向切面变成（AOP）

## 数据存储 Data Access

* JDBC （JdbcTemplate）
* 事务抽象（Transaction） spring在ejb的基础上做了一些简化操作
* DAO支持（DAO Support）
* O/R 映射（O/R Mapping） spring对Hibernate、JDO的支持（已被JPA替代）
* XML编列（XML Marshalling）

## WEB技术(Web)

* Web Servlet
    * Spring MVC
    * WebSocket （servlet3.0开始）
    * SockJS

* Web Reactive (spring5.0开始，底层默认是Netty Server)
    * Spring WebFlux
    * WebClient （把同步变异步）
    * WebSocket

## 技术整合（Integration）
* 远程调用（Remoting）
* Java消息服务JAVA Message Service（JMS）
* Java连接架构（JCA）
* Java扩展 Java Management Extensions（JMX）
* Java邮件客户端（Email）
* 本地任务（Tasks）
* 本地调度（Scheduling）
* 缓存抽象（Caching）
* Spring测试（Testing）
  * 模拟对象（Mock Objects）spring里面会生成一个MockHttp接口
  * TestContext框架
  * Spring MVC 测试 
  * Web 测试客户端（WebTestClient）
  

##  BeanDefinition
* BeanDefinition 是 Spring Framework中定义 Bean 的配置元信息接口，包含：
  * Bean 的类名
  * Bean 行为配置元素，如作用域、自动绑定的模式、生命周期等
  * 其他 Bean 引用，又可称作合作者（Collaborators）或者依赖（Dependencies）
  * 配置设置，比如 Bean 属性（Properties）
  
* BeanName 通过 BeanNameGenerator 接口进行创建，该接口有两个实现类
 * DefaultBeanNameGenerator
 * AnnotationBeanNameGenerator

# Bean 实例化
  * 常规方式
    * 通过构造器（配置原信息：XML、Java 注解和 Java API）
    * 通过静态工厂方法（配置元信息：XML 和 Java API）
    * 通过 Bean 工厂方法（配置原信息：XML 和 Java API）
    * 通过 FactoryBean（配置元信息：XML、Java 注解 和 Java API）
  
  * 特殊方式
    * 通过 ServiceLoaderFactoryBean（配置元信息：XML、Java 注解 和 Java API）
    * 通过 AutowireCapableBeanFactory#createBean(java.lang.Class, int, boolean)
    * 通过 BeanDefinitionRegistry#registerBeanDefinition(String, BeanDefinition)
  
* Bean 初始化
  * @PostConstruct 注解
  * 实现 InitializingBean 接口的 afterPropertiesSet() 方法
  * 自定义初始化方法
    * XML 配置: <bean init-method = "init">
    * Spring 注解: @Bean(initMethod = "init")
    * Spring API: AbstractBeanDefinition#setInitMethodName(String)
    
* Bean 延时加载
  * XML 配置: <bean lazy-init="true">
  * Spring 注解: @Lazy(true)
    
* Bean 销毁（Destroy）
  * @PreDestory 标注方法
  * 实现 DisposableBean 接口的 destory() 方法
  * 自定义销毁方法
    * XML 配置： <bean destory="destroy">
    * Java 注解：@Bean(destroy="destroy")
    * Java API：AbstractBeanDefinition#setDestroyMethodName(String)
    
# 依赖查找
  * 单一查找
    * 根据 Bean 名称查找
      * getBean(String)
      * Spring 2.5 覆盖默认参数：getBean(String, Object...)
    * 根据 Bean 类型查找
      * Bean 实时查找
        * Spring 3.9 getBean(Class)
        * Spring 4.1 覆盖默认参数：getBean(Class, Object...)
      * Spring 5.1 Bean 延迟查找
        * getBeanProvider(Class)
        * getBeanProvider(ResolvableType)
    * 根据 Bean 名称 + 类型查找： getBean(String, Class)
    
  * 集合类型依赖查找接口 `ListableBeanFactory`
    * 根据 Bean 类型查找
      * 获取同类型 Bean 名称列表
        * getBeanNamesForType(Class)
        * Spring 4.2 getBeanNamesForType(ResolvableType)
      * 获取同类型 Bean 实例列表
        * getBeansOfType(Class) 以及重载方法
    * 通过注解类型查找
      * Spring 3.0 获取标注类型 Bean 名称列表
        * getBeanNamesForAnnotation(Class<? extends Annotation>)
      * Spring 3.0 获取标注类型 Bean 实例列表
        * getBeansWithAnnotation(Class<? extends Annotation>)
      * Spring 3.0 获取指定名称 + 标注类型 Bean 实例
        * findAnnotationOnBean(String, Class<? extends Annotation>)
    
  * 层次性依赖查找接口 HierarchicalBeanFactory
    * 双亲 BeanFactory: getParentBeanFactory()
    * 层次性查找
      * 根据 Bean 名称查找
        * 以及 containsLocalBean() 方法实现
      * 根据 Bean 类型查找实例列表
        * 单一类型： BeanFactoryUtils#beanOfType()
        * 集合类型： BeanFactoryUtils#beansOfTypeIncludingAncestors
      * 根据 Java 注解查找名称列表
        * BeanFactoryUtils#beanNamesForTypeIncludingAncestors
    
  * Bean 延迟查找
    * ObjectFactory#getObject()
    * `ObjectProvider` 继承与 `ObjectFactory`
      * Spring5 对 Java8 特性扩展
        * 函数式接口
          * getIfAvailable(Supplier)
          * ifAvailable(Consumer)
        * Stream 扩展 stream()
    
# IOC 依赖注入

  * 自动绑定模式，Spring2.0使用`Autowire` 枚举类
    * no：默认的实现，未激活Autowiring，需要手动指定依赖注入对象
    * byName：根据被注入属性的名称作为Bean名称进行查找，并将对象设置到该属性
    * byType：根据被注入的类型作为依赖类型进行查找，并将对象设置到该属性
    * constructor：特殊byType类型，用于构造器参数
    
  * Setter方法注入
    * 手动模式
      * XML 资源配置元信息
      * Java 注解配置元信息
      * API 配置元信息
    * 自动模式
      * byName
      * byType
    
  * 构造器注入
    * 手动模式
      * XML 资源配置元信息
      * Java 注解配置元信息
      * API 配置元信息
    
    * 自动模式
      * constructor
    
  * 延迟依赖注入
    * 使用 API ObjectFactory 延迟注入
      * 单一类型
      * 集合类型
    
    * 使用 API ObjectProvider 延迟注入（推荐）
      * 单一类型
      * 集合类型