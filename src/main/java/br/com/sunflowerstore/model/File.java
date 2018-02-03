package br.com.sunflowerstore.model;

import org.springframework.util.Base64Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class File {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private String filename;
	@Column(length = 2147483647)
	private byte[] content;
	private String contentType;
	private Date createdOn;

	public File() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getPictureBase64() {
		return (content == null ? "/photos/no_image_available.png"
				: "data:image/png;base64," + Base64Utils.encodeToString(content));
	}

}
