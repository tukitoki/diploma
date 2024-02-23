package ru.vsu.cs.raspopov.coreservice.users.model.enums

enum class Role(
    val id: Long,
    val authority: Authority,
) {

    CLIENT(1, Authority.CLIENT),
    EMPLOYEE(2, Authority.EMPLOYEE),
    MANAGER(3, Authority.MANAGER),
    ;
}