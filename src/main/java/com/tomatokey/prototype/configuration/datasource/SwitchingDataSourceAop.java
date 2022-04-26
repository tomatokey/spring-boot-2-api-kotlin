package com.tomatokey.prototype.configuration.datasource;

import com.tomatokey.prototype.configuration.ComponentOrder;
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

    @Around("@annotation(tran)")
    public Object switchingForMethod(ProceedingJoinPoint pjp, Transactional tran) throws Throwable {
        try {
            final DataSourceType dataSourceType = tran.readOnly() ? DataSourceType.REF : DataSourceType.UPD;
            MultiDataSourceContextHolder.setDataSourceType(dataSourceType);
            return pjp.proceed();
        } finally {
            MultiDataSourceContextHolder.removeContextHolder();
        }
    }

}
