package com.tomatokey.framework.configuration.jdbc.datasource;

import com.tomatokey.framework.configuration.ComponentOrder;
import com.tomatokey.framework.configuration.jdbc.annotation.TransactionalForUpd;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Order(ComponentOrder.SwitchingDataSourceAop)
@Aspect
@Component
public class SwitchingDataSourceAop {

    /**
     * {@link TransactionalForUpd}を設定した場合、更新用DataSourceに切り替えます
     *
     * @param pjp
     * @param tran
     * @return
     * @throws Throwable
     */
    @Around("@annotation(tran)")
    public Object switchingDataSource(ProceedingJoinPoint pjp, TransactionalForUpd tran) throws Throwable {
        try {
            MultiDataSourceContextHolder.setDataSourceType(DataSourceType.UPD);
            return pjp.proceed();
        } finally {
            MultiDataSourceContextHolder.removeContextHolder();
        }
    }

    /**
     * {@link Transactional}は検査例外発生時にrollbackされないため、
     * 使用している場合はエラーメッセージを表示します
     *
     * @param pjp
     * @param tran
     * @return
     * @throws Throwable
     */
    @Around("@annotation(tran)")
    public Object switchingForMethod(ProceedingJoinPoint pjp, Transactional tran) throws Throwable {
        throw new IllegalAccessException(String.format("@TransactionalForUpdを使用してください。使用箇所=[%s]", pjp.getSignature()));
    }

}
