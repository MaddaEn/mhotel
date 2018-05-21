package hotel.inj;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped @Named(value="fifi")
public class ServiceImpl2 implements ServiceIntf {

	@Override
	public String doCeva() {
		return "ServiceImpl2 DOALTCFEVA";
	}

}
