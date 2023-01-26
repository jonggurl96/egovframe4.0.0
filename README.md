# eGovFramework
전자정부 프레임워크 시작하기

**package egovframework.example
package cpservice로 변경**
`단, resource, webapp 등의 폴더는 그대로` > 잘못 설정한거;;


# 동작 원리
실행 - web.xml - dispatcher-servlet.xml, context-*.xml 위치 설정

dispatcher-servlet.xml
context:component-scan, interceptor, renderer, UrlBasedViewResolver, SessionLocaleResolver 등이 있음.

# context-*.xmls
- context-aspect.xml: 관점 지향 context (주로 Exception Handler)
- context-common.xml: 각 언어별 문자열 상수 저장(message-common.properties)
- context-datasource.xml: JDBC DataSource 명시
    > 사용할 JDBC 빈 주석만 해제 후 url, id, pw 변경해 사용
- context-idgen.xml: 테이블 데이터 id 생성용
- context-mapper.xml: SqlSession 빈 설정 파일 iBatis 사용 시 MapperConfigurer 필요 없음
    - sql-mapper-config.xml: MyBatis 설정 파일 (mybatis-config.xml)
        ```
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <property datasource>
            <property configLocation>
            <property mapperLocations>
        </bean>
        <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate" destroy-method="clearCache">
            <constructor-arg name=sqlSessionFactory" ref="sqlSessionFactory"/>
        </bean>
        ```
- context-properties.xml: pageUnit, pageSize와 같은 상수값
- context-sqlMap.xml: iBatis용 설정 파일
    - sql-map-config.xml:iBatis 설정 파일
- context-transaction.xml: 트랜잭션 관점 설정 (Exception 처리 등)
- context-validator.xml: validator 관련 설정들과 xml 파일 위치

# 프로젝트 생성 후 해야할 일
1. Java Compiler 버전 수정
2. Tomcat, Servlet 버전 수정
3. 패키지 명 변경: 이클립스에서 하다가 경로 꼬일 수 있으니 python 코드로 한 번에 해결

