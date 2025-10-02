package com.example.scheduler

import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit

@SpringBootApplication
class SchedulerApplication {


    @Bean
    fun methodToolCallbackProvider(dogAdoptionScheduler: DogAdoptionScheduler) =
        MethodToolCallbackProvider
            .builder()
            .toolObjects(dogAdoptionScheduler)
            .build()
}

fun main(args: Array<String>) {
    runApplication<SchedulerApplication>(*args)
}


@Component
class DogAdoptionScheduler {

    @Tool(description = "schedule an appointment to pick up or adopt a dog from a Pooch Palace location")
    fun schedule(
        @ToolParam(description = "the id of the dog") dogId: Int,
        @ToolParam(description = "the name of the dog") dogName: String?
    ): String {
        val i = Instant
            .now()
            .plus(3, ChronoUnit.DAYS)
            .toString()
        println("scheduling $dogId/$dogName for $i")
        return i
    }
}
