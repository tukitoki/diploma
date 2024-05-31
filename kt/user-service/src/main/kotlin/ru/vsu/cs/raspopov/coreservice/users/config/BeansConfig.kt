package ru.vsu.cs.raspopov.coreservice.users.config

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class BeansConfig : ApplicationContextInitializer<GenericApplicationContext> {

    private fun beans() = beans {
        bean {
            BCryptPasswordEncoder()
        }
    }

    override fun initialize(applicationContext: GenericApplicationContext) {
        beans().initialize(applicationContext)
    }
}