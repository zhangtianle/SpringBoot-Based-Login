## 基于Spring Boot的登录页面

本项目基于Spring Boot框架，搭建了一个简单的登录微服务。  

Spring Boot相对于传统的SSM(Spring MVC + Mybatis + Spring)框架用起来更加简单，不需要进行复杂的配置，方便灵活。  

Spring Boot让我们的Spring应用变的更轻量化。比如：你可以仅仅依靠一个Java类来运行一个Spring应用。你也可以打包你的应用为jar并通过使用java -jar来运行你的Spring Web应用。  

使用Spring Boot可以很方便的建立微服务。  


### 效果图
[项目测试地址](http://tianle.me:8080)：http://tianle.me:8080  

![效果图](https://img.tianle.me/image/20180502/1.png)  

### 应用技术
Spring Boot + bootstrap + thymeleaf

### 项目搭建

使用Intellij中的Spring Initializr来快速构建Spring Boot  

菜单栏中选择File=>New=>Project..  

一直点下一步  

![p1](https://img.tianle.me/image/20180502/p1.png)  
![p2](https://img.tianle.me/image/20180502/p2.png)  
![p3](https://img.tianle.me/image/20180502/p3.png)  
![p4](https://img.tianle.me/image/20180502/p4.png)  

最后点击Finish  

联网自动从网站上下载Spring Boot的模板，稍作等待框架就搭好啦。  

#### 项目目录结构  
上面步骤中的项目名字和这个截图有点不一样(login)  
src/main/java/ 为代码文件  
src/main/resources/ 为资源文件  

为了保证项目资源结构的清晰，我们把 src/main/java/ 再进一步进行划分：  
bean 目录存放的是要用到的实体类  
controller 目录存放的是控制层类  

src/main/resources/template/ 为静态页面的模板文件，这里用了thymeleaf模板渲染引擎框架（据说Spring Boot推荐）  
src/main/resources/application.properties 为Spring Boot的配置文件  
![p5](https://img.tianle.me/image/20180502/p5.png)  

#### maven配置  
我们做的是Java web项目，在其默认生成的maven配置文件中添加web和thymeleaf依赖。  
pom.xml  
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

#### application.properties配置  

这里我们配置 thymeleaf模板渲染引擎  
```
# Enable template caching.
spring.thymeleaf.cache=true
# Check that the templates location exists.
spring.thymeleaf.check-template-location=true
# Content-Type value.
spring.thymeleaf.servlet.content-type=text/html
# Enable MVC Thymeleaf view resolution.
spring.thymeleaf.enabled=true
# Template encoding.
spring.thymeleaf.encoding=UTF-8
# Comma-separated list of view names that should be excluded from resolution.
spring.thymeleaf.excluded-view-names=
# Template mode to be applied to templates. See also StandardTemplateModeHandlers.
spring.thymeleaf.mode=HTML
# Prefix that gets prepended to view names when building a URL.
spring.thymeleaf.prefix=classpath:/templates/
# Suffix that gets appended to view names when building a URL.
spring.thymeleaf.suffix=.html
```

其余的Spring Boot属性配置文件参考(本项目没有配置，使用的默认)：    
[Spring Boot属性配置文件详解](http://blog.didispace.com/springbootproperties/)  

#### Bean  
本项目为用户登录，只考虑用户这一个角色，包含用户名和密码。  
```java
package me.tianle.login.bean;

public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

```

#### Controller   
web项目的控制器写在这里，处理页面的请求，前后台交互  


@Controller：修饰class，用来创建处理http请求的对象  
@RestController：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。  
@RequestMapping：配置url映射  

目前版本没有添加数据库，能否登录判断逻辑直接写死在代码中。    

```java
package me.tianle.login.controller;

import me.tianle.login.bean.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @RequestMapping("/")
    public ModelAndView index(ModelMap map) {
        map.addAttribute("host", "https://tianle.me");
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        String name = user.getName();
        String password = user.getPassword();

        if (name.equals("qinya") && password.equals("tianle")) {
            return "Success";
        } else {
            return "Failed";
        }
    }
}

```

#### 项目打包运行  
使用maven进行打包。  
```
mvn install
```
Spring Boot里面嵌入了Tomcat，直接运行  
```
java -jar xxx.jar
```
![p6](https://img.tianle.me/image/20180502/p6.png)  
浏览器输入: http://localhost:8080  
账号：qinya  
密码：tianle  


### 参考来源  
[Spring Boot基础教程](http://blog.didispace.com/Spring-Boot%E5%9F%BA%E7%A1%80%E6%95%99%E7%A8%8B/)  
[基于Bootstrap的简洁登录界面设计效果](http://www.htmleaf.com/css3/ui-design/201610114094.html)  