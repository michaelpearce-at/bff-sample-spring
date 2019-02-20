package au.com.airtasker.bffspring.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class LocalDateDeserializer {
    @Bean
    fun bffLocalDateDeserializer(): JsonDeserializer<LocalDate> {
        return object : JsonDeserializer<LocalDate>() {
            override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDate {
                return p?.valueAsString?.let {
                    LocalDate.parse(it, DateTimeFormatter.ISO_DATE_TIME)
                } ?: throw IOException("value not available")
            }
        }
    }
}
