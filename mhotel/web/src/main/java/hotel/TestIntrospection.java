package hotel;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import hotel.rest.CustomerResource;

public class TestIntrospection {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		CustomerResource cr = (CustomerResource)Class.forName("hotel.rest.CustomerResource").newInstance();
		Class customerResourceClass = cr.getClass();
		Annotation[] annon = customerResourceClass.getAnnotations();
		for(Annotation a : annon) {
			System.out.println("Class annotation:" + a);
		}
		Method[] meths = customerResourceClass.getDeclaredMethods();
		for(Method m : meths) {
			System.out.println("Class method:" + m);
			for(Annotation a : m.getAnnotations()) {
				System.out.println("Method annotation:" + a);

			}
			if("bau".equals(m.getName())) {
				System.out.println("Gasit metoda bau" + m);
				try {
					m.setAccessible(true);
					m.invoke(cr);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
