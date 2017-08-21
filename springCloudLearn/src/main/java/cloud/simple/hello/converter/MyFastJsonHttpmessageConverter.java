/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package cloud.simple.hello.converter;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

public class MyFastJsonHttpmessageConverter {
    public HttpMessageConverters fastJsonConverter() {
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fjConf = new FastJsonConfig();

        fjConf.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fjConf);
        HttpMessageConverter<?> converter = fastJsonConverter;

        return new HttpMessageConverters(converter);

    }
}