# Controller 경로 변경
web.xml 3개 url-pattern 변경
1. encodingFilter: /*
2. HTMLTagFilter: /*
3. action: /
3-1. 아래 코드 추가
    ```
    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>*.js</url-pattern>
        <url-pattern>*.css</url-pattern>
        <url-pattern>*.jpg</url-pattern>
        <url-pattern>*.gif</url-pattern>
        <url-pattern>*.ico</url-pattern>
        <url-pattern>*.swf</url-pattern>
        <url-pattern>*.png</url-pattern>
        <!-- etc... -->
    </servlet-mapping>
    ```
# MyBatis 연동하기
> sqlSession이라는 이름의 빈(SqlSessionFactory) 생성
1. 기존 DAO 형태로 사용할 경우
    - extends EgovAbstractMapper
2. Mapper interface 사용할 경우
    - @Mapper("**Mapper")
    - mapper.xml namespace = "**Mapper"
    - method 이름이 mapper.xml id
    - @Resource(name = "**Mapper")로 Injection
    - 아래 설정 필요 (context-mapper.xml)
    ```
    <bean class="org.egovframe.rte.psl.dataaccess.mapper.MapperConfigurer>
        <property name="basePackage" value="/" />
    </bean>
    ```
3. Annotation 방식
    - xml 작성 필요 없음
    - dynamic query 사용 불가능
    ```
    @Mapper("**Mapper")
    public interface {
        @Select("select * from tbl")
        public List<E> select**(...);
    }
    ```
> underscore case to camel case
> > mybatis-config.xml - configuration - settings
> > ```
> > <setting name="mapUnderscoreToCamelCase" value="true"/>
> > ```




# @Controller와 @RestController
## @Controller
> method들은 view의 이름을 return
> ```
> @RequestMapping(value="/list", method=RequestMethod.GET)
> public void list() throws Exception {
>   // 설정파일 prefix / list.suffix로 자동으로 이동
> }
> @RequestMapping(value="/list", method=RequestMethod.GET)
> public String list() throws Exception {
>   return "redirect:/board/listAll";
>   // url로 redirect
> }
>  ```
## @RestController
> method들은 data와 ResponseEntity를 return
> ```
> @RequestMapping(value="/board/{bno}", method=RequestMethod.GET)
> public ResponseEntity<VO>list(@PathVariable("bno") int bno) {
>     ResponseEntity<VO> entity = null;
>     try {
>         ...
>         entity = new ResponseEntity<VO>(vo, HttpStatus.OK);
>     } catch (Exception e) {
>         entity = new ResponseEntity<VO>(e.printStackTrace(), HttpStatus.BAD_REQUEST);
>     }
>     return entity;
> }
> ```

# Junit Test
pom.xml version 4.13으로 변경
## DB 연동 시 context를 읽지 못해 에러가 발생한다면 아래 2개의 context파일만 추가
```
@ContextConfiguration(
    locations={"file:src/main/resources/egovframework/spring/context-datasource.xml",
    //DataSource bean 위치
    "file:src/main/resources/egovframework/spring/context-mapper.xml"}
    //SqlSession bean 위치
)
```

# 페이징
```
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
PaginationInfo pageInfo = new PaginationInfo();

//required fileds
pageInfo.setCurrentPageNo(int); // 현재 페이지 번호
pageInfo.setRecordCountPerPage(int); // 한 페이지 당 게시되는 게시물 수
pageInfo.setPageSize(int); // 페이지 리스트에 게시되는 페이지의 수
pageInfo.setTotalRecordCount(int); // 전체 게시글 수

//not reauired fields
pageInfo.getPageCount(); // 페이지 개수
pageInfo.getFirstPageNoOnPageList(); // 페이지 리스트의 첫 페이지 번호
pageInfo.getLastPageNoOnPageList(); // 페이지 리스트의 마지막 페이지 번호
pageInfo.getFirstRecordIndex(); // 페이지에 게시될 첫번째 레코드 인덱스
pageInfo.getLastRecordIndex(); // 페이지에 게시될 마지막 레코드 인덱스
```

## 페이징 - 사용 예시
- Controller.java
> ```
> pageInfo.set...(...); // currentPageNo, recordCountPerPage, pageSize, totalRecordCount
> service.listPage("namespace.listPageId", pageInfo);
> ```
- Mapper.xml
> ```
> <!-- parameterType은 pageInfo 또는 hashMap 사용 -->
> <select id="namespace.listPageId" parameterType="pageInfo" resultType="vo">
>     select * from tbl
>     order by id desc
>     limit #{firstRecordIndex}, #{recordCountPerPage}
> </select>
> ```
## 페이징 UI
- jsp
```
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>

<div id="paging">
    <!-- ${pageInfo}는 Controller에서 전송된 값 -->
    <ui:pagination paginationInfo = "${pageInfo}" type="image" jsFunction="yourFunc();">
    <form:hidden path="pageIndex"/> <!-- pageIndex 빈을 사용하는 경우 -->
</div>

<script>
    function yourFunc(pageNo) {
        self.location="...url...";
    }
</script>

```
> dispatcher-servlet.xml의 paginationManager bean
> > property rendererType: Map
> > > ui:pagination tag의 type
> > > > image: imageRenderer
> > > > ```
> > > > <bean id=imageRenderer class="egovframework.example.sample.web.EgovImgPaginationRenderer"/>
> > > > ```
> > > 빈 설정이 없거나 type renderer가 없다면 두 클래스가 기본값으로 사용된다.
> > egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager
> > egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationRenderer

# Java에서 properties 파일 읽어오기
## @Configuration
> property 설정, 빈(singletone) 등록
```
@Configuration
public class PropertyConfig {
    @Bean(name="beanName")
	public PropertiesFactoryBean propertiesFactoryBean() throws Exception {
		PropertiesFactoryBean pfb = new PropertiesFactoryBean();
		ClassPathResource cpr = new ClassPathResource("property 파일 경로");
		//최상위 폴더: src/main/resources
		pfb.setLocation(cpr);
		return pfb;
	}
}
```
> 실제 사용
'''
@Value("#{beanName['propertyName']}")
private T varName;
'''

# Interceptor
> Spring interceptor lifecycle
> > request > filter > DispatcherServlet > HandlerInterceptor > Controller > view resolver > filter > response
## abstract class HandlerInterceptorAdapter, interface HandlerInterceptor
> org.springframework.web.servlet.handler.*;

> HandlerInterceptorAdapter는 egovframeword 4.0.0부터 spring 5.3 이상을 사용하여 제거됨
> spring 5.3 이상일 경우 implements HandlerInterceptor 사용

```
// Controller에서 동작하기 전 수행
public boolean preHandle(request, response, handler) throws Exception;

// Controller의 동작이 끝난 뒤 수행
public void postHandle(request, response, handler, modelAnndView) throws Exception;

// View Rendering까지 모두 끝난 후 수행
public void afterCompletion(request, response, handler, exception) throws Exception;
```
## dispatcher-servlet.xml 설정 정보 추가
```
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="url">
        <bean id="interceptor" class="Interceptor F.Q.N">
    </mvc:interceptor>
</mvc:interceptors>
```

# JSON
## pom.xml
```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.5.4</version>
</dependency>
```
## dispatcher-servlet.xml 설정 정보 추가
```
<proprety name="mappingJackson2HttpMessageConverter">
    <list>
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"/>
    </list>
</property>
```

# Property
## *.properties 파일
> K=V의 형태로 저장된 파일<br>
> bean으로 사용할 객체 생성
```
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertyConfig {

    @Bean(name="propertyConfigBean")
    public PropertiesFactoryBean propertiesFactoryBean() throws Exception {
        PropertiesFactoryBean pfb = new PropertiesFactoryBean();
        ClassPathResource cpr = new ClassPathResource("*.properties path");
        pfb.setLocation(cpr);
        return pfb;
    }
}
```
> 실제 사용 예시 - Java
```
@Value("#{pageConfigBean['K']}")
private String pageSize;
```
> 실제 사용 예시 - JSP
> > context-properties.xml에 *.properties 파일 경로 저장
```
<spring:message code="K"/>
```
## context-properties.xml 파일
> propertiesService properties에 아래 코드 추가
```
<entry key="K" value="V"/>
```
> 1. jsp에서 사용 시 아래처럼 사용
```
<spring:eval expression="@propertiesService.getString('K')" var="delButton"/>
<c:out value="${delButton}"/>
```
> 2. java에서 사용 시 아래처럼 사용
```
@Resource(name = "propertiesService")
protected EgovPropertyService propertiesService;

propertiesService.getString("K");
propertiesService.getInt("K");
propertiesService.getBoolean("K");
.
.
.
```

# Resource mapping 정적 자원 매핑
> dispatcher-servlet.xml
```
<mvc:resources location="" mapping="" />
/**
 * location: 실제 자원의 위치("src/main/webapp의 하위 경로")
 * mapping: url(root Path/url)
 */
