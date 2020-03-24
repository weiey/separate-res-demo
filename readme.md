##分离lib,resources打包
效果图 
![效果图](img/效果图1.png "效果图")
运行脚本 
```java -jar -Dloader.path=resources,lib  demo.jar ```
[项目地址：github]


``` <!-- 分离lib -->
<build>
         <finalName>${project.artifactId}</finalName>
         <defaultGoal>compile</defaultGoal>
         <plugins>
             <plugin>
                 <groupId>org.apache.maven.plugins</groupId>
                 <artifactId>maven-compiler-plugin</artifactId>
                 <version>3.1</version>
                 <configuration>
                     <encoding>UTF-8</encoding>
                 </configuration>
             </plugin>
 
 
             <!-- 分离lib -->
             <plugin>
                 <groupId>org.apache.maven.plugins</groupId>
                 <artifactId>maven-dependency-plugin</artifactId>
                 <executions>
                     <execution>
                         <id>copy-dependencies</id>
                         <phase>package</phase>
                         <goals>
                             <goal>copy-dependencies</goal>
                         </goals>
                         <configuration>
                             <!-- 依赖包输出目录，将来不打进jar包里 -->
                             <outputDirectory>${project.build.directory}/lib</outputDirectory>
                             <excludeTransitive>false</excludeTransitive>
                             <stripVersion>false</stripVersion>
                             <includeScope>runtime</includeScope>
                         </configuration>
                     </execution>
                 </executions>
             </plugin>
 
 
             <plugin>
                 <groupId>org.apache.maven.plugins</groupId>
                 <artifactId>maven-jar-plugin</artifactId>
                 <configuration>
                     <archive>
                         <!-- 指定配置文件目录，这样jar运行时会去找到同目录下的resources文件夹下查找 -->
                         <manifestEntries>
                             <Class-Path>resources/</Class-Path>
                         </manifestEntries>
                     </archive>
                     <!-- 打包时忽略的文件（也就是不打进jar包里的文件） -->
                     <excludes>
                         <exclude>static/**</exclude>
                         <exclude>templates/**</exclude>
                         <exclude>*.yml</exclude>
                         <exclude>*.properties</exclude>
                         <exclude>*.xml</exclude>
                         <exclude>*.txt</exclude>
                     </excludes>
                 </configuration>
             </plugin>
             <!-- copy资源文件 -->
             <plugin>
                 <artifactId>maven-resources-plugin</artifactId>
                 <executions>
                     <execution>
                         <id>copy-resources</id>
                         <phase>package</phase>
                         <goals>
                             <goal>copy-resources</goal>
                         </goals>
                         <configuration>
                             <resources>
                                 <resource>
 
                                     <directory>src/main/resources</directory>
 
                                 </resource>
                             </resources>
                             <outputDirectory>${project.build.directory}/resources</outputDirectory>
                         </configuration>
                     </execution>
                 </executions>
             </plugin>
 
             <plugin>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-maven-plugin</artifactId>
                 <version>2.0.3.RELEASE</version>
 
                 <configuration>
                     <fork>true</fork>
                     <mainClass>com.example.demo.DemoApplication</mainClass>
                     <!-- 解压出lib文件夹 -->
                     <layout>ZIP</layout>
                     <includes>
                         <include>
                             <groupId>nothing</groupId>
                             <artifactId>nothing</artifactId>
                         </include>
                     </includes>
                 </configuration>
                 <executions>
                     <execution>
                         <goals>
                             <goal>repackage</goal>
                         </goals>
                     </execution>
                 </executions>
 
             </plugin>
         </plugins>
     </build>```
