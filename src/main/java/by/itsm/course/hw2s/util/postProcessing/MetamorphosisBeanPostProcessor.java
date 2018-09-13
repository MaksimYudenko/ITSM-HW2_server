package by.itsm.course.hw2s.util.postProcessing;

import by.itsm.course.hw2s.core.model.Response;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class MetamorphosisBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Object> beans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        boolean match = Arrays.stream(bean.getClass().getMethods())
                .anyMatch(m -> m.isAnnotationPresent(Metamorphosis.class));
        if (match) {
            beans.put(beanName, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beans.containsKey(beanName)) {
            Object original = beans.get(beanName);
            return Proxy.newProxyInstance(
                    original.getClass().getClassLoader(),
                    original.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        Object result = method.invoke(original, args);
                        if (method.isAnnotationPresent(Metamorphosis.class)
                                || method.getName().equalsIgnoreCase("metamorphosis")) {
                            Response res = (Response) result;
                            res.setMessage(res.getMessage() + " from proxy.");
                            result = res;
                        }
                        return result;
                    }
            );
        }
        return bean;
    }

}