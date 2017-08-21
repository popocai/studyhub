/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package cloud.simple.hello;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
public class App extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //super.configureMessageConverters(converters);

        /*
         * 1. 定义一个converter转换消息的对象
         * 2. 添加fastjson的配置信息
         * 3. 在convert中添加配置信息
         * 4. 将converter添加到converters当中
         */

        /*

        //1
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        
        //2
        FastJsonConfig fjConf = new FastJsonConfig();
        fjConf.setSerializerFeatures(SerializerFeature.PrettyFormat);
        
        //3
        converter.setFastJsonConfig(fjConf);
        
        //4
        converters.add(converter);
        */
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public HttpMessageConverters fastJsonConverter() {
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fjConf = new FastJsonConfig();

        fjConf.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fjConf);
        HttpMessageConverter<?> converter = fastJsonConverter;
        return new HttpMessageConverters(converter);

    }

}
