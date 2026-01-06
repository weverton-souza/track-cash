package com.project.track.cash.configuration

class TenancyConstant {
    companion object {
        const val FLYWAY_PUBLIC_SCHEMA = "public"
        const val FLYWAY_PUBLIC_LOCATION = "classpath:db/migration/public"
        const val FLYWAY_TENANT_LOCATION = "classpath:db/migration/multi-tenant"

        const val TENANT_ID = "TENANT_ID"
        const val DEFAULT_TENANT = "public"
        const val USER_KEY = "X-User"
        const val AUTHORIZATION = "Authorization"
    }
}
