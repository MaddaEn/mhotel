package hotel.inj;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped @Named(value="pufi")
public class ServiceImpl1 implements ServiceIntf {

	@Override
	public String  doCeva() {
		return "ServiceImpl1 doceva";

	}

}
