
[toc]

# flyway配置
https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html

https://www.cnblogs.com/javastack/p/14950066.html

https://flywaydb.org/documentation/usage/plugins/springboot




# mybaits 配置

https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/

## 获取自动生成的id
https://mybatis.org/mybatis-3/sqlmap-xml.html#insert_update_and_delete



## 问题
class path resource [mapper/*.xml] cannot be opened because it does not exist

在test目录下定义或者在正式目录下定义、

很可能配置错了，配置mapper路径的话是
```properties
mybatis.mapper-locations=classpath:mapper/*.xml
```


# spring log 

打断点到 `org.springframework.transaction.support.AbstractPlatformTransactionManager.
getTransaction`里，就能发现是否开启debug日志，以及实际使用的实现。

比如使用JDBC的时候，logger是`org.springframework.jdbc.support.JdbcTransactionManager`，那么在配置如下日志级别

```properties
logging.level.org.springframework.jdbc.support.JdbcTransactionManager=debug
```


# spring transaction 
https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/transaction.html

其中关于回滚的需要注意的是
>
> In its default configuration, the Spring Framework’s transaction infrastructure code only marks a transaction for rollback in the case of runtime, unchecked exceptions; that is, when the thrown exception is an instance or subclass of RuntimeException. ( Errors will also - by default - result in a rollback). Checked exceptions that are thrown from a transactional method do not result in rollback in the default configuration.

这章有更详细的解释[tx config](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/transaction.html#transaction-declarative-txadvice-settings)



也就是发现运行时异常，unchecked exception的时候，默认回滚。

其他的异常，就不默认回滚了。

所以在声明式事务里面，注解上可以增加很多属性，可以声明要对那些异常做回滚，哪些不做。

## spring 事务传播
https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#tx-propagation



# swagger

https://springdoc.org/

参考里面的
1. 如何开启swagger-ui
```properties
springdoc.swagger-ui.path=/swagger-ui.html

```
2. 开启api-docs查询

```properties
springdoc.api-docs.path=/api-docs
```
如果要访问yaml格式，则访问`/api-docs.yaml`



# messageResource
https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/boot-features-internationalization.html

主要配置
```properties
spring.messages.basename=messages,config.i18n.messages
```
注意，配置用逗号分割，并且写到文件名.

但是spring原生的并不是很好用，要制定到文件名。那么如果有很多文件，就需要手动添加，能否有通配符的写法？

答案是，spring没提供，需要我们自己自定义一下

参考

- http://springrules.blogspot.com/2014/09/using-wildcards-for-spring.html
- https://stackoverflow.com/questions/34724398/reloadableresourcebundlemessagesource-using-wildcard


问题：
expected to be of type 'org.springframework.context.support.ReloadableResourceBundleMessageSource' but was actually of type 'org.springframework.context.support.ResourceBundleMessageSource'

因为默认不是这个bean，需要自行定义，如
```java
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }
}
```