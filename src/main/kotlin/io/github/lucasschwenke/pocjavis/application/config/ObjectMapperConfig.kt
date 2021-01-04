package io.github.lucasschwenke.pocjavis.application.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class ObjectMapperConfig {

    @Bean
    fun configureObjectMapper(): ObjectMapper = jacksonObjectMapper().let {
        it.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        it.registerKotlinModule()
        it.registerModule(createDateModule())
        it.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }

    private fun createDateModule(): SimpleModule = with(SimpleModule()) {
        addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
        addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())
        addSerializer(LocalDate::class.java, LocalDateSerializer())
        addDeserializer(LocalDate::class.java, LocalDateDeserializer())
    }
}

class LocalDateTimeSerializer : StdSerializer<LocalDateTime>(LocalDateTime::class.java) {

    override fun serialize(value: LocalDateTime, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.toIsoLocalDateTime())
    }
}

class LocalDateTimeDeserializer : StdDeserializer<LocalDateTime>(LocalDateTime::class.java) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDateTime =
        p.readValueAs(String::class.java).toLocalDateTime()
}

class LocalDateSerializer : StdSerializer<LocalDate>(LocalDate::class.java) {

    override fun serialize(value: LocalDate, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.toIsoLocalDate())
    }
}

class LocalDateDeserializer : StdDeserializer<LocalDate>(LocalDate::class.java) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDate =
        p.readValueAs(String::class.java).toLocalDate()
}

fun LocalDateTime.toIsoLocalDateTime(): String = this.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
fun LocalDate.toIsoLocalDate(): String = this.format(DateTimeFormatter.ISO_LOCAL_DATE)

fun String.toLocalDateTime(): LocalDateTime = LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
fun String.toLocalDate(): LocalDate = LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)