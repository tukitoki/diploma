package ru.vsu.cs.raspopov.coreservice.users.service.impl

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.coreservice.users.exception.GeneralException
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UpdatePasswordRequest
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UserDto
import ru.vsu.cs.raspopov.coreservice.users.model.entity.User
import ru.vsu.cs.raspopov.coreservice.users.model.enums.UserStatus
import ru.vsu.cs.raspopov.coreservice.users.model.table.Users
import ru.vsu.cs.raspopov.coreservice.users.service.UserService
import java.time.LocalDateTime

@Transactional
@Service
class UserServiceImpl : UserService {

    override fun getUserById(id: Long) = throwableFindEntityById(id).toDto()

    override fun createUser(userDto: UserDto): UserDto {
        validateUser(userDto)

        val u = User.new {
            this.username = userDto.username
            this.password = userDto.password
            this.email = userDto.email
            this.phone = userDto.phone
            this.status = userDto.status
        }

        return u.toDto()
    }

    override fun deleteUserById(id: Long) {
        val deletedUser = throwableFindEntityById(id)

        deletedUser.delete()
    }

    override fun updateUserById(id: Long, userDto: UserDto): UserDto {
        validateUser(userDto)

        val updatableUser = throwableFindEntityById(id)

        updatableUser.apply {
            this.username = userDto.username
            this.email = userDto.email
            this.phone = userDto.phone
            this.updatedAt = LocalDateTime.now()
        }

        return updatableUser.toDto()
    }

    override fun updateUserPasswordById(id: Long, request: UpdatePasswordRequest): UserDto {
        val updatableUser = throwableFindEntityById(id)

        updatableUser.password = request.password

        return updatableUser.toDto()
    }

    override fun changeUserStatusById(id: Long, status: UserStatus): UserDto {
        val updatableUser = throwableFindEntityById(id)

        updatableUser.status = status

        return updatableUser.toDto()
    }

    private fun findEntityById(id: Long) = Users
        .select { Users.id eq id }
        .let { User.wrapRows(it) }
        .singleOrNull()

    private fun throwableFindEntityById(id: Long) = findEntityById(id) ?: throw GeneralException("No user exist")

    private fun validateUser(userDto: UserDto) {
        if (User.find {
                Users.username
                    .eq(userDto.username)
                    .and(Users.id neq userDto.userId)
            }.empty()) throw GeneralException("This username is already taken")

        if (User.find {
                Users.email
                    .eq(userDto.email)
                    .and(Users.id neq userDto.userId)
            }.empty()) throw GeneralException("This email is already taken")

        if (User.find {
                Users.phone
                    .eq(userDto.phone)
                    .and(Users.id neq userDto.userId)
            }.empty()) throw GeneralException("This phone number is already taken")
    }
}