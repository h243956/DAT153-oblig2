package com.oblig1.entities;

public class Picture {

  private String filename;
  private String name;

  public Picture(String filename, String name) {
    this.filename = filename;
    this.name = name;
  }

  public String getFilename() {
    return this.filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name=name;
  }

  @Override
  public String toString() {
    return "Picture { Filename: " + this.filename + ", Name: " + this.name + " }";
  }

}
