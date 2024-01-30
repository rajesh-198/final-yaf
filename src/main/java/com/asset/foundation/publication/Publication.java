package com.asset.foundation.publication;

import com.asset.foundation.utility.abstractclass.AbstractClass;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class Publication extends AbstractClass<Long>{

  private String name;

  private String type;

  @Lob
  private byte[] data;

  public Publication() {
  }

  public Publication(String name, String type, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;
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

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }


}
