package mhotel.model;

import java.io.Serializable;

public class Room implements Serializable {
	private Long mId; // PK
	private int mFloor;
	private String mNumber;
	private int mNumberOfBeds;
	private boolean mAvailableForRent;
	private Hotel mHotel;

	public Long getId() {
		return mId;
	}

	public void setId(Long pId) {
		mId = pId;
	}

	public int getFloor() {
		return mFloor;
	}

	public void setFloor(int pFloor) {
		mFloor = pFloor;
	}

	public String getNumber() {
		return mNumber;
	}

	public void setNumber(String pNumber) {
		mNumber = pNumber;
	}

	public int getNumberOfBeds() {
		return mNumberOfBeds;
	}

	public void setNumberOfBeds(int pNumberOfBeds) {
		mNumberOfBeds = pNumberOfBeds;
	}

	public boolean isAvailableForRent() {
		return mAvailableForRent;
	}

	public void setAvailableForRent(boolean pAvailableForRent) {
		mAvailableForRent = pAvailableForRent;
	}
	
	

	public Hotel getHotel() {
		return mHotel;
	}

	public void setHotel(Hotel pHotel) {
		mHotel = pHotel;
	}

	@Override
	public String toString() {
		return "Room [mId=" + mId + ", mFloor=" + mFloor + ", mNumber=" + mNumber + ", mNumberOfBeds=" + mNumberOfBeds
				+ ", mAvailableForRent=" + mAvailableForRent + "]";
	}
	
	

}
