package com.project.track.cash.enums

enum class UserType(val description: String, val hasAdminAccess: Boolean) {
    OWNER("Proprietário ou sócio do negócio", true),
    FINANCIAL_MANAGER("Gestor financeiro", true),
    FINANCIAL_ANALYST("Analista financeiro", false),
    ACCOUNTANT("Contador", false),
    FISCAL_ASSISTANT("Auxiliar fiscal", false),
    ADMINISTRATIVE("Administrativo", false),
    VIEWER("Acesso somente leitura", false),
    SYSTEM_ADMIN("Administrador do sistema", true);
}
