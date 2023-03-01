package ru.store.store_rest.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.springframework.stereotype.Component
import ru.store.store_rest.model.RequestResponse

@Aspect
@Component
class LogAspectService(private val logAspectGeneral: LogAspectGeneral) {

    @Pointcut("@annotation(ru.store.store_rest.annotation.Log)")
    fun aspectService() {}

    @Before("aspectService()")
    fun beforeLogService(jp: JoinPoint) {
        val requestResponse = jp.args.joinToString(prefix = "[", postfix = "]")
        logAspectGeneral.log(jp, RequestResponse.REQUEST, requestResponse)
    }

    @AfterReturning(pointcut = "aspectService()", returning = "response")
    fun afterOkLogService(jp: JoinPoint, response: Any) {
        val requestResponse = if (response is Collection<*>) "size ${response.size}" else response.toString()
        logAspectGeneral.log(jp, RequestResponse.RESPONSE, requestResponse)
    }

    @AfterThrowing(pointcut = "aspectService()", throwing = "exception")
    fun afterErrorLogService(jp: JoinPoint, exception: Exception) {
        logAspectGeneral.logError(jp, exception)
    }
}

