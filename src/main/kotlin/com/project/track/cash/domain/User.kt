package com.project.track.cash.domain


import com.project.track.cash.enums.AccountStatus
import com.project.track.cash.enums.UserType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID
import org.hibernate.annotations.SQLDelete
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "user", schema = "public")
@SQLDelete(sql = "UPDATE \"user\" set deleted = true where id = :id")
data class User(
    @Id
    @field:Column
    override var id: UUID = UUID.randomUUID(),

    @field:Column
    var name: String = "",

    @field:Column
    var email: String = "",

    @field:Column
    private var password: String = "",

    @Enumerated(EnumType.STRING)
    @field:Column
    var type: UserType = UserType.OWNER,

    @Enumerated(EnumType.STRING)
    @field:Column
    var status: AccountStatus = AccountStatus.PENDING_APPROVAL,

    @field:Column
    var verified: Boolean = false,

    @field:Column
    var sellerTenantId: UUID? = null,

    ) : AbstractEntity(id = id), UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority("ROLE_$type"))

    fun authoritiesAsStringList(): MutableList<String> = mutableListOf("ROLE_$type")

    override fun getPassword(): String = this.password

    override fun getUsername(): String = this.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}