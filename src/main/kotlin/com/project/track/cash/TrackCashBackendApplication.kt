package com.project.track.cash

import com.project.track.cash.configuration.properties.SecurityProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.web.config.EnableSpringDataWebSupport

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.project.*"])
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@EnableConfigurationProperties(value = [SecurityProperties::class])
class TrackCashBackendApplication

fun main(args: Array<String>) {
    runApplication<TrackCashBackendApplication>(*args)
}
