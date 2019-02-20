package au.com.airtasker.bffspring.configuration

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

@Configuration
class BffConfiguration @Autowired constructor(val localDateDeserializer: JsonDeserializer<LocalDate>) {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
            registerKotlinModule()
            val module = SimpleModule()
            module.addDeserializer(LocalDate::class.java, localDateDeserializer)
            registerModule(module)
        }
    }


    @Bean
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        val messageConverters = mutableListOf<HttpMessageConverter<*>>()
        val jsonMessageConverter = MappingJackson2HttpMessageConverter()
        jsonMessageConverter.objectMapper = objectMapper()
        messageConverters.add(jsonMessageConverter)
        restTemplate.messageConverters = messageConverters

        return restTemplate
    }
}

