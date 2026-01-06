package com.project.track.cash.utils

class TenantUtils {
    companion object {
        fun convertToTenantId(tenantId: String): String {
            return if (tenantId == "public") {
                "public"
            } else {
                "_${tenantId.replace("-", "")}"
            }
        }
    }
}
