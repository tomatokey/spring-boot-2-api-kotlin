package com.prototype.framework.configuration.jdbc.datasource

import com.prototype.framework.configuration.ComponentOrder
import com.prototype.framework.configuration.jdbc.annotation.TransactionalForUpd
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Order(ComponentOrder.SwitchingDataSourceAop)
@Aspect
@Component
class SwitchingDataSourceAop {
    /**
     * [TransactionalForUpd]を設定した場合、更新用DataSourceに切り替えます
     *
     * @param pjp
     * @param tran
     * @return
     * @throws Throwable
     */
    @Around("@annotation(tran)")
    @Throws(Throwable::class)
    fun switchingDataSource(pjp: ProceedingJoinPoint, tran: TransactionalForUpd): Any {
        return try {
            MultiDataSourceContextHolder.dataSourceType = DataSourceType.UPD
            pjp.proceed()
        } finally {
            MultiDataSourceContextHolder.removeContextHolder()
        }
    }

    /**
     * [Transactional]は検査例外発生時にrollbackされないため、
     * 使用している場合はエラーメッセージを表示します
     *
     * @param pjp
     * @param tran
     * @return
     * @throws Throwable
     */
    @Around("@annotation(tran)")
    @Throws(Throwable::class)
    fun switchingForMethod(pjp: ProceedingJoinPoint, tran: Transactional): Any {
        throw IllegalAccessException(String.format("@TransactionalForUpdを使用してください。使用箇所=[%s]", pjp.signature))
    }
}