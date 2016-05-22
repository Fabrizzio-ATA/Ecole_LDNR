package packageAppli;

import java.time.LocalDate;

public class MaTable {
	private Integer id = Integer.valueOf(0);
	private String name = "";
	private LocalDate date = LocalDate.now();

	//************************************************************************
	//** CONSTRUCTORS
	//************************************************************************

	public MaTable(Integer id, String name, LocalDate date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}
	
	public MaTable() {}
	
	// TODO  COnfirm this constructor is valid
	public MaTable(String nom, LocalDate date) {
		this.setName(nom);
		this.setDate(date);
	}
//	
//	private MaTable(Integer id, String name, LocalDate date) {
//		this(name,date);
//		this.setId(id);
//	}
	
	//************************************************************************
	//** GETTERS SETTERS
	//************************************************************************
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (null != id && 0 < id)
		{
			this.id = id;
		}
		else
			System.out.println("## Prob id ##");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	//************************************************************************
	//** METHODES
	//************************************************************************
	/**
	 * Description of the method hasSameContent
	 * @param obj
	 * @return boolean
	 */
	public boolean hasSameContent(MaTable obj){
		return(this.getName().equals(obj.getName())
				&& this.getDate().equals(obj.getDate()));
	}
	
}
