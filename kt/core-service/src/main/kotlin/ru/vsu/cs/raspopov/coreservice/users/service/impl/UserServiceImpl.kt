package ru.vsu.cs.raspopov.coreservice.users.service.impl

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.neq
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.coreservice.users.common.exception.ExceptionCode.AUTH_FAILED
import ru.vsu.cs.raspopov.coreservice.users.common.exception.GeneralException
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UpdatePasswordRequest
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UserAuthRequest
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UserDto
import ru.vsu.cs.raspopov.coreservice.users.model.entity.User
import ru.vsu.cs.raspopov.coreservice.users.model.enums.UserStatus
import ru.vsu.cs.raspopov.coreservice.users.model.table.Users
import ru.vsu.cs.raspopov.coreservice.users.service.UserService
import ru.vsu.cs.raspopov.exposed.exists
import java.time.LocalDateTime

@Transactional
@Service
class UserServiceImpl(
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    private val passwordEncoder: BCryptPasswordEncoder,
) : UserService {

    override fun getUserById(id: Long) = throwableFindEntityById(id).toDto()

    override fun getUserByToken(token: String): UserDto {
        TODO("Not yet implemented")
    }

    override fun createUser(userDto: UserDto): UserDto {
        validateUser(userDto)

        val u = User.new {
            this.username = userDto.username
            this.password = passwordEncoder.encode(userDto.password)
            this.email = userDto.email
            this.phone = userDto.phone
            this.status = userDto.status
            this.role = userDto.role
        }

        return u.toDto()
    }

    override fun authentication(request: UserAuthRequest): UserDto {
        val user = User.find {
            Users.username eq request.username
        }.singleOrNull() ?: throw GeneralException(AUTH_FAILED)

        val matchingResult = passwordEncoder.matches(request.password, user.password)

        if (matchingResult.not())
            throw GeneralException(AUTH_FAILED)

        return user.toDto()
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
            this.role = userDto.role
            this.updatedAt = LocalDateTime.now()
        }

        return updatableUser.toDto()
    }

    override fun updateUserPasswordById(id: Long, request: UpdatePasswordRequest): UserDto {
        val updatableUser = throwableFindEntityById(id)

        updatableUser.password = passwordEncoder.encode(request.password)

        return updatableUser.toDto()
    }

    override fun changeUserStatusById(id: Long, status: UserStatus): UserDto {
        val updatableUser = throwableFindEntityById(id)

        updatableUser.status = status

        return updatableUser.toDto()
    }

    private fun findEntityById(id: Long) = Users
        .selectAll()
        .where { Users.id eq id }
        .let { User.wrapRows(it) }
        .singleOrNull()

    private fun throwableFindEntityById(id: Long) = findEntityById(id) ?: throw GeneralException("No user exist")

    private fun validateUser(userDto: UserDto) {
        val neqUserId: Expression<Boolean> = Users.id neq userDto.userId
        val takenPart = when {
            User.find {
                Users.username
                    .eq(userDto.username)
                    .and(neqUserId)
            }.exists() -> "username"
            User.find {
                Users.email
                    .eq(userDto.email)
                    .and(neqUserId)
            }.exists() -> "email"
            User.find {
                Users.phone
                    .eq(userDto.phone)
                    .and(neqUserId)
            }.exists() -> "phone number"
            else -> null
        }

        if (takenPart != null)
            throw GeneralException("This $takenPart is already taken")
    }
}