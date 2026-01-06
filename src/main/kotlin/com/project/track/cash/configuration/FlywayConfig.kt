package com.project.track.cash.configuration

import com.project.track.cash.configuration.TenancyConstant.Companion.FLYWAY_PUBLIC_LOCATION
import com.project.track.cash.configuration.TenancyConstant.Companion.FLYWAY_PUBLIC_SCHEMA
import com.project.track.cash.configuration.TenancyConstant.Companion.FLYWAY_TENANT_LOCATION
import com.project.track.cash.configuration.security.JwtSecurityFilter
import com.project.track.cash.domain.Seller
import com.project.track.cash.repository.SellerRepository
import com.project.track.cash.utils.TenantUtils
import java.util.function.Consumer
import javax.sql.DataSource
import org.flywaydb.core.Flyway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.flyway.autoconfigure.FlywayProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(FlywayProperties::class)
class FlywayConfig {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(FlywayConfig::class.java)
    }

    @Bean
    fun flyway(dataSource: DataSource): Flyway {
        val flyway: Flyway = Flyway.configure().locations(FLYWAY_PUBLIC_LOCATION)
            .dataSource(dataSource).schemas(FLYWAY_PUBLIC_SCHEMA)
            .load()
        flyway.migrate()

        return flyway
    }

    @Bean
    fun commandLineRunner(repository: SellerRepository, dataSource: DataSource): CommandLineRunner {
        return CommandLineRunner {
            repository.findAll()
                .forEach(
                    Consumer { seller: Seller ->
                        LOGGER.info(seller.name, seller.id.toString(), seller.tenantId.toString())
                        val flyway = Flyway.configure()
                            .locations(FLYWAY_TENANT_LOCATION, "db/migration/${TenantUtils.convertToTenantId(seller.tenantId.toString())}")
                            .dataSource(dataSource)
                            .baselineOnMigrate(true)
                            .schemas(TenantUtils.convertToTenantId(seller.tenantId.toString()))
                            .load()
                        flyway.migrate()
                    }
                )
        }
    }
}