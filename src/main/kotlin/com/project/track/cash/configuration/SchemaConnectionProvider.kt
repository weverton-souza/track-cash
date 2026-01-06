package com.project.track.cash.configuration

import java.sql.Connection
import java.sql.SQLException
import javax.sql.DataSource
import org.hibernate.HibernateException
import org.hibernate.cfg.AvailableSettings
import org.hibernate.engine.config.spi.ConfigurationService
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.hibernate.service.spi.ServiceRegistryAwareService
import org.hibernate.service.spi.ServiceRegistryImplementor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class SchemaConnectionProvider : MultiTenantConnectionProvider<String>, ServiceRegistryAwareService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SchemaConnectionProvider::class.java.name)
        private const val SEARCH_PATH_TO_SCHEMA = "SET search_path to "
    }

    @Transient
    private var dataSource: DataSource? = null

    override fun getAnyConnection(): Connection = dataSource!!.connection

    override fun releaseAnyConnection(connection: Connection) = connection.close()

    override fun getConnection(tenantIdentifier: String): Connection {
        val connection = this.anyConnection

        try {
            if (tenantIdentifier.matches("^_[0-9a-f]{32}$".toRegex())) {
                connection.createStatement().execute("""$SEARCH_PATH_TO_SCHEMA $tenantIdentifier""".trim())
                LOGGER.debug("class=SchemaConnectionProvider, method=getConnection, message=Schema set: schema={}", tenantIdentifier)
            }
        } catch (e: SQLException) {
            LOGGER.error(
                "class=SchemaConnectionProvider, method=getConnection, message=Failed to set schema: schema={}, error={}",
                tenantIdentifier, e.message
            )
            throw HibernateException("Could not alter JDBC connection to schema [$tenantIdentifier]", e)
        }
        return connection
    }

    override fun releaseConnection(tenantIdentifier: String, connection: Connection) {
        try {
            connection.createStatement().execute("SET search_path to public")
            LOGGER.debug(
                "class=SchemaConnectionProvider, method=releaseConnection, message=Connection released: schema={}",
                tenantIdentifier
            )
        } catch (e: SQLException) {
            LOGGER.error(
                "class=SchemaConnectionProvider, method=releaseConnection, message=Failed to release: schema={}, error={}",
                tenantIdentifier, e.message
            )
            throw HibernateException("Could not alter JDBC connection to schema [$tenantIdentifier]", e)
        }
        connection.close()
    }

    override fun injectServices(serviceRegistry: ServiceRegistryImplementor) {
        dataSource = serviceRegistry
            .getService(ConfigurationService::class.java)
            ?.settings
            ?.get(AvailableSettings.JAKARTA_NON_JTA_DATASOURCE) as DataSource?

        LOGGER.trace(
            "class=SchemaConnectionProvider, method=injectServices, message=Services injected: hasDataSource={}",
            dataSource != null
        )
    }

    override fun isUnwrappableAs(unwrapType: Class<*>): Boolean = false

    override fun <T : Any?> unwrap(unwrapType: Class<T>): T? = null

    override fun supportsAggressiveRelease(): Boolean = true
}