package org.opml;

import java.net.URL;
import java.util.Date;

public class Head {

	private String title;
	private Date dateCreated;
	private Date dateModified;
	private String ownerName;
	private String ownerEmail;
	private URL ownerId;
	private URL docs;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerEmail() {
		return this.ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public URL getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(URL ownerId) {
		this.ownerId = ownerId;
	}

	public URL getDocs() {
		return this.docs;
	}

	public void setDocs(URL docs) {
		this.docs = docs;
	}

}
