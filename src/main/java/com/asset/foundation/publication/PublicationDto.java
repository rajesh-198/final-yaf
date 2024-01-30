package com.asset.foundation.publication;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto implements Serializable {
    private Long id;

    private String name;

    private String type;

    private MultipartFile data;

    private byte[] dataBytes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MultipartFile getData() {
		return data;
	}

	public void setData(MultipartFile data) {
		this.data = data;
	}

	public byte[] getDataBytes() {
		return dataBytes;
	}

	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}
    
    

}
