package mhotel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements Serializable {
	private Long mId; // PK
	private String mName;
	private int mRating;
	private Address mAddress;
	private List<Room> mRooms;

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

	public int getRating() {
		return mRating;
	}

	public void setRating(int pRating) {
		mRating = pRating;
	}

	public Address getAddress() {
		return mAddress;
	}

	public void setAddress(Address pAddress) {
		mAddress = pAddress;
	}

	public List<Room> getRooms() {
		if(mRooms == null) {
			mRooms = new ArrayList<>();
		}
		return mRooms;
	}

	public void setRooms(List<Room> pRooms) {
		mRooms = pRooms;
	}

	@Override
	public String toString() {
		return "Hotel [mId=" + mId + ", mName=" + mName + ", mRating=" + mRating + ", mAddress=" + mAddress
				+ ", mRooms=" + mRooms + "]";
	}

	
}
