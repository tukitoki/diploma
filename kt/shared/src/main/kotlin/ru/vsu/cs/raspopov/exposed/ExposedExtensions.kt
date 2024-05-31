package ru.vsu.cs.raspopov.exposed

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.andIfNotNull
import org.jetbrains.exposed.sql.selectAll

fun SizedIterable<*>.exists() = this.empty().not()

fun <T : Comparable<T>> IdTable<T>.findThrowableById(
    id: T,
    ex: Throwable,
    predicate: Op<Boolean>? = null,
) = findByPredicate(this@findThrowableById.id.eq(id).andIfNotNull { predicate }) ?: throw ex

inline fun IdTable<*>.findThrowableByPredicate(
    ex: Throwable,
    crossinline where: SqlExpressionBuilder.() -> Op<Boolean>,
) = findByPredicate(SqlExpressionBuilder.where()) ?: throw ex

inline fun IdTable<*>.findByPredicate(
    crossinline where: SqlExpressionBuilder.() -> Op<Boolean>,
) = findByPredicate(SqlExpressionBuilder.where())

fun IdTable<*>.findByPredicate(
    where: Op<Boolean>,
) = this.selectAll()
    .where { where }
    .firstOrNull()