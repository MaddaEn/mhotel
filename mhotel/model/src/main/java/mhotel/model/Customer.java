package mhotel.model;

import java.io.Serializable;

public class Customer implements Serializable {
	private Long mId; // PK
	private String mName;
	private String mSex;
	private String mLegalId; // PassportId,SSN,
	private String mLegalIdType; // PASSPORT,DRIVERID,IDENTITY_CARD,etc
	private Address mAddress;
	
	
	public Long getId() {
		return mId;
	}
	public void setId(Long pId) {
		mId = pId;
	}
	public String getName() {
		return mName;
	}
	public void setName(String pName) {
		mName = pName;
	}
	public String getSex() {
		return mSex;
	}
	public void setSex(String pSex) {
		mSex = pSex;
	}
	public String getLegalId() {
		return mLegalId;
	}
	public void setLegalId(String pLegalId) {
		mLegalId = pLegalId;
	}
	public String getLegalIdType() {
		return mLegalIdType;
	}
	public void setLegalIdType(String pLegalIdType) {
		mLegalIdType = pLegalIdType;
	}
	public Address getAddress() {
		return mAddress;
	}
	public void setAddress(Address pAddress) {
		mAddress = pAddress;
	}
	@Override
	public String toString() {
		return "Customer [mId=" + mId + ", mName=" + mName + ", mSex=" + mSex + ", mLegalId=" + mLegalId
				+ ", mLegalIdType=" + mLegalIdType + ", mAddress=" + mAddress + "]";
	}
	
	
}
