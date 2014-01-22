package study.rowmapper.entities;

import java.util.Date;

/**
 * no enum
 * @author InSeok
 *
 */
public class BookWithNoEnum {
	public static String getTableName() {
		return "BOOKS";
	}
	
	private int id;
	
	private String name;
	
	private String author;
	
	private Date publishDate;
	
	private String comment;
	
	private int status;
	
	private Integer rentUserId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = new Date(Math.round(publishDate.getTime() / 1000d) * 1000);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getRentUserId() {
		return rentUserId;
	}

	public void setRentUserId(Integer rentUserId) {
		this.rentUserId = rentUserId;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", publishDate=" + publishDate + ", comment=" + comment + "]";
	}
}
