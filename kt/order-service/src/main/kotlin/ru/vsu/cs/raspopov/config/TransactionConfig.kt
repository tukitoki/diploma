package ru.vsu.cs.raspopov.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.transaction.PlatformTransactionManager
import ru.vsu.cs.raspopov.config.properties.DatabaseProperties

@Configuration
class TransactionConfig(
    private val databaseProperties: DatabaseProperties,
) {

    @Primary
    @Bean(name = ["transactionManager"])
    fun transactionManager(): PlatformTransactionManager =
        DataSourceTransactionManager().apply {
            DriverManagerDataSource().apply {
                this.url = databaseProperties.url
                this.password = databaseProperties.password
                this.username = databaseProperties.username
            }
        }
}