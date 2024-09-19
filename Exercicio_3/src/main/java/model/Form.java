package model;

public class Form {
	private int id_form;
	private int id_animal;
	private int id_usuario;
	private int status; // (0: rejeitado; 1: aceito; 2: pendente),
	private boolean visitas_ong;
	private boolean experiencia;
	private String moradia;
	private String disponibilidade;
	private String viagem;
	private String comentarios;

	public Form(){
		setIdForm(-1);
		status = 0;
		visitas_ong = false;
		experiencia = false;
		moradia = null;
		disponibilidade = null;
		viagem = null;
	}
	
	public Form(int id, int id_a, int id_u, int status, boolean visitas, boolean exp, String moradia, 
	String disp,String viagem, String comentarios){
		setIdForm(id);
		setIdAnimal(id_a);
		setIdUsuario(id_u);
		setStatus(status);
		setVisitas(visitas);
		setExp(exp);
		setMoradia(moradia);
		setDisponibilidade(disp);
		setViagem(viagem);
		setComentarios(comentarios);
	}
	
	public void setIdForm(int id) {
		id_form = id;
	}
	
	public int getIdForm() {
		return this.id_form;
	}
	
	public void setIdAnimal(int id) {
		this.id_animal = id;
	}
	
	public int getIdAnimal() {
		return this.id_animal;
	}
	
	public void setIdUsuario(int id) {
		this.id_usuario = id;
	}
	
	public int getIdUsuario() {
		return this.id_usuario;
	}
	
	public void setStatus(int i) {
		if(i == 0 || i == 1 || i == 2) {
			this.status = i;
		} else if (i > 2) {
			this.status = 2;
		}
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public void setVisitas(boolean b) {
		this.visitas_ong = b;
	}
	
	public boolean getVisitas() {
		return this.visitas_ong;
	}
	
	public void setExp(boolean b) {
		this.experiencia = b;
	}
	
	public boolean getExp() {
		return this.experiencia;
	}
	
	public void setMoradia(String s){
		this.moradia = s;
	}
	
	public String getMoradia() {
		return this.moradia;
	}
	
	public void setDisponibilidade(String s){
		this.disponibilidade = s;
	}
	
	public String getDisponibilidade() {
		return this.disponibilidade;
	}
	
	public void setViagem(String s){
		this.viagem= s;
	}
	
	public String getViagem() {
		return this.viagem;
	}
	
	public void setComentarios(String s){
		this.comentarios= s;
	}
	
	public String getComentarios() {
		return this.comentarios;
	}
	
	
	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Formulario: "+getIdForm()+", status: "+getStatus();
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getIdForm() == ((Form) obj).getIdForm());
	}	
}
