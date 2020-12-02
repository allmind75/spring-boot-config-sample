package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.whiteship.Holoman;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

//@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws LifecycleException, IOException {
//        SpringApplication.run(DemoApplication.class, args);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        /**
         * windows 환경에서는 아래와 같이 docBase 설정 필요
         */
        String docBase = Files.createTempDirectory("tomcat-basedir").toString();
        System.out.println(docBase);
        Context context = tomcat.addContext("/", docBase);

        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
                PrintWriter writer = resp.getWriter();
                writer.println("<html><head><title>");
                writer.println("Hey, Tomcat");
                writer.println("</title></head>");
                writer.println("<body><h1>Hello Tomcat</h1></body>");
                writer.println("</html>");
            }
        };
        String servletName = "helloServlet";
        tomcat.addServlet("/", servletName, servlet);
        context.addServletMappingDecoded("/hello", servletName);

        /**
         * Spring boot version 변경으로 tomcat 9 로 올라가면서
         * getConnector를 자동 추가하지 않으므로 아래 코드 추가
         * https://tomcat.apache.org/tomcat-9.0-doc/api/org/apache/catalina/startup/Tomcat.html
         */
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }


    @Bean
    public Holoman holoman() {
        Holoman holoman = new Holoman();
        holoman.setName("whiteship");
        holoman.setHowLong(60);
        return holoman;
    }

}
