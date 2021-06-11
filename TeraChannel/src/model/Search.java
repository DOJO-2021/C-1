package model;

public class Search {
  private int search_id;
  private String search_word;
public Search(int search_id, String search_word) {
	super();
	this.search_id = search_id;
	this.search_word = search_word;
}
public Search() {
	super();
	this.search_id = 0;
	this.search_word = "";
}
public int getSearch_id() {
	return search_id;
}
public void setSearch_id(int search_id) {
	this.search_id = search_id;
}
public String getSearch_word() {
	return search_word;
}
public void setSearch_word(String search_word) {
	this.search_word = search_word;
}
}
