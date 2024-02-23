//package ru.vsu.cs.raspopov.authservice.service.impl
//
//import org.springframework.data.redis.core.RedisTemplate
//import org.springframework.stereotype.Service
//import ru.vsu.cs.raspopov.authservice.model.redis.AccessToken
//import ru.vsu.cs.raspopov.authservice.model.redis.RefreshToken
//import java.time.Duration
//import java.util.*
//
//@Service
//class TokenRedisService(
////    private val accessRedisTemplate: RedisTemplate<UUID, AccessToken>,
////    private val refreshRedisTemplate: RedisTemplate<UUID, RefreshToken>,
//
////    private val redisAccessTokenRepo: RedisAccessTokenRepo,
////    private val redisRefreshTokenRepo: RedisRefreshTokenRepo,
//) {
//
//    fun storeAccessToken(accessToken: AccessToken) {
//        redisAccessTokenRepo.save(accessToken)
//
//
//    }
//
////    fun getAccessTokenByRefreshJti(
////        refreshJti: UUID
////    ) = accessRedisTemplate.opsForValue().get(refreshJti)
//
//    fun storeRefreshToken(refreshToken: RefreshToken) {
//        redisRefreshTokenRepo.save(refreshToken)
//
////        refreshRedisTemplate.opsForValue().set(
////            refreshToken.jti,
////            refreshToken,
////            refreshToken.ttl
////        )
//    }
//
////    fun getRefreshToken(
////        jti: UUID
////    ) = accessRedisTemplate.opsForValue().get(jti)
////
////    fun clearRefreshTokenByJti(jti: UUID) {
////        accessRedisTemplate.opsForValue().getAndDelete(jti)
////    }
//}