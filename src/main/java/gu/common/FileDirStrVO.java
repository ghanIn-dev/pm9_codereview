package gu.common;

public class FileDirStrVO {

	private int id; 
    private String subDir;
    private String name;
    private String classsno;
    private String parentName;
	private int depth;
    private int fileflag;
    private int parentId;
       
    
    
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClasssno() {
		return classsno;
	}
	public void setClasssno(String classsno) {
		this.classsno = classsno;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubDir() {
		return subDir;
	}
	public void setSubDir(String subDir) {
		this.subDir = subDir;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getFileflag() {
		return fileflag;
	}
	public void setFileflag(int fileflag) {
		this.fileflag = fileflag;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}