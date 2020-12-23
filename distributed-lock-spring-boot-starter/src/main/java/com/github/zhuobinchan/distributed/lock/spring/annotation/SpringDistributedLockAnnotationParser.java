package com.github.zhuobinchan.distributed.lock.spring.annotation;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @author zhuobin chan on 2020-12-22 19:04
 */
public class SpringDistributedLockAnnotationParser implements DistributedLockAnnotationParser {
    private final ExpressionParser parser = new SpelExpressionParser();
    private final ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    public String parseDistributedLockAnnotation(LockKeySpelView view) {
        Method method = view.getMethod();
        Object[] args = view.getArgs();
        String lockKeySpel = view.getLockKeySpel();
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        if (params != null) {
            for (int len = 0; len < params.length; len++) {
                context.setVariable(params[len], args[len]);
            }
        }
        Expression expression = parser.parseExpression(lockKeySpel);
        if (expression.isWritable(context)) {
            return expression.getValue(context, String.class);
        } else {
            return lockKeySpel;
        }
    }
}
