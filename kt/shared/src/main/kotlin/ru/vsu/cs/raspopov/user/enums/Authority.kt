package ru.vsu.cs.raspopov.user.enums

enum class Authority(
    val description: String,
) {
    CLIENT("CLIENT"),
    EMPLOYEE("EMPLOYEE"),
    MANAGER("MANAGER"),
}