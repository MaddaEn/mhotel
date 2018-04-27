package mhotel.model;

import java.io.Serializable;

public class Address implements Serializable {
	private Long mId;// PK
	private String mStreet;
	private String mNumber;
	private String mCity;
	private String mCountry;
	private String mPostalCode;

	public String getStreet() {
		return mStreet;
	}

	public void setStreet(String pStreet) {
		mStreet = pStreet;
	}

	public String getNumber() {
		return mNumber;
	}

	public void setNumber(String pNumber) {
		mNumber = pNumber;
	}

	public String getCity() {
		return mCity;
	}

	public void setCity(String pCity) {
		mCity = pCity;
	}

	public String getCountry() {
		return mCountry;
	}

	public void setCountry(String pCountry) {
		mCountry = pCountry;
	}

	public String getPostalCode() {
		return mPostalCode;
	}

	public void setPostalCode(String pPostalCode) {
		mPostalCode = pPostalCode;
	}
	
	

	public Long getId() {
		return mId;
	}

	public void setId(Long pId) {
		mId = pId;
	}

	@Override
	public String toString() {
		return "Address [mId=" + mId + ", mStreet=" + mStreet + ", mNumber=" + mNumber + ", mCity=" + mCity
				+ ", mCountry=" + mCountry + ", mPostalCode=" + mPostalCode + "]";
	}

	
}
