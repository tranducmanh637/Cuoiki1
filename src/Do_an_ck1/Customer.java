package Do_an_ck1;

public class Customer {
    private String customerID;
    private String customerName;
    private String sdt;
    private String mail;
    private String job;
    private String source;
    private String address;
    private String note;
	public Customer(String customerID, String customerName, String sdt, String mail, String job, String source,
			String address, String note) {
		
		this.customerID = customerID;
		this.customerName = customerName;
		this.sdt = sdt;
		this.mail = mail;
		this.job = job;
		this.source = source;
		this.address = address;
		this.note = note;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerName=" + customerName + ", sdt=" + sdt + ", mail="
				+ mail + ", job=" + job + ", source=" + source + ", address=" + address + ", note=" + note + "]";
	}
}
