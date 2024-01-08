package ru.vsu.cs.raspopov.coreservice.users.common.exposed

import org.jetbrains.exposed.sql.SizedIterable

fun SizedIterable<*>.exists() = this.empty().not()