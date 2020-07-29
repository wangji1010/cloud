package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudAlibabaMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaMain9002.class,args);
    }
}