```

# AJAX
```
$.ajax({
    type: "post",
    url: "/",
    contentType: "application/json", // 보내는 데이터의 타입
    dataType: "text", // 서버에서 받을 데이터의 타입
    data: JSON.stringify(data),
    success: function(data) {
        // dataType이 'text'일 경우 JSON.parse(data)
        // dataType: "json" 사용하자
    }, // 요청 성공
    error: function(data) {}, // 요청 실패
    complete: function(data) {}, // 모든 작업을 마친 후 실행
    beforeSend: function(data) {} // 요청 전 실행
    ...
});
```
## Callback 함수
```
$.ajax({
    AJAX 요청
}).done(function(data) {
    ...
    // 요청 성공 시 호출
}).fail(function(data) {
    ...
    // 요청 실패 시 호출
}).always(function(data) {
    ...
    //성공 유무와 상관 없이 항상 실행
});
```
> 실행 순서<br>
> - 성공시
> > success > complete > done > always
> - 실패시
> > error > coplete > fail > always

### done의 장점
$.ajax();의 반환 값이 애플리케이션의 다른 곳과 연결될 수 있는 지연된 promise 객체

### promise
```
let func = () => {
    let deferred = $.Deferred();
    try {
        //로직
        deferred.resolve("성공");
    } catch (err) {
        deferred.reject(err);
    }
    return deferred.promise();
};
func().done(function(msg) {
    console.log(msg);
}).fail(function(err) {
    console.error(err);
}).always(function() {
    console.log("완료");
});
// 또는
func().then(function(msg) {
    console.log(msg);
}, function(err) {
    console.error(err);
}).then(function() {
    console.log("완료");
});
// then(성공 시 콜백, 실패 시 콜백);
```