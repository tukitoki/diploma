package ru.vsu.cs.raspopov.coreservice.users.model.enums

enum class Authority(
    val description: String,
) {
    CLIENT("CLIENT"),
    EMPLOYEE("EMPLOYEE"),
    MANAGER("MANAGER"),
}