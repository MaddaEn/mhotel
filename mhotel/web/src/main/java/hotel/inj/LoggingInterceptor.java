package hotel.inj;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Logged
public class LoggingInterceptor {
	@AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        System.out.println("##################Entering method: " + ctx.getMethod().getName());
        //or logger.info statement 
        Object result = ctx.proceed();
        System.out.println("##################Exiting method: " + ctx.getMethod().getName());
        return result;
    }
}
