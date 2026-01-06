package com.project.track.cash.configuration

import com.project.track.cash.utils.Context
import com.project.track.cash.utils.ContextHolder
import com.project.track.cash.utils.toPlainString
import java.util.Objects.nonNull
import java.util.UUID
import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.stereotype.Component

@Component
class ContextTenantIdentifierResolver : CurrentTenantIdentifierResolver<String> {

    override fun resolveCurrentTenantIdentifier(): String {
        val context: Context? = ContextHolder.context

        if (context != null && nonNull(context.getTenantId()) && nonNull(context.getUserId())) {
            return toSchemaString(context.getTenantId()!!)
        }

        return TenancyConstant.DEFAULT_TENANT
    }

    private fun toSchemaString(tenantId: UUID): String = "_" + tenantId.toPlainString()

    override fun validateExistingCurrentSessions(): Boolean = true
}
