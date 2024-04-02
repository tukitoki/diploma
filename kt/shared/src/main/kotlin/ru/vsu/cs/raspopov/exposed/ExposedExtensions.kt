package ru.vsu.cs.raspopov.exposed

import org.jetbrains.exposed.sql.SizedIterable

fun SizedIterable<*>.exists() = this.empty().not()