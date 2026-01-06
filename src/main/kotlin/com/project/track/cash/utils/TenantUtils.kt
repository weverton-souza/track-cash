package com.project.track.cash.utils

class TenantUtils {
    companion object {

        private const val INSTITUTION_TENANT_ID_TENANT_FILE_NAME = "institution_tenant_id.txt"

        fun convertToTenantId(tenantId: String): String {
            return if (tenantId == "public") {
                "public"
            } else {
                "_${tenantId.replace("-", "")}"
            }
        }
    }
}
