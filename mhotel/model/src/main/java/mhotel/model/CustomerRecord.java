package mhotel.model;

import java.io.Serializable;
import java.util.Date;

public class CustomerRecord implements Serializable {
	private Long mId; // PK
	private Customer mCustomer;
	private Room mRoom;
	private Date mCheckInDate;
	private Date mCheckOutDate; // NULL IFF NOT CHECKED OUT

	public Long getId() {
		return mId;
	}

	public void setId(Long pId) {
		mId = pId;
	}

	public Customer getCustomer() {
		return mCustomer;
	}

	public void setCustomer(Customer pCustomer) {
		mCustomer = pCustomer;
	}

	public Room getRoom() {
		return mRoom;
	}

	public void setRoom(Room pRoom) {
		mRoom = pRoom;
	}

	public Date getCheckInDate() {
		return mCheckInDate;
	}

	public void setCheckInDate(Date pCheckInDate) {
		mCheckInDate = pCheckInDate;
	}

	public Date getCheckOutDate() {
		return mCheckOutDate;
	}

	public void setCheckOutDate(Date pCheckOutDate) {
		mCheckOutDate = pCheckOutDate;
	}

	@Override
	public String toString() {
		return "CustomerRecord [mId=" + mId + ", mCustomer=" + mCustomer + ", mRoom=" + mRoom + ", mCheckInDate="
				+ mCheckInDate + ", mCheckOutDate=" + mCheckOutDate + "]";
	}

}
