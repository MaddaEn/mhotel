package hotel.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rs")
public class RestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
		Set<Class<?>> clzz = new HashSet<>();
		clzz.add(CustomerResource.class);
		// ADD OTHER RESOURCES
		

    	return clzz;
    }
}
