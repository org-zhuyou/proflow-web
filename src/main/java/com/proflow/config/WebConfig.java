package com.proflow.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Leonid on 2018/7/3.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**");
    }

    @Bean
    public HttpMessageConverter<?> fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON_UTF8}));
        converter.getFastJsonConfig().setSerializerFeatures(new SerializerFeature[]{SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.DisableCircularReferenceDetect});
        ValueFilter valueFilter = new ValueFilter() {
            //o 是class
            //s 是key值
            //o1 是value值
            @Override
            public Object process(Object o, String s, Object o1) {
                Object obj = null;
                obj = o1 instanceof Long ? o1.toString() : o1;
                // 添加将所有int转成字符串
                obj = o1 instanceof Integer ? o1.toString() : obj;
                // 添加将所有BigDecimal转换成字符串
                if (o1 instanceof BigDecimal && s.toUpperCase().indexOf("AMOUNT") != -1) {
                    DecimalFormat df = new DecimalFormat("0.00");
                    return df.format(obj);
                }
                // 所有参数名中  含有passwd的都不序列化
                if (s.toUpperCase().indexOf("PASSW") != -1) {
                    return "******";
                }

                return obj;
            }
        };
        converter.getFastJsonConfig().setSerializeFilters(valueFilter);
        return converter;
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(this.fastJsonHttpMessageConverter());
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor());
    }
}
