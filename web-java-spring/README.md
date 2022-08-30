# spring https 参考
https://docs.spring.io/spring-cloud-skipper/docs/1.0.0.BUILD-SNAPSHOT/reference/html/configuration-security-enabling-https.html

自测试的时候自己生成一个keystone来用就行。

如果使用apache http Client 的话，可以参考他的官网

https://github.com/apache/httpcomponents-client/blob/5.1.x/httpclient5/src/test/java/org/apache/hc/client5/http/examples/ClientCustomSSL.java

https://hc.apache.org/httpcomponents-client-5.1.x/examples.html


然后maven坐标是
```xml
        <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents.client5/httpclient5 -->
        <dependency>
            <groupId>org.apache.httpcomponents.client5</groupId>
            <artifactId>httpclient5</artifactId>
            <version>5.1.3</version>
        </dependency>
```

测试代码是
```java
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.ssl.TLS;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpClientsTest {

    public final static void main(final String[] args) throws Exception {
        // Trust standard CA and those trusted by our custom strategy
        final SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(new TrustStrategy() {

                    @Override
                    public boolean isTrusted(
                            final X509Certificate[] chain,
                            final String authType) throws CertificateException {
                        return true;
                    }

                })
                .build();
        // Allow TLSv1.2 protocol only
        final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslcontext)
                .setTlsVersions(TLS.V_1_2)
                .build();
        final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();
        try (CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build()) {

            final HttpGet httpget = new HttpGet("https://localhost:9090/test");

            System.out.println("Executing request " + httpget.getMethod() + " " + httpget.getUri());

            final HttpClientContext clientContext = HttpClientContext.create();
            try (CloseableHttpResponse response = httpclient.execute(httpget, clientContext)) {
                System.out.println("----------------------------------------");
                System.out.println(response.getCode() + " " + response.getReasonPhrase());
                System.out.println(EntityUtils.toString(response.getEntity()));

                final SSLSession sslSession = clientContext.getSSLSession();
                if (sslSession != null) {
                    System.out.println("SSL protocol " + sslSession.getProtocol());
                    System.out.println("SSL cipher suite " + sslSession.getCipherSuite());
                }
            }
        }
    }
}


```



# 整合springfox

报错
```log
Caused by: java.lang.NullPointerException: Cannot invoke "org.springframework.web.servlet.mvc.condition.PatternsRequestCondition.getPatterns()" because "this.condition" is null
	at springfox.documentation.spring.web.WebMvcPatternsRequestConditionWrapper.getPatterns(WebMvcPatternsRequestConditionWrapper.java:56) ~[springfox-spring-webmvc-3.0.0.jar:3.0.0]
	at springfox.documentation.RequestHandler.sortedPaths(RequestHandler.java:113) ~[springfox-core-3.0.0.jar:3.0.0]
	at springfox.documentation.spi.service.contexts.Orderings.lambda$byPatternsCondition$3(Orderings.java:89) ~[springfox-spi-3.0.0.jar:3.0.0]
	at java.base/java.util.Comparator.lambda$comparing$77a9974f$1(Comparator.java:473) ~[na:na]
	at java.base/java.util.TimSort.countRunAndMakeAscending(TimSort.java:355) ~[na:na]
	at java.base/java.util.TimSort.sort(TimSort.java:220) ~[na:na]
	at java.base/java.util.Arrays.sort(Arrays.java:1307) ~[na:na]
	at java.base/java.util.ArrayList.sort(ArrayList.java:1721) ~[na:na]
	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:392) ~[na:na]
	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258) ~[na:na]
	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258) ~[na:na]
	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258) ~[na:na]
	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499) ~[na:na]
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[na:na]
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682) ~[na:na]
	at springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider.requestHandlers(WebMvcRequestHandlerProvider.java:81) ~[springfox-spring-webmvc-3.0.0.jar:3.0.0]
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197) ~[na:na]
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499) ~[na:na]
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[na:na]
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682) ~[na:na]
	at springfox.documentation.spring.web.plugins.AbstractDocumentationPluginsBootstrapper.withDefaults(AbstractDocumentationPluginsBootstrapper.java:107) ~[springfox-spring-web-3.0.0.jar:3.0.0]
	at springfox.documentation.spring.web.plugins.AbstractDocumentationPluginsBootstrapper.buildContext(AbstractDocumentationPluginsBootstrapper.java:91) ~[springfox-spring-web-3.0.0.jar:3.0.0]
	at springfox.documentation.spring.web.plugins.AbstractDocumentationPluginsBootstrapper.bootstrapDocumentationPlugins(AbstractDocumentationPluginsBootstrapper.java:82) ~[springfox-spring-web-3.0.0.jar:3.0.0]
	at springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper.start(DocumentationPluginsBootstrapper.java:100) ~[springfox-spring-web-3.0.0.jar:3.0.0]
	at org.springframework.context.support.DefaultLifecycleProcessor.doStart(DefaultLifecycleProcessor.java:178) ~[spring-context-5.3.21.jar:5.3.21]
	... 14 common frames omitted


Process finished with exit code 1

```

找到[issue](https://github.com/springfox/springfox/issues/3462)

[stackflow](https://stackoverflow.com/questions/70036953/springboot-2-6-0-spring-fox-3-failed-to-start-bean-documentationpluginsboot)


## 解决后访问
http://localhost:9090/swagger-ui/

参考swagger实现

访问实际是 Swagger2ControllerWebMvc 这个controller

- https://github.com/springfox/springfox/blob/master/springfox-boot-starter/src/main/java/springfox/boot/starter/autoconfigure/SwaggerUiWebMvcConfigurer.java
```java
public class SwaggerUiWebMvcConfigurer implements WebMvcConfigurer {
    private final String baseUrl;

    public SwaggerUiWebMvcConfigurer(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
        registry.
                addResourceHandler(baseUrl + "/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(baseUrl + "/swagger-ui/")
                .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
    }
}
```


## 在IOC容器里获取对应的

既然知道是Swagger2ControllerWebMvc 这个controller，那么我们就可以在实际运行的时候，注入此类，直接调用此类获取swagger文档，然后做自己的业务了。

